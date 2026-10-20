package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Allows a user to read faculty contents from a file, and also write contents to a file.
 * @author John Cirincione
 *
 */
public class FacultyRecordIO {
	
	/**
	 * Reads through a faculty list
	 * @param fileName the name of the file being read
	 * @return facultyList the list of faculty
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		LinkedList<Faculty> facultyList = new LinkedList<>();
		try (Scanner in = new Scanner(new FileInputStream(fileName))) {
			while (in.hasNextLine()) {
				// Skip invalid student records instead of error
				try {
					Faculty faculty = readFaculty(in.nextLine());
					facultyList.add(faculty);
				} catch (IllegalArgumentException ignored) {
					// Skip invalid line 
				}
			}
		}
		return facultyList;
    }
	
	private static Faculty readFaculty(String line) {
		String[] components = line.split(",");
		if (components.length != 6) {
			throw new IllegalArgumentException("Faculty record must have length 6 but had length " + components.length);
		}
		return new Faculty(components[0], components[1], components[2], components[3], components[4], Integer.valueOf(components[5]));
	}
	
	/**
	 * Writes a list of faculty to a file
	 * @param fileName the name of the file being created
	 * @param facultyDirectory the list of faculty
	 * @throws IOException if unable to write file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		try (PrintStream fileWriter = new PrintStream(new FileOutputStream(fileName))) {
			// sorted list not iterable, shame
			for (int i = 0; i < facultyDirectory.size(); i++) {
				User faculty = facultyDirectory.get(i);
				fileWriter.println(faculty);
			}
		}
	}
}
