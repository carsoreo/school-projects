/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.manager.TestPlanManager;

/**
 * Test cases for TestPlanReader
 * @author clorejol
 *
 */
class TestPlanReaderTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.io.TestPlanReader#TestPlanReader()}.
	 */
	@Test
	void testTestPlanReader() {
		assertDoesNotThrow(() -> new TestPlanReader());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.io.TestPlanReader#readTestPlansFile(java.io.File)}.
	 */
	@Test
	void testReadTestPlansFile() {
		TestPlanManager m = new TestPlanManager();
		m.loadTestPlans(new File("test-files/junit_writer_test_expected.txt"));
		assertEquals(m.getTestPlanNames()[0], "Failing Tests");
		assertThrows(IllegalArgumentException.class, () ->  m.loadTestPlans(new File("test-files/doesnotexist.txt")));
		
		//Will pass once errors in getTestPlanNames are fixed.
		assertEquals(m.getTestPlanNames()[1], "MyTest");
		
		m.clearTestPlans();
		assertThrows(IllegalArgumentException.class, () -> m.loadTestPlans(new File("test-files/test-plans3.txt")));
		assertEquals(m.getTestPlanNames().length, 1);
		
		m.clearTestPlans();
		m.loadTestPlans(new File("test-files/test-plans8.txt"));
		m.setCurrentTestPlan("WolfScheduler");
		assertThrows(IndexOutOfBoundsException.class, () -> m.getCurrentTestPlan().getTestCase(0));
		
	}
	

}
