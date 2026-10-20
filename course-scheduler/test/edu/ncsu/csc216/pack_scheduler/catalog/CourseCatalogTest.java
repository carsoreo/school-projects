package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests CourseCatalog
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";

	/**
	 * Test add course catalog from file
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);		

	}
	/**
	 * Test WolfScheduler.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		//Row 6
		assertEquals("CSC217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		//Row 7
		assertEquals("CSC217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		//Row 8
		assertEquals("CSC217", catalog[8][0]);
		assertEquals("223", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		//Row 9
		assertEquals("CSC217", catalog[9][0]);
		assertEquals("601", catalog[9][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		//Row 10
		assertEquals("CSC226", catalog[10][0]);
		assertEquals("001", catalog[10][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[10][2]);
		//Row 11
		assertEquals("CSC230", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("C and Software Tools", catalog[11][2]);
		//Row 12
		assertEquals("CSC316", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("Data Structures and Algorithms", catalog[12][2]);
	}
	
	/**
	 * Test CourseCatalog.addCourseToCatalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		//cc.loadCoursesFromFile(validTestFile);

		//Course c = new Course("CSC 216", "Software Fundamentals", "001", 3, "sheckman", "MW", 1200, 1300);
		assertTrue(cc.addCourseToCatalog("CSC216", "Software Fundamentals", "001", 3, "sheckman", 10, "MW", 1200, 1300));

		// Duplicate
		assertFalse(cc.addCourseToCatalog("CSC216", "Software Fundamentals", "001", 3, "sheckman", 10, "TH", 1200, 1300));
		// Note duplicate because different sections
		assertTrue(cc.addCourseToCatalog("CSC216", "Software Fundamentals", "002", 3, "sheckman", 10, "TH", 1200, 1300));
	}
	
	/**
	 * Test PackScheduler.saveCourseCatalog().
	 */
	@Test
	public void testExportSchedule() {
		CourseCatalog cc = new CourseCatalog();
		
		//Add courses and test that exports correctly
		cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 10, "MW", 1330, 1445);

		assertEquals(1, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_export.txt");
		checkFiles("test-files/expected_export.txt", "test-files/actual_export.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Test PackScheduler.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertNull(cc.getCourseFromCatalog("CSC216", "002"));

		Course c = new Course("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 10, "MW", 1330, 1445);
		cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 10, "MW", 1330, 1445);
		assertEquals(c, cc.getCourseFromCatalog("CSC216", "002"));
	}
	
	/**
	 * Test PackScheduler.removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		cc.removeCourseFromCatalog("CSC216", "002");
		assertEquals(12, cc.getCourseCatalog().length);
	}
	
	/**
	 * Test PackScheduler.newCourseCatalog()
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Test invalid course catalog
	 */
	@Test
	public void testInvalidCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, cc.getCourseCatalog().length);	
	}
}
