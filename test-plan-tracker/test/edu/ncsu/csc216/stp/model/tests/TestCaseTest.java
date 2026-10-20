/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TestCase Class.
 * @author Cameron Seitz
 *
 */
class TestCaseTest {

	/**
	 * Test method for constructor
	 */
	@Test
	void testTestCase() {
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		assertEquals("id", tc.getTestCaseId());
		assertEquals("type", tc.getTestType());
		assertEquals("desc", tc.getTestDescription());
		assertEquals("expResults", tc.getExpectedResults());
		
		assertNull(tc.getTestPlan());
		assertEquals("FAIL", tc.getStatus());
		assertFalse(tc.isTestCasePassing());
		
	}

	/**
	 * Test method for addTestResult method
	 */
	@Test
	void testAddTestResult() {
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		tc.addTestResult(false, "blank1");
		tc.addTestResult(false, "blank2");
		tc.addTestResult(true, "success");
		
		assertTrue(tc.isTestCasePassing());
		assertEquals("PASS", tc.getStatus());
		
		
		tc.addTestResult(false, "blank3");
		assertFalse(tc.isTestCasePassing());
		assertEquals("FAIL", tc.getStatus());
	}

	/**
	 * Test method for getActualResultsLog method
	 */
	@Test
	void testGetActualResultsLog() {
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		tc.addTestResult(false, "blank1");
		tc.addTestResult(false, "blank2");
		tc.addTestResult(true, "success");
		
		assertEquals("- FAIL: blank1\n" +
				"- FAIL: blank2\n" +
				"- PASS: success\n", tc.getActualResultsLog());
	}

	/**
	 * Test method for toString method
	 */
	@Test
	void testToString() {
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		tc.addTestResult(false, "blank1");
		tc.addTestResult(false, "blank2");
		tc.addTestResult(true, "success");
		
		assertEquals("# id,type\n" +
				"* desc\n" +
				"* expResults\n" +
				"- FAIL: blank1\n" +
				"- FAIL: blank2\n" +
				"- PASS: success\n", tc.toString());
	}



}
