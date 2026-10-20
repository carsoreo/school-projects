package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;


/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 *
 */
public class SearchTableMapTest {
	
	/** Map to test SearchTableMap using Integers and Strings */
    private Map<Integer, String> map;
    
    /** Map to test SearchTableMap using Students */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(map.put(4, "string4"));
        assertEquals("SearchTableMap[3, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        assertNull(map.put(1, "string1"));
        assertEquals("SearchTableMap[1, 3, 4]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        map.put(1, "stringone");
        assertEquals(map.get(1), "stringone");
        assertEquals("SearchTableMap[1, 3, 4]", map.toString());
        assertEquals(3, map.size());
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertEquals(4, map.size());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students
        studentMap.put(s4, 4);
        studentMap.put(s1, 1);
        studentMap.put(s2, 2);
        studentMap.put(s5, 5);
        studentMap.put(s3, 3);
        
        assertEquals(studentMap.size(), 5);
        
        
        int key5 = studentMap.get(s5);
        assertEquals(key5, 5);
        
        int key4 = studentMap.get(s4);
        assertEquals(key4, 4);
        
        int key3 = studentMap.get(s3);
        assertEquals(key3, 3);
        
        int key2 = studentMap.get(s2);
        assertEquals(key2, 2);
        
        int key1 = studentMap.get(s1);
        assertEquals(key1, 1);
        
        
        
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
        int key = map.iterator().next();
        assertEquals(1, key);
        
        map.iterator().next();
        
        try {
        	map.iterator().remove();
            fail("Exception should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        
        try {
        	map.values().iterator().remove();
            fail("Exception should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        
        String s = map.values().iterator().next();
        assertEquals("string1", s);
        
        
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));

        
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        
        
    }
}