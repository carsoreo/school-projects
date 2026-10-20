package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {
	
	/** Heap to test using APQ */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e0,  -9);
        heap.replaceKey(e1,  -8);
        heap.replaceKey(e2,  -7);
        heap.replaceKey(e3,  -6);
        heap.replaceKey(e4,  -5);
        heap.replaceKey(e5,  -4);
        heap.replaceKey(e6,  -3);
        heap.replaceKey(e7,  -2);
        
        assertEquals((int) e0.getKey(), -9);
        assertEquals((int) e1.getKey(), -8);
        assertEquals((int) e2.getKey(), -7);
        assertEquals((int) e3.getKey(), -6);
        assertEquals((int) e4.getKey(), -5);
        assertEquals((int) e5.getKey(), -4);
        assertEquals((int) e6.getKey(), -3);
        assertEquals((int) e7.getKey(), -2);
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
		heap.replaceValue(e0, "-9");
		heap.replaceValue(e1, "-8");
		heap.replaceValue(e2, "-7");
		heap.replaceValue(e3, "-6");
		heap.replaceValue(e4, "-5");
		heap.replaceValue(e5, "-4");
		heap.replaceValue(e6, "-3");
		heap.replaceValue(e7, "-2");

		assertEquals(e0.getValue(), "-9");
		assertEquals(e1.getValue(), "-8");
		assertEquals(e2.getValue(), "-7");
		assertEquals(e3.getValue(), "-6");
		assertEquals(e4.getValue(), "-5");
		assertEquals(e5.getValue(), "-4");
		assertEquals(e6.getValue(), "-3");
		assertEquals(e7.getValue(), "-2");
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.remove(e1);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(7, heap.size());
        
        heap.remove(e4);
        heap.remove(e3);
        heap.remove(e5);
        heap.remove(e6);
        heap.remove(e7);
        
        assertEquals(2, heap.size());
        
        heap.remove(e2);
        heap.remove(e8);
        
        assertEquals(0, heap.size());
        
        try {
        	heap.remove(e0);
        } catch (Exception e) {
        	assertEquals("Invalid Adaptable PQ Entry.", e.getMessage());
        }
        
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> e1 = sHeap.insert(s1, "1");
        Entry<Student, String> e2 = sHeap.insert(s2, "2");
        Entry<Student, String> e3 = sHeap.insert(s3, "3");
        Entry<Student, String> e4 = sHeap.insert(s4, "4");
        Entry<Student, String> e5 = sHeap.insert(s5, "5");
        
        sHeap.remove(e5);
        sHeap.remove(e4);
        sHeap.remove(e3);
        sHeap.remove(e2);
        sHeap.remove(e1);
        
        assertEquals(0, sHeap.size());
    }
}