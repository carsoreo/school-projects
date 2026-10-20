/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test cases for TestPlan
 * @author clorejol
 *
 */
class TestPlanTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.TestPlan#addTestCase(TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		TestPlan t = new TestPlan("MyPlan");
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		TestCase tc2 = new TestCase("id2", "type", "desc", "expResults");
		t.addTestCase(tc);
		assertEquals(1, t.getTestCases().size());
		t.addTestCase(tc2);
		assertEquals(2, t.getTestCases().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.TestPlan#getTestCasesAsArray()}.
	 */
	@Test
	void testGetTestCasesAsArray() {
		TestPlan t = new TestPlan("MyPlan");
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		TestCase tc2 = new TestCase("id2", "type2", "desc2", "expResults");
		t.addTestCase(tc);
		assertEquals(1, t.getTestCases().size());
		t.addTestCase(tc2);
		assertEquals(2, t.getTestCases().size());
		
		String[][] array = t.getTestCasesAsArray();
		
		assertEquals(array[0][0], "id");
		assertEquals(array[0][1], "type");
		assertEquals(array[0][2], "FAIL");
		assertEquals(array[1][0], "id2");
		assertEquals(array[1][1], "type2");
		assertEquals(array[1][2], "FAIL");
	}

}
