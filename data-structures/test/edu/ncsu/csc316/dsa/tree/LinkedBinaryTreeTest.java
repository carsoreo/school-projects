package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 6 assignment page or just given explicitly to use in the
 * Workshop 6 assignment page.
 * 
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author clorejol
 */
public class LinkedBinaryTreeTest {
	
	/** Holds the tree to test*/
    private LinkedBinaryTree<String> tree;
    
    /** The position of the one node */
    private Position<String> one;
    
    /** The position of the two node */
    private Position<String> two;
    
    /** The position of the three node */
    private Position<String> three;
    
    /** The position of the four node */
    private Position<String> four;
    
    /** The position of the five node */
    private Position<String> five;
    
    /** The position of the six node */
    private Position<String> six;
    
    /** The position of the seven node */
    private Position<String> seven;
    
    /** The position of the eight node */
    private Position<String> eight;
    
    /** The position of the nine node */
    private Position<String> nine;
    
    /** The position of the ten node */
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * @param <E> element to use in position. (should not affect testing)
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        InvalidPosition<String> invalid = new InvalidPosition<String>();
        try {
        	tree.set(invalid, "huh?");
        } catch (Exception e) {
        	assertEquals(e.getMessage(), "Position is not a valid linked binary tree node");
        }
        
        assertEquals(tree.set(eight, "8"), "eight");
        assertEquals(tree.set(eight, "eight"), "8");
        
        try {
        	tree.set(null, "huh?");
        } catch (Exception e) {
        	assertEquals(e.getMessage(), "Position is not a valid linked binary tree node");
        }
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(tree.size(), 10);
        
        tree.remove(three);
        
        assertEquals(tree.size(), 9);
        
        tree.remove(seven);
        
        assertEquals(tree.size(), 8);
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(tree.numChildren(one), 2);
        assertEquals(tree.numChildren(five), 0);
        assertEquals(tree.numChildren(three), 1);
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(tree.parent(eight), four);
        assertEquals(tree.parent(one), null);
        assertEquals(tree.parent(six), two);
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(nine, tree.sibling(eight));
        assertEquals(three, tree.sibling(two));
        
        assertEquals(null, tree.sibling(one));
        assertEquals(null, tree.sibling(four));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertFalse(tree.isInternal(eight));
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
        
        assertEquals(one, tree.setRoot(one));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(three, it.next());
        assertEquals(four, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        
        Iterator<Position<String>> it2 = tree.preOrder().iterator();
        try {
        	it2.remove();
        }
        catch (Exception e) {
        	assertEquals(e.getMessage(), "The remove operation is not supported yet.");
        }
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertEquals(six, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(ten, it.next());
        assertEquals(two, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertEquals(four, it.next());
        assertEquals(three, it.next());
        assertEquals(one, it.next());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(six, it.next());
        assertEquals(two, it.next());
        assertEquals(seven, it.next());
        assertEquals(ten, it.next());
        assertEquals(five, it.next());
        assertEquals(one, it.next());
        assertEquals(eight, it.next());
        assertEquals(four, it.next());
        assertEquals(nine, it.next());
        assertEquals(three, it.next());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	assertTrue(tree.isEmpty());
    	assertEquals(tree.size(), 0);
    	
        assertEquals(tree.addRoot("ROOT"), tree.root());
    }
    
    /**
     * Tests the level order transition through a binary tree
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
        one = tree.addRoot("one");
        
        two = tree.addLeft(one, "two");
        assertEquals(tree.left(one), two);
        
        assertEquals("LinkedBinaryTree[\none\n two\n]", tree.toString());
        
        assertEquals(tree.size(), 2);
        
        try {
        	tree.addLeft(one, "fail");
        } catch(Exception e) {
        	assertEquals("Node already has a left child.", e.getMessage());
        }
        
        try {
        	tree.addLeft(one, "fail");
        } catch(Exception e) {
        	assertEquals("Node already has a left child.", e.getMessage());
        }
        
        assertEquals(tree.size(), 2);
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	one = tree.addRoot("one");
        
        two = tree.addRight(one, "two");
        assertEquals(tree.right(one), two);
        
        assertEquals(tree.size(), 2);
        
        try {
        	tree.addRight(one, "fail");
        } catch(Exception e) {
        	assertEquals("Node already has a right child.", e.getMessage());
        }
        
        assertEquals(tree.size(), 2);
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
    	Position<String> temp = tree.addRoot("temp");
    	assertEquals(tree.remove(temp), "temp");
    	
        createTree();
        assertEquals(tree.remove(eight), "eight");
        assertEquals(tree.size(), 9);
        
        assertEquals(tree.remove(six), "six");
        assertEquals(tree.size(), 8);
        
        assertEquals(tree.remove(seven), "seven");
        assertEquals(tree.size(), 7);
        
        assertEquals(tree.remove(ten), "ten");
        assertEquals(tree.size(), 6);
        
        assertEquals(tree.remove(five), "five");
        assertEquals(tree.size(), 5);
        
        //assertEquals("", tree.toString());
        
        try {
        	tree.remove(one);
        } catch (Exception e) {
        	assertEquals(e.getMessage(), "The node has two children");
        }
        
      
    }
}