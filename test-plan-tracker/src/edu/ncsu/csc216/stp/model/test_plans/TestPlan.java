package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Subclass of AbstractTestPlan which implements Comparable. Manages the creation of
 * a general TestPlan and can handle adding, getting test cases as a 2D aray,
 * and comparing TestPlan objects. Overrides methods found in AbstractTestPlan to employ
 * alternate functionality.
 * 
 * @author clorejol
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	/**
	 * Sets the name of the TestPlan object by calling the constructor
	 * of the parent class and constructs a TestPlan. Takes testPlanName
	 * as a String parameter to set the name.
	 * 
	 * @param testPlanName name of the TestPlan to be set.
	 * @throws IllegalArgumentException if the testPlanName is the same as
	 * 		FailingTestList.FAILING_TEST_LIST_NAME case insensitive
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		if (testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}
	
	/**
	 * Overrides method from Abstract class. Converts the testCases SwapList
	 * into a 2D array of Strings which are then returned.
	 * 
	 * @return cases 2D String array which holds test cases as Strings
	 * @throws Exception for any exception that the SwapList class throws.
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		int size = getTestCases().size();
		String[][] cases = new String[size][3];
		for(int i = 0; i < size; i++) {
			cases[i][0] = getTestCases().get(i).getTestCaseId();
			cases[i][1] = getTestCases().get(i).getTestType();
			cases[i][2] = getTestCases().get(i).getStatus();
		}
		return cases;
	}
	
	/**
	 * Overrides the method form AbstractTestPlan. Instead adds test cases
	 * via a call to super and by setting the given TestCase parameter's TestPlan
	 * to the currentTestPlan.
	 * 
	 */
	@Override
	public void addTestCase(TestCase t) {
		super.addTestCase(t);
		t.setTestPlan(this);
	}
	
	/**
	 * Compares two TestPlans, one being the parameter. Returns an integer
	 * based on the result of the comparison. Uses the name of the TestPlans
	 * as a way to compare the two.
	 * 
	 * @param plan TestPlan to be parsed for a name and compared.
	 * @return value integer returned based on whether or not the compared
	 * TestPlan objects are the same or not based on their name.
	 */
	@Override
	public int compareTo(TestPlan plan) {
		
		String name1 = getTestPlanName();
		String name2 = plan.getTestPlanName();
		
		
		name1 = name1.toUpperCase();
		name2 = name2.toUpperCase();
		
		if (name1.compareTo(name2) < 0) {
			return -1;
		}
		else if (name1.compareTo(name2) > 0) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
	
}
