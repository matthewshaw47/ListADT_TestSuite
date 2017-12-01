/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Programming Assignment 4: Research Genealogy
// FILE:             LinkedList.java
//
// Author: Matthew Shaw, mshaw6@wisc.edu, mshaw6, 001
//
// ---------------- OTHER ASSISTANCE CREDITS 
//
// N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An Iterable list that is implemented using a singly-linked chain of nodes
 * with a header node and without a tail reference.
 * 
 * The "header node" is a node without a data reference that will 
 * reference the first node with data once data has been added to list.
 * 
 * The iterator returned is a LinkedListIterator constructed by passing 
 * the first node with data.
 * 
 * CAUTION: the chain of nodes in this class can be changed without
 * calling the add and remove methods in this class.  So, the size()
 * method must be implemented by counting nodes.  This counting must
 * occur each time the size method is called.  DO NOT USE a numItems field.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedList<E> implements ListADT<E> {

	private Listnode<E> header = new Listnode<E>(null);
	
	/** 
	 * Returns a reference to the header node for this linked list.
	 * The header node is the first node in the chain and it does not 
	 * contain a data reference.  It does contain a reference to the 
	 * first node with data (next node in the chain). That node will exist 
	 * and contain a data reference if any data has been added to the list.
	 * 
	 * NOTE: Typically, a LinkedList would not provide direct access
	 * to the headerNode in this way to classes that use the linked list.  
	 * We are providing direct access to support testing and to 
	 * allow multiple nodes to be moved as a chain.
	 * 
	 * @return a reference to the header node of this list. 0
	 */
	public Listnode<E> getHeaderNode() {
		return header;
	}

	/**
	 * Method to get an iterator for the linked list
	 * 
	 * @return iterator object
	 */
	public LinkedListIterator<E> iterator() {
		
		LinkedListIterator<E> itr = new LinkedListIterator<E>(header.getNext());
		
		return itr;
	}

	/**
	 * Used to abstractly add a node to the end of the list
	 * 
	 * @param item item to be added to the list
	 */
	public void add(E item) {
		
		Listnode<E> curr = header;
		Listnode<E> newNode = new Listnode<E>(item);
		
		// trace through linked list to get to the tail node
		while ( curr.getNext() != null ) {
			curr = curr.getNext();
		}
		curr.setNext(newNode);
	}

	/**
	 * Used to add a node containing specified data at a particular position.
	 * 
	 * @param pos position to insert data
	 * @param item item to be contained within inserted node
	 */
	public void add(int pos, E item) {
		// if the desired position is not valid, throw an exception
		if ( pos < 0 || pos >= size() ) throw new IndexOutOfBoundsException();
		
		Listnode<E> curr = header;
		Listnode<E> newNode = new Listnode<E>(item);
		int index = 0;
		// iterate through the linked list to get to position specified
		while ( curr.getNext() != null ) {
			// if at specified position, add the new node
			if ( index == pos ) {
				newNode.setNext(curr.getNext());
				curr.setNext(newNode);
				return;
			}
			index++;
			curr = curr.getNext();
		}
	}

	/**
	 * searches the linkedlist and returns true if the specified item is a part of the list
	 * 
	 * @param item item to search for within the list
	 * @return whether of not the item is within the linked list
	 */
	public boolean contains(E item) {
		
		Listnode<E> curr = header;
		// move through the list, searching for a matching element
		while ( curr.getNext() != null ) {
			if ( curr.getNext().getData().equals(item) )
				return true;
			curr = curr.getNext();
		}
		return false;
	}
	
	/**
	 * Returns data at position pos without removing it
	 * 
	 * @param pos position of the desired element (0 based indexing)
	 * @return the object at specified position
	 */
	public E get(int pos) {
		// if the desired position is not valid, throw an exception
		if ( pos < 0 || pos > this.size() - 1 ) throw new IndexOutOfBoundsException();
		
		int index = 0;
		Listnode<E> curr = header;
		E get = null;
		// moves through linked list up to element at position pos
		while ( curr.getNext() != null ) {
			// if we are at the desired position
			if ( index == pos ) {
				get = curr.getNext().getData();
			}
			curr = curr.getNext();
			index++;
		}
		return get;
	}

	/**
	 * Method for determining if the linked list is empty or not
	 * 
	 * @return whether or not the linked list is empty
	 */
	public boolean isEmpty() {
		if ( header.getNext() == null )
			return true;
		return false;
	}

	/**
	 * Removes the element at position pos and returns the data from the removed node.
	 * 
	 * @param pos position of the item to be removed
	 * @return removed item
	 */
	public E remove(int pos) {
		// if the desired position is not valid, throw an exception
		if ( pos < 0 || pos >= size() ) throw new IndexOutOfBoundsException();
		
		int index = 0;
		Listnode<E> curr = header;
		E remove = null;
		// move through the list up to desired element
		while ( curr.getNext() != null ) {
			// if at desired position, remove the node
			if ( index == pos ) {
				remove = curr.getNext().getData();
				curr.setNext( curr.getNext().getNext() );
				return remove;
			}
			index++;
			curr = curr.getNext();
		}
		return remove;
	}

	/**
	 * Method for determining the size of the linked list
	 * 
	 * @return size of the linked list
	 */
	public int size() {
		int size = 0;
		Listnode<E> curr = header;
		// while there are still nodes in the list, increment the count of the size
		while ( curr.getNext() != null ) {
			size++;
			curr = curr.getNext();
		}
		return size;
	}
}
