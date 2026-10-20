package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 10 assignment page or just given explicitly to use in the
 * Workshop 10 assignment page.
 * 
 * The UpTreeDisjointSetForest is implemented as a forest of linked up-trees.
 * Using balanced union, {@link DisjointSetForest#union} has worst-case runtime
 * of O(1). Using path-compression find, {@link DisjointSetForest#find} has
 * worst-case O(logn), but over time has worst-case runtime O(log*(n))
 * [log-star].
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements stored in the disjoint set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of
    // up-trees
    // NOTE: The textbook implementation does not include this
    // functionality; instead, the textbook implementation leaves
    // the responsibility of tracking positions to the client in a
    // separate map structure
	/** Secondary map used to find the root of each new tree */
    private Map<E, UpTreeNode<E>> map;

    /**
     * Constructs a new DisjointSetForest
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    /**
     * This class has methods which have been coded using descriptions given in 
     * the Workshop 10 assignment page or just given explicitly to use in the
     * Workshop 10 assignment page.
     * 
     * An UpTreeNode maintains an element, a reference to the node's parent, and (if
     * it's the root of an up-tree) the number of nodes stored within that up-tree.
     * 
     * @author Dr. King
     * @author clorejol
     *
     * @param <E> type of element to be used in the UpTreeNode as its value.
     */
    private static class UpTreeNode<E> implements Position<E> {
    	
    	/** Element the node will contain */
        private E element;
        
        /** Node which acts of the parent of the current node */
        private UpTreeNode<E> parent;
        
        /** Number of children the current node has*/
        private int count;

        /**
         * Constructs a new UpTreeNode with the given element, a reference to itself as
         * the parent, and a count of 1.
         * 
         * @param element the element to store in the new UpTreeNode
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }

        /**
         * Sets the element of the UpTreeNode to the given element
         * 
         * @param element the element to store in the UpTreeNode
         */
        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        /**
         * Sets the parent of the UpTreeNode to the given UpTreeNode
         * 
         * @param parent the UpTreeNode to set as the current node's parent
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }

        /**
         * Returns a reference to the parent of the current UpTreeNode
         * 
         * @return a reference to the parent of the current UpTreeNode
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the number of nodes contained in the UpTree rooted at the current
         * UpTreeNode
         * 
         * @param count integer to set the count to.
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * If the current UpTreeNode is the root of an up-tree, returns the number of
         * elements stored within the UpTree. Otherwise, if the current UpTreeNode is
         * not the root of an up-tree, count is undefined.
         * 
         * @return the number of elements stored within the UpTree, if the current
         *         UpTreeNode is the root; otherwise, count is undefined.
         */
        public int getCount() {
            return count;
        }
    }

    @Override
    public Position<E> makeSet(E value) {
        UpTreeNode<E> node = new UpTreeNode<E>(value);
        node.setParent(node);
        node.setCount(1);
        map.put(value, node);
        return node;
    }

    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        // track of the location of each position in the forest.
        // Our implementation includes a Map to handle this for the
        // client, so we should allow the client to find the set
        // that contains a node by specifying the element
    	if (value == null) {
    		return null;
    	}
    	UpTreeNode<E> p = map.get(value);
    	
    	if (p == null) {
    		return null;
    	}
    	return findHelper(p);
    }

    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        // Implement path-compression find
    	UpTreeNode<E> p = validate(current);
    	if (!p.getParent().equals(p)) {
    		p.setParent(findHelper(p.getParent()));
    	}
    	return p.getParent();
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(s);
        UpTreeNode<E> b = validate(t);
        
        a = findHelper(a);
        b = findHelper(b);
        if (a.getCount() > b.getCount()) {
        	a.setCount(a.getCount() + b.getCount());
        	b.setParent(a);
        }
        else {
        	b.setCount(b.getCount() + a.getCount());
        	a.setParent(b);
        }
    }

    private UpTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>) p;
    }
}