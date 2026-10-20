package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 6 assignment page or just given explicitly to use in the
 * Workshop 6 assignment page. Additionally, the postOrder and levelOrder method were coded
 * using help from the Introduction to Trees slides from class by Dr. Jason King.
 * 
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public E set(Position<E> p, E value) {
    	AbstractTreeNode<E> temp = validate(p);
    	E tempVal = temp.getElement();
    	temp.setElement(value);
        return tempVal;
    }   
    
    /**
	 * Safely casts a Position, p, to be an AbstractTreeNode.
	 * 
	 * @param p the position to cast to a AbstractTreeNode
	 * @return a reference to the AbstractTreeNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  AbstractTreeNode
	 */
	protected abstract AbstractTreeNode<E> validate(Position<E> p);

    @Override
    public Iterable<Position<E>> preOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helper method for the preOrder traversal method. Adds the given position
     * to the given PositionCollection and then recursively reads through the
     * children of the parent node of passed in Position.
     * 
     * @param p position to read children of.
     * @param traversal PositionCollection type to hold Position values.
     */
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        traversal.add(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }   
    
    @Override
    public Iterable<Position<E>> postOrder() {
    	PositionCollection traversal = new PositionCollection();
    	if (!isEmpty()) {
            postOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helper method for the postOrder traversal method. Reads the
     * children of a given parent recursively and adds them to a
     * given PositionCollection.
     * 
     * @param p position to read children of.
     * @param traversal PositionCollection type to hold Position values.
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
        for (Position<E> c : children(p)) {
            postOrderHelper(c, traversal);
        }
        traversal.add(p);
    }
    
    @Override
    public Iterable<Position<E>> levelOrder()
    {
    	
    	PositionCollection traversal = new PositionCollection();
    	Queue<Position<E>> queue = new ArrayBasedQueue<Position<E>>();
    	if(root() == null || isEmpty()) {
    		return traversal;
    	}
    	queue.enqueue(root());
    	while(!queue.isEmpty()) {
    		Position<E> q = queue.dequeue();
    		traversal.add(q);
    		for (Position<E> c: children(q)) {
    			queue.enqueue(c);
    		}
    	}
    	return traversal;
    }
    
    /**
     * This class has methods which have been coded using descriptions given in 
     * the Workshop 6 assignment page or just given explicitly to use in the
     * Workshop 6 assignment page.
     * 
	 * PositionCollection implements the {@link Iterable} interface to allow traversing
	 * through the positions of the tree. PositionCollection does not allow removal
	 * operations
	 * 
	 * @author Dr. King
	 *
	 */
	protected class PositionCollection implements Iterable<Position<E>> {
		
		/** List which holds positions of tree nodes */
	    private List<Position<E>> list;
	
	    /**
	     * Construct a new PositionCollection
	     */
	    public PositionCollection() {
	        list = new SinglyLinkedList<Position<E>>();
	    }
	
	    /**
	     * Add a position to the collection. Null positions or positions with null
	     * elements are not added to the collection
	     * 
	     * @param position the position to add to the collection
	     */
	    public void add(Position<E> position) {
	        if (position != null && position.getElement() != null) {
	            list.addLast(position);
	        }
	    }
	
	    /**
	     * Return an iterator for the PositionCollection
	     * @return iterator of Positions from the tree
	     */
	    public Iterator<Position<E>> iterator() {
	        return new PositionSetIterator();
	    }
	    
	    /**
	     * This class has methods which have been coded using descriptions given in 
	     * the Workshop 6 assignment page or just given explicitly to use in the
	     * Workshop 6 assignment page.
	     * 
	     * @author clorejol
	     * @author Dr. King
	     */
	    private class PositionSetIterator implements Iterator<Position<E>> {
	    	
	    	/** Holds the iterator to be used */
	        private Iterator<Position<E>> it;
	        
	        /**
	         * Constructor for the PositionSetIterator
	         */
	        public PositionSetIterator() {
	            it = list.iterator();
	        }
	
	        @Override
	        public boolean hasNext() {
	            return it.hasNext();
	        }
	
	        @Override
	        public Position<E> next() {
	            return it.next();
	        }
	
	        @Override
	        public void remove() {
	            throw new UnsupportedOperationException("The remove operation is not supported yet.");
	        }
	    }
	}
	
	/**
	 * This class has methods which have been coded using descriptions given in 
	 * the Workshop 6 assignment page or just given explicitly to use in the
	 * Workshop 6 assignment page.
	 * 
	 * A protected abstract class which allows for getting and setting of tree nodes.
	 * Will be used to implement various types of trees.
	 * 
	 * @author Dr. King
	 * @author clorejol
	 *
	 * @param <E> element to be read for nodes.
	 */
	protected abstract static class AbstractTreeNode<E> implements Position<E> {
		
		/** Holds the element of the current tree node */
        private E element;
        
        public AbstractTreeNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Reads in a parameter for an indent, root, and a StringBuilder to print out
     * a string which represents the tree. It begins at the root and prints the
     * children with the specified indent.
     * 
     * @param sb a StringBuilder type parameter to build strings
     * @param indent String type parameter to determine indent type
     * @param root Position type for the root of the tree to determine start point
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
}