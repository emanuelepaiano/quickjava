/*
Stack data structure - this file is part of Jtiny
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
package list;

/**
 * this class permits to implement a data stack LIFO
 **/
public class Stack{
	
	private Chain list;
	
	/**
	 * create stack
	 **/
	public Stack(){
		list=new Chain();
	}
	
	/**
	 * add element to stack
	 **/
	public void push(Object x){
		list.addFirst(x);
	}
	
	/**
	 * extract and remove element from top in Stack
	 * @return Object element
	 **/
	public Object pop(){
		return list.removeFirst();
	}
	
	/**
	 * get (without removing) element from top in Stack
	 * @return Object element
	 **/
	public Object top(){
		return list.getHead();		
	}
	
	/**
	 * remove all elements from stack
	 **/
	public void makeEmpty(){
		list.empty();
	}
	
	/**
	 * Verify if stack is empty
	 * @return boolean value. TRUE if queue is Empty. False if is not empty
	 **/
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	/**
	 * return elements number inside Stack
	 **/
	public int getNumElements()
	{
		return list.getNumNode();
	}
	
	
}

