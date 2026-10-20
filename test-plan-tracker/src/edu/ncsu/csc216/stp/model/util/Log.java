/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * Implements ILog Interface. 
 * Log is an ArrayList of generic type and has
 * methods to construct a log, 
 * add elements, get an element at an index, and 
 * return the current size of the ArrayList.
 * 
 * @author Cameron Seitz
 * @param <E> generic object type
 */
public class Log<E> implements ILog<E> {

	/** array of elements */
	private E[] log;
	
	/** number of elements in the array */
	private int size;
	
	/** the starting length of the array during construction */
	private static final int INIT_CAPACITY = 10;
	
	/**
	 * Initializes the log array to INIT_CAPACITY
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		log = (E[]) new Object [INIT_CAPACITY];
	}
	
	/**
	 * Implements Interface's add() method.
	 * Adds the passed in element to the array to the end of the array
	 * with no gaps.
	 * Throws NullPointerException with the message "Cannot add null element."
	 * if the element to add is null.
	 * @param element the element of type E to add
	 * @throws NullPointerException if the element to add is null
	 *
	 */
	@Override
	public void add(E element) {
		if (element == null)
			throw new NullPointerException ("Cannot add null element.");
		ensureCapacity(size + 1);
		log [size++] = element;

	}

	/**
	 * If the size of the array with one more element in it is
	 * equal to or greater than the length of the array, a new
	 * array with twice the length is created, contents of the old
	 * array are copied over, and the log field is assigned to the 
	 * new array.
	 * @param updatedSize the size of the array if an element were to be added
	 *
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int updatedSize) {
		if (updatedSize >= log.length) {
           
            E[] o = (E[]) new Object[log.length * 2];
            
            for (int i = 0; i < size; i++) {
                o[i] = log[i];
            }
            
            log = o;
        }
        
    }
		
	

	/**
	 * Implements the get() method in interface ILog.
	 * Returns the element at index in array.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if idx is less than 0 or greater than or equal to size.
	 * @param idx the index of the element to return
	 * @return the element at index in array
	 * @throws IndexOutOfBoundsException if idx is less
	 * 		than 0 or greater than or equal to size
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException ("Invalid index.");
		return log[idx];
	}

	/**
	 * Implements the size() method in interface ILog.
	 * Returns the current size of the array.
	 * @return size of the array
	 */
	@Override
	public int size() {
		return size;
	}

}
