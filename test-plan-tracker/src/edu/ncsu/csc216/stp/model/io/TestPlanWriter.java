/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.PrintStream;


import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Class which handles writing a given SortedList of TestPlan objects to a given File.
 * Used by the TestPlanManager class to execute it's saveTestPlan() method.
 * 
 * @author clorejol
 *
 */
public class TestPlanWriter {
	
	/**
	 * Constructor for TestPlanWriter which is left empty.
	 */
	public TestPlanWriter() {
		//Intentionally left empty
	}
	
	/**
	 * Writes the contents of a give parameter SortedList of TestPlans with a certain format to
	 * a given File. Handles any errors and throws an exception if there are any problems.
	 * 
	 * @param file File where the contents of plans will be saved to.
	 * @param plans SortedList of TestPlan objects to be parsed into Strings to be saved.
	 * @throws IllegalArgumentException if there are any errors or exceptions saving the file.
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> plans) {
		try {
			PrintStream fileWriter = new PrintStream(file);
			int size = plans.size();
			for (int i = 0; i < size; i++) {
	    		fileWriter.print("! " + plans.get(i).getTestPlanName() + "\n");
	    		int sizeCase = plans.get(i).getTestCases().size();
	    		for (int j = 0; j < sizeCase; j++) {
	    			fileWriter.print(plans.get(i).getTestCase(j).toString());
	    		}
	    	}
	    	
	    	fileWriter.close();
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		
	}
	
}
