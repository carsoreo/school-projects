package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class LinkedAbstractListTest {

	@Test
	void testConstructor() {
		LinkedAbstractList<String> alist;
		alist = new LinkedAbstractList<String>(10);
		assertEquals(0, alist.size());
		
	}
	
	@Test
	<E> void testAdd() {
		LinkedAbstractList<String> alist;
		alist = new LinkedAbstractList<String>(10);
		String e = "test";
		alist.add(0, e);
		assertEquals(1, alist.size());
		alist.add(1, "test2");
		assertEquals(2, alist.size());
		alist.add(0, "test3");
		assertEquals(3, alist.size());
		String actual = alist.get(0);
		assertEquals(actual, "test3");
		assertEquals(alist.get(1), "test");
		assertEquals(alist.get(2), "test2");
		assertThrows(NullPointerException.class,
				() -> alist.add(0, null));
		assertThrows(IllegalArgumentException.class,
				() -> alist.add(0, "test"));
		assertThrows(IndexOutOfBoundsException.class,
				() -> alist.add(5, "test4"));
		
	}
	
	@Test
	<E> void testRemove() {
		LinkedAbstractList<String> alist;
		alist = new LinkedAbstractList<String>(10);
		String e = "test";
		alist.add(0, e);
		assertEquals(1, alist.size());
		alist.add(1, "test2");
		assertEquals(2, alist.size());
		alist.add(0, "test3");
		assertEquals(3, alist.size());
		System.out.println(alist);
		String actual = alist.get(0);
		assertEquals(actual, "test3");
		assertEquals(alist.get(1), "test");
		assertEquals(alist.get(2), "test2");
		alist.remove(0);
		assertEquals(2, alist.size());
		assertEquals("test", alist.get(0));
		assertEquals("test2", alist.get(1));
		alist.remove(1);
		assertEquals("test", alist.get(0));
		assertEquals(1, alist.size());

	}

	@Test
	void testSet() {
		LinkedAbstractList<String> alist;
		alist = new LinkedAbstractList<String>(10);
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

