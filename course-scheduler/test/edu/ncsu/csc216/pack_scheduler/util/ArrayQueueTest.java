package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	@Test
	void testEnqueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		queue.enqueue("One");
		assertEquals(1, queue.size());
		queue.enqueue("Two");
		assertEquals(2, queue.size());
		queue.enqueue("Three");
		assertEquals(3, queue.size());
		queue.enqueue("Four");
		assertEquals(4, queue.size());
		queue.enqueue("Five");
		assertEquals(5, queue.size());
		queue.enqueue("Six");
		assertEquals(6, queue.size());
		queue.enqueue("Seven");
		assertEquals(7, queue.size());
		queue.enqueue("Eight");
		assertEquals(8, queue.size());
		queue.enqueue("Nine");
		assertEquals(9, queue.size());
		queue.enqueue("Ten");
		assertEquals(10, queue.size());
		assertThrows(IllegalArgumentException.class, () -> queue.enqueue("Eleven"));
		queue.setCapacity(11);
		queue.enqueue("Eleven");
		assertEquals(11, queue.size());
		assertThrows(IllegalArgumentException.class, () -> queue.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> queue.setCapacity(9));
		
	        
	}

	@Test
	void testDequeue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		queue.enqueue("One");
		assertEquals(1, queue.size());
		queue.enqueue("Two");
		assertEquals(2, queue.size());
		assertEquals("One", queue.dequeue());
		assertEquals("Two", queue.dequeue());
		assertThrows(NoSuchElementException.class, () -> queue.dequeue());
	}

	@Test
	void testIsEmpty() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		assertTrue(queue.isEmpty());
		queue.enqueue("One");
		assertFalse(queue.isEmpty());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}

}
