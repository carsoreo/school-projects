package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 7 assignment page or just given explicitly to use in the
 * Workshop 7 assignment page.
 * 
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class SplayTreeMapTest {
	
	/** Tree to be used in testing for a SplayMap */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior.
     * This test was adapted from the Exercise from the SplayTrees lecture.
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        //This test was adapted from the Exercise from the SplayTrees lecture
        tree.put(5, "dog");
        tree.put(10, "cat");
        tree.put(15, "frog");
        tree.put(20, "wolf");
        tree.put(8, "lion");
        
        assertEquals(8, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(15, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(20, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.right(tree.root())).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     * This test was adapted from the Exercise from the SplayTrees lecture.
     */ 
    @Test
    public void testGet() {
    	tree.put(5, "dog");
        tree.put(10, "cat");
        tree.put(15, "frog");
        tree.put(20, "wolf");
        tree.put(8, "lion");
        
        assertEquals(tree.get(10), "cat");
        assertEquals(tree.get(15), "frog");
        assertEquals(tree.get(20), "wolf");
    }
    
    /**
     * Test the output of the remove(k) behavior
     * This test was adapted from the Exercise from the SplayTrees lecture.
     */     
    @Test
    public void testRemove() {
    	tree.put(5, "5");
        tree.put(10, "10");
        
        assertEquals(null, tree.remove(15));
        
        //Last visited node should be root
        assertEquals(10, (int)tree.root().getElement().getKey());
        
        //Reset tree
        assertEquals("5", tree.remove(5));
        assertEquals("10", tree.remove(10));
        
        //Add tree, adapted from the Exercise from the SplayTrees lecture
        tree.put(5, "dog");
        tree.put(10, "cat");
        tree.put(15, "frog");
        tree.put(20, "wolf");
        tree.put(8, "lion");
    	
        assertEquals("cat", tree.remove(10));
        assertEquals(4, tree.size());
        
        assertEquals(15, (int)tree.root().getElement().getKey());
        assertEquals(8, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(20, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.left(tree.root())).getElement().getKey());
    	
    }
}