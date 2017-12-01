/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Programming Assignment 4: Research Genealogy
// FILE:             Stack.java
//
// Author: Matthew Shaw, mshaw6@wisc.edu, mshaw6, 001
//
// ---------------- OTHER ASSISTANCE CREDITS 
//
// N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.EmptyStackException;

/**
 * Basic Stack data structure, prioritized as first in, last out.
 * 
 * Internal Data Structure makes use of a singly linked chain of nodes, implemented
 * by the LinkedList class.
 * 
 * @author Matthew Shaw
 *
 * @param <T> Data type to be stored within the stack
 */
public class Stack<T> implements StackADT<T> {
	
	private LinkedList<T> stack;
	
	/**
	 * Constructor, initializes an empty linked list
	 */
	public Stack() {
		stack = new LinkedList<T>();
	}
	
	/**
	 * Method to determine if the stack is empty
	 * 
	 * @return whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * Places a data item onto the top of the stack
	 * 
	 * @throws IllegalArgumentException if the data is n
	 * @param data data to be put onto the stack
	 */
	public void push(T data) throws IllegalArgumentException {
		if ( data == null ) throw new IllegalArgumentException();
		if ( stack.size() == 0 ) stack.add(data);
		else stack.add(0,data);
	}

	/**
	 * Gets the item at the top of the stack without removing it
	 * 
	 * @throws EmptyStackException if it is called upon an empty stack
	 * @return the top element of the stack
	 */
	public T peek() throws EmptyStackException {
		if ( stack.size() == 0 ) throw new EmptyStackException();
		return stack.get(0);
	}

	/**
	 * Removes the top element of the stack and returns it
	 * 
	 * @throws EmptyStackException if called upon an empty stack
	 * @return the top element from the stack
	 */
	public T pop() throws EmptyStackException {
		if ( stack.size() == 0 ) throw new EmptyStackException();
		return stack.remove(0);
	}

	/**
	 * Reverse the ordering of the stack
	 * 
	 * @returns a copy of the current stack, but in reverse order
	 */
	public StackADT<T> reverse() {
		Stack<T> reversed = new Stack<T>();
		Stack<T> temp = this;
		// pops items from a copy of the current stack and pushes it to a temporary stack
		while ( !temp.isEmpty() ) {
			reversed.push( temp.pop() );
		}
		return reversed;
	}
}
