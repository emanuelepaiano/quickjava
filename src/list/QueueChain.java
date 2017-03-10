/*
Queue data structure
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
 * this class permits to implement a queue in java
 **/
public class QueueChain {
	private int numElem;
	Chain list;
	
	public QueueChain()
	{
		numElem=0;
		list=new Chain();
	}
	
	/**
	 * return elements number
	 **/
	public int getNumElem()
	{
		return numElem;
	}
	
	/**
	 * add element to queue
	 * @param infop element to add
	 **/
	public void insert(Object infop)
	{
	 list.addLast(infop);
	 numElem++;
	}
	
	/**
	 * extract element from queue
	 * @return Object element
	 **/
	public Object extract()
	{
	 if (!isEmpty()){
	 numElem--;
	 return list.removeFirst();
	 }else return null;
	}
	
	/**
	 * Verify if queue is empty
	 * @return boolean value. TRUE if queue is Empty. False if is not empty
	 **/
	public boolean isEmpty()
	{
		return (getNumElem()==0);
	}
	
	/**
	 * remove all element from queue
	 **/
	public void makeEmpty()
	{
		numElem=0;
		list=new Chain();
	}
	
	/**
	 * print all queue element
	 **/
	public void printQueue()
	{
		
	 list.printChain();
		
	}

	
}
