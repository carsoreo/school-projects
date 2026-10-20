/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;


/**
 * Test cases for TestPlanManager
 * @author clorejol
 *
 */
class TestPlanManagerTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#TestPlanManager()}.
	 */
	@Test
	void testTestPlanManager() {
		TestPlanManager t = new TestPlanManager();
		
		//Check only failing test list
		assertEquals(t.getTestPlanNames().length, 1);
		
		//Check failing test list is curr plan
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "Failing Tests");
		
		//Make sure is changed is false
		assertFalse(t.isChanged());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#loadTestPlans(java.io.File)}.
	 */
	@Test
	void testLoadTestPlans() {
		TestPlanManager t = new TestPlanManager();
		
		assertDoesNotThrow(() -> t.loadTestPlans(new File("test-files/junit_writer_test_expected.txt")));
		assertEquals(t.getTestPlanNames()[0], "Failing Tests");
		assertEquals(t.getTestPlanNames()[1], "MyTest");
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "Failing Tests");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#saveTestPlans(java.io.File)}.
	 */
	@Test
	void testSaveTestPlans() {
		TestPlanManager t = new TestPlanManager();
		assertDoesNotThrow(() -> t.loadTestPlans(new File("test-files/junit_writer_test_expected.txt")));
		assertDoesNotThrow(() -> t.saveTestPlans(new File("test-files/junit_save_test_actual.txt")));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#addTestPlan(java.lang.String)}.
	 */
	@Test
	void testAddTestPlan() {
		TestPlanManager t = new TestPlanManager();
		
		assertThrows(IllegalArgumentException.class, () -> t.addTestPlan("Failing Tests"));
		assertDoesNotThrow(() -> t.addTestPlan("My Test Plan"));
		
		assertEquals("Failing Tests", t.getTestPlanNames().clone()[0]);
		assertEquals("My Test Plan", t.getTestPlanNames().clone()[1]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#getTestPlanNames()}.
	 */
	@Test
	void testGetTestPlanNames() {
		TestPlanManager t = new TestPlanManager();
		t.addTestPlan("TestPlan");
		t.addTestPlan("TestPlan2");
		t.addTestPlan("TestPlan3");
		
		assertEquals("Failing Tests", t.getTestPlanNames()[0]);
		assertEquals("TestPlan", t.getTestPlanNames()[1]);
		assertEquals("TestPlan2", t.getTestPlanNames()[2]);
		assertEquals("TestPlan3", t.getTestPlanNames()[3]);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#isChanged()}.
	 */
	@Test
	void testIsChanged() {
		TestPlanManager t = new TestPlanManager();
		
		assertFalse(t.isChanged());
		
		t.addTestPlan("TestPlan");
		
		assertTrue(t.isChanged());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#getCurrentTestPlan()}.
	 */
	@Test
	void testGetCurrentTestPlan() {
		TestPlanManager t = new TestPlanManager();
		
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "Failing Tests");
		
		t.addTestPlan("TestPlan");
		
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "TestPlan");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#setCurrentTestPlan(edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan)}.
	 */
	@Test
	void testSetCurrentTestPlan() {
		TestPlanManager t = new TestPlanManager();
		t.addTestPlan("TestPlan1");
		t.addTestPlan("TestPlan2");
		t.addTestPlan("TestPlan3");
		t.addTestPlan("TestPlan4");
		
		assertDoesNotThrow(() -> t.setCurrentTestPlan("TestPlan3"));
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "TestPlan3");
		assertDoesNotThrow(() -> t.setCurrentTestPlan("TestPlan1"));
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "TestPlan1");
		assertDoesNotThrow(() -> t.setCurrentTestPlan("TestPlan4"));
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "TestPlan4");
		
		assertDoesNotThrow(() -> t.setCurrentTestPlan("TestPlan5"));
		assertEquals(t.getCurrentTestPlan().getTestPlanName(), "Failing Tests");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#editTestPlan(java.lang.String)}.
	 */
	@Test
	void testEditTestPlan() {
		TestPlanManager t = new TestPlanManager();
		
		assertThrows(IllegalArgumentException.class, () -> t.editTestPlan("MyTestPlan"));
		
		t.addTestPlan("TestPlan1");
		t.addTestPlan("TestPlan2");
		t.addTestPlan("TestPlan3");
		t.addTestPlan("TestPlan4");
		assertThrows(IllegalArgumentException.class, () -> t.editTestPlan("Failing Tests"));
		assertThrows(IllegalArgumentException.class, () -> t.editTestPlan("TestPlan1"));
		
		assertDoesNotThrow(() -> t.editTestPlan("MyPlan"));
		assertThrows(IllegalArgumentException.class, () -> t.editTestPlan("MyPlan"));
		assertDoesNotThrow(() -> t.editTestPlan("TestPlan 4"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#removeTestPlan()}.
	 */
	@Test
	void testRemoveTestPlan() {
		TestPlanManager t = new TestPlanManager();
		
		assertThrows(IllegalArgumentException.class, () -> t.removeTestPlan());
		
		t.addTestPlan("TestPlan1");
		t.addTestPlan("TestPlan2");
		t.addTestPlan("TestPlan3");
		t.addTestPlan("TestPlan4");
		
		assertDoesNotThrow(() -> t.removeTestPlan());
		assertEquals(4, t.getTestPlanNames().length);
		assertEquals("TestPlan3", t.getTestPlanNames()[3]);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#addTestCase(TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		TestPlanManager t = new TestPlanManager();
		TestCase tc = new TestCase("id", "type", "description", "expected");
		TestCase tc1 = new TestCase("id1", "type", "description", "expected");
		TestCase tc2 = new TestCase("id2", "type", "description", "expected");
		
		assertDoesNotThrow(() -> t.addTestCase(tc));
		
		t.addTestPlan("TestPlan1");
		t.addTestPlan("TestPlan2");
		t.addTestPlan("TestPlan3");
		t.addTestPlan("TestPlan4");
		
		assertDoesNotThrow(() -> t.addTestCase(tc));
		assertDoesNotThrow(() -> t.addTestCase(tc1));
		assertDoesNotThrow(() -> t.addTestCase(tc2));
		assertEquals(tc, t.getCurrentTestPlan().getTestCase(0));
		assertEquals(tc1, t.getCurrentTestPlan().getTestCase(1));
		assertEquals(tc2, t.getCurrentTestPlan().getTestCase(2));
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#addTestResult(int, boolean, java.lang.String)}.
	 */
	@Test
	void testAddTestResult() {
		TestPlanManager t = new TestPlanManager();
		t.addTestPlan("TestPlan1");
		t.addTestPlan("TestPlan2");
		t.addTestPlan("TestPlan3");
		t.addTestPlan("TestPlan4");
		
		TestCase tc = new TestCase("id", "type", "description", "expected");
		assertDoesNotThrow(() -> t.addTestCase(tc));
		
		assertDoesNotThrow(() -> t.addTestResult(0, false, "results"));
		assertEquals("- FAIL: results\n", t.getCurrentTestPlan().getTestCase(0).getActualResultsLog());
	}


}
