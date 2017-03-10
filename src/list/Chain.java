/*
Chain data structure - this file is part of Jtiny
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
 * this class permits to implement a chain list
 **/
public class Chain {

	private int numNode;
	private NodeChain Head, end;

	public Chain()
	{
		empty();
	} 
	
	/**
	 * remove all elements from chain
	 **/
	public void empty()
	{
		numNode=0;
		Head=end=new NodeChain();
	}
	
	/**
	 * Verify if chain is empty
	 * @return boolean value. TRUE if queue is Empty. False if is not empty
	 **/
	public boolean isEmpty()
	{
		return Head==end;
	}
	
	/**
	 * get the nodes number presents in chain
	 * @return nodes number
	 **/
	public int getNumNode()
	{
		return numNode;
	}
	
	/**
	 * Extract head element from Chain
	 * @return top element 
	 **/
	public Object getHead()
	{
		if (!isEmpty())	return Head.getNext().getInfo();
		else return null;
	}
	
	/**
	 * Extract tail element from Chain
	 * @return last element 
	 **/
	public Object getEnd()
	{
		if (!isEmpty()) return end.getInfo();
		else return null;
	}
	
	/**
	 * Edit head element in Chain
	 * @param Object head element
	 **/
	public void setHead(Object Headp)
	{
		Head.setInfo(Headp);
	}
	
	/**
	 * Edit end element in Chain
	 * @param Object end element
	 **/
	public void setEnd(Object endp)
	{
		end.setInfo(endp);
	}
	
	/**
	 * return and remove first element in Chain
	 * @return Object head element
	 **/
	public Object removeFirst()
	{
		Object value=null;
		if (!isEmpty()){
		value=Head.getNext().getInfo();
		Head=Head.getNext();
		Head.setInfo(null);
		numNode--;
		}
		return value;
	}
	
	/**
	 * return and remove lastt element in Chain
	 * @return Object tail element
	 **/
	public Object removeLast()
	{
		Object value=null;
		if (!isEmpty()) {
		NodeChain temp=Head;
		while (temp!=end)
		{
			temp=temp.getNext();
		}
		value=end.getInfo();
		temp.setNext(null);
		end=temp;
		numNode--;
		}
        return value;
	}
	
	/**
	 * get node present in chain at index position
	 * @return NodeChain  element
	 **/
	public NodeChain getMidNode(int index)
	{
		NodeChain temp=new NodeChain(null);
		if (!isEmpty())
		{
			temp=Head;
			for (int i=1;i<=index;i++) 
			{
				 temp=temp.getNext();
			 }
			
			
			}
		return temp;
	}
	
	/**
	 * Add element at first position
	 * @param Object element
	 **/
	public void addFirst(Object nodep)
	{
	 Head.setInfo(nodep);
	 NodeChain n=new NodeChain();
	 n.setNext(Head);
	 Head=n;
	 numNode++;
	}
	
	/**
	 * Add element at first position
	 * @param Object element
	 **/
	public void addLast(Object nodep)
	{
		NodeChain n=new NodeChain(nodep);
		n.setNext(null);
		end.setNext(n);
		end=n;
		numNode++;
	}
	
	/**
	 * print all element presents in Chain
	 **/
	public void printChain()
	{
		for (int i=1;i<=numNode;i++)
		{
			System.out.println(getMidNode(i).getInfo());
		}
	}
	
	
}
