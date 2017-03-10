/*
Node Chain data structure - useful to create list
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
 * this class permits to implement Node for chain
 **/
public class NodeChain {

	private NodeChain next;
	private Object info;
	
	/**
	 * create Empty Node
	 **/
	public NodeChain()
	{
		next=null;
		info=null;
	}
	
	/**
	 * create Node with element infop
	 * @param infop set element
	 **/
	public NodeChain(Object infoP)
	{
		next=null;
		info=infoP;
	}
	
	/**
	 * return Element from Node
	 * @return Object element
	 **/
	public Object getInfo()
	{
		return info;
	}
	
	/**
	 * get pointer to next node
	 * @return NodeChain node
	 **/
	public NodeChain getNext()
	{
		return next;
	}
	
	/**
	 * set element into node
	 * @param Object
	 **/
	public void setInfo(Object infop)
	{
		info=infop;
	}
	
	/**
	 * set pointer to next node
	 * @param NodeChain node
	 **/
	public void setNext(NodeChain nextp)
	{
		next=nextp;
	}
	
}
