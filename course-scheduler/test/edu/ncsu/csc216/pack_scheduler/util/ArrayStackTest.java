/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Tests for ArrayStack.
 * @author John Cirincione
 *
 */
class ArrayStackTest {

	/**
	 * Test method for push
	 */
	@Test
	void testPush() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		stack.push("One");
		assertEquals(1, stack.size());
		stack.push("Two");
		assertEquals(2, stack.size());
		stack.push("Three");
		assertEquals(3, stack.size());
		stack.push("Four");
		assertEquals(4, stack.size());
		stack.push("Five");
		assertEquals(5, stack.size());
		stack.push("Six");
		assertEquals(6, stack.size());
		stack.push("Seven");
		assertEquals(7, stack.size());
		stack.push("Eight");
		assertEquals(8, stack.size());
		stack.push("Nine");
		assertEquals(9, stack.size());
		stack.push("Ten");
		assertEquals(10, stack.size());
		assertThrows(IllegalArgumentException.class, () -> stack.push("Eleven"));
		stack.setCapacity(11);
		stack.push("Eleven");
		assertEquals(11, stack.size());
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(8));
	}

	/**
	 * Test method for pop
	 */
	@Test
	void testPop() {
		ArrayStack<String> stack = new ArrayStack<String>(6);
		stack.push("One");
		assertEquals(1, stack.size());
		stack.push("Two");
		assertEquals(2, stack.size());
		assertEquals("Two", stack.pop());
		assertEquals("One", stack.pop());
		assertThrows(EmptyStackException.class, () -> stack.pop());
	}

	/**
	 * Test method for isEmpty
	 */
	@Test
	void testIsEmpty() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		assertTrue(stack.isEmpty());
		stack.push("One");
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());
	}

}
