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
public class SkipListMapTest {
	
	/** Map used to test SkipList  for Strings and Integers*/
    private Map<Integer, String> map;
    
    /** Map used to test SkipList that holds Student objects*/
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals("string3", map.put(3, "stringthird"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());
        
        
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertEquals(4, map.size());
        
        assertEquals("SkipListMap[1, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.remove(1));
        assertEquals("SkipListMap[3, 4, 5]", map.toString());
        
        assertEquals("string4", map.remove(4));
        assertEquals("SkipListMap[3, 5]", map.toString());
        
        assertEquals(null, map.remove(4));
        assertEquals("SkipListMap[3, 5]", map.toString());
        
        
        assertNull(map.put(2, "string2"));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(4, "string4"));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.remove(1));
        assertEquals("SkipListMap[2, 3, 4, 5]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals("SkipListMap[3, 4, 5]", map.toString());
        assertEquals(3, map.size());
        
        SkipListMap<Integer, String> newMap = new SkipListMap<Integer, String>();
        assertEquals("SkipListMap[\n-INF -> +INF\n]", newMap.toFullString());
        
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
        
        studentMap.put(s1, 1);
        studentMap.put(s2, 2);
        studentMap.put(s3, 3);
        studentMap.put(s4, 4);
        studentMap.put(s5, 5);
        
        assertEquals(studentMap.size(), 5);
        
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students
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
