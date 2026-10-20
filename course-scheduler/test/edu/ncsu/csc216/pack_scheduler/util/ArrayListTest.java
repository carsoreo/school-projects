package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArrayListTest {

	@Test
	void testConstructor() {
		ArrayList<String> alist;
		alist = new ArrayList<String>();
		assertEquals(0, alist.size());
		
	}
	
	@Test
	<E> void testAdd() {
		ArrayList<String> alist;
		alist = new ArrayList<String>();
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
		ArrayList<String> alist;
		alist = new ArrayList<String>();
		String e = "test";
		alist.add(0, e);
		assertEquals(1, alist.size());
		alist.add(1, "test2");
		assertEquals(2, alist.size());
		alist.add(0, "test3");
		assertEquals(3, alist.size());
		String actual = alist.get(0);
		assertEquals(actual, "test3");
		alist.remove(0);
		System.out.println(alist);
		assertEquals(2, alist.size());
		assertEquals("test", alist.get(0));
		assertEquals("test2", alist.get(1));

	}

	@Test
	void testSet() {
		ArrayList<String> alist;
		alist = new ArrayList<String>();
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

}
