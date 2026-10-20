/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test cases for AbstractTestPlan
 * @author clorejol
 *
 */
class AbstractTestPlanTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#hashCode()}.
	 */
	@Test
	void testHashCode() {
		AbstractTestPlan t = new TestPlan("MyTest");
		
		//This may end up changing and I don't know if this is the best way to test this.
		assertEquals(-1976173411, t.hashCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#AbstractTestPlan(java.lang.String)}.
	 */
	@Test
	void testAbstractTestPlan() {
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#setTestPlanName(java.lang.String)}.
	 */
	@Test
	void testSetTestPlanName() {
		AbstractTestPlan t = new TestPlan("MyTest");
		assertThrows(IllegalArgumentException.class, () -> t.setTestPlanName(""));
		assertThrows(IllegalArgumentException.class, () -> t.setTestPlanName(null));
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#addTestCase(TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		AbstractTestPlan t = new TestPlan("MyTest");
		TestCase tc = new TestCase("id", "type", "description", "expected");
		assertThrows(NullPointerException.class, () -> t.addTestCase(null));
		assertDoesNotThrow(() -> t.addTestCase(tc));
		assertEquals(tc, t.getTestCase(0));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#removeTestCase(int)}.
	 */
	@Test
	void testRemoveTestCase() {
		AbstractTestPlan t = new TestPlan("MyTest");
		TestCase tc = new TestCase("id", "type", "description", "expected");
		assertThrows(NullPointerException.class, () -> t.addTestCase(null));
		assertDoesNotThrow(() -> t.addTestCase(tc));
		assertEquals(tc, t.getTestCase(0));
		TestCase returnedCase = t.removeTestCase(0);
		assertEquals(tc, returnedCase);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#getTestCases()}.
	 */
	@Test
	void testGetTestcase() {
		AbstractTestPlan t = new TestPlan("MyTest");
		TestCase tc = new TestCase("id", "type", "description", "expected");
		assertDoesNotThrow(() -> t.addTestCase(tc));
		assertEquals(tc, t.getTestCases().get(0));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#getNumberOfFailingTests()}.
	 */
	@Test
	void testGetNumberOfFailingTests() {
		AbstractTestPlan t = new TestPlan("MyTest");
		TestCase tc = new TestCase("id", "type", "description", "expected");
		TestCase tc2 = new TestCase("id2", "type", "description", "expected");
		TestCase tc3 = new TestCase("id3", "type", "description", "expected");
		
		//Should pass after TestCase implementation is finished
		assertDoesNotThrow(() -> t.addTestCase(tc));
		assertEquals(1, t.getNumberOfFailingTests());
		assertDoesNotThrow(() -> t.addTestCase(tc2));
		assertEquals(2, t.getNumberOfFailingTests());
		assertDoesNotThrow(() -> t.addTestCase(tc3));
		assertEquals(3, t.getNumberOfFailingTests());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#addTestResult(int, boolean, java.lang.String)}.
	 */
	@Test
	void testAddTestResult() {
		AbstractTestPlan t = new TestPlan("MyTest");
		TestCase tc = new TestCase("id", "type", "description", "expected");
		t.addTestCase(tc);
		assertThrows(IllegalArgumentException.class, () -> t.addTestResult(0, false, null));
		
		assertDoesNotThrow(() -> t.addTestResult(0, false, "Wow it worked"));
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		AbstractTestPlan t = new TestPlan("MyTest");
		AbstractTestPlan t1 = new TestPlan("MyTest");
		
		assertTrue(t.equals(t1));
	}

}
