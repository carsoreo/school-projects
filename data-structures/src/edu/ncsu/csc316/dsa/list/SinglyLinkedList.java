package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 2 assignment page or just given explicitly to use in the
 * Workshop 2 assignment page.
 * 
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

	@Override
	public void add(int index, E element) {
		
		checkIndexForAdd(index);
		size++;
		
		if (size - 1 == 0) {
			LinkedListNode<E> newNode = new LinkedListNode<E>(element, null);
			front.setNext(newNode);
			tail = newNode;
		}
		else if (index == 0) {
			front.setNext(new LinkedListNode<E>(element, front.next));
		}
		else if(index == size - 1) {
			LinkedListNode<E> newNode = new LinkedListNode<E>(element, null);
			tail.setNext(newNode);
			tail = newNode;
		}
		else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			current.setNext(new LinkedListNode<E>(element, current.next)); 
		}
		
		
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		if (index == 0) {
			return front.getNext().getElement();
		}
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.getElement();
	}

	@Override
	public E remove(int index) {
		E oldData = null;
		checkIndex(index);
		
		if (index == 0) {
			size--;
			oldData = front.next.getElement();
			front.next = front.next.next;
		}
		else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			size--;
			oldData = current.next.data;
			current.next = current.next.next;
		}
		return oldData;
	}

	@Override
	public E set(int index, E element) {
		E oldData = null;
		checkIndex(index);
		
		if (index == 0) {
			oldData = front.next.data;
			front.getNext().setElement(element);
		}
		else {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			oldData = current.data;
			current.setElement(element);
		}
		return oldData;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
        add(size(), element);
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
	 * Node used to hold values in a singly linked list. Has the ability to
	 * set/get the next node and set/get the element of the node.
	 * @author clorejol
	 *
	 * @param <E> type to be used by node
	 */
	private static class LinkedListNode<E> {
        
		/** Data held within the node */
		private E data;
		
		/** The node the node is pointing to */
        private LinkedListNode<E> next;
		
        /**
         * Constructor for LinkedListNode when given just one element
         * @param element to be held in node
         */
        public LinkedListNode(E element) {
			this.data = element;
			this.next = null;
		}
        
        /**
         * Constructor for LinkedListNode when element and next is given
         * @param element to be held in node
         * @param node next node after current node
         */
        public LinkedListNode(E element, LinkedListNode<E> node) {
        	this.data = element;
        	this.next = node;
        }
        
        /**
         * Returns the element of the node
         * @return data element being held in node
         */
        public E getElement() {
        	return data;
        }
        
        /**
         * Returns the next node
         * @return next LinkedListNode directly after current node
         */
        public LinkedListNode<E> getNext() {
        	return next;
        }
        
        /**
         * Sets the next node directly after the current node
         * @param node to be set to next node
         */
        public void setNext(LinkedListNode<E> node) {
        	next = node;
        }
        
        /**
         * Sets the element of the node
         * @param element to be set to node
         */
        public void setElement (E element) {
        	data = element;
        }
		
    }
    
	/**
	 * This class has methods which have been coded using descriptions given in 
	 * the Workshop 2 assignment page or just given explicitly to use in the
	 * Workshop 2 assignment page.
	 * 
	 * Iterator used to go through list holding the last 2 previous nodes so the
	 * middle element held can be removed.
	 * 
	 * @author clorejol
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    /**
	     * Keep track of the next node that will be processed
	     */
	    private LinkedListNode<E> current;
	    
	    /** 
	     * Keep track of the node that was processed on the last call to 'next'
	     */
	    private LinkedListNode<E> previous;
	    
	    /** 
	     * Keep track of the previous-previous node that was processed
	     * so that we can update 'next' links when removing
	     */
	    private LinkedListNode<E> previousPrevious;
	    
	    /**
	     * Keep track of whether it's ok to remove an element (based on whether
	     * next() has been called immediately before remove())
	     */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	    	super();
	        current = front.next;
	        previous = front;
	        previousPrevious = front;
	        removeOK = false;
	    }

	    @Override
	    public boolean hasNext() {
	        return !(current == null);
	    }

	    @Override
	    public E next() {
	        if (hasNext()) {
	        	removeOK = true;
	        	if (current == null) {
	        		return null;
	        	}
	        	else if (previousPrevious == previous) {
	        		E hold = current.data;
	        		current = current.next;
	        		previous = previous.next;
	        		return hold;
	        	}
	        	else {
	        		E hold = current.data;
	        		current = current.next;
	        		previous = previous.next;
	        		previousPrevious = previousPrevious.next;
	        		return hold;
	        	}
	        }
	        else {
	        	throw new NoSuchElementException();
	        }
	    }
	     
	    @Override    
	    public void remove() {
	        if(removeOK) {

	        	if (current == null) {
	        		previousPrevious.setNext(current);
	        		previous = previousPrevious;
	        		removeOK = false;
	        		tail = previousPrevious;
	        		size--;
	        	}
	        	else {
	        		previousPrevious.next = current;
	        		previous = previousPrevious;
	        		removeOK = false;
	        		size--;
	        	}
	        	
	        }
	        else {
	        	throw new IllegalStateException();
	        }
	    }
	}
}