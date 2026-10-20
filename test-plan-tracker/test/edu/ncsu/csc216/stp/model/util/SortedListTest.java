/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests SortedList Class in util package
 * @author Cameron Seitz
 *
 */
class SortedListTest {

	/**
	 * Test method for constructor
	 */
	@Test
	void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for add method
	 */
	@Test
	void testAdd() {
		SortedList<String> l = new SortedList<String>();
		
		//Test adding a null element
		assertThrows(NullPointerException.class, 
				() -> l.add(null));
		
		//add at empty
		l.add("apple");
		assertEquals("apple", l.get(0));
		
		//Test adding a duplicate element
		assertThrows(IllegalArgumentException.class, 
				() -> l.add("apple"));
		
		//add before first index
		l.add("aapple");
		assertEquals("aapple", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals(2, l.size());
		
		//add at end of list
		l.add("zen");
		assertEquals("aapple", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals("zen", l.get(2));
		assertEquals(3, l.size());
		
		//add at middle of list
		l.add("dog");
		assertEquals("aapple", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals("dog", l.get(2));
		assertEquals("zen", l.get(3));
		assertEquals(4, l.size());
		
	}

	/**
	 * Test method for remove method
	 */
	@Test
	void testRemove() {
		SortedList<String> l = new SortedList<String>();
		
		//Test removing with empty list 
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.remove(0));
		
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		
		assertEquals("cat", l.remove(1));
		assertEquals(2, l.size());
		assertEquals("apple", l.get(0));
		assertEquals("dog", l.get(1));
		
		assertEquals("apple", l.remove(0));
		assertEquals(1, l.size());
		assertEquals("dog", l.get(0));
		
		//Test removing at index larger than size - 1
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.remove(5));
	}

	/**
	 * Test method for contains method
	 */
	@Test
	void testContains() {
		SortedList<String> l = new SortedList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		assertTrue(l.contains("apple"));
		assertTrue(l.contains("dog"));
		
		assertEquals("apple", l.remove(0));
		assertFalse(l.contains("apple"));
		
		assertFalse(l.contains(null));
		
	}

	

}
