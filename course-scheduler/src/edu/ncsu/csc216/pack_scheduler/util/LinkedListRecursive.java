package edu.ncsu.csc216.pack_scheduler.util;


/**
 * Recursive LinkedList that can add/remove elements 
 * to a list at a given index, or at the end of the list.
 * @author jtcirinc
 *
 * @param <E> an object of type E
 */
public class LinkedListRecursive<E> {
	/** The front node of the list */
	private ListNode front;
	/** number of elements in the list */
	private int size;
	
	/**
	 * Constructs an empty list
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Gets the size of the linked list
	 * @return size the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks for whether or not the list is empty
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Checks whether or not an element is contained in the list and delegates to the listNode method
	 * @param e element being checked for duplicates
	 * @return true if the element is found
	 */
	public boolean contains(E e) {
		if(size == 0 || front == null) {
			return false;
		}
		 //Recursion??
		return front.contains(e);
	}
	/**
	 * Adds an element to the end of the list
	 * @param e element to be added to the list
	 * @return true if the element was added
	 */
	public boolean add(E e) {
		if(front == null) {
			front = new ListNode(e);
			size++;
			return true;
		}
		if(front.contains(e)) {
			throw new IllegalArgumentException();
		}
		front.add(size - 1, e);
		size++;
		return true;
	}
	/**
	 * Adds an element at a given index to the list
	 * @param idx the index to be added
	 * @param e element to be added
	 * @return true if the element was added
	 */
	public boolean add(int idx, E e) {
		if(idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(e == null) {
			throw new NullPointerException();
		}
		if(front == null) {
			front = new ListNode(e);
			size++;
			return true;
		}
		if(front.contains(e)) {
			throw new IllegalArgumentException();
		}
		if(idx == 0) {
			front = new ListNode(e, front);
			size++;
			return true;
		} else {
			front.add(idx - 1, e);
			size++;
			return true;
		}
	}
	
	/**
	 * Gets an element at a specific index, delegates to the listNode method
	 * @param idx the index of the element
	 * @return true if the element was found
	 */
	public E get(int idx) {
		if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}
	
	/**
	 * Removes an element at a given index, delegates to the ListNode method
	 * @param idx the index of the element to be removed
	 * @return true if the element was removed
	 */
	public E remove(int idx) {
		if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0 || idx == 0) {
			E temp = front.data;
			front = front.next;
			size--;
			return temp;
		} else {
			size--;
			return front.remove(idx);
		}
	}
	
	/**
	 * Removes a given element from the list, delegates to the Listnode remove() method
	 * @param e element to be removed
	 * @return true if the element was successfully removed
	 */
	public boolean remove(E e) {
		if(e == null) {
			return false;
		}
		if(size == 0) {
			return false;
		}
		if(e.equals(front.data)) {
			front = front.next;
			size--;
			return true;
		} 
		else {
			size--;
			return front.remove(e);
		}
	}
	
	/**
	 * Sets an element at a given index
	 * @param idx the index for the element to be set
	 * @param e the element to be set
	 * @return true if the element was set
	 */
	public E set(int idx, E e) {
		if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(front.contains(e)) {
			throw new IllegalArgumentException();
		}
		if(e == null) {
			throw new NullPointerException();
		}
		return front.set(idx, e);
	}
	
	/**
	 * Node used in conjunction with the parent class. Has methods
	 * for removing, setting, getting, and adding. Uses recursion as well.
	 * @author jtcirinc
	 * @author clorejol
	 *
	 */
	private class ListNode {
		/** data in the node */
		private E data;
		/** next node */
		private ListNode next;
		
		/**
		 * Constructor for a list node. Uses any type as a parameter.
		 * @param data type of object to be used in the node.
		 */
		ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Method which removes an element from a list. Utilizes recursion.
		 * @param e Type of object to be removed.
		 * @return false if data of the element is null. True if the
		 * desired data is found. Recurses if neither happens.
		 */
		public boolean remove(E e) {
			if(data == null) {
				return false;
			}
			if(e.equals(next.data)) {
				next = next.next;
				return true;
			}
			return next.remove(e);
		}
		
		/**
		 * Sets the data of a node at a given index using a given data type.
		 * Utilizes recursion.
		 * @param idx integer used as an index to find the correct node.
		 * @param e Element to be appended to the node.
		 * @return temp data of the node if the index is zero. Recurses if not.
		 */
		public E set(int idx, E e) {
			if(idx == 0) {
				E temp = data;
				data = e;
				return temp;
			} else {
				return next.set(idx - 1, e);
			}
		}
		
		/**
		 * Method used for recusion in the node for the remove method.
		 * @param idx index given to transverse the node.
		 * @return temp if the index is equal to 1. Removes the prior node
		 * if anything else.
		 */
		public E remove(int idx) {
			if(idx == 1) {
				E temp = next.data;
				next = next.next;
				return temp;
			} else {
				return next.remove(idx - 1);
			}
		}
		
		/**
		 * Returns the data in a node based on a given index.
		 * Utilizes recursion.
		 * @param idx integer used to determine the location of the node.
		 * @return data if the index is equal to zero. If next is not null,
		 * will recurse. If it is null, returns null.
		 */
		public E get(int idx) {
			if(idx == 0) {
				return data;
			} 
			else {
				if(next != null) {
					return next.get(idx - 1);
				} else {
					return null;
				}
			}
		}
		
		/**
		 * Method used by the node to add another node. Calls on the
		 * method in the superclass. Utilizes recursion.
		 * @param idx used to determine where to add the node.
		 * @param e Element to be used in the node created.
		 */
		public void add(int idx, E e) {
			if(idx == 0) {
				next = new ListNode(e, next);
			} else {
				next.add(idx - 1, e);
			}
		}
		
		/**
		 * Method which checks if the list contains the given element.
		 * Utilizes recursion.
		 * @param e Element to be checked if it is in the list
		 * @return true if the data is equal to the parameter. False if the
		 * next node is null, recurses if anything else.
		 */
		public boolean contains(E e) {
			if(e.equals(data)) {
				return true;
			}
			if(next == null) {
				return false;
			}
			return next.contains(e);
			
		}

		ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
	}
}
