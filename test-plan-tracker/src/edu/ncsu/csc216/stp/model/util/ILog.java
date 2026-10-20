/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;



/**
 * Interface with methods to be implemented in Log that can add elements
 * to the log, get elements from the log, and return the current size of the log.
 * @author Cameron Seitz
 * @param <E> generic object type for ILog
 *
 */
public interface ILog<E> {
	
	/**
	 * Relies on Log to implement the adding of elements to the log.
	 * Throws NullPointerException with the message "Cannot add null element."
	 * if the element to add is null.
	 * @param element the element of type E to add
	 * @throws NullPointerException if the element to add is null
	 *
	 */
	void add(E element);
	
	/**
	 * Relies on Log to return the element in the log given
	 * an index.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to get is less than 0 or greater than size - 1.
	 * @param idx the element to get's index in the list
	 * @return the element of type E
	 * @throws IndexOutOfBoundsException if the index of the element to get is less
	 * 		than 0 or greater than size - 1
	 *
	 */
	E get (int idx);
	
	/**
	 * Relies on Log to implement returning the size of the log.
	 * Returns the amount of elements in the log.
	 * @return the number of elements in the log
	 *
	 */
	int size();
}
