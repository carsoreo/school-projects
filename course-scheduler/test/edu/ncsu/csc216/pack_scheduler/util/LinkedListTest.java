/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test for a linked list made by us
 * 
 * @author John Cirincione
 *
 */
class LinkedListTest {

	@Test
	void testConstructor() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		assertEquals(0, alist.size());
		
	}
	
	@Test
	<E> void testAdd() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		String e = "test";
		alist.add(0, e);
		assertEquals(1, alist.size());
		alist.add(1, "test2");
		assertEquals(2, alist.size());
		alist.add(0, "test3");
		assertEquals(3, alist.size());
		String actual = alist.get(0);
		assertEquals(actual, "test3");
		assertThrows(NullPointerException.class,
				() -> alist.add(0, null));
		assertThrows(IllegalArgumentException.class,
				() -> alist.add(0, "test"));
		assertThrows(IndexOutOfBoundsException.class,
				() -> alist.add(5, "test4"));
		
	}
	
	@Test
	<E> void testRemove() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		String e = "test";
		alist.add(0, e);
		assertEquals(1, alist.size());
		alist.add(1, "test2");
		System.out.println(alist);
		assertEquals(2, alist.size());
		alist.add(0, "test3");
		System.out.println(alist);
		assertEquals(3, alist.size());
		String actual = alist.get(0);
		assertEquals(actual, "test3");
		System.out.println(alist);
		alist.remove(1);
		System.out.println(alist);
		assertEquals(2, alist.size());
		assertEquals("test3", alist.get(0));
		assertEquals("test2", alist.get(1));

	}

	@Test
	void testSet() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		String e = "test";
		alist.add(0, e);
		assertEquals(1, alist.size());
		alist.add(1, "test2");
		assertEquals(2, alist.size());
		alist.add(0, "test3");
		assertEquals(3, alist.size());
		String actual = alist.get(0);
		assertEquals(actual, "test3");
		alist.set(0, "test4");
		assertEquals("test4", alist.get(0));
		assertEquals("test", alist.get(1));
		assertEquals("test2", alist.get(2));
	}
	
	@Test
	void testHasPrevious() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		assertFalse(alist.listIterator(0).hasPrevious());
	}
	
	@Test
	void testPrevious() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		alist.add("1");
		alist.add("2");
		alist.add("3");
		assertEquals(alist.listIterator(1).previous(), "1");
	}

	@Test
	void testLastIndexOf() {
		LinkedList<String> alist;
		alist = new LinkedList<String>();
		alist.add("orange");
		alist.add("apple");
		alist.add("banna");
		alist.add("kiwi");
		
		assertEquals(0, alist.lastIndexOf("orange"));
	}
}
