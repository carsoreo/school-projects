package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class which manages a Queue using a ArrayList. This implements the
 * Queue interface and has methods which manage the queue by enqueue, dequeue,
 * checking if the queue is empty, getting the size of the queue, and setting the
 * capacity of the queue. 
 * @author clorejol
 *
 * @param <E> Type used to determine what type to use in the lists.
 */
public class ArrayQueue<E> implements Queue<E> {

	/** State which holds ArrayList list*/
	private ArrayList<E> list;
	
	/** Integer used to hold the current capacity of the queue */
	private int capacity;
	
	/**
	 * Constructor for ArrayQueue. Creates a new LinkedAbstractList
	 * to be held by list with a capacity of 10.
	 * @param capacity integer to be set as capacity.
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Overridden method from Queue interface. Adds
	 * an element to the queue using the element from
	 * the parameter.
	 * @param element E to be added to the queue.
	 * @throws IllegalArgumentException if the capacity has been reached.
	 */
	@Override
	public void enqueue(E element) {
		if (size() >= capacity) {
			throw new IllegalArgumentException();
		}
		else {
			list.add(0, element);
		}
		
	}
	
	/**
	 * Overridden method from Queue interface.
	 * Removes the element at the end of the queue and
	 * returns it.
	 * @return element removed from the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	@Override
	public E dequeue() {
		if(size() == 0) {
			throw new NoSuchElementException();
		}
		return list.remove(size() - 1);
	}
	
	/**
	 * Overridden method from Queue interface.
	 * Checks if the queue is empty and returns a
	 * boolean value accordingly. True if empty,
	 * false if not.
	 * @return true if the queue is empty, false if not.
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * Overridden method from Queue interface.
	 * Gets the current size of the queue and returns it
	 * as an integer.
	 * @return size of the queue as an integer.
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	/**
	 * Overridden method from Queue interface.
	 * Sets the capacity of the queue by calling setCapacity
	 * from LinkedAbstractList using an integer given as a
	 * parameter. Throws an error if the capacity is negative or
	 * less than the current amount of elements in the queue.
	 * @param capacity integer used to set capacity.
	 * @throws IllegalArgumentException if the capacity to be set
	 * is less than the number of elements in the queue or negative.
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		
	}

}