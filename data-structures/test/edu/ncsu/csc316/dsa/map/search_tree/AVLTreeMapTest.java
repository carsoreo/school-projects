package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 7 assignment page or just given explicitly to use in the
 * Workshop 7 assignment page.
 * 
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class AVLTreeMapTest {
	
	/** Value used to hold the tree for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     * This test case was taken from the Exercises done in class during the AVL Trees lecture.
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        //This test case was taken from the Exercises done in class during the AVL Trees lecture
        tree.put(5, "5");
        tree.put(10, "10");
        tree.put(15, "15");
        tree.put(20, "20");
        tree.put(25, "25");
        //Trinode restructure should have occured.
        
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(20, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(25, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(15, (int)tree.left(tree.right(tree.root())).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     * This test case was taken from the Exercises done in class during the AVL Trees lecture.
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        
        tree.put(5, "5");
        tree.put(10, "10");
        tree.put(15, "15");
        tree.put(20, "20");
        tree.put(25, "25");
        
        //assertEquals(null, tree.get(null)); //WILL TEST THIS AFTER COVERAGE TO SEE IF NECESSARY
        assertEquals(null, tree.get(30));
        
        assertEquals("5", tree.get(5));
        assertEquals("20", tree.get(20));
        assertEquals("15", tree.get(15));
    }
    
    /**
     * Test the output of the remove(k) behavior.
     * This test case was taken from the Exercises done in class during the AVL Trees lecture.
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        //This test case was taken from the Exercises done in class during the AVL Trees lecture
        tree.put(30, "30");
        tree.put(20, "20");
        tree.put(55, "55");
        tree.put(10, "10");
        tree.put(25, "25");
        tree.put(45, "45");
        tree.put(60, "60");
        tree.put(15, "15");
        tree.put(40, "40");
        tree.put(50, "50");
        tree.put(65, "65");
        tree.put(35, "35");
        
        assertEquals("20", tree.remove(20));
        
        assertEquals(45, (int)tree.root().getElement().getKey());
        assertEquals(30, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(55, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(60, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(50, (int)tree.left(tree.right(tree.root())).getElement().getKey());
    }
}