/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * This class represents a Test Case object. It maintains information about its Id, test type,
 * description, expected results, associated TestPlan, and a log of TestResults. It has methods to 
 * get and set these fields, add a TestResult to the log, return if it is passing as a boolean or
 * String, return the log of TestResults as a String, and return a String for the whole TestCase.
 * 
 * @author Cameron Seitz
 *
 */

public class TestCase {
	
	/** String tracking a TestCase's ID */
	private String testCaseId;
	
	/** String tracking a TestCase's test type */
	private String testType;
	
	/** String tracking a TestCase's description */
	private String testDescription;
	
	/** String tracking a TestCase's expected results */
	private String expectedResults;
	
	/** String tracking a TestCase's associated TestPlan */
	private TestPlan testPlan;
	
	/** A list of test results for a TestCase using custom Log class */
	private Log<TestResult> testResults;
	
	/**
	 * Constructs a TestCase with a TestCase id, test type, test description, and expected results.
	 * The testResults field is constructed as an empty Log and testPlan is set to null. 
	 * Throws IlleglaArgumentException with the message "Invalid test information." 
	 * if any of the parameters are empty strings or null.
	 * @param testCaseId the TestCase's ID
	 * @param testType the TestCase's type
	 * @param testDescription the TestCase's description
	 * @param expectedResults the TestCase's expected results
	 * @throws IllegalArgumentException if any of the parameters are empty string or null
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		testResults = new Log<TestResult>();
		testPlan = null;
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
	}
	
	/**
	 * Returns the TestCase's Id.
	 *
	 * @return the TestCase's Id
	 */
	public String getTestCaseId() {
		return testCaseId;
	}
	
	/**
	 * Sets the TestCase's Id. Throws IllegalArgumentException with the message 
	 * "Invalid test information." if the parameter is null or empty string.
	 * @param testCaseId the TestCase's ID
	 * @throws IllegalArgumentException with the message "Invalid test information."
	 * 		if the parameter is null or empty string.
	 */
	public void setTestCaseId(String testCaseId) {
		emptyNullCheck(testCaseId);
		this.testCaseId = testCaseId;
	}
	
	/**
	 * Returns the TestCase's test type.
	 *
	 * @return the TestCase's test type
	 */
	public String getTestType() {
		return testType;
	}
	
	/**
	 * Sets the TestCase's test type. Throws IllegalArgumentException with the message 
	 * "Invalid test information." if the parameter is null or empty string.
	 * @param testType the TestCase's type
	 * @throws IllegalArgumentException with the message "Invalid test information."
	 * 		if the parameter is null or empty string.
	 */
	public void setTestType(String testType) {
		emptyNullCheck(testType);
		this.testType = testType;
	}
	
	/**
	 * Returns the TestCase's description. 
	 *
	 * @return the TestCase's description
	 */
	public String getTestDescription() {
		return testDescription;
	}
	
	/**
	 * Sets the TestCase's description. Throws IllegalArgumentException with the message 
	 * "Invalid test information." if the parameter is null or empty string.
	 * @param testDescription the TestCase's description
	 * @throws IllegalArgumentException with the message "Invalid test information."
	 * 		if the parameter is null or empty string.
	 */
	public void setTestDescription(String testDescription) {
		emptyNullCheck(testDescription);
		this.testDescription = testDescription;
	}
	
	/**
	 * Returns the TestCase's expected results. 
	 *
	 * @return the TestCase's expected results
	 */
	public String getExpectedResults() {
		return expectedResults;
	}
	
	/**
	 * Sets the TestCase's expected results. Throws IllegalArgumentException with the message 
	 * "Invalid test information." if the parameter is null or empty string.
	 * @param expectedResults the expected results for a TestCase
	 * @throws IllegalArgumentException with the message "Invalid test information."
	 * 		if the parameter is null or empty string.
	 */
	public void setExpectedResults(String expectedResults) {
		emptyNullCheck(expectedResults);
		this.expectedResults = expectedResults;
	}
	
