package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Some of this code was here originally given in Workshop 1.
 * Tests the GPAComparator class.
 * @author clorejol
 *
 */
public class StudentGPAComparatorTest {
	
	/** Student named to note being the first student by GPA */
	private Student sOne;
	
	/** Student named to note being the second student by GPA */
	private Student sTwo;
	
	/** Student named to note being the third student by GPA */
	private Student sThree;
	
	/** Student named to note being the fourth student by GPA */
	private Student sFour;
	
	/** Student named to note being the five student by GPA */
	private Student sFive;
	
	/** Comparator object used to determine order */
	private StudentGPAComparator comparator;
	
	/**
	 * Sets up student objects and comparator for following tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}
	
	/**
	 * Tests the compare method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);

		assertSame(comparator.compare(sOne, sOne), 0);
		
		assertFalse(comparator.compare(sOne, sThree) < 0);
		assertFalse(comparator.compare(sTwo, sThree) < 0);
		
		assertTrue(comparator.compare(sFive, sOne) < 0);
		assertTrue(comparator.compare(sFour, sTwo) < 0);
	}

}
