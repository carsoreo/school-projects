/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

/**
 * TestResult represents the results from an execution of TestCase.
 * It maintains information about its passing status and a String
 * of actual results from the testing. It has methods to get and set
 * these pieces of information. 
 * @author Cameron Seitz
 *
 */
public class TestResult {
	/** Public constant for a passing test */
	public static final String PASS = "PASS";
	
	/** Public constant for a failing test */
	public static final String FAIL = "FAIL";
	
	/** boolean tracking if a test result is passing */
	private boolean passing = false;
	
	/** test results from running a test */
	private String actualResults;
	
	
	/**
	 * Constructs a TestResult with a String for the actual results
	 * and a boolean if it is passing or not. Throws IllegalArgumentException
	 * if the string is null or empty with the message "Invalid test results."
	 * @param passing the passing status of the TestResult
	 * @param actualResults the details about the actual result from the test
	 * @throws IllegalArgumentException if the actual results are empty string or null
	 */
	public TestResult(boolean passing, String actualResults) {
		setPassing(passing);
		setActualResults(actualResults);
	}
	
	/**
	 * Returns the actual results string.
	 * @return the actual results string
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Private helper method to check for a valid actualResults string and set it.
	 * Throws an IllegalArgumentException if actual results is null or empty string with
	 * the exception message "Invalid test results."
	 * @param actualResults the details about the actual results of the test
	 * @throws IllegalArgumentException if the actual results are empty string or null
	 */
	private void setActualResults(String actualResults) {
		if (actualResults == null || actualResults.length() == 0)
			throw new IllegalArgumentException("Invalid test results.");
		this.actualResults = actualResults;
	}
	
	/**
	 * Returns the passing status of the TestResult.
	 * @return the passing status of the TestResult
	 */
	public boolean isPassing() {
		return passing;
	}
	
	/**
	 * Private helper method to set the passing status for a TestResult.
	 * @param passing the passing status of the TestResult
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}
	
	/**
	 * Returns a string representation of a TestResult for listing in the GUI and printing to a file.
	 * If a test is passing, "PASS" is printed followed by a colon, then a space, and then the actual results. 
	 * If the test is failing, "FAIL" is printed followed by a colon, then a space, and then the actual results.
	 */
	@Override
	public String toString() {
		String str = "";
		if (passing) {
			
			str += PASS;
		}
		else {
			str += FAIL;
		}
		str += ": " + actualResults;
		return str;
	}
}
