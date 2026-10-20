package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Interface implemented by ArrayStack and LinkedStack to make a stack
 * using both types of lists. Allows classes push and pop elements out of a stack
 * and check if the stack is empty, get the size, and set the capacity.
 * @author clorejol
 *
 * @param <E> Type of element to be used in the stack.
 */
public interface Stack<E> {
	
	/**
	 * Pushes a given element from the parameter into a stack. Throws an error
	 * if there is no more room in the stack.
	 * @param element E to be pushed into the stack.
	 * @throws IllegalArgumentException if there is no more room in the stack.
	 */
	void push(E element);
	
	/**
	 * Pops a given element from the parameter from a stack. Throws an error
	 * if the stack is empty. Then returns the popped element.
	 * @return element E removed from the stack.
	 * @throws EmptyStackException if the stack is empty when popping.
	 */
	E pop();
	
	/**
	 * Checks if the stack is empty and returns a boolean value accordingly.
	 * True if empty, false if not.
	 * @return true if the stack is empty, false if not.
	 */
	boolean isEmpty();
	
	/**
	 * Gets the size of the stack by checking how many element are in the stack.
	 * Returns the size as an integer.
	 * @return size integer which holds the calculated size of the stack.
	 */
	int size();
	
	/**
	 * Method used to set the capacity of the stack. This capacity determines
	 * the max number of elements which can be held in the stack. If the 
	 * parameter is negative or less than the current number of values in the stack
	 * an error is thrown.
	 * @param capacity integer which is used to set the capacity of the stack.
	 * @throws IllegalArgumentException if the new capacity is less than the current
	 * number of elements or a negative integer.
	 */
	void setCapacity(int capacity);
	
	
	
}
