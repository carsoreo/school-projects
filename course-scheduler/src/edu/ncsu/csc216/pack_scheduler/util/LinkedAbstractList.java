/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**

 * Linked list as a chain of list nodes allowing for manipulation of 
 * data type e
 * 
 * extends AbstractList
 * 
 * @author angodwi2 ntcampbe
 * 
 * @param <E> type of object in the given list
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** Front of the node list*/
	private ListNode front;
	/** Size of the list */
	private int size;
	/** Capacity of the list */
	private int capacity;
	/** Constructor for the linked list
	 * 
	 * @param capacity is the capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		if (capacity >= 0) {
			this.capacity = capacity;
		} 
		else {
			throw new IllegalArgumentException();
		}
	}
	@Override
	public void add(int idx, E e) {
		if(idx < 0 || idx - 1 >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if(size == capacity) {
			throw new IllegalArgumentException();
		}
		if(e == null) {
			throw new NullPointerException();
		}
		ListNode current = front;
		for(int i = 0; i < size; i++) {
			if(e.equals(current.data)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if(size == 0) {
			front = new ListNode(e);
		}
		if(idx == 0) {
			front = new ListNode(e, front);

		}

		else {
			ListNode addNode = front;
			for(int i = 0; i < idx - 1; i++) {
				addNode = addNode.next;
			}
			addNode.next = new ListNode(e, addNode.next);
//			else {
//				//may be .next
		}
		size++;
		
	}
	
	@Override
	public E remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if(front == null) {
			return null;
		}
		if(idx == 0) {
			E removed = front.data;
			front = front.next;
			size--;
			return removed;
		} else {
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			E removed = current.next.data;
			current.next = current.next.next;
			size--;
			return removed;
		}
		
	}
	
	@Override
	public E set(int index, E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for(int i = 0; i < size; i++) {
			if(e.equals(current.data)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		
		ListNode setNode = front;
		for(int i = 0; i < index; i++) {
			setNode = setNode.next;
		}
		E data = setNode.data;
		setNode.data = e;
		return data;
	}
	
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode getNode = front;
		for(int i = 0; i < index; i++) {
			getNode = getNode.next;
		}
		return getNode.data;
	}

	@Override
	public int size() {
		
		return size;
	}
	
	/**
	 * Sets capacity of the LinkedAbstract list based on a parameter integer.
	 * 
	 * @param capacity integer used to determine capacity.
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	/**
	 * List node class for the linked list
	 *
	 * @author angodwi2
	 *
	 */
	public class ListNode {
		/** data in the node */
		private E data;
		/** next node */
		private ListNode next;
		
		ListNode(E data) {
			this.data = data;
		}
		
		ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
	}

}
