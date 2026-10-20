package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 2 assignment page or just given explicitly to use in the
 * Workshop 2 assignment page.
 * 
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 *
 */
public class SinglyLinkedTest {
	
	/** List to test data on */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());

        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        assertFalse(list.isEmpty());
        
        list.add(4, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
        assertFalse(list.isEmpty());
        
        list.add(5, "six");
        assertEquals(6, list.size());
        assertEquals("six", list.get(5));
        assertFalse(list.isEmpty());
        
        list.add(6, "seven");
        assertEquals(7, list.size());
        assertEquals("seven", list.get(6));
        assertFalse(list.isEmpty());
        
        list.add(7, "eight");
        assertEquals(8, list.size());
        assertEquals("eight", list.get(7));
        assertFalse(list.isEmpty());
        
        list.add(8, "nine");
        assertEquals(9, list.size());
        assertEquals("nine", list.get(8));
        assertFalse(list.isEmpty());
        
        list.add(9, "ten");
        assertEquals(10, list.size());
        assertEquals("ten", list.get(9));
        assertFalse(list.isEmpty());
        
        list.add(10, "eleven");
        assertEquals(11, list.size());
        assertEquals("eleven", list.get(10));
        assertFalse(list.isEmpty());
        
        list.add(11, "twelve");
        assertEquals(12, list.size());
        assertEquals("twelve", list.get(11));
        assertFalse(list.isEmpty());
        
        list.add(12, "thirteen");
        assertEquals(13, list.size());
        assertEquals("thirteen", list.get(12));
        assertFalse(list.isEmpty());
        
        list.add(13, "fourteen");
        assertEquals(14, list.size());
        assertEquals("fourteen", list.get(13));
        assertFalse(list.isEmpty());
        
        try{
            list.add(15,  "fifteen");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.add(10, "between");
        assertEquals("between", list.get(10));
        assertFalse(list.isEmpty());
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	
    	assertTrue(list.isEmpty());
    	
    	list.addLast("one");
    	assertEquals("one", list.get(0));
    	list.addLast("two");
    	assertEquals("two", list.get(1));
    	list.addLast("three");
    	assertEquals("three", list.get(2));
    	list.addLast("four");
    	assertEquals("four", list.get(3));
    	list.addLast("five");
    	assertEquals("five", list.get(4));
    	
    	assertEquals(5, list.size());
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	list.add(0, "one");
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	list.add(4, "five");
    	
    	assertEquals("five", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	list.addFirst("one");
    	assertEquals("one", list.get(0));
    	list.addFirst("two");
    	assertEquals("two", list.get(0));
    	list.addFirst("three");
    	assertEquals("three", list.get(0));
    	list.addFirst("four");
    	assertEquals("four", list.get(0));
    	list.addFirst("five");
    	assertEquals("five", list.get(0));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	list.add(0, "one");
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	list.add(4, "five");
    	
    	assertEquals("one", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        list.addLast("two");
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertEquals("two", it.next());
        it.remove();
        assertEquals(list.get(0), "one");
        assertEquals(list.size(), 1);
        
        
        try{
            list.get(1);
        } catch(Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        
        it = list.iterator();
        assertEquals("one", it.next());
        assertEquals(list.size(), 1);
        try{
            it.next();
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        assertEquals(list.last(), "one");
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	list.add(0, "one");
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	list.add(4, "five");
    	
    	assertEquals("three", list.remove(2));
    	assertEquals(list.size(), 4);
    	assertEquals(list.get(3), "five");
    	
    	try{
    		list.remove(10);
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	list.add(0, "one");
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	list.add(4, "five");
    	
    	assertEquals("one", list.removeFirst());
    	assertEquals("two", list.removeFirst());
    	assertEquals("three", list.removeFirst());
    	assertEquals("four", list.removeFirst());
    	assertEquals("five", list.removeFirst());
    	
    	
    	try{
    		list.removeFirst();
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    	assertTrue(list.isEmpty());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	list.add(0, "one");
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	list.add(4, "five");
    	
    	assertEquals("five", list.removeLast());
    	assertEquals("four", list.removeLast());
    	assertEquals("three", list.removeLast());
    	assertEquals("two", list.removeLast());
    	assertEquals("one", list.removeLast());
    	
    	try{
    		list.removeLast();
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }

    	assertTrue(list.isEmpty());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	list.add(0, "one");
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	list.add(4, "five");
    	
    	try{
    		list.set(-1, "broken");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    	
    	try{
    		list.set(6, "broken");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    	
    	assertEquals("three", list.set(2, "third"));
    	assertEquals("third", list.get(2));
    }
}