package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 6 assignment page or just given explicitly to use in the
 * Workshop 6 assignment page. Additionally, the inOrder method was coded
 * using help from the Introduction to Trees slides from class by Dr. Jason King.
 * 
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
	 @Override
	    public Iterable<Position<E>> inOrder() {
		 PositionCollection traversal = new PositionCollection();
	    	if (!isEmpty()) {
	            inOrderHelper(root(), traversal);
	        }
	        return traversal;
	    }

	    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
	    	if (p == null) {
	    		return;
	    	}
	    	inOrderHelper(left(p), traversal);
	    	traversal.add(p);
	    	inOrderHelper(right(p), traversal);
	    }
    
    @Override
    public int numChildren(Position<E> p) {
        int childrenCount = 0;
        if (left(p) != null) { 
        	childrenCount++;
        }
        if (right(p) != null) { 
        	childrenCount++;
        }
        return childrenCount;
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
    	if (parent(p) == null) {
    		return null;
    	}
        if (left(parent(p)) == p) {
        	return right(parent(p));
        }
        else {
        	return left(parent(p));
        }
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}