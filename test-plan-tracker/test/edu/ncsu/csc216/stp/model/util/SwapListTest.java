/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SwapList Class in util package
 * @author Cameron Seitz
 *
 */
class SwapListTest {

	/**
	 * Test method for constructor
	 */
	@Test
	void testSwapList() {
		SwapList<String> l = new SwapList<String>();
		assertEquals(0, l.size());
	}

	/**
	 * Test method for add method
	 */
	@Test
	void testAdd() {
		SwapList<String> l = new SwapList<String>();
		
		//Test adding a null element
		assertThrows(NullPointerException.class, 
				() -> l.add(null));
		
		//add at empty
		l.add("apple");
		assertEquals("apple", l.get(0));
		
		//should allow duplicates
		assertDoesNotThrow(() -> l.add("apple"));
		
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("c");
		assertEquals(13, l.size());
		assertEquals("c", l.get(12));
		
	}

	/**
	 * Test method for remove method
	 */
	@Test
	void testRemove() {
		SwapList<String> l = new SwapList<String>();
		
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
	 * Test method for move up method
	 */
	@Test
	void testMoveUp() {
		SwapList<String> l = new SwapList<String>();
		
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move top element
		l.moveUp(0);
		assertEquals("apple", l.get(0));
		assertEquals("cat", l.get(1));
		assertEquals("dog", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move middle element
		l.moveUp(1);
		assertEquals("cat", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals("dog", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move back element
		l.moveUp(2);
		assertEquals("apple", l.get(0));
		assertEquals("dog", l.get(1));
		assertEquals("cat", l.get(2));
		
		
	}

	/**
	 * Test method for move down method
	 */
	@Test
	void testMoveDown() {
		SwapList<String> l = new SwapList<String>();
		
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move top element
		l.moveDown(0);
		assertEquals("cat", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals("dog", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move middle element
		l.moveDown(1);
		assertEquals("apple", l.get(0));
		assertEquals("dog", l.get(1));
		assertEquals("cat", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move back element
		l.moveDown(2);
		assertEquals("apple", l.get(0));
		assertEquals("cat", l.get(1));
		assertEquals("dog", l.get(2));
		
		
	}

	/**
	 * Test method for moveToFront method
	 */
	@Test
	void testMoveToFront() {
		SwapList<String> l = new SwapList<String>();
		
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move top element
		l.moveToFront(0);
		assertEquals("apple", l.get(0));
		assertEquals("cat", l.get(1));
		assertEquals("dog", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move middle element
		l.moveToFront(1);
		assertEquals("cat", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals("dog", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move back element
		l.moveToFront(2);
		assertEquals("dog", l.get(0));
		assertEquals("apple", l.get(1));
		assertEquals("cat", l.get(2));
		
	}

	/**
	 * Test method for moveToBack method
	 */
	@Test
	void testMoveToBack() {
		SwapList<String> l = new SwapList<String>();
		
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move top element
		l.moveToBack(0);
		assertEquals("cat", l.get(0));
		assertEquals("dog", l.get(1));
		assertEquals("apple", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move middle element
		l.moveToBack(1);
		assertEquals("apple", l.get(0));
		assertEquals("dog", l.get(1));
		assertEquals("cat", l.get(2));
		
		
		//reset list
		l = new SwapList<String>();
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//move back element
		l.moveToBack(2);
		assertEquals("apple", l.get(0));
		assertEquals("cat", l.get(1));
		assertEquals("dog", l.get(2));
	}

	/**
	 * Test method for get method
	 */
	@Test
	void testGet() {
		SwapList<String> l = new SwapList<String>();
		
		//Test getting from empty list
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.get(0));
		
		l.add("apple");
		l.add("cat");
		l.add("dog");
		
		//Test getting from negative index
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.get(-1));
		
		//Test getting from index larger than size - 1
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.get(3));
		
		//Test getting from index at size - 1
		assertDoesNotThrow(() -> l.get(2));
	}


}
