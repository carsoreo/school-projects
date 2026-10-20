package edu.ncsu.csc216.pack_scheduler.io;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a file.
 * @author Zane Taylor
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses. Any invalid
     * Courses are ignored. If the file to read cannot be found or the permissions are incorrect
     * a FileNotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
        SortedList<Course> courses = new SortedList<>(); // Create an empty array of Course objects
        // Create a file scanner to read the file
        try (Scanner fileReader = new Scanner(new FileInputStream(fileName))) {
            while (fileReader.hasNextLine()) { // While we have more lines in the file
                try { // Attempt to do the following
                    // Read the line, process it in readCourse, and get the object
                    // If trying to construct a Course in readCourse() results in an exception,
                    // flow of control will transfer to the catch block, below
                    Course course = readCourse(fileReader.nextLine());

                    // Create a flag to see if the newly created Course is a duplicate of something already in the list
                    boolean duplicate = false;
                    // Look at all the courses in our list
                    for (int i = 0; i < courses.size(); i++) {
                        Course current = courses.get(i);
                        // Check if the name and section are the same
                        if (course.getName().equals(current.getName()) &&
                                course.getSection().equals(current.getSection())) {
                            // It's a duplicate!
                            duplicate = true;
                            break; // We can break out of the loop, no need to continue searching
                        }
                    }
                    // If the course is NOT a duplicate
                    if (!duplicate) {
                        courses.add(course); // Add to the SortedList!
                    } // Otherwise ignore
                } catch (IllegalArgumentException ignored) {
                    // The line is invalid b/c we couldn't create a course, skip it!
                }
            }
        } // Auto-close the Scanner b/c we're responsible with our file handles
        return courses; // Return the SortedList with all the courses we read!
    }

    /**
     * Reads a course from a course record.
     * @param line a line from the course record format
     * @return the course
     * @throws IllegalArgumentException if the course record is invalid
     */
    private static Course readCourse(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",");
            
            String instructor = null;

            String name = scanner.next();
            String title = scanner.next();
            String section = scanner.next();
            int creditHours = scanner.nextInt();
            instructor = scanner.next();
            int enrollmentCap = scanner.nextInt();
            String meetingDays = scanner.next();
            
            
            
            if ("A".equals(meetingDays)) {
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Token after arranged meeting found");
                }
                Course c = new Course(name, title, section, creditHours, null, enrollmentCap, "A");
                if(instructor != null) {
                	Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor);
                	if (f != null) {
                		f.getSchedule().addCourseToSchedule(c);
                	}
                }
                return c;
            }

            int startTime = scanner.nextInt();
            int endTime = scanner.nextInt();
            if (scanner.hasNext()) {
                throw new IllegalArgumentException("Token after end time found");
            }
            Course c = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays, startTime, endTime);
            if(instructor != null) {
            	Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor);
            	if (f != null) {
            		f.getSchedule().addCourseToSchedule(c);
            	}
            }
            return c;

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Expected token in course line not found", e);
        }
    }

    /**
     * Writes the given list of Courses to file.
     * @param fileName file to write schedule of Courses to
     * @param courses list of Courses to write
     * @throws IOException if an error occurs while writing to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
        try (PrintStream fileWriter = new PrintStream(fileName)) {
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                fileWriter.println(course.toString());
            }
        }
    }

}
