package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This code has code that was initially given from the
 * Workshop 1 assignment page from the downloadable zip-file.
 * This test has been adapted to work for QuickSorter in Workshop 4.
 * 
 * Tests QuickSorter
 * @author clorejol
 *
 */
public class QuickSorterTest {
	
	/** Integer array of ascending numbers */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	
	/** Integer array of descending numbers */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	
	/** Integer array of random ordered numbers */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
	/** Value used to hold the sorter object */
	private QuickSorter<Integer> quickSorter;
	
	/**
	 * Sets up sorter for following tests.
	 */
	@Before
	public void setUp() {
		quickSorter = new QuickSorter<Integer>();
	}
	
	/**
	 * Tests the sorter on integers
	 */
	@Test
	public void testSortIntegers() {
		quickSorter.sort(dataAscending);
		assertEquals(1, dataAscending[0].intValue());
		assertEquals(2, dataAscending[1].intValue());
		assertEquals(3, dataAscending[2].intValue());
		assertEquals(4, dataAscending[3].intValue());
		assertEquals(5, dataAscending[4].intValue());

		quickSorter.sort(dataDescending);
		assertEquals(1, dataDescending[0].intValue());
		assertEquals(2, dataDescending[1].intValue());
		assertEquals(3, dataDescending[2].intValue());
		assertEquals(4, dataDescending[3].intValue());
		assertEquals(5, dataDescending[4].intValue());

		quickSorter.sort(dataRandom);
		assertEquals(1, dataRandom[0].intValue());
		assertEquals(2, dataRandom[1].intValue());
		assertEquals(3, dataRandom[2].intValue());
		assertEquals(4, dataRandom[3].intValue());
		assertEquals(5, dataRandom[4].intValue());
	}
	
	
	/**
	 * Tests the sorter on students with random element as pivot
	 */
	@Test
	public void testSortStudent1() {
		
		
		QuickSorter<Student> studentSorter = new QuickSorter<Student>();
		
		//This code is used in other test classes that were given in the Workshop 1 zip file.
		Student sOne = new Student("AOneFirst", "AOneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("BTwoFirst", "BTwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("CThreeFirst", "CThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("DFourFirst", "DFourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("EFiveFirst", "EFiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Student[] sAscending = { sOne, sTwo, sThree, sFour, sFive };
		Student[] sDescending = { sFive, sFour, sThree, sTwo, sOne };
		Student[] sRandom = { sThree, sOne, sFive, sFour, sTwo };

		studentSorter.sort(sAscending);
		assertEquals(sOne, sAscending[0]);
		assertEquals(sTwo, sAscending[1]);
		assertEquals(sThree, sAscending[2]);
		assertEquals(sFour, sAscending[3]);
		assertEquals(sFive, sAscending[4]);
		
		studentSorter.sort(sDescending);
		assertEquals(sOne, sDescending[0]);
		assertEquals(sTwo, sDescending[1]);
		assertEquals(sThree, sDescending[2]);
		assertEquals(sFour, sDescending[3]);
		assertEquals(sFive, sDescending[4]);
		
		studentSorter.sort(sRandom);
		assertEquals(sOne, sRandom[0]);
		assertEquals(sTwo, sRandom[1]);
		assertEquals(sThree, sRandom[2]);
		assertEquals(sFour, sRandom[3]);
		assertEquals(sFive, sRandom[4]);
		
		
	}
	
	/**
	 * Tests the sorter on students with first element as pivot
	 */
	@Test
	public void testSortStudent2() {
		
		
		QuickSorter<Student> studentSorter = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		
		//This code is used in other test classes that were given in the Workshop 1 zip file.
		Student sOne = new Student("AOneFirst", "AOneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("BTwoFirst", "BTwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("CThreeFirst", "CThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("DFourFirst", "DFourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("EFiveFirst", "EFiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Student[] sAscending = { sOne, sTwo, sThree, sFour, sFive };
		Student[] sDescending = { sFive, sFour, sThree, sTwo, sOne };
		Student[] sRandom = { sThree, sOne, sFive, sFour, sTwo };

		studentSorter.sort(sAscending);
		assertEquals(sOne, sAscending[0]);
		assertEquals(sTwo, sAscending[1]);
		assertEquals(sThree, sAscending[2]);
		assertEquals(sFour, sAscending[3]);
		assertEquals(sFive, sAscending[4]);
		
		studentSorter.sort(sDescending);
		assertEquals(sOne, sDescending[0]);
		assertEquals(sTwo, sDescending[1]);
		assertEquals(sThree, sDescending[2]);
		assertEquals(sFour, sDescending[3]);
		assertEquals(sFive, sDescending[4]);
		
		studentSorter.sort(sRandom);
		assertEquals(sOne, sRandom[0]);
		assertEquals(sTwo, sRandom[1]);
		assertEquals(sThree, sRandom[2]);
		assertEquals(sFour, sRandom[3]);
		assertEquals(sFive, sRandom[4]);
		
		
	}
	
	/**
	 * Tests the sorter on students with last element as pivot
	 */
	@Test
	public void testSortStudent3() {
		
		
		QuickSorter<Student> studentSorter = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		
		//This code is used in other test classes that were given in the Workshop 1 zip file.
		Student sOne = new Student("AOneFirst", "AOneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("BTwoFirst", "BTwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("CThreeFirst", "CThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("DFourFirst", "DFourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("EFiveFirst", "EFiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Student[] sAscending = { sOne, sTwo, sThree, sFour, sFive };
		Student[] sDescending = { sFive, sFour, sThree, sTwo, sOne };
		Student[] sRandom = { sThree, sOne, sFive, sFour, sTwo };

		studentSorter.sort(sAscending);
		assertEquals(sOne, sAscending[0]);
		assertEquals(sTwo, sAscending[1]);
		assertEquals(sThree, sAscending[2]);
		assertEquals(sFour, sAscending[3]);
		assertEquals(sFive, sAscending[4]);
		
		studentSorter.sort(sDescending);
		assertEquals(sOne, sDescending[0]);
		assertEquals(sTwo, sDescending[1]);
		assertEquals(sThree, sDescending[2]);
		assertEquals(sFour, sDescending[3]);
		assertEquals(sFive, sDescending[4]);
		
		studentSorter.sort(sRandom);
		assertEquals(sOne, sRandom[0]);
		assertEquals(sTwo, sRandom[1]);
		assertEquals(sThree, sRandom[2]);
		assertEquals(sFour, sRandom[3]);
		assertEquals(sFive, sRandom[4]);
		
		
	}
	
	/**
	 * Tests the sorter on students with middle element as pivot
	 */
	@Test
	public void testSortStudent4() {
		
		
		QuickSorter<Student> studentSorter = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		
		//This code is used in other test classes that were given in the Workshop 1 zip file.
		Student sOne = new Student("AOneFirst", "AOneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("BTwoFirst", "BTwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("CThreeFirst", "CThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("DFourFirst", "DFourLast", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("EFiveFirst", "EFiveLast", 5, 5, 5.0, "fiveUnityID");
		
		Student[] sAscending = { sOne, sTwo, sThree, sFour, sFive };
		Student[] sDescending = { sFive, sFour, sThree, sTwo, sOne };
		Student[] sRandom = { sThree, sOne, sFive, sFour, sTwo };

		studentSorter.sort(sAscending);
		assertEquals(sOne, sAscending[0]);
		assertEquals(sTwo, sAscending[1]);
		assertEquals(sThree, sAscending[2]);
		assertEquals(sFour, sAscending[3]);
		assertEquals(sFive, sAscending[4]);
		
		studentSorter.sort(sDescending);
		assertEquals(sOne, sDescending[0]);
		assertEquals(sTwo, sDescending[1]);
		assertEquals(sThree, sDescending[2]);
		assertEquals(sFour, sDescending[3]);
		assertEquals(sFive, sDescending[4]);
		
		studentSorter.sort(sRandom);
		assertEquals(sOne, sRandom[0]);
		assertEquals(sTwo, sRandom[1]);
		assertEquals(sThree, sRandom[2]);
		assertEquals(sFour, sRandom[3]);
		assertEquals(sFive, sRandom[4]);
		
		
	}
}
