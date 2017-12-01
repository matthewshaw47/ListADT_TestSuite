/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Programming Assignment 4: Research Genealogy
// FILE:             Queue.java
//
// Author: Matthew Shaw, mshaw6@wisc.edu, mshaw6, 001
//
// ---------------- OTHER ASSISTANCE CREDITS 
//
// N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Basic Queue data structure, prioritized as first in, first out.
 * 
 * Internal data structure makes use of a single linked chain of nodes, implemented
 * by the LinkedList class. 
 * 
 * @author Matthew Shaw
 *
 * @param <T> Data type contained within the queue
 */
public class Queue<T> implements QueueADT<T> {
	
	private LinkedList<T> queue;
	
	/**
	 *  Initializes an empty linked list
	 */
	public Queue() {
		queue = new LinkedList<T>();
	}
	
	/**
	 * Determines if the queue is empty
	 * 
	 * @return whether or not the queue is empty
	 */
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * Adds an element into the queue
	 * 
	 * @throws IllegalArgumentException if trying to add null data to the queue
	 * @param data data to be added to the queue
	 */
	public void enqueue(T data) throws IllegalArgumentException {
		if ( data == null ) throw new IllegalArgumentException();
		else queue.add(data);
	}

	/**
	 * Removes the first element to have been placed within the queue
	 * 
	 * @throws EmptyQueueException if it is called upon an empty queue
	 * @return the item removed from the queue
	 */
	public T dequeue() throws EmptyQueueException {
		if ( queue.size() == 0 ) throw new EmptyQueueException();
		// remove first node
		return queue.remove(0);
	}

	/**
	 * Gets the next element within the queue without removing it
	 * 
	 * @throws EmptyQueueException if it is called on an empty queue
	 * @return the next element in the queue
	 */
	public T element() throws EmptyQueueException {
		if ( queue.size() == 0 ) throw new EmptyQueueException();
		// get the first node in the linked list
		return queue.get(0);
	}
}
