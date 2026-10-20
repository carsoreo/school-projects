package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 2 assignment page or just given explicitly to use in the
 * Workshop 2 assignment page.
 * 
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class PositionalLinkedListTest {
	
	/** Value used to hold a list to use for testing */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        list.addFirst("new first");
        
        assertEquals("new first", list.first().getElement());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        Position<String> second = list.addFirst("two");
        
        assertEquals(second, list.before(first));
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        list.addLast("two");
        assertEquals(2, list.size());
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        
        assertEquals(3, list.size());
        assertEquals(third.getElement(), list.last().getElement());
        assertEquals(first.getElement(), list.first().getElement());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	Position<String> first = list.addLast("one");
    	Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        
        assertEquals(first, list.before(second));
        assertEquals(second, list.before(third));
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	Position<String> first = list.addLast("one");
    	Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        
        assertEquals(third, list.after(second));
        assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	Position<String> first = list.addLast("one");
    	Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        
        Position<String> oneToTwo = list.addBefore(second, "1-2");
        assertEquals(list.before(second), oneToTwo);
        
        Position<String> twoToThree = list.addBefore(third, "2-3");
        assertEquals(list.before(third), twoToThree);
        
        Position<String> zeroToOne = list.addBefore(first, "0-1");
        assertEquals(list.before(first), zeroToOne);
        
        
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	Position<String> first = list.addLast("one");
    	Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        
        Position<String> twoToThree = list.addAfter(second, "2-3");
        assertEquals(list.after(second), twoToThree);
        
        Position<String> threeToFour = list.addAfter(third, "3-4");
        assertEquals(list.after(third), threeToFour);
        
        Position<String> oneToTwo = list.addAfter(first, "1-2");
        assertEquals(list.after(first), oneToTwo);
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	Position<String> first = list.addLast("one");
    	list.addLast("two");
        Position<String> third = list.addLast("three");
        
        assertEquals("one", list.set(first, "first"));
        assertEquals("first", list.first().getElement());
        
        assertEquals("three", list.set(third, "third"));
        assertEquals("third", list.last().getElement());
        
        assertEquals(list.size(), 3);
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addLast("one");
    	Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        
        assertEquals("three", list.remove(third));
        assertEquals(list.size(), 2);
        
        assertEquals("two", list.remove(second));
        assertEquals(list.size(), 1);
        
        assertEquals("one", list.remove(first));
        assertEquals(list.size(), 0);
        
        Position<String> first2 = list.addLast("one");
    	Position<String> second2 = list.addLast("two");
        Position<String> third2 = list.addLast("three");
        Position<String> fourth2 = list.addLast("four");
        Position<String> fifth2 = list.addLast("five");
        
        list.remove(third2);
        assertEquals(fourth2, list.after(second2));
        
        list.remove(fourth2);
        assertEquals(second2, list.before(fifth2));
        
        list.remove(first2);
        
        assertEquals(null, list.before(second2));
        
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        Iterator<String> it = list.iterator();
        
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        list.addLast("one");
        
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.first().getElement());
        
        it = list.iterator();
        
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        it = list.iterator();
        assertEquals("one", it.next());
        assertEquals("one", list.first().getElement());
        it.remove();
        assertEquals(0, list.size());
        
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        
    }

}