package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 2 assignment page or just given explicitly to use in the
 * Workshop 2 assignment page.
 * 
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		size++;
		return addBetween(element, validate(p).getNext(), validate(p));
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		size++;
		return addBetween(element, validate(p), validate(p).getPrevious());
	}
	
	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
		next.setPrevious(newNode);
		prev.setNext(newNode);
		return newNode;
    }

	@Override
	public Position<E> addFirst(E element) {
		size++;
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		size++;
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
		if (p == tail.getPrevious()) {
			return null;
		}
		return validate(p).getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		if (p == front.getNext()) {
			return null;
		}
		return validate(p).getPrevious();
	}

	@Override
	public Position<E> first() {
		if (size() == 0) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if (size() == 0) {
			return null;
		}
		return tail.getPrevious();
	}

	 @Override
	    public Iterable<Position<E>> positions() {
	        return new PositionIterable();
	    }

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		node.getPrevious().setNext(node.next);
		node.getNext().setPrevious(node.previous);
		size--;
		return node.getElement();
	}

	@Override
	public E set(Position<E> p, E element) {
		E toReturn = p.getElement();
		validate(p).setElement(element);
		return toReturn;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
    /**
     * This class has methods which have been coded using descriptions given in 
     * the Workshop 2 assignment page or just given explicitly to use in the
     * Workshop 2 assignment page.
     * 
     * Node used to hold elements in a positional linked list. Can edit and check what
     * node is next or before it.
     * 
     * @author clorejol
     *
     * @param <E> type for the node to use.
     */
	private static class PositionalNode<E> implements Position<E> {
		
		/** Element to be held in the node */
        private E element;
        
        /** Next node in the list*/
        private PositionalNode<E> next;
        
        /** Previous node in the list of nodes */
        private PositionalNode<E> previous;
        
        
        /**
         * Constructor for node when only an element is given
         * @param value to store in node
         */
        public PositionalNode(E value) {
            this(value, null);
        }
        
        /**
         * Constructor for node when a value and next node is given
         * @param value to store in node
         * @param next node directly after current node
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }
        
        /**
         * Constructor for node when a value, next node, and previous node is given
         * @param value to store in node
         * @param next node directly after current node
         * @param prev node directuly before current node
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }
        
        /**
         * Sets the previous node to this node
         * @param prev node before this node
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }
        
        /**
         * Returns the previous node to this node
         * @return previous node to this node
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the node directly after the current node
         * @param next node to be set to be after current node
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }
        
        /**
         * Gets the node directly after the current node
         * @return next node after the current one
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element of the current node
         * @param element to be set in current node
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
	
	/**
	 * This class has methods which have been coded using descriptions given in 
	 * the Workshop 2 assignment page or just given explicitly to use in the
	 * Workshop 2 assignment page.
	 * 
	 * Iterator which handles positions instead of elements
	 * @author clorejol
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		
		/** Value which holds the current position of the iterator */
        private Position<E> current;
        
        /** Value to check if it is valid to remove the current value the iterator is at */
        private boolean removeOK;
        
        /**
         * Constructor for PositionIterator which sets removeOK to false and current to front
         */
        public PositionIterator() {
            super();
            current = front;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
            return validate(current).getNext().getElement() != null;
        }

        @Override
        public Position<E> next() {
        	if(hasNext()) {
        		current = validate(current).getNext();
                removeOK = true;
                return current;
        	}
        	else {
        		throw new NoSuchElementException();
        	}
            
        }

        @Override
        public void remove() {
        	if (removeOK) {
        		Position<E> newCurrent = validate(current).getPrevious(); 
        		PositionalLinkedList.this.remove(current);
        		current = newCurrent;
        		removeOK = false;
        	}
        	else {
	        	throw new IllegalStateException();
	        }
        }
    }
	
	/**
	 * This class has methods which have been coded using descriptions given in 
	 * the Workshop 2 assignment page or just given explicitly to use in the
	 * Workshop 2 assignment page.
	 * 
	 * Iterator for PositionalLinkedList which deals with elements instead
	 * of positions
	 * @author clorejol
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		
		/** Value used to hold an iterator */
        private Iterator<Position<E>> it;

        public ElementIterator() {
            it = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
	
	/**
	 * This class has methods which have been coded using descriptions given in 
	 * the Workshop 2 assignment page or just given explicitly to use in the
	 * Workshop 2 assignment page.
	 * 
	 * Wrapper class for iterators so that a Iterable object can be returned
	 * @author clorejol
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
}