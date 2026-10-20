package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 2 assignment page or just given explicitly to use in the
 * Workshop 2 assignment page.
 * 
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    
    /**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }

	@Override
	public void add(int index, E element) {
		
		//Add size and check if it is valid
		checkIndexForAdd(index);
		size++;
		
		ensureCapacity(size);
		
		//Shift data over to make space
		for(int i = size() - 1; i > index; i--) {
			data[i] = data[i - 1];
		}
		
		//Add element
		data[index] = element;
		
		
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		
		//Check if index is not valid
		checkIndex(index);
		
		//Store removed data
		E removedVal = data[index];
		
		//Move data over to fill gaps
		data[index] = null;
		for(int i = index; i < size() - 1; i++) {
			data[i] = data[i + 1];
		}
		
		size--;
		return removedVal;
	}

	@Override
	public E set(int index, E element) {
		//Check if index is not valid
		checkIndex(index);
		if (data[index] == null) {
			throw new IndexOutOfBoundsException();
		}
		
		
		//Return original element and set the new one
		E oldElement = data[index];
		data[index] = element;
		return oldElement;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
	
	/**
	 * This class has methods which have been coded using descriptions given in 
	 * the Workshop 2 assignment page or just given explicitly to use in the
	 * Workshop 2 assignment page.
	 * 
	 * Iterator used to go through list to remove an element by calling next and remove.
	 * Holds the current position of the iterator and a boolean to check if removal
	 * is currently valid.
	 *  	
	 * @author clorejol
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    
		/** Position of the iterator */
		private int position;
		
		/** Boolean to check if removing is ok */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        super();
	        position = 0;
	        removeOK = false;
	    }

	    @Override
	    public boolean hasNext() {
			return !(position == size() || data == null);
	    }

	    @Override
	    public E next() {
	        if (hasNext()) {
	        	position++;
	        	removeOK = true;
	        	return data[position - 1];
	        }
	        else {
	        	throw new NoSuchElementException();
	        }
	    }
	        
	    @Override
	    public void remove() {
	    	if (removeOK) {
	    		ArrayBasedList.this.remove(position - 1);
	    		removeOK = false;
	    		position--;
	    	}
	    	else {
	    		throw new IllegalStateException();
	    	}
	        
	    }
	}
}