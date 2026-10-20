package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This code has code that was initially given from the
 * Workshop 1 assignment page from the downloadable zip-file.
 * Tests the Student object.
 * @author clorejol
 *
 */
public class StudentTest {
	
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
	
	/**
	 * Sets up following tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}
	
	/**
	 * Tests setting first name
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}
	
	/**
	 * Tests setting last name
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}
	
	/**
	 * Tests setting id
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}
	
	/**
	 * Tests setting GPA
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
		
	/**
	 * Tests setting Unity ID
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}
	
	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo() {
		//Note this is based on first name associated NOT the s"" name of the value
		assertTrue(sThree.compareTo(sFour) > 0);
		
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertSame(sOne.compareTo(sOne), 0);
		assertSame(sTwo.compareTo(sTwo), 0);
		
		//Testing slightly different students since code immediately rules out equal students
		Student sSlightIdNo = new Student("OneFirst", "OneLast", 2, 1, 1.0, "oneUnityID");
		assertTrue(sOne.compareTo(sSlightIdNo) < 0);
		assertFalse(sSlightIdNo.compareTo(sOne) < 0);
		
		Student sSlightFirst = new Student("OneFirstZ", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertEquals(sOne.compareTo(sSlightFirst), -1);
		assertEquals(sSlightFirst.compareTo(sOne), 1);
	}
	
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals() {
		assertFalse(sOne.equals(sFive));
		assertTrue(sOne.equals(sOne));
	}
	
	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		assertEquals("Student [first=OneFirst, last=OneLast, id=1, creditHNours=1, gpa=1.0, unityID=oneUnityID]", sOne.toString());
	}
	
	/**
	 * Tests the hashCode method
	 */
	@Test
	public void testHashCode() {
		assertEquals(1414505017, sOne.hashCode());
	}
}
