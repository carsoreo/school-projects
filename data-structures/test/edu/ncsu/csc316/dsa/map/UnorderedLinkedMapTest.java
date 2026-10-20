package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {
	
	/** Map which uses String and Integers to test UnorderedLinkedMap */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals("string3", map.put(3, "stringthird"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(0, null));
        assertNull(map.remove(0));

        
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 1, 4, 2, 5]", map.toString());
        
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 1, 2, 5]", map.toString());
        
        assertNull(map.get(-1));
        
        assertNull(map.get(null));
        
        assertNull(map.get(21));
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertEquals(4, map.size());
        
        assertEquals(null, map.remove(10));
        assertEquals(4, map.size());
        assertEquals("UnorderedLinkedMap[1, 4, 5, 3]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals(3, map.size());
        assertEquals("UnorderedLinkedMap[1, 4, 5]", map.toString());
        
        assertNull(map.remove(null));
        
        
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        assertTrue(map.iterator().hasNext());
        Iterator<Integer> iterator = map.iterator();
        
        int i1 = iterator.next();
        assertEquals(i1, 1);
        
        int i2 = iterator.next();
        assertEquals(i2, 4);
        
        int i3 = iterator.next();
        assertEquals(i3, 2);
        
        int i4 = iterator.next();
        assertEquals(i4, 5);
        
        int i5 = iterator.next();
        assertEquals(i5, 3);
        
        try {
        	iterator.next();
        	fail();
        }
        catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
        
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
    	
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        assertEquals(map.entrySet().iterator().next().getValue(), "string1");
        
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        assertEquals(map.values().iterator().next(), "string1");
        
        
    }
}