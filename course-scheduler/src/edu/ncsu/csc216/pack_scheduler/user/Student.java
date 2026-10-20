package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The Student class holds information about an individual student,
 * consisting of a first and last name, ID, email, hashed password, and max credits.
 * Use {@link #toString()} to convert to student record format for use in writing to file.
 * @author Olivia Nowell
 * @author Zane Taylor
 * @author Ashley Godwin
 */
public class Student extends User implements Comparable<Student> {

	/** The default max credits */
	public static final int MAX_CREDITS = 18;

	/** The maximum number of credit hours this student can enroll in */
	private int maxCredits;
	/** The users Schedule */
	private Schedule schedule;

	/**
	 * Creates a new student.
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's ID
	 * @param email the student's email
	 * @param hashPW the student's hashed password
	 * @param maxCredits the student's max credits
	 * @throws IllegalArgumentException if any of the parameters are invalid,
	 *                                  see the setter method javadocs for details
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	/**
	 * Creates a new student. The max credits will default to {@link #MAX_CREDITS}.
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's ID
	 * @param email the student's email
	 * @param hashPW the student's hashed password
	 * @throws IllegalArgumentException if any of the parameters are invalid,
	 *                                  see the setter method javadocs for details
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		schedule = new Schedule();
	}

	/**
	 * Gets the student's max credits.
	 * @return the student's max credits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the student's max credits.
	 * @param maxCredits the student's max credits, must be between 3 and 18
	 * @throws IllegalArgumentException if the max credits is out of bounds
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

//	/**
//	 * Checks if this student has the same data as another student.
//	 * @param obj another object, this method will always return false if obj is not a Student
//	 * @return true if this student is equal to the other object
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Student other = (Student) obj;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (firstName == null) {
//			if (other.firstName != null)
//				return false;
//		} else if (!firstName.equals(other.firstName))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		if (maxCredits != other.maxCredits)
//			return false;
//		if (password == null) {
//			if (other.password != null)
//				return false;
//		} else if (!password.equals(other.password))
//			return false;
//		return true;
//	}
//
//	/**
//	 * Generates a hash code from this student's data.
//	 * @return the hash code
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		result = prime * result + maxCredits;
//		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		return result;
//	}

	/**
	 * Converts this student to the student record format by listing each field separated by commas.
	 * @return this student converted to the student record format
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}

	@Override
	public int compareTo(Student o) {
		if (o == null) {
			throw new IllegalArgumentException("Object is null");
		}
		try {
			if (Math.signum(getLastName().compareTo(o.getLastName())) == -1) {
				return -1;
			}
			else if (Math.signum(getLastName().compareTo(o.getLastName())) == 1) {
				return 1;
			}
			else if (Math.signum(getLastName().compareTo(o.getLastName())) == 0) {
				
				if (Math.signum(getFirstName().compareTo(o.getFirstName())) == -1) {
					return -1;
				}
				else if (Math.signum(getFirstName().compareTo(o.getFirstName())) == 1) {
					return 1;
				}
				else if (Math.signum(getFirstName().compareTo(o.getFirstName())) == 0) {
					if (Math.signum(getId().compareTo(o.getId())) == -1) {
						return -1;
					}
					else if (Math.signum(getId().compareTo(o.getId())) == 1) {
						return 1;
					}
				}
			}
		} catch (ClassCastException c) {
			throw new IllegalArgumentException("Note: this class has a natural ordering that is inconsistent with equals.");
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCredits);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
	
	/**
	 * Gets students schedule
	 * 
	 * @return students schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if the student can add a course to their schedule
	 * @param c is the course
	 * @return true if the course can be added, false otherwise
	 */
	public boolean canAdd(Course c) {	
		if(!schedule.canAdd(c)) {
			return false;
		}
		
		return schedule.getScheduleCredits() + c.getCredits() <= getMaxCredits();

	}
}

