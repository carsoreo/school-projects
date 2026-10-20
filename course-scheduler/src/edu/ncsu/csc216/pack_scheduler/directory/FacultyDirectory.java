package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Directory of Faculty in the system
 * Allows to add, clear, remove, save, and load faulty
 * 
 * @author NathN
 *
 */
public class FacultyDirectory {

	/** Hash Algorithm to hash passwords */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** list of faculty in directory */
	private LinkedList<Faculty> facultyDirectory;
	
	/**
	 * creates an empty directory of faculty
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * gets first and last name along with the id for all faculty loaded
	 * 
	 * @return 2D array of faculty information
	 */
	public String[][] getFacultyDirectory() {
		String[][] array = new String[facultyDirectory.size()][3];
		for(int i = 0; i < facultyDirectory.size(); i++) {
			array[i][0] = facultyDirectory.get(i).getFirstName();
			array[i][1] = facultyDirectory.get(i).getLastName();
			array[i][2] = facultyDirectory.get(i).getId();
		}
		return array;
	}

	/**
	 * returns the faculty that matches the id given
	 * @param id of faculty to be revived
	 * @return faculty that matches id
	 */
	public Faculty getFacultyById(String id) {
		for(int i = 0; i < facultyDirectory.size(); i++) {
			if(facultyDirectory.get(i).getId().equals(id)) {
				return facultyDirectory.get(i);
			}
		}
		return null;
	}

	/**
	 * saves the current faculty to given file
	 * @param fileName name of file to be written on
	 * @throws IllegalArgumentException if there is an error in writing the file
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);

		}
	}

	/**
	 * creates empty faculty directory clearing the old one
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
		
	}

	/**
	 * loads faculty from given file
	 *  
	 * @param fileName to be read
	 * @throws IllegalArgumentException if error reading file
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
		
	}

	/**
	 * adds faculty to directory
	 * 
	 * @param firstName of faculty of be added
	 * @param lastName of faculty of be added
	 * @param id of faculty of be added
	 * @param email of faculty of be added
	 * @param password of faculty of be added
	 * @param repeatPassword of faculty of be added
	 * @param maxCourses of faculty of be added
	 * @return true if faculty is added
	 * @throws IllegalArgumentException if faculty cannot be added
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is thrown, it's passed up from Student
		//to the GUI
		Faculty faculty = null;
		faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}

	/**
	 * removes faculty with matching id
	 * @param id of faculty to be removed
	 * @return true if faculty is removed
	 * @throws IllegalArgumentException if no id is selected
	 */
	public boolean removeFaculty(String id) {
		if(id == null) {
			throw new IllegalArgumentException("No faculty selected.");
		}
		for(int i = 0; i < facultyDirectory.size(); i++) {
			if(facultyDirectory.get(i).getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
		
	}

}