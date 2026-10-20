package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 8 assignment page or just given explicitly to use in the
 * Workshop 8 assignment page.
 * 
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class LinearProbingHashMapTest {
	
	
	/** Map which holds hash values for testing */
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        
        assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("string3", map.put(3, "string3again"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(-3, "string-3"));
        assertEquals(map.size(), 3);
        
        //Check to make sure constructors work fine
        Map<Integer, String> map2 = new LinearProbingHashMap<Integer, String>(true);
        assertNull(map2.put(3, "string3"));
        assertEquals(map2.get(3), "string3");
        
        Map<Integer, String> map3 = new LinearProbingHashMap<Integer, String>(true);
        assertNull(map3.put(3, "string3"));
        assertEquals(map3.get(3), "string3");
        
        Map<Integer, String> map4 = new LinearProbingHashMap<Integer, String>();
        assertNull(map4.put(3, "string3"));
        assertEquals(map4.get(3), "string3");
        
        Map<Integer, String> map5 = new LinearProbingHashMap<Integer, String>(1);
        assertNull(map5.put(3, "string3"));
        assertEquals(map5.get(3), "string3");
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(-3, "string-3"));
        
        assertEquals(map.get(3), "string3");
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(-3, "string-3"));
        
        assertEquals(map.remove(3), "string3");
        assertEquals(map.size(), 2);
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(-3, "string-3"));
        
        Iterator<Integer> it = map.iterator();
        assertEquals(-3, (int)it.next()); // should be in a map in index -4
        assertEquals(3, (int)it.next()); // should be in a map in index 3
        assertEquals(4, (int)it.next()); // should be in a map in index 3
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(-3, "string-3"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();  
        assertTrue(it.hasNext());
        
        assertEquals(-3, (int)it.next().getKey()); // should be in a map in index -4
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 3
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 3
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(-3, "string-3"));
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        assertEquals("string-3", it.next()); // should be in a map in index -4
        assertEquals("string3", it.next()); // should be in a map in index 3
        assertEquals("string4", it.next()); // should be in a map in index 3
    }
}