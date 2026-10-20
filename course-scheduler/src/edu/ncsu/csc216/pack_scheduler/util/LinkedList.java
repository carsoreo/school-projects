package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Doubly Linked list using list nodes and an iterator
 * 
 * @author NathN
 *
 * @param <E> type of objects in list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** dummy node in font of list*/
	private ListNode front;
	/** dummy node in back of list*/
	private ListNode back;
	/** size of list */
	private int size;
	
	/**
	 * Constructs empty list with empty front and back 
	 * assigning next and prev allowing for traversal
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	
	
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator iterator = new LinkedListIterator(index);
		return iterator;
	}
	
	@Override
	public void add(int idx, E e) {
		if(contains(e)) {
			throw new IllegalArgumentException();
		}
		super.add(idx, e);
		size++;
		
	}
	
	@Override
	public E set(int idx, E e) {
		if(contains(e)) {
			throw new IllegalArgumentException();
		}
		return super.set(idx, e);
	}
	@Override
	public int size() {
		return size;
	}

	private class ListNode {
		/** data in the actual node */
		public E data;
		/** next node in the list */
		public ListNode next;
		/** previous node in the list */
		public ListNode prev;
		
		/**
		 * creation of list node with only data next and prev are null
		 * 
		 * @param data to be the value of node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		/**
		 * creation of node with prev and next as non null nodes
		 * 
		 * @param data to be the value of node
		 * @param prev to be the value of the previous node
		 * @param next to be the value of the next node
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	private class LinkedListIterator implements ListIterator<E> {
		/** The previous listnode */
		public ListNode previous;
		/** next node in list */
		public ListNode next;
		/** last node retrieved */
		private ListNode lastRetrieved;
		/** index of previous node */
		public int previousIndex;
		/** index of next node */
		public int nextIndex;
		
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			ListNode current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			previous = current;
			next = current.next;
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
			
		}
		@Override
		public boolean hasNext() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			return next.data != null;
		}
		@Override
		public E next() {
			if (next.data  == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			previous = next;
			next = next.next;
			previousIndex = previousIndex + 1;
			nextIndex = nextIndex + 1;
			return lastRetrieved.data;
		}
		@Override
		public boolean hasPrevious() {
			if (previous == null) {
				throw new NoSuchElementException();
			}
			return previous.data != null;
		}
		@Override
		public E previous() {
			if (previous.data  == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			next = previous;
			previous = previous.prev;
			previousIndex = previousIndex - 1;
			nextIndex = nextIndex - 1;
			return lastRetrieved.data;
		}
		
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		@Override
		public int previousIndex() {
			return previousIndex;
		}
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved.next.prev = lastRetrieved.prev;
			size--;
			
		}
		@Override
		public void set(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.data = e;			
		}
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode addition = new ListNode(e, previous, next);
			next.prev = addition;
			previous.next = addition;
			lastRetrieved = null;
		}
		
		
	}

}
