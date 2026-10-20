package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Class which extends User and is very similar to Student. Can set and get
 * max courses. Also is a POJO constructed with a first name,
 * last name, email, id, and password. Has methods that can be used
 * to check if two faculty are equal, generate hashCode, and convert
 * the object to a string.
 * @author clorejol
 *
 */
public class Faculty extends User {
	
	/** Value which holds the amount of courses a faculty can teach */
	private int maxCourses;
	
	/** The max value a faculty can teach */
	public static final int MAX_COURSES = 3;
	
	/** The minimum value a faculty can teach */
	public static final int MIN_COURSES = 1;
	
	/** Value which holds the schedule of the faculty */
	private FacultySchedule schedule;
	
	/**
	 * Constructor for Faculty which calls on the extended classes constructor. Takes
	 * first name, last name, id, email, and password as the parameter.
	 * 
	 * @param firstName String first name of the faculty to be used as a parameter.
	 * @param lastName String last name of the faculty to be used as a parameter.
	 * @param id String id of the faculty to be used as a parameter.
	 * @param email String email of the faculty to be used as a parameter.
	 * @param password String password of the faculty to be used as a parameter.
	 * @param maxCourses integer used to hold the max amount of courses for a faculty
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Gets the value of maxCourses for Faculty.
	 * 
	 * @return maxCourses integer to be returned.
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the value of maxCourses for Faculty based on a given
	 * integer as a parameter.
	 * 
	 * @param maxCourses integer to be set to maxCourses.
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES ||  maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Returns the FacultySchedule object associated with the Faculty class.
	 * 
	 * @return schedule FacultySchedule object associated with the Faculty class.
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if the number of scheduled courses is greater than the max courses
	 * for a faculty. Returns true if there are more scheduled courses than max
	 * courses.
	 * 
	 * @return boolean true if maxCourses are less than the number of scheduled
	 * courses for a faculty. False if not.
	 */
	public boolean isOverloaded() {
		return getMaxCourses() < schedule.getNumScheduledCourses();
	}

	
	/**
	 * Generates hash code for the Faculty object.
	 * 
	 * @return int hash code of the Faculty object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCourses);
		return result;
	}

	/**
	 * Returns a boolean based on whether the parameter
	 * Faculty object is the same as this Faculty object.
	 * 
	 * @param obj Object to be compared.
	 * @return boolean true if the two Faculty objects are equal,
	 * false if the two Faculty objects are not equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}
	
	/**
	 * Converts the Faculty object into a string by separating each of it's
	 * components into a comma separated list.
	 * 
	 * @return String converted from the components of Faculty separated by
	 * commas.
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCourses;
	}
}
