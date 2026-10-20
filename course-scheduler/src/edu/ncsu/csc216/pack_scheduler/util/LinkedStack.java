/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Creates a stack for the LinkedAbstractList class
 * @param <E> object type in the list
 * @author John Cirincione
 * @author clorejol
 *
 */
public class LinkedStack<E> implements Stack<E> {
	/** list to be used */
	LinkedAbstractList<E> list;
	/**
	 * Constructs the stack
	 * @param capacity the capacity of the stack
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the end of the list
	 * @param element the element of the list
	 */
	@Override
	public void push(E element) {
		list.add(element);
	}
	
	/**
	 * Removes the last element of the stack
	 * @return E the removed element
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(size() - 1);
	}
	
	/**
	 * Checks if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Gets the size of the stack
	 */
	@Override
	public int size() {
		int size = list.size();
		return size;
	}

	/**
	 * Sets the capacity of the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
		
	}

	
}
