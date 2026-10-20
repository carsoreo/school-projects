/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test cases for FailingTestList
 * @author clorejol
 *
 */
class FailingTestListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#setTestPlanName(java.lang.String)}.
	 */
	@Test
	void testSetTestPlanName() {
		FailingTestList f = new FailingTestList();
		assertThrows(IllegalArgumentException.class, () -> f.setTestPlanName("Not correct"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#addTestCase(TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		FailingTestList f = new FailingTestList();
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		TestCase tc2 = new TestCase("id2", "type", "desc", "expResults");
		assertDoesNotThrow(() -> f.addTestCase(tc));
		
		tc2.addTestResult(true, "wow");
		assertThrows(IllegalArgumentException.class, () -> f.addTestCase(tc2));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#getTestCasesAsArray()}.
	 */
	@Test
	void testGetTestCasesAsArray() {
		FailingTestList f = new FailingTestList();
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		TestCase tc2 = new TestCase("id2", "type2", "desc", "expResults");
		TestPlan tp = new TestPlan ("TestPlan");
		
		tc.setTestPlan(tp);
		tc2.setTestPlan(tp);
		
		f.addTestCase(tc);
		f.addTestCase(tc2);
		
		assertEquals(f.getTestCasesAsArray()[0][0], "id");
		assertEquals(f.getTestCasesAsArray()[0][1], "type");
		assertEquals(f.getTestCasesAsArray()[0][2], "TestPlan");
		assertEquals(f.getTestCasesAsArray()[1][0], "id2");
		assertEquals(f.getTestCasesAsArray()[1][1], "type2");
		assertEquals(f.getTestCasesAsArray()[1][2], "TestPlan");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#clearTestCases()}.
	 */
	@Test
	void testClearTestCases() {
		FailingTestList f = new FailingTestList();
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		TestCase tc2 = new TestCase("id2", "type2", "desc", "expResults");
		
		f.addTestCase(tc);
		f.addTestCase(tc2);
		
		f.clearTests();
		
		assertEquals(0, f.getTestCases().size());
	}

}
