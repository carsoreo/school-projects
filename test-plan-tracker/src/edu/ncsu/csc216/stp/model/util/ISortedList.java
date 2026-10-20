
package edu.ncsu.csc216.stp.model.util;



/**
 * Interface with methods to be implemented in SortedList that can add elements and 
 * remove elements from the SortedList, as well as return the current size of the list
 * and get an element at an index. Also has a method for checking if the list contains
 * an element. Object type E must extend Comparable. Does not allow for duplicate elements.
 * 
 * @author Cameron Seitz
 * @param <E> generic object type for ISortedList that implements Comparable
 */
public interface ISortedList<E extends Comparable<E>> {
	
	/**
	 * Relies on SortedList to implement adding elements to the list in sorted order.
	 * Throws NullPointerException with the message "Cannot add null element."
	 * if the element to add is null. Throws IllegalArgumentException with the message 
	 * "Cannot add duplicate element." if the element to add already exists in the list.
	 * @param element the element of type E to add
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException with the message if the element to
	 * 		add already exists in the list
	 */
	void add(E element);
	
	/**
	 * Relies on SortedList to implement the removing of elements from the list.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to remove is less than 0 or greater than size - 1.
	 * Returns the original element at the index that got removed.
	 * @param idx the index of element to remove from the list
	 * @return the element removed
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to remove is less than 0 or greater than size - 1
	 */
	E remove(int idx);
	
	/**
	 * Relies on SortedList to implement a check if an element exists in the list.
	 * If the list does contain the element, it returns true, false if not.
	 * @param element the element to be checked
	 * @return true if the list contains the element, false otherwise
	 */
	boolean contains(E element);
	
	/**
	 * Relies on SortedList to get the element in the list given
	 * an index. Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to get is less than 0 or greater than size - 1.
	 * @param idx the index of element to get in the list
	 * @return the element of type E
	 * @throws IndexOutOfBoundsException if the index of the element to get is less
	 * 		than 0 or greater than size - 1
	 *
	 */
	E get (int idx);
	
	/**
	 * Relies on SortedList to implement returning the size of the list.
	 * Returns the amount of elements in the list.
	 * @return the number of elements in the list
	 *
	 */
	int size();
}