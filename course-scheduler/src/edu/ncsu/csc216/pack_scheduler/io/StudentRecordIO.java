package edu.ncsu.csc216.pack_scheduler.io;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Enables the client to read a file that contains
 * student records and write the contents of 
 * that file into another file.  The user could
 * also create a new student and write it
 * to a file.  Gives the user a way to easily transfer
 * student records that can later 
 * be used by the Student Directory, or add students. 
 * @author Ashley Godwin
 * @author Olivia Nowell
 * @author Zane Taylor
 */
public class StudentRecordIO {

	/**
	 * Reads students information of a file and creates new student objects in an array list
	 * by calling readStudent();
	 * @param fileName The relative path to the file, non-null
	 * @return Records of the student
	 * @throws FileNotFoundException If the file cannot be found or cannot be read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		SortedList<Student> students = new SortedList<>();
		try (Scanner in = new Scanner(new FileInputStream(fileName))) {
			while (in.hasNextLine()) {
				// Skip invalid student records instead of error
				try {
					Student student = readStudent(in.nextLine());
					students.add(student);
				} catch (IllegalArgumentException ignored) {
					// Skip invalid line
				}
			}
		}
		return students;
    }

	/**
	 * Reads a line from a file converting student text and hash information into a
	 * student object
	 * @param line The line represents one student and their information
	 * @return a new student's record
	 */
	private static Student readStudent(String line) {
		String[] components = line.split(",");
		if (components.length != 6) {
			throw new IllegalArgumentException("Student record must have length 6 but had length " + components.length);
		}
		String maxC = components[5];
		int maxCReal = Integer.parseInt(maxC);
		return new Student(components[0], components[1], components[2], components[3], components[4], maxCReal);
	}

	/**
	 * Writes a list of student records to a file.
	 * @param fileName The relative path to the file, non-null
	 * @param studentDirectory The list of student records to write, non-null
	 * @throws IOException If the file does not exist or cannot be opened
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		try (PrintStream fileWriter = new PrintStream(new FileOutputStream(fileName))) {
			// sorted list not iterable, shame
			for (int i = 0; i < studentDirectory.size(); i++) {
				User student = studentDirectory.get(i);
				fileWriter.println(student);
			}
		}
	}

}
