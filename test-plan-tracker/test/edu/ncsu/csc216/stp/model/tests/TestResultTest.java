/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TestResult Class
 * @author Cameron Seitz
 *
 */
class TestResultTest {

	/**
	 * Test method for constructor
	 */
	@Test
	void testTestResult() {
		TestResult tr = new TestResult(true, "actual results.");
		
		assertEquals("actual results.", tr.getActualResults());
		assertTrue(tr.isPassing());
		
	}

	/**
	 * Test method for toString
	 */
	@Test
	void testToString() {
		
		TestResult tr = new TestResult(true, "actual results.");
		
		assertEquals("PASS: actual results.", tr.toString());
		
		tr = new TestResult(false, "failure.");
		
		assertEquals("FAIL: failure.", tr.toString());
		
	}

	

}
