package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Class which manages all of the test plans and the failing test list. Manages
 * the current test plan, if the class has been changed, the failing test list,
 * and a list of test plans. Can load test plans, add test plans, set the current test plan
 * get the current test plan, get failing tests, get test plan names, edit test plans,
 * remove test plans, add test cases, add test results, and clear the list of test plans.
 * 
 * @author clorejol
 *
 */
public class TestPlanManager {
	
	/** Boolean used to check if the TestPlanManager has been changed */
	private boolean isChanged;
	
	/** Value used to hold a FailingTestList object */
	private FailingTestList failingTestList;
	
	/** Value used to hold an AbstractTestPlan object for the current test plan */
	private AbstractTestPlan currentTestPlan;
	
	/** ISortedList of TestPlan objects uses to hold TestPlan objects manipulated by the manager */
	private ISortedList<TestPlan> testPlans; 
	
	/**
	 * Constructor for TestPlanManager. Does so by setting isChanged to false,
	 * constructing a FailingTestList for failingTestlist, and constructing
	 * an AbstractTestPlan for currentTestPlan.
	 */
	public TestPlanManager() {
		clearTestPlans();		
	}
	
	/**
	 * Loads test plans into the manager by accessing a file. A test plan is
	 * not added if there is a plan with the same name already in testPlans.
	 * 
	 * @param testPlanFile File to be read from to load TestPlans into testPlans.
	 */
	public void loadTestPlans(File testPlanFile) {
		testPlans = TestPlanReader.readTestPlansFile(testPlanFile);
		setCurrentTestPlan("Failing Tests");
		isChanged = true;
	}
	
	/**
	 * Saves test plans held by manager in testPlans to a text file and sets isChanged
	 * to false.
	 * 
	 * @param testPlanFile File used to save contents of testPlans to.
	 */
	public void saveTestPlans(File testPlanFile) {
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		isChanged = false;
	}
	
