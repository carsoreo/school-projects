package edu.ncsu.csc216.stp.model.util;

/**
 * SortedList is a specialized, custom LinkedList implementation with methods to 
 * construct the SortedList, add and remove elements to and from it, get an element at an index, 
 * and return the size. It also has a method to check if the list contains an element and a 
 * private method to check if a given index is valid for the list's size. 
 * of the list. Doesn't allow duplicate elements. Adds elements in sorted order.
 * 
 * @author Cameron Seitz
 * @param <E> generic object type
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** number of elements in SortedList */
	private int size;
	
	/** first ListNode in the SortedList */
	private ListNode front;
	
	/**
	 * Constructor for SortedList that sets front to null.
	 */
	public SortedList() {
		front = null;
	}
	
	
	/**
	 * Implements ISortedList's method to add elements to the list in sorted order.
	 * Throws NullPointerException with the message "Cannot add null element."
	 * if the element to add is null. Throws IllegalArgumentException with the message 
	 * "Cannot add duplicate element." if the element to add already exists in the list.
	 * @param element the element of type E to add
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if the element to
	 * 		add already exists in the list
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		//duplicates check
		if (contains(element))
			throw new IllegalArgumentException("Cannot add duplicate element.");
			
		// Add to an empty list or front of a list
        if (front == null || element.compareTo(front.data) < 0) {
            front = new ListNode(element, front);
        } 
        
        // insert into end or middle of a list
        else {
            ListNode current = front;
            while (current.next != null && current.next.data.compareTo(element) < 0) {
                current = current.next;
            }
            current.next = new ListNode(element, current.next);
        }
        size++;
		
	}

	/**
	 * Implements ISortedList's method to remove elements from the list.
	 * Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to remove is less than 0 or greater than size - 1.
	 * Returns the original element at the index that got removed.
	 * @param idx the index of element to remove from the list
	 * @return the original element at the index that got removed.
	 * @throws IndexOutOfBoundsException with the message "Invalid index."
	 * 		if the index of the element to remove is less than 0 or greater than size - 1
	 */
	@Override
	public E remove(int idx) {
		// Precondition: 0 <= idx < size()
       checkIndex(idx);
            
        E value = null;
        
        // Special Case: front of list or only node
        if (idx == 0) {
            value = front.data;
            front = front.next;
        } 
        
        // removing from elsewhere in the list
        else {
            ListNode current = front;
            for (int i = 0; i < idx - 1; i++) {
                current = current.next;
            }
            value = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return value;
		
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

	/**
	 * Implements ISortedList's method to check if an element exists in the list.
	 * If the list does contain the element, it returns true, false if not.
	 * @param element the element to be checked
	 * @return true if the list contains the element, false otherwise
	 */
	@Override
	public boolean contains(E element) {
		
		ListNode current = front;
		while(current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
		
		
	}

	/**
	 * Implements ISortedList's method to get the element in the list given
	 * an index. Throws IndexOutOfBoundsException with the message "Invalid index."
	 * if the index of the element to get is less than 0 or greater than size - 1.
	 * @param idx the index of element to get in the list
	 * @return the element of type E
	 * @throws IndexOutOfBoundsException if the index of the element to get is less
	 * 		than 0 or greater than size - 1
	 *
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        return current.data; 
		
	}

	
	/**
	 * Implements ISortedList's method to return the size of the list.
	 * Returns the amount of elements in the list.
	 * @return the number of elements in the list
	 *
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Inner class of SortedList that represents a List Node in the SortedList.
	 * Maintains information about its data field and reference to the next ListNode.
	 * Has one constructor that makes a ListNode given a piece of data and
	 * a reference to the next ListNode.
	 */
	private class ListNode {
		
		/** data field within a ListNode */
        public E data;
        
        /** reference to the next ListNode */
        public ListNode next;
        
        /** 
         * Constructs a new ListNode with a data field
         * and a reference to the next ListNode.
         * 
         * @param data the data held in the ListNode
         * @param next the reference to the next ListNode
         * 
         */
        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
	
}