/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Test cases for TestPlanWriter
 * @author clorejol
 *
 */
class TestPlanWriterTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.io.TestPlanWriter#TestPlanWriter()}.
	 */
	@Test
	void testTestPlanWriter() {
		assertDoesNotThrow(() -> new TestPlanWriter());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.io.TestPlanWriter#writeTestPlanFile(java.io.File, edu.ncsu.csc216.stp.model.util.ISortedList)}.
	 */
	@Test
	void testWriteTestPlanFile() {
		TestPlan t = new TestPlan("MyTest");
		TestCase tc = new TestCase("id", "type", "desc", "expResults");
		TestCase tc2 = new TestCase("id2", "type", "desc", "expResults");
		t.addTestCase(tc);
		t.addTestCase(tc2);
		t.addTestResult(0, false, "I forgor");
		
		SortedList<TestPlan> sl = new SortedList<TestPlan>();
		sl.add(t);
		
		File f = new File("test-files/junit_writer_test.txt");
		assertDoesNotThrow(() -> TestPlanWriter.writeTestPlanFile(f, sl));
		assertDoesNotThrow(() -> checkFiles("test-files/junit_writer_test.txt", "test-files/junit_writer_test_expected.txt"));
		assertThrows(IllegalArgumentException.class, () -> TestPlanWriter.writeTestPlanFile(null, sl));
		
	}
	
	/**
	 * Helper method to compare files
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