	/**
	 * Adds a new TestPlan object to testPlans by constructing a TestPlan based
	 * on the given String parameter to use as name. Throws errors if the given
	 * String is the same as the name of any TestPlan already in testPlans or if
	 * the String is the same as the name of the failing test list.
	 * 
	 * @param testPlanName String to be used to set the name of the constructed TestPlan.
	 * @throws IllegalArgumentException if the parameter value is the same as the required name for the
	 *  failing test list or a duplicate name. Also will throw any exception that the SortedList class throws.
	 */
	public void addTestPlan(String testPlanName) {
		if (testPlanName.equals(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equalsIgnoreCase(testPlanName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		TestPlan newTestPlan = new TestPlan(testPlanName);
		
		testPlans.add(newTestPlan);
		
		setCurrentTestPlan(newTestPlan.getTestPlanName());
		isChanged = true;
		
	}
	
	/**
	 * Returns the names of the TestPlan objects in testPlans as an array of Strings
	 * with the name of the failing test list first.
	 * 
	 * @return names String array used to hold the names of the test plans.
	 */
	public String[] getTestPlanNames() {
		String[] names = new String[testPlans.size() + 1];
		
		names[0] = FailingTestList.FAILING_TEST_LIST_NAME;
		
		for(int i = 0; i < testPlans.size(); i++) {
			names[i + 1] = testPlans.get(i).getTestPlanName();
		}
		
		return names;
		
	}
	
	/**
	 * A private helper method which is used to rebuild the failing test list
	 * to make sure that the failing test list is always updated when one of
	 * the tests in it is updated or new tests get added to it.
	 * 
	 * @throws Exception for any exception that the SwapList or SortedList class throws.
	 */
	private void getFailingTests() {
		failingTestList = new FailingTestList();
		for (int i = 0; i < testPlans.size(); i++) {
			for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
				if (!testPlans.get(i).getTestCases().get(j).isTestCasePassing()) {
					failingTestList.addTestCase(testPlans.get(i).getTestCases().get(j));
				}
			}
			
		}
	}

	/**
	 * Getter method for isChanged which returns the boolean value of isChanged.
	 * 
	 * @return the isChanged value to be returned.
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Getter method for currentTestPlan which returns the AbstractTestPlan
	 * or FailingTestList held in currentTestPlan .
	 * 
	 * @return the currentTestPlan of TestPlanManager to be returned.
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}

	/**
	 * Sets the currentTestPlan to the AbstractTestPlan with the same name as the String passed in. 
	 * If the test plan is not found, sets failingTestList as the currentTestPlan.
	 * 
	 * @param testPlanName the new name to try to be set.
	 */
	public void setCurrentTestPlan(String testPlanName) {
		int size = testPlans.size();
		for(int i = 0; i < size; i++) {
			if(testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				currentTestPlan = testPlans.get(i);
				return;
			}
		}
		getFailingTests();
		currentTestPlan = failingTestList;
	}
	
	/**
	 * Edits the currentTestPlan name. Uses a String parameter to set the name. If
	 * the parameter is the same as the name of a test plan already in the list, the same
	 * name as the failing test list, or if the currentTestPlan is currently failingTestList.
	 * 
	 * @param testPlanName String used to set the name of the currentTestPlan
	 * @throws IllegalArgumentException if the test plan to be edited is the failing test list or
	 * if the name is a duplicate TestPlan name or if the given name is the same name
	 * as the failing test list. Exception for any exception that the SortedList class throws.
	 */
	public void editTestPlan(String testPlanName) {
		if (currentTestPlan.equals(failingTestList)) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		
		if (testPlanName.equals(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		int size = testPlans.size();
		for(int i = 0; i < size; i++) {
			if(testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		currentTestPlan.setTestPlanName(testPlanName);
		isChanged = true;
		
	}
	
	/**
	 * Removes the currentTestPlan from the testPlans list. Throws errors if the currentTestPlan
	 * is the failingTestList. If removal is successful, sets the currentTestPlan to failingTestList
	 * and changes isChanged to true.
	 * 
	 * @throws IllegalArgumentException if the current test list is the failing test list.
	 */
	public void removeTestPlan() {
		if (currentTestPlan.equals(failingTestList)) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		else {
			//Find index of test plan to be removed
			for(int i = 0; i < testPlans.size(); i++) {
				if(testPlans.get(i).getTestPlanName().equals(currentTestPlan.getTestPlanName())) {
					testPlans.remove(i);
					isChanged = true;
					setCurrentTestPlan(FailingTestList.FAILING_TEST_LIST_NAME);
				}
			}
			
			
		}
	}
	
	/**
	 * Adds a test case to the currentTestPlan's list of test cases. Checks if currentTestPlan
	 * is a TestPlan, if not does nothing. Calls getFailingTests to update the failing test list.
	 * If successful in adding the test case, isChanged is updated to true.
	 *
	 * @param t TestCase to be added to the testCases list of the test plan.
	 */
	public void addTestCase(TestCase t) {
		if (currentTestPlan.equals(failingTestList)) {
			//If current test plan is not a test plan (is a failing test list) do nothing
			return;
		}
		currentTestPlan.addTestCase(t);
		if ("FAIL".equals(t.getStatus())) {
			getFailingTests();
			isChanged = true;
		}
	}
	
	/**
	 * Adds a test result to a test case using a given integer as an index, boolean to determine
	 * whether the test passed or failed, and a String to hold the actual results of the test. Also
	 * updates failingTestList by calling getFailingTests() if the test is failing.
	 * 
	 * @param idx integer where the test result is added on to the test case.
	 * @param passing boolean which determines whether the test case is passing or failing.
	 * @param actualResult String which holds the details of the actual results of executing the test case.
	 */
	public void addTestResult(int idx, boolean passing, String actualResult) {
		currentTestPlan.addTestResult(idx, passing, actualResult);
		if (!passing) {
			getFailingTests();
		}
	}
	
	/**
	 * Clears the entire TestPlanManager by setting testPlans and failingTestList to their respective empty
	 * lists. Also sets currentTestPlan to the empty failingTestList and changes isChanged to false.
	 */
	public void clearTestPlans() {
		testPlans = new SortedList<TestPlan>();
		failingTestList = new FailingTestList();
		currentTestPlan = failingTestList;
		isChanged = false;
	}
	
	
	
}
