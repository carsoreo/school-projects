package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * This code was created using a template outlined in the
 * Workshop 1 assignment page.
 * 
 * @author Dr. King
 * @author clorejol
 * @param <E> element to be sorted.
 */
public interface Sorter<E> {
	
	/**
	 * Sorts a given array of elements in ascending order.
	 * 
	 * @param input array of elements to be sorted.
	 */
	void sort(E[] input);
}
