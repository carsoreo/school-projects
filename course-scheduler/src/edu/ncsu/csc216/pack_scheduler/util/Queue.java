package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface with methods for using a queue. Overridden with other classes
 * which use a LinkedList or ArrayList for the queue.
 * @author jtcirinc
 *
 * @param <E> element used to determine the type of the queue.
 */
public interface Queue<E> {
	
	/**
	 * adds the element to back of the queue
	 * 
	 * @param element to be added
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element at the front of the Queue
	 * 
	 * @return element in the front of the queue
	 */
	E dequeue();
	
	/**
	 * Returns true if the Queue is empty
	 * 
	 * @return true if the Queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the Queue
	 * 
	 * @return the number of elements in the Queue
	 */
	int size();
	
	/**
	 * Sets the Queue's capacity
	 * 
	 * @param capcity to be the queue
	 * @throws IllegalArgumentException if param is negative or less that number of elements in queue
	 */
	void setCapacity(int capcity);
	
}
