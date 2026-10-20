package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * 
 * ArrayList holds a list of objects and modifies the size of an array to implement certain
 * functions. Contains a method to get a value at a certain index, set a value at a certain
 * index, get the size of the array, add a value at a certain index, and remove a value at
 * a certain index. Also has a helper function that is used to grow the array used by ArrayList.
 * 
 * @author ghgupton
 * @author jbsimms
 * @author clorejol
 *
 * @param <E> element type
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Initial size of ArrayList */
	private static final int INIT_SIZE = 10;
	/** Array of values */
	private E[] list;
	/** Size of array */
	private int size;

	/**
	 * Default constructor which creates a new list of a given object using the initial size.
	 * Also, sets the size value that is returned when the size method is called to zero.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/**
	 * Returns the element in the array at a given index
	 * 
	 * @param index index of element to be returned.
	 * @return element at index given.
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater than the
	 * size of the array.
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		return list[index];
	}

	/**
	 * Takes a parameter for index and the element to add then adds it to the array.
	 * Will throw an exception if the list is empty or element at the list is empty.
	 * Will also throw an exception if the element is already in the list or the element
	 * added is null = or the index is out of bounds for the array.
	 * 
	 * @param index index of the value to be set.
	 * @param element element of the value to be set.
	 * @return element that was be replaced.
	 * @throws IndexOutOfBoundsException if the list is empty or null, the given index is
	 * out of range for the elements in the array. NullPointerException if the element
	 * given is null. IllegalArgumentException if the element is already in the list.
	 */
	public E set(int index, E element) {
		if (list == null || list.length == 0 || list[index] == null) {
			throw new IndexOutOfBoundsException();
		}
		E oldElem = list[index];
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		if(element == null) throw new NullPointerException();
		for (int i = 0; i < size(); i++) {
			if(list[i].equals(element)) throw new IllegalArgumentException();
		}
		list[index] = element;
		return oldElem;
	}

	/**
	 * Returns the size of the array which is determined by the amount of
	 * elements in the array that are not null.
	 * @return size current size of the array
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds an element to array based on a given parameter for an index and an element.
	 * Exceptions will be thrown if the element being added is null, the element is already
	 * in the list, or if the index for the element is out of bounds for the list.
	 * 
	 * @param index index to place element in array
	 * @param element element to be added to array
	 * @throws IllegalArgumentException if the element is already in the list. NullPointerException
	 * if the element trying to be added is null. IndexOutOfBoundsException if the index given is out
	 * of range for the array.
	 */
	public void add(int index, E element) {
		for (int i = 0; i < size(); i++) {
			if(list[i].equals(element)) throw new IllegalArgumentException();
		}
		if (element == null) throw new NullPointerException();
		size++;
		if(index < 0 || index >= size()) {
			size--;
			throw new IndexOutOfBoundsException();
		}
		if (size() > list.length) expandArray();
		
		for(int i = size() - 1; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = element;
	}

	/**
	 * Removes an element at a given index and returns that element. Throws an error
	 * if the list is empty.
	 *   
	 * @param index index of item to remove from the array
	 * @return element removed from the array
	 * @throws IndexOutOfBoundsException if list is empty.
	 */
	public E remove(int index) {
		if(index < 0 || index >= size() || list[index] == null) {
			throw new IndexOutOfBoundsException();
		}
		E elem = list[index];
		
		list[index] = null;
		for(int i = index; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}

		list[size()] = null;
		size--;
		return elem;
	}
	
	/**
	 * Helper method used to double the size of the array if the array is too
	 * small.
	 */
	@SuppressWarnings("unchecked")
	private void expandArray() {
		int newSize = list.length * 2;
		//@SuppressWarnings("unchecked")
		
		E[] newList = (E[]) new Object[newSize];
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}

		list = newList;
	}
}