package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 10 assignment page or just given explicitly to use in the
 * Workshop 10 assignment page.
 * 
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class TreeSetTest {
	
	/** Set to test TreeSetTest on */
    private Set<Integer> set;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(4);
        assertEquals(2, set.size());
        assertFalse(set.isEmpty());
        
        
        set.add(3);
        assertEquals(3, set.size());
        assertFalse(set.isEmpty());
        
        assertTrue(set.contains(5));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
                
        assertFalse(set.contains(0));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        int check1 = set.remove(5);
        assertEquals(5, check1);
        
        int check2 = set.remove(10);
        assertEquals(10, check2);
        
        int check3 = set.remove(15);
        assertEquals(15, check3);
        
        int check4 = set.remove(20);
        assertEquals(20, check4);
        
        int check5 = set.remove(25);
        assertEquals(25, check5);
        
        assertFalse(set.contains(5));
        assertFalse(set.contains(10));
        assertFalse(set.contains(15));
        assertFalse(set.contains(20));
        assertFalse(set.contains(25));
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.retainAll(other);
        
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the removeAll behavior
     */     
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        
        assertFalse(set.contains(10));
        assertFalse(set.contains(20));
        assertEquals(set.size(), 3);
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        
        assertEquals(8, set.size());
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
        Iterator<Integer> it = set.iterator();
        
        int check1 = it.next();
        assertEquals(5, check1);
        
        int check2 = it.next();
        assertEquals(10, check2);
        
        int check3 = it.next();
        assertEquals(15, check3);
        
        int check4 = it.next();
        assertEquals(20, check4);
        
        int check5 = it.next();
        assertEquals(25, check5);
    }
}