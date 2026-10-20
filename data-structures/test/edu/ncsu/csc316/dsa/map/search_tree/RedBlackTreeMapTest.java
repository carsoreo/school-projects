package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 7 assignment page or just given explicitly to use in the
 * Workshop 7 assignment page.
 * 
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class RedBlackTreeMapTest {
	
	/** Value used to hold a RedBlack tree for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior.
     * This test case was taken from the exercise from the Red-Black Trees lecture.
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        //This test case was taken from the exercise from the Red-Black Trees lecture
        tree.put(5, "5");
        tree.put(10, "10");
        tree.put(20, "20");
        tree.put(25, "25");
        tree.put(15, "15");
        tree.put(19, "19");
        
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(20, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(25, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(15, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(19, (int)tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     * This test case was taken from the exercise from the Red-Black Trees lecture.
     */     
    @Test
    public void testGet() {
    	tree.put(5, "5");
        tree.put(10, "10");
        tree.put(20, "20");
        tree.put(25, "25");
        tree.put(15, "15");
        tree.put(19, "19");
        
        assertEquals(tree.get(5), "5");
        assertEquals(tree.get(19), "19");
        assertEquals(tree.get(20), "20");
        assertEquals(tree.get(10), "10");
    }
    
    /**
     * Test the output of the remove(k) behavior
     * This test case was taken from the exercise from the Red-Black Trees lecture.
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        //This test case was taken from the exercise from the Red-Black Trees lecture
        tree.put(14, "14");
        tree.put(7, "7");
        tree.put(16, "16");
        tree.put(4, "4");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(18, "18");
        tree.put(3, "3");
        tree.put(5, "5");
        tree.put(17, "17");
        
        assertEquals("3", tree.remove(3));
        assertEquals("12", tree.remove(12));
        assertEquals("17", tree.remove(17));
        assertEquals("18", tree.remove(18));
        assertEquals("15", tree.remove(15));
        assertEquals("16", tree.remove(16));
        assertEquals("14", tree.remove(14));
        
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
    }
}