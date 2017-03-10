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

import java.util.Hashtable;


public class Dictionary {
	/**
	 * Hashing table for word to replace while translating
	 * */
	private Hashtable<String,String> words=new Hashtable<String, String>();
	
	/**
	 * Translate word into value present in dictionary
	 * @param word word to translate
	 * @return Translated word example: getTarget("name") return John
	 * */
	public String getTarget(String word)
	{
		return words.get(word).toString();
	}
	
	/**
	 * Add matching value to dictionary
	 * @param source word to translate (key)
	 * @param target translation value (value)
	 * */
	public void addWord(String source, String target)
	{
		words.put(source, target);
		
		if (source!="void")
		{
			words.put(source+"[]", target+"[]");
			words.put(source+"[][]", target+"[][]");
			words.put(source+"[][][]", target+"[][][]");
			words.put(source+"[][][][]", target+"[][][][]");
			words.put(source+"[][][][][]", target+"[][][][][]");
		}
	}
	
	/**
	 * Verify if a word present into dictionary
	 * @param word the key to search
	 * @return true if word present false if not found
	 * */
	public boolean isPresent(String word)
	{
		return words.containsKey(word);
	}
	
	/**
	 * get Words numbers presents into dictionary
	 * @return an integer value
	 * */
	public int getNumWords()
	{
		return words.size();
	}
	
	
}
