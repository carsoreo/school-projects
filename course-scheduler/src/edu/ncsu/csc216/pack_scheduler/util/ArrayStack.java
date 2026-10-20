/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Class which manages a stack using an array. Can push, pop, check if empty,
 * return the size, and set the capacity of the stack.
 * @author clorejol
 *
 *@param <E> element to be used in the stack
 */
public class ArrayStack<E> implements Stack<E> {
	
	/** The ArrayStack */
	private ArrayList<E> list;
	
	/** Value which stores the capacity of the list */
	private int capacity;
	
	/**
	 * Constructs the stack
	 * @param capacity the capacity of the stack
	 */ 
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the end of the list
	 * @param element the element of the list
	 */
	@Override
	public void push(E element) {
		if (size() >= capacity) {
			throw new IllegalArgumentException();
		}
		list.add(size(), element);
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
		if(capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		} 
		this.capacity = capacity;
		
	}

}
