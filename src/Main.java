/*
Jtiny v0.6 - QuickJava Preprocessor
Copyright (C) 2012  Emanuele Paiano - nixw0rm@gmail.com

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import list.*;

public class Main {

	/**
	 * @param args[0]: source file
	 */
	
	/**
	 * Input Jtiny source code
	 * */
	private static File source;
	
	/**
	 * quickjava source dir
	 * */
	private static String workspace;
	
	/**
	 * Input Jtiny object for reading
	 * */
	private static InputStream sourceInput;
	
	/**
	 * Stack. This stack contains the '{' and '}' sequences
	 * if this stack is empty we are outside methods
	 * */
	private static Stack sM;
	
	/**
	 * Stack. This stack contains the '(' and ')' sequences
	 * if this stack is empty we are outside parametres
	 * */
	private static Stack sA;
	
	/**
	 * Array to store of Jtiny supported data types.
	 * */
	private static Dictionary rules;
	
	/**
	 * Var to store main class
	 * */
	private static String name;

	private static String libs;

	/**
	 * Istructions added by QuickJava
	 * */
	
	/**
	 * for loading quickjava source
	 * */
	private static String includeCmd="#include";
	
	/**
	 * starting program
	 * */
	public static void main(String[] args) 
	{
		if (args.length<1) use();
			if (args[0]==null)
			{
				errorMsg("I can't find source file first!");
				System.exit(-1);
			}
			
		/* 
		 * get workspace dir
		 **/
		if (args.length==2)
		{
			workspace=args[1];
		}else{
			
		/* set workspace to current directory */
			
			workspace="";
		}
			
		/* 
		 * Initialize Stacks for Computation
		 **/
		sM=new Stack();
		sA=new Stack();
		rules=new Dictionary();
		
		/*
		 * All Java primitive data types (except String)
		 * */
	
		rules.addWord("int", "public static int");
		rules.addWord("void", "public static void");
		rules.addWord("char", "public static char");
		rules.addWord("short", "public static short");
		rules.addWord("long", "public static long");
		rules.addWord("double", "public static double");
		rules.addWord("float", "public static float");
		rules.addWord("boolean", "public static boolean");
		rules.addWord("String", "public static String");
		rules.addWord("byte", "public static byte");
		
		loadTypes(args[1]+"types.static", rules);
		
		
		/* 
		 * Starting create Java Output Class
		 **/
		String javaCode=getCode(args[0]);
		
		System.out.println("class "+name+"\n{");
		System.out.println(javaCode);
		System.out.println("}");
		
	}
	
	/** 
	 * load types to convert static from file. Usually file is types.static
	 * @param file static file
	 * @return array with types list to convert static 
	 **/
	public static void loadTypes(String file, Dictionary dict)
	{
		String strLine;
		String[] typesList={};
		
		DataInputStream in;
		BufferedReader br;
		
		source=new File(file);
		try {
			sourceInput=new FileInputStream(source);
		} catch (FileNotFoundException e) {
			System.out.println(file+": types.static file not found. You can miss error while compiling sources.");
		}
		 in = new DataInputStream(sourceInput);
		 br = new BufferedReader(new InputStreamReader(in));
		 try {			  
			  
			  /* Starting reading input file */
			  
			  while ((strLine = br.readLine()) != null) 
			  {
					  typesList=strLine.split("\\;");
			  }
			  
			sourceInput.close();
			
			for(int i=0;i<typesList.length;i++)
			{
				dict.addWord(typesList[i], "public static "+typesList[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** 
	 * get word by position. All words are divided by spaces
	 * @param statement input string to scan
	 * @param position word index to extract from statement
	 * @return position-word from statement 
	 **/
	public static String getWord(String statement, int position)
	{
		String[] temp=statement.split(" ");
		if (temp.length>0) return temp[position];
		else 
			return "";
	}
	
	/** 
	 * get Java code from file. The code is translated in java format
	 * @param file quickjava source file
	 * @return formatted java code from file 
	 **/
	public static String getCode(String file)
	{
		String output="";
		String strLine;
		String arg=file;
		String[] forSplit;
		
		DataInputStream in;
		BufferedReader br;
		
		char firstCh;
		source=new File(workspace+file);
		try {
			sourceInput=new FileInputStream(source);
		} catch (FileNotFoundException e) {
			System.out.println(workspace+file+": File not found. Abort building");
			System.exit(-1);
		}
		 in = new DataInputStream(sourceInput);
		 br = new BufferedReader(new InputStreamReader(in));
		 try {
			  
			  
			  /* Removing any extension from argv[2] */
			  name=arg;
			  name=name.replaceAll(name.substring(name.indexOf('.')), "");
			  		  
			  /* Converting input filename  to Class Name */
			  firstCh=name.charAt(0);
			  name=String.valueOf(firstCh).toUpperCase()+name.substring(1);
			  
			  
			  /* Starting reading input file */
			  
			  while ((strLine = br.readLine()) != null) 
			  {
				  if (!getWord(strLine,0).matches(includeCmd))
				  {
					  forSplit=strLine.split("\\(");
					  if (forSplit[0].matches("for")) 
						  strLine=strLine.replaceAll(";", ";\n ");
					  strLine=strLine.replaceAll("\\*\\/", "\\*\\/\n");
					  output=output+" "+replace(strLine);
				  }else if (getWord(strLine,0).matches(includeCmd))
				  {
					  output=output+" "+getLib(getWord(workspace+strLine,1).replaceAll(";", ""));
				  }
			  }
			  
			sourceInput.close();
			
			output=output.replaceAll("\\}","\\}\n ");
			output=output.replaceAll("\\{","\\ {\n ");
			return output;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** 
	 * similar to getCode, but don't optimize code. The code is translated in java format
	 * @param file Jtiny source file
	 * @return formatted java code from file 
	 **/
	public static String getLib(String file)
	{
		String output="";
		String strLine;
		String[] forSplit;
		DataInputStream in;
		BufferedReader br;
		
		source=new File(workspace+file);
		try {
			sourceInput=new FileInputStream(source);
		} catch (FileNotFoundException e) {
			System.out.println(workspace+file+": File not found. Abort building");
			System.exit(-1);
		}
		 in = new DataInputStream(sourceInput);
		 br = new BufferedReader(new InputStreamReader(in));
		 try {
			  
			  
			  /* Starting reading input file */
			  
			  while ((strLine = br.readLine()) != null) 
			  {
				  if (!getWord(strLine,0).matches(includeCmd))
				  {
					  forSplit=strLine.split("\\(");
					  if (forSplit[0].matches("for")) 
						  strLine=strLine.replaceAll(";", ";\n ");
					  strLine=strLine.replaceAll("\\*\\/", "\\*\\/\n");
					  output=output+" "+replace(strLine);
				  }else if (getWord(strLine,0).matches(includeCmd))
				  {
					  output=output+" "+getLib(getWord(strLine,1).replaceAll(";", ""));
				  }
			  }
			  
			  
			  
			sourceInput.close();
			return output;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** 
	 * print message on standard output
	 * @param message to string
	 * @return void 
	 **/
	public static void errorMsg(String msg)
	{
		System.out.println(msg);
	}
	
	/** 
	 * Scan the string and format in java format (adding static to vars outside methods)
	 * @param strLine line of Jtiny code to convert
	 * @return formatted java code of strLine
	 **/
	public static String replace(String strLine)
	{
		String temp="";
		String[] keyword=strLine.split(" ");
		for (int i=0;i<keyword.length;i++) 
			if (temp!="") temp=temp+" "+computeWord(keyword[i]);
			else temp=temp+computeWord(keyword[i]);
		return temp;
	}
	
	/** 
	 * Scan the word and format in java format if you can (you can't add static keyword inside method ;))
	 * @param word to scan
	 * @return formatted java code word
	 **/
	public static String computeWord(String word)
	{
		computeLine(word);
			if(rules.isPresent(word))
			{
				if (canReplace())
				{
					//word.replaceAll("]", "\\]*");
					word=word.replaceFirst(java.util.regex.Pattern.quote(word), rules.getTarget(word));
				}
			}
		return word;
	}
	
	/** 
	 * Scan the string and check for '(,),{,}' to understand if you are in method or outside
	 * @param string of Jtiny code to scan
	 * @return void
	 **/
	public static void computeLine(String buffer)
	{		
		for(int i=0;i<buffer.length();i++)
		{	
			computeChar(buffer.charAt(i));		
		}		
	}
	
	/**
	 * Check if you can change the code to current status or not.
	 * @return boolean value. TRUE if you are outside methods and functions. FALSE if not.
	 * */
	public static boolean canReplace()
	{
		return (sM.isEmpty() && sA.isEmpty());
	}
	
	
	/**
	 * If current char like {,},(,) add to stack
	 * @param current char scanning
	 * @return void
	 * */
	public static void computeChar(char c)
	{
		if (c=='{')
		{
			sM.push("{");
		}
		
		if (c=='}')
		{
			sM.pop();
		}
		if (c=='(')
		{
			sA.push("(");
		}
		if (c==')')
		{
			sA.pop();
		}
	}
	
	/**
	 * Print use sintax if command line error
	 * */
	public static void use()
	{
		 System.out.println("Jtiny QuickJava Preprocessor v0.6\n");
		 System.out.println("Use: jtiny <main source> [sources path]");
		 System.out.println("<main source>: quickjava source code with main() method");
		 System.out.println("[sources path]: quickjava sources working directory\n");
		 System.out.println("example: java -jar jtiny.jar main.java mysource/");
		 System.exit(-1);
	}
}
