package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapPriorityQueueTest {
	
	/** PQHeap to test */
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertEquals(heap.size(), 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(2, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(3, (int)heap.min().getKey());
        
        
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertEquals(heap.size(), 0);
        
        assertNull(heap.min());
        
        Entry<Integer, String> e = heap.insert(8, "eight");
        Entry<Integer, String> e2 =  heap.insert(3, "three");
        
        assertEquals(heap.min(), e2);
        assertFalse(heap.min().equals(e));
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        Entry<Integer, String> e = heap.insert(8, "eight");
        Entry<Integer, String> e2 =  heap.insert(3, "three");
        
        assertEquals(e2, heap.deleteMin());
        assertEquals(e, heap.deleteMin());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s1, "1");
        sHeap.insert(s2, "2");
        sHeap.insert(s3, "3");
        sHeap.insert(s4, "4");
        sHeap.insert(s5, "5");
        
       
        
        assertEquals(sHeap.deleteMin().getValue(), "1");
        assertEquals(sHeap.deleteMin().getValue(), "2");
        assertEquals(sHeap.deleteMin().getValue(), "3");
        assertEquals(sHeap.deleteMin().getValue(), "4");
        assertEquals(sHeap.deleteMin().getValue(), "5");
    }
}