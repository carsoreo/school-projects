package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 7 assignment page or just given explicitly to use in the
 * Workshop 7 assignment page.
 * 
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class BinarySearchTreeMapTest {
	
	/** Unbalanced BinarySearchTree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	
    	//NOTE: I have no idea how else to test this...
        assertEquals("BalanceableBinaryTree[\n"
        		+ "null\n"
        		+ "]", tree.toString());
    	
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals(2, tree.size());
        assertFalse(tree.isEmpty());
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(3, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        assertEquals(null, tree.get(4));
        
        assertEquals(3, tree.size());
        assertFalse(tree.isEmpty());
        tree.put(0, "zero");
        assertEquals(4, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        
        Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
        assertEquals(it.next().getValue(), "zero");
        assertEquals(it.next().getValue(), "one");
        assertEquals(it.next().getValue(), "two");
        assertEquals(it.next().getValue(), "three");
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());

        tree.put(3, "three");
        assertEquals(3, tree.size());
        
        tree.put(0, "zero");
        assertEquals(4, tree.size());
        
        assertEquals("one", tree.get(1));
        assertEquals("zero", tree.get(0));
        assertEquals("three", tree.get(3));
        assertEquals("two", tree.get(2));
        
        assertEquals(null, tree.get(-1));
        assertEquals(null, tree.get(20));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());

        tree.put(3, "three");
        assertEquals(3, tree.size());
        
        tree.put(0, "zero");
        assertEquals(4, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(3, tree.size());
        
        assertEquals("zero", tree.remove(0));
        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(2, tree.size());
        
        assertEquals("two", tree.remove(2));
        assertEquals(3, (int) tree.root().getElement().getKey());
        assertEquals(1, tree.size());
        
        assertEquals("three", tree.remove(3));
        assertEquals(null, tree.root().getElement());
        assertEquals(0, tree.size());
        
        
        assertEquals(null, tree.remove(0));
        
        tree.put(55,  "55");
        tree.put(29, "29");
        tree.put(87, "87");
        tree.put(-3, "-3");
        tree.put(42, "42");
        tree.put(60, "60");
        tree.put(91, "91");
        
        assertEquals("55", tree.remove(55));
        assertEquals(60, (int) tree.root().getElement().getKey());
        
        
        
    }
}