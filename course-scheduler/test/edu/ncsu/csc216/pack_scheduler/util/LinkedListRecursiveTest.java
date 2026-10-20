/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test for LinkedListRecursive
 * @author John Cirincione
 *
 */
class LinkedListRecursiveTest {

	@Test
	public void testContainsAndEmpty() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		assertFalse(rlist.contains("anything"));
		assertTrue(rlist.isEmpty());
		String e = "test"; 
		rlist.add(e);
		assertFalse(rlist.isEmpty());
		assertEquals(1, rlist.size());
		rlist.add("test2");
		assertEquals(2, rlist.size());
		assertTrue(rlist.contains(e));
		assertThrows(IndexOutOfBoundsException.class,
				() -> rlist.get(-1));
		assertThrows(IndexOutOfBoundsException.class,
				() -> rlist.get(5));
	}
	
	@Test
	public void testRemove() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		String e = "test"; 
		rlist.add(e);
		assertEquals(1, rlist.size());
		rlist.add("test2");
		assertEquals(2, rlist.size());
		assertTrue(rlist.contains(e));
		rlist.remove(0);
		assertEquals(1, rlist.size());
		assertEquals("test2", rlist.get(0));
		rlist.add("test3");
		assertEquals(2, rlist.size());
		rlist.remove(1);
		assertEquals(1, rlist.size());
		assertEquals("test2", rlist.get(0));
		assertThrows(IndexOutOfBoundsException.class,
				() -> rlist.remove(-1));
		assertThrows(IndexOutOfBoundsException.class,
				() -> rlist.remove(5));
	}
	
	@Test
	public void testRemoveElement() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		String e = "test";
		assertFalse(rlist.remove("test"));
		rlist.add(e);
		assertEquals(1, rlist.size());
		rlist.add("test2");
		assertEquals(2, rlist.size());
		assertTrue(rlist.contains(e));
		rlist.remove(e);
		assertEquals(1, rlist.size());
		assertEquals("test2", rlist.get(0));
		rlist.add("test3");
		assertEquals(2, rlist.size());
		rlist.remove("test3");
	}

	@Test
	public void testSet() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		String e = "test"; 
		rlist.add(e);
		assertEquals(1, rlist.size());
		rlist.add("test2");
		assertEquals("test", rlist.set(0, "renamed"));
		assertEquals("renamed", rlist.get(0));
	}
	
	@Test
	public void testAddAtIndex() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		String e = "test"; 
		rlist.add(0, e);
		rlist.add(1, "test 2");
		assertEquals(2, rlist.size());
		assertTrue(rlist.contains(e));
		rlist.add(2, "test3");
		assertEquals(3, rlist.size());
		assertThrows(NullPointerException.class,
				() -> rlist.add(0, null));
		assertThrows(IllegalArgumentException.class,
				() -> rlist.add(0, "test"));
		assertThrows(IndexOutOfBoundsException.class,
				() -> rlist.add(5, "test4"));
		assertThrows(IndexOutOfBoundsException.class,
				() -> rlist.add(-1, "test4"));
	}
	
	@Test
	public void testAdd() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		rlist.add("test");
		assertThrows(IllegalArgumentException.class,
				() -> rlist.add("test"));
		
	}
	
	@Test 
	public void testAddIndex() {
		LinkedListRecursive<String> rlist;
		rlist = new LinkedListRecursive<String>();
		rlist.add(0, "apple");
		rlist.add(0, "orange");
		assertEquals("orange", rlist.get(0));
		assertEquals("apple", rlist.get(1));
	}
}
