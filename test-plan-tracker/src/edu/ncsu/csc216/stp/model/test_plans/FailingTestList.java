package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Subclass of AbstractTestPlan that can add, set, get test cases as a 2D array, and
 * clear itself of test cases. Used to hold all test cases which are failing.
 * Overrides methods found in AbstractTestPlan to employ alternate functionality.
 * 
 * @author clorejol
 *
 */
public class FailingTestList extends AbstractTestPlan {
	
	/** String used as the name for the failing test list */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Constructs the failing test list using a constant as
	 * the name of the list.
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 * Overrides a method used in AbstractTestPlan.
	 * Adds a given test case to the list of failing tests. Does so by
	 * checking if the test is failing. If failing, errors are thrown.
	 * If not, the test is then added to the list of failing tests.
	 * 
	 * @param t TestCase to be added to failing test list.
	 * @throws IllegalArgumentException if test is not failing or there
	 * is an error with SwapList.
	 */
	@Override
	public void addTestCase(TestCase t) {
		if ("FAIL".equals(t.getStatus())) {
			super.addTestCase(t);
		}
		else {
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
		
	}
	
	/**
	 * Overrides a method used in AbstractTestPlan.
	 * Sets the name of the failing test list, but checks if the parameter
	 * is the designated name for a failing test list. If not, errors are thrown.
	 * 
	 * @param testPlanName String to be set as the name of the failing
	 * test list.
	 * @throws IllegalArgumentException if the parameter value does not
	 * match the required name for the failing test list.
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		String expected = "FAILING TESTS";
		if (!expected.equals(testPlanName.toUpperCase())) {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
		super.setTestPlanName(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 * Overrides an abstract method in AbstractTestPlan.
	 * Converts the testCases SwapList
	 * into a 2D array of Strings which are then returned.
	 * 
	 * @return cases 2D array of Strings converted from TestCase
	 * objects found in the testCases SwapList.
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		int size = getTestCases().size();
		String[][] cases = new String[size][3];
		for(int i = 0; i < size; i++) {
			cases[i][0] = getTestCases().get(i).getTestCaseId();
			cases[i][1] = getTestCases().get(i).getTestType();
			try {
				cases[i][2] = getTestCases().get(i).getTestPlan().getTestPlanName();
			}
			catch (NullPointerException e) {
				cases[i][2] = "";
			}
			
		}
		return cases;
	}
	
	/**
	 * Clears the list of failing tests by removing every
	 * test case.
	 */
	public void clearTests() {
		//int size = getTestCases().size();
		
		while(getTestCases().size() != 0) {
			getTestCases().remove(0);
		}
	}

}
