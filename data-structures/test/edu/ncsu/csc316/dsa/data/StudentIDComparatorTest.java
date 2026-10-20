package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This code has code that was initially given from the
 * Workshop 1 assignment page from the downloadable zip-file.
 * Tests the StudentIDComparator class
 * @author clorejol
 *
 */
public class StudentIDComparatorTest {
	
	/** Student object designated as one*/
	private Student sOne;
	
	/** Student object designated as two*/
	private Student sTwo;
	
	/** Student object designated as three*/
	private Student sThree;
	
	/** Student object designated as four*/
	private Student sFour;
	
	/** Student object designated as five*/
	private Student sFive;
	
	/** Value to hold comparator object to determine order*/
	private StudentIDComparator comparator;
	
	/**
	 * Sets up student objects and comparator before following tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}
	
	/**
	 * Tests the compare method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		
		assertTrue(comparator.compare(sOne, sThree) < 0);
		assertTrue(comparator.compare(sOne, sFour) < 0);
		
		assertFalse(comparator.compare(sFive, sFour) < 0);
		assertFalse(comparator.compare(sFive, sThree) < 0);
		
		assertSame(comparator.compare(sOne, sOne), 0);
	}


}
