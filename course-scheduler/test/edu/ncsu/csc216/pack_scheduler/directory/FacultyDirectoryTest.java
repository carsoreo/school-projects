package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class FacultyDirectoryTest {

	@Test
	void testFacultyDirectory() {
		FacultyDirectory f = new FacultyDirectory();
		assertEquals(0, f.getFacultyDirectory().length);
	}

	@Test
	void testGetFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "flast", "flast@ncsu.edu", "pw", "pw", 3);
		String[][] array = fd.getFacultyDirectory();
		assertEquals("first", array[0][0]);
		assertEquals("last", array[0][1]);
		assertEquals("flast", array[0][2]);
	}

	@Test
	void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		//Add a faculty
		fd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 2);
		String[][] s1 = fd.getFacultyDirectory();
		String s1Name = s1[0][0];
		//String[][] s2 = sd.getStudentById("zking").getStudentDirectory();
		assertEquals(s1Name, fd.getFacultyById("zking").getFirstName());
	}

	@Test
	void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.loadFacultyFromFile("test-files/expected_faculty_records.txt");
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		assertDoesNotThrow(() -> checkFiles("test-files/actual_faculty_records.txt", "test-files/expected_faculty_records.txt"));

	}

	@Test
	void testNewFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "flast", "flast@ncsu.edu", "pw", "pw", 3);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	@Test
	void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.loadFacultyFromFile("test-files/expected_faculty_records.txt");
		assertEquals(fd.getFacultyDirectory()[0][0], "Ashely");
	}


	@Test
	void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "flast", "flast@ncsu.edu", "pw", "pw", 3);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.removeFaculty("flast");
		assertEquals(0, fd.getFacultyDirectory().length);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}