package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Lets the user create a catalog that is capable
 * of holding courses for the different students
 * in the StudentDirectory.
 * 
 * @author angodwi2
 * @author zataylo3
 * @author iwnowell
 *
 */
public class CourseCatalog {
	/** Holds the sorted list of courses in a catalog */
	private SortedList<Course> catalog;
	
	/** Constructs a catalog*/
	public CourseCatalog() {
		catalog = new SortedList<>();
	}
	
	/** Creates a new and empty course catalog*/
	public void newCourseCatalog() {
		catalog = new SortedList<>();
	}
	
	/** Loads courses from a file into the catalog 
	 * @param fileName is the name of the file containing the courses
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e){
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**Adds a course to the catalog 
	 * 
	 * @param name is the name of the course
	 * @param title is the title of the course
	 * @param section is section of course
	 * @param credits are how many credit hours course has
	 * @param instructorId is instructor's ID
	 * @param meetingDays are days the course meet
	 * @param startTime is the time the course starts
	 * @param endTime is the time the course ends
	 * @param enrollmentCap is the max students who can be enrolled
	 * @return true if the course is successfully added, false if already exists in catalog
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course courseToAdd = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for (int i = 0; i < catalog.size(); i++) {
			Course courseInCatalog = catalog.get(i);
			if (courseInCatalog.getName().equals(courseToAdd.getName()) &&
					courseInCatalog.getSection().equals(courseToAdd.getSection())) {
				return false;
			}
		}
		catalog.add(courseToAdd);
		return true;
	}

	/**
	 * Removes a course from the catalog.
	 * @param name the course name
	 * @param section the course section
	 * @return true if the course was removed, false if the course does not exist
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			if (course.getName().equals(name) && course.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a course from the catalog.
	 * @param name the course name
	 * @param section the course section
	 * @return the course if found, null otherwise
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			if (course.getName().equals(name) && course.getSection().equals(section)) {
				return course;
			}
		}
		return null;
	}

	/**
	 * Gets the course catalog as a 2D array with courses as rows and
	 * name, section, title, and meeting string as columns, though some may be empty strings.
	 * @return the course catalog table, may be empty (length 0)
	 */
	public String[][] getCourseCatalog() {
		if (catalog.isEmpty()) {
			return new String[0][];
		}
		String[][] grid = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			grid[i] = course.getShortDisplayArray();
		}
		return grid;
	}

	/**
	 * Writes the catalog to file.
	 * @param fileName the name of the file to write to
	 * @throws IllegalArgumentException if an error occurs while writing to file
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot write to file.");
		}
	}

}
