package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * SortedListTest tests the hidden class of SortedList
 * @author Olivia Nowell, Zane Taylor, Ashley Godwin
 */
public class SortedListTest {
	/**
	 * testSortedList tests the constructor for the class SortedList
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
	}

	/**
	 * testAdd tests the add(E element) method for the SortedList class
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add("apricot");
		assertEquals("apricot", list.get(1));
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(2));
		
		list.add("orange");
		assertEquals("apricot", list.get(1));
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(2));
		assertEquals("orange", list.get(3));
		
		
		assertEquals(4, list.size());
		
		
		//Test adding a null element
		
		try {
			list.add(null);
		} catch(NullPointerException e) {
			System.out.println("null element"); 
		}
				
		//Test adding a duplicate element
		try {
			list.add("orange");
		} catch(IllegalArgumentException e) {
			System.out.println("duplicate element");
		}
		
	}
	
	/**
	 * testGet tests the get(int index) method for the SortedList class
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.

		//Test getting an element from an empty list
		assertThrows("Empty list should throw IndexOutOfBounds on get() but did not",
				IndexOutOfBoundsException.class, () -> list.get(0));
		
		//Add some elements to the list
		list.add("pineapple");
		list.add("grapefruit");
		
		//Test getting an element at an index < 0
		assertThrows("List should throw IndexOutOfBounds on get(-1) but did not",
				IndexOutOfBoundsException.class, () -> list.get(-1));
		
		//Test getting an element at size
		assertThrows("List should throw IndexOutOfBounds on get(size) but did not",
				IndexOutOfBoundsException.class, () -> list.get(2));
		
	}
	
	/**
	 * testRemove tests the remove(int index) method for the SortedList class
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("empty list");
		}
		
		//Add some elements to the list - at least 4
		list.add("pear");
		list.add("strawberry");
		list.add("grape");
		list.add("apple");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("index < 0");
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("index out of bounds");
		}
		
		//Test removing a middle element
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("grape", list.get(1));
		assertEquals("strawberry", list.get(2));
		
		//Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("grape", list.get(1));
		//Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("grape", list.get(0));
		//Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	/**
	 * testIndexOF tests the indexOf(E element) method for the SortedList class
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("0"));
		
		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("grape");
		list.add("watermelon");
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(-1, list.indexOf("grapefruit"));
		assertEquals(2, list.indexOf("grape"));
		assertEquals(3, list.indexOf("watermelon"));
		
		//Test checking the index of null
		try {
			assertEquals(-1, list.indexOf(null));
		} catch(NullPointerException e) {
			System.out.println("null element");
		}
		
	}
	
	/**
	 * testClear tests the clear() method for the SortedList class
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("grape");
		list.add("watermelon");
		
		//Clear the list
		list.clear();
		
		//Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * testIsEmpty tests the isEmpty() method for the SortedList class
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//Add at least one element
		list.add("apple");
		
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * testContains tests the contains(E other) method for the SortedList class
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("apple"));
		
		//Add some elements
		list.add("apple");
		list.add("orange");
		list.add("lemon");
		
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("grape"));
		assertTrue(list.contains("orange"));
		assertFalse(list.contains("strawberry"));
		assertTrue(list.contains("lemon"));
		assertFalse(list.contains("kiwi"));
	}
	
	/**
	 * testEquals tests the overridden Equals(java.lang.Object obj) method for the SortedList class
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cherry");

		list2.add("orange");
		list2.add("pear");
		list2.add("grapefruit");

		list3.add("apple");
		list3.add("banana");
		list3.add("cherry");
		
		//Test for equality and non-equality
		assertEquals(list1, list1);
		assertNotEquals(list1, list2);
		assertEquals(list1, list3);

		assertNotEquals(list2, list1);
		assertEquals(list2, list2);
		assertNotEquals(list2, list3);

		assertEquals(list3, list1);
		assertNotEquals(list3, list2);
		assertEquals(list3, list3);
	}
	
	/**
	 * testHashCode tests the overridden hashCode method for the SortedList class
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cherry");

		list2.add("orange");
		list2.add("pear");
		list2.add("grapefruit");

		list3.add("apple");
		list3.add("banana");
		list3.add("cherry");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list1.hashCode());
		assertNotEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list1.hashCode(), list3.hashCode());

		assertNotEquals(list2.hashCode(), list1.hashCode());
		assertEquals(list2.hashCode(), list2.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());

		assertEquals(list3.hashCode(), list1.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
		assertEquals(list3.hashCode(), list3.hashCode());
	}

}
 