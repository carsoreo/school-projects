/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Class which handles reading in the contents of a text file to build a SortedList of TestPlan objects.
 * Does so using three methods which all handle and construct different elements used to create TestPlan objects.
 * Used by the TestPlanManager class to execute it's loadTestPlan() method.
 * 
 * @author clorejol
 *
 */
public class TestPlanReader {
	
	/**
	 * Constructor for TestPlanReader which is left empty.
	 */
	public TestPlanReader() {
		//unused
	}
	
	/**
	 * Reads text from a given file and parses several parameters which are used to
	 * construct different element required to make a TestPlan object. Then puts those
	 * objects in a SortedList to be returned. Invalid test cases or plans are ignored.
	 * This is done using a helper method called processTestPlan.
	 * 
	 * @param file File to be read to create a list of TestPlan objects.
	 * @return planList ISortedList of TestPlan objects to be returned.
	 * @throws IllegalArgumentException if the file does not exist.
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File file) {
		SortedList<TestPlan> plans = new SortedList<TestPlan>();
		try {
			Scanner fileReader = new Scanner(new FileInputStream(file));
			
			if (!fileReader.next().equals("!")) {
				throw new IllegalArgumentException("Unable to load file.");
			}
			
			fileReader.useDelimiter("\\r?\\n?[!] ");
			while(fileReader.hasNext()) {
				String planBody = fileReader.next();
				
				TestPlan plan = processTestPlan(planBody);
				if (plan == null) {
					break;
				}
				else {
					try {
						plans.add(plan);
					}
					catch (IllegalArgumentException e) {  //duplicate
						//skip
					}
					
				}
			}
			fileReader.close();
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		return plans;
	}
	
	/**
	 * Private helper method for readTestPlansFile.
	 * Reads a given String parameter and from that String parses elements used
	 * to construct a TestPlan. Does this using a helper method called processTest.
	 * Also ignores invalid TestPlan parameters and skips to the next one.
	 * 
	 * @param testPlanString String given by another method to be parsed to create a TestPlan.
	 * @return plan TestPlan object to be returned after parsing a given String.
	 */
	private static TestPlan processTestPlan(String testPlanString) {
		try {
			Scanner planReader = new Scanner(testPlanString);
			String planLine = planReader.nextLine();
			if (planLine.contains("#")) {
				planReader.close();
				return null;
			}
			TestPlan plan = new TestPlan(planLine.trim());
			planReader.useDelimiter("\\r?\\n?[#] ");
			
			while(planReader.hasNext()) {
				try {
					TestCase testCase = processTest(plan, planReader.next());
					if (testCase == null) {
						break;
					}
					else {
						plan.addTestCase(testCase);
					}
					
				}
				catch(Exception e) {
					//ignore
				}
			}
			planReader.close();
			
			return plan;
			
			
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Private helper method for processTestPlan.
	 * Reads a given String parameter and from that String parses elements used
	 * to construct a TestCase. Also ignores invalid TestCase parameters and skips to the next one.
	 * 
	 * @param plan AbstractTestPlan that TestCase(s) are to be added to. //Do not know why this is needed right now
	 * @param s String to be parsed to create a TestCase from.
	 * @return case TestCase object to be returned after parsing a given String.
	 */
	private static TestCase processTest(AbstractTestPlan plan, String s) {
		try {
			Scanner bodyParser = new Scanner(s);
			Scanner headParser = new Scanner(bodyParser.nextLine());
			String cleanDes = null;
			String cleanExp = null;
			
			headParser.useDelimiter(",");
			String id = headParser.next();
			String testType = headParser.next();
			headParser.close();
			
			try {
				
				//Expected and description scanner
				bodyParser.useDelimiter("\\r?\\n?[*] ");
				for (int i = 0; i < 2; i++) {
					 
					 if (i == 0 ) {
						 String description = bodyParser.next();
						 cleanDes = description.replaceAll("\r", "");
					 }
					 if (i == 1) {
						 String expected = bodyParser.next();
						 cleanExp = expected.replaceAll("\r", "");
					 }
				}
				bodyParser.close();
				
				String[] expectedAndActualResults = cleanExp.split("\\r?\\n?[-] ");
				
				TestCase testCase = new TestCase(id, testType, cleanDes, expectedAndActualResults[0]);
				TestPlan planCast = (TestPlan) plan;
				testCase.setTestPlan(planCast);
				
				for (int i = 1; i < expectedAndActualResults.length; i++) {
					boolean testResultsStatus = false;
					String result = expectedAndActualResults[i];
					if ("FAIL".equals(result.substring(0, 4))) {
						testResultsStatus = false;
					}
					else if ("PASS".equals(result.substring(0, 4))) {
						testResultsStatus = true;
					}
					else {
						return null;
					}
					testCase.addTestResult(testResultsStatus, result.substring(6).trim());
				}
				
				return testCase;
			}
			catch(Exception e) {
			}
		}
		catch(Exception e) {
			return null;
		}
		return null;
	}
}

