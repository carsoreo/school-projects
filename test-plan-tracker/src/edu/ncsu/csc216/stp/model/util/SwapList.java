package edu.ncsu.csc216.stp.model.util;

/**
 * SwapList is a specialized, custom ArrayList implementation with methods to 
 * construct the SwapList, add and remove elements to and from it, move elements up, down,
 * to the front, and to the back within the list, get an element at an index, and return the size
 * of the list. Has private methods for checking the index and ensuring the capacity meets the
 * demand for SwapList's add method. Allows duplicate elements.
 * 
 * @author Cameron Seitz
 * @param <E> generic object type
 */
public class SwapList<E> implements ISwapList<E> {

	/** size of array when initialized*/
	private static final int INITIAL_CAPACITY = 10;
	
	/** array that represents the list of elements in SwapList*/
	private E[] list;
	
	/** number of elements in list currently*/
	private int size;
	
	/**
	 * Constructor that initializes the list with size INITIAL_CAPACITY.
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object [INITIAL_CAPACITY];
	}
	
	/**
	 * Implements ISwapList's method that handles the adding of elements to the list.
	 * Throws NullPointerException with the message "Cannot add null element."
	 * if the element to add is null. Throws IllegalArgumentException with the message
	 * "Cannot add element." if the element could not be added.
	 * @param element the element of type E to add
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if element cannot be added 

	 */
	@Override
	public void add(E element) {
		if (element == null)
			throw new NullPointerException("Cannot add null element.");
		
		checkCapacity(size + 1);
		
		try {
			list[size++] = element;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Cannot add element");
		}
		
		
	}
	
	/**
	 * If the size of the array with one more element in it is
	 * equal to or greater than the length of the array, a new
	 * array with twice the length is created, contents of the old
	 * array are copied over, and the list field is assigned to the 
	 * new array.
	 * @param updatedSize the size of the array if an element were to be added
	 *
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int updatedSize) {
		if (updatedSize >= list.length) {
           
            E[] o = (E[]) new Object[list.length * 2];
            
            for (int i = 0; i < size; i++) {
                o[i] = list[i];
            }
            
            list = o;
        }
        
    }

	/**
	 * Implements ISwapList's method to remove elements from the list.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to remove is less than 0 or greater than size - 1.
	 * Returns the original element at the index.
	 * @param idx the index of element to remove from the list
	 * @return the element previously at the index
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to remove is less than 0 or greater than size - 1
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		
E 		rtn = list[idx];
        
        for (int i = idx; i < size - 1; i++) {
            list [i] = list [i + 1];
        }
        
        list[size - 1] = null;
        size--;
        return rtn;
		
	}

	/**
	 * Implements ISwapList's method to handle the moving of elements within the list.
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to move is less than 0 or greater than size - 1.
	 * @param idx the index of element to move within the list
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to move is less than 0 or greater than size - 1
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
		
	}

	/**
	 * Implements ISwapList's method to handle the moving of elements within the list.
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to move is less than 0 or greater than size - 1.
	 * @param idx the index of element to move within the list
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to move is less than 0 or greater than size - 1
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
		}
		
	}

	/**
	 * Implements ISwapList's method to handle the moving of elements within the list.
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to move is less than 0 or greater than size - 1.
	 * @param idx the index of element to move within the list
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to move is less than 0 or greater than size - 1
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		E temp = list[idx];
		if (idx != 0) {
			for (int i = idx - 1; i >= 0; i--) {
	            list [i + 1] = list [i];
	        }
			list[0] = temp; 
		}
	}

	/**
	 * Implements ISwapList's method to handle the moving of elements within the list.
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to move is less than 0 or greater than size - 1.
	 * @param idx the index of element to move within the list
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to move is less than 0 or greater than size - 1
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		E temp = list[idx];
		if (idx != size - 1) {
			for (int i = idx; i < size - 1; i++) {
	            list [i] = list [i + 1];
	        }
			list[size - 1] = temp; 
		}
	}

	/**
	 * Implements ISwapList's method to return the element in the list given
	 * an index. Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to get is less than 0 or greater than size - 1.
	 * @param idx the index of element to get in the list
	 * @return the element of type E
	 * @throws IndexOutOfBoundsException if the index of the element to get is less
	 * 		than 0 or greater than size - 1
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}

	/**
	 * Implements ISwapList's method to return the size of the list.
	 * Returns the amount of elements in the list.
	 * @return the number of elements in the list
	 *
	 */
	@Override
	public int size() {
		return size;
	}
	
	
	/**
	 * Checks to make sure the index corresponds to an element in the array,
	 * meaning between 0 and size -1, inclusive. If the index is invalid,
	 * it throws an IndexOutOfBoundsException with message "Invalid index."
	 * @param idx the index to check
	 * @throws IndexOutOfBoundsException if the index is invalid
	 *
	 */
	private void checkIndex(int idx) {
		 if (idx < 0 || idx > size - 1) 
	            throw new IndexOutOfBoundsException("Invalid index.");
	}
	
}