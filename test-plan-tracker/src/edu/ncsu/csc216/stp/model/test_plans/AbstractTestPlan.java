/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import java.util.Objects;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * Abstract class which has a state of a list of testCases using the custom
 * ISwapList utility. Also has a name of the test plan. Can modify the name of
 * the test plan and add, remove, and return the test cases within it. Additionally,
 * can generate hash code and check if two objects are equal. Contains an Abstract
 * method which is used in FailingTestList and TestPlan.
 * 
 * @author clorejol
 */
public abstract class AbstractTestPlan {
	
	/** Value which holds the name of the test plan */
	private String testPlanName;
	
	/** Value which holds the test cases of the test plan */
	private ISwapList<TestCase> testCases;
	
	/**
	 * Constructor for AbstractTestPlan. Takes a String as a parameter
	 * to be set to testPlan name. Also creates a new SwapList for test
	 * cases. Handles errors for invalid input.
	 * 
	 * @param testPlanName String to be set as the test plan name.
	 * @throws IllegalArgumentException if the given String is null or empty.
	 */
	public AbstractTestPlan(String testPlanName) {
		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		setTestPlanName(testPlanName);
		testCases = new SwapList<TestCase>();
	}
	
	/**
	 * Sets the name of the test plan by taking a String to be set
	 * to testPlanName. Handles errors for an invalid input.
	 * 
	 * @param testPlanName String to be set as the test plan name.
	 * @throws IllegalArgumentException if the given String is null or empty.
	 */
	public void setTestPlanName(String testPlanName) {
		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.testPlanName = testPlanName;
	}

	/**
	 * Returns the current name of the test plan.
	 * 
	 * @return the testPlanName name of the test plan.
	 */
	public String getTestPlanName() {
		return testPlanName;
	}
	
	/**
	 * Returns the current value held by testCases.
	 * 
	 * @return cases SwapList of TestCase objects of the AbstractTestPlan
	 */
	public ISwapList<TestCase> getTestCases()  {
		return testCases;
	}
	
	/**
	 * Adds a test to the test plan by adding a TestCase object to the
	 * testCases SwapList. Throws exceptions if there are any errors using SwapList.
	 * 
	 * @param test TestCase object to be added to the testCases.
	 */
	public void addTestCase(TestCase test) {
		testCases.add(test);
	}
	
	/**
	 * Removes a TestCase object from the testCases list using a given integer
	 * to be used as an index to find the TestCase to remove. Returns the removed
	 * TestCase as well. Throws exceptions if there are any errors using SwapList.
	 * 
	 * @param idx int used as an index to find TestCase to remove.
	 * @return removedCase TestCase object which was removed from testCases.
	 */
	public TestCase removeTestCase(int idx) {
		return testCases.remove(idx);
	}
	
	/**
	 * Gets a TestCase object from the testCases list using a given integer
	 * to be used as an index to find the TestCase to get. Returns the removed
	 * TestCase. Throws exceptions if there are any errors using SwapList.
	 * 
	 * @param idx integer used as an index to find TestCase to get.
	 * @return case TestCase object which was gotten from testCases.
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
	}
	
	/**
	 * Counts the number of failing test cases. This is done using the testCases
	 * SwapList and using the method in TestCase to check if the test is failing.
	 * Throws exceptions if there are any errors using SwapList.
	 * 
	 * @return failingTests integer number of failing tests to be returned.
	 */
	public int getNumberOfFailingTests() {
		int size = testCases.size();
		int numberOfFailingTests = 0;
		for (int i = 0; i < size; i++) {
			if ("FAIL".equals(testCases.get(i).getStatus())) {
				numberOfFailingTests++;
			}
		}
		return numberOfFailingTests;
	}
	
	/**
	 * Adds a test result to a test case. Takes parameters for an integer which is used
	 * as an index to determine where to add the test result. Then uses a boolean to determine
	 * if the test is passing or failing and a String to hold the results of the test. Throws
	 * exceptions if there are any errors using SwapList.
	 * 
	 * @param idx integer used as an index of the test result.
	 * @param passing boolean value of the result of the either failing or passing test.
	 * @param actualResults String which documents details of the results of the test.
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		testCases.get(idx).addTestResult(passing, actualResults);
	}
	
	/**
	 * Abstract method which is overwritten by TestPlan and FailingTestList.
	 * Used to turn the SwapList testCase into a 2D array. Returns
	 * the 2D array created by parsing through testCaes. Throws
	 * exceptions if there are any errors using SwapList.
	 * 
	 * @return cases 2D array built using different aspect of Test Case objects
	 * parsed from testCases.
	 */
	public abstract String[][] getTestCasesAsArray();
	
	/**
	 * Method which overrides the default Java hashCode() method.
	 * Generates a hash code as an integer using testPlanName.
	 * 
	 * @return integer to be used as a hash code.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(testPlanName);
	}
	
	
	/**
	 * Method which overrides the default Java equals() method.
	 * Checks if any two given AbstractTestPlan objects are the same. Taking
	 * one AbstractTestPlan object as the parameter. If true, returns true. If
	 * false, returns false.
	 * 
	 * @return true if AbstractTestPlan objects are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		return testPlanName.equalsIgnoreCase(other.testPlanName);
	}
	

}