	/**
	 * Creates a TestResult object with a boolean passing status and String for the actual results after a test.
	 * Throws an IllegalArgumentException if a TestResult cannot be constructed.  
	 * @param passing the passing status of a TestResult
	 * @param actualResults the results from a test
	 * @throws IllegalArgumentException if a TestResult cannot be constructed
	 */
	public void addTestResult (boolean passing, String actualResults) {
		
		try {
			TestResult newResult = new TestResult(passing, actualResults);
			testResults.add(newResult);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		
	}
	
	/**
	 * Returns true if the last TestResult in the Log is passing and false
	 * if the last TestResult in the Log is failing or if there are no TestResults in the Log.  
	 *
	 * @return true if the last TestResult in the Log is passing, false otherwise
	 */
	public boolean isTestCasePassing() {
		if (testResults == null || testResults.size() == 0) {
			return false;
		}
		
		return testResults.get(testResults.size() - 1).isPassing();
		
	}
	
	/**
	 * Returns the TestCase's passing status ("PASS" or "FAIL").
	 *
	 * @return the TestCase's passing status ("PASS" or "FAIL")
	 */
	public String getStatus() {
		if (isTestCasePassing()) {
			return TestResult.PASS;
		}
		else {
			return TestResult.FAIL;
		}
	}
	
	/**
	 * Returns the testResults Log as a String with a leading "-" at the start
	 * and a "\n" at the end using TestResult's toString method.
	 *
	 * @return the testResults Log as a String
	 */
	public String getActualResultsLog() {
		String str = "";
		for (int i = 0; i < testResults.size(); i++) {
			str += "- ";
			str += testResults.get(i).toString();
			str += '\n';
		}
		return str;
	}
	
	/**
	 * Sets the testPlan field with the testPlan passed in. If the parameter is 
	 * null, an IllegalArgumentException is thrown with the message 
	 * "Invalid test plan."
	 *
	 * @param testPlan the testPlan to set the field with
	 * @throws IllegalArgumentException if the testPlan passed in is null
	 */
	public void setTestPlan(TestPlan testPlan) {
		if (testPlan == null)
			throw new IllegalArgumentException("Invalid test plan.");
		this.testPlan = testPlan;
	}
	
	/**
	 * Returns the TestCase's associated TestPlan
	 *
	 * @return the TestCase's associated TestPlan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}
	
	/**
	 * Returns the TestCase as a String for printing to a file. Uses the
	 * getActualResultsLog() method as part of the String.
	 * 
	 * Starts with a # to represent test cases. Test Cases have a name 
	 * and the type of test case. The next line starts with * to represent 
	 * the test description OR the expected results. 
	 * Test Cases will have both a test description and expected results (exactly two * items). 
	 * These items may span multiple lines. 
	 * Lines representing test results in the results log 
	 * start with -. These lines have either PASS or FAIL, followed by a colon and then a 
	 * record of the actual results of execution. The actual results of execution can span multiple lines.
	 * Any text between two lines starting with #, *, and - or between two lines starting with * and # 
	 * or - or end of file is the expected results. This text may include any character except for ! or # or * or -
	 */
	@Override
	public String toString() {
		String str = "";
		str += "# " + testCaseId + "," + testType + '\n';
		str += "* " + testDescription + '\n';
		str += "* " + expectedResults + '\n';
		str += getActualResultsLog();
		return str;
	}
	
	/**
	 * Private helper method for the setter methods. Throws IllegalArgumentException with the message 
	 * "Invalid test information." if the parameter to be used is null or an empty string.
	 * @param toBeChecked the parameter of a setter method to be checked
	 * @throws IllegalArgumentException if the parameter to be checked is null or empty string
	 */
	private void emptyNullCheck(String toBeChecked) {
		if (toBeChecked == null || toBeChecked.length() == 0)
			throw new IllegalArgumentException("Invalid test information.");
	}
	
}
