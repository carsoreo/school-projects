/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Log Class in util package
 * @author Cameron Seitz
 *
 */
class LogTest {

	/**
	 * Test method for constructor
	 */
	@Test
	void testLog() {
		Log<String> l = new Log<String>();
		assertEquals(0, l.size());
	}

	/**
	 * Test method for add method
	 */
	@Test
	void testAdd() {
		Log<String> l = new Log<String>();
		assertEquals(0, l.size());
		
		l.add("a");
		assertEquals("a", l.get(0));
		
		//make sure it grows
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
		assertEquals(12, l.size());
		
		//Test adding a null element
		assertThrows(NullPointerException.class, 
				() -> l.add(null));
	}

	
	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.Log#get(int)}.
	 */
	@Test
	void testGet() {
		Log<String> l = new Log<String>();
		
		l.add("a");
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
		assertEquals("c", l.get(11));
		
		//Test getting at negative index
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.get(-1));
		//Test getting at index size
		assertThrows(IndexOutOfBoundsException.class, 
				() -> l.get(12));
	}


}
