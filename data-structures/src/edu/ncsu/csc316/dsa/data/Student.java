package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * This code has code that was initially given from the
 * Workshop 1 assignment page from the downloadable zip-file.
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author clorejol
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	
	/** First name attached to student */
	private String first;
	
	/** Last name attached to student */
	private String last;
	
	/** Id used to identify student */
	private int id;
	
	/** Credit hours the student is taking */
	private int creditHours;
	
	/** GPA of the student as a double */
	private double gpa;
	
	/** Unity ID of the student */
	private String unityID;
	
	/**
	 * Constructor for Student class which reads in a first name,
	 * last name, student ID, credit hours, GPA, and unityID as parameters
	 * for a Student object. Then sets all of the listed parameters.
	 * 
	 * @param firstName String first name of student to be set.
	 * @param lastName String last name of student to be set.
	 * @param idStudent int ID of the student to be set.
	 * @param credit int credit hours taken by the student to be set.
	 * @param gpa double grade point average of the student to be set.
	 * @param unityID String Unity ID of the student to be set.
	 */
	public Student(String firstName, String lastName, int idStudent, int credit, double gpa, String unityID) {
		setFirst(firstName);
		setLast(lastName);
		setId(idStudent);
		setCreditHours(credit);
		setGpa(gpa);
		setUnityID(unityID);
	}
	
	/**
	 * Retrieves the first name of the Student.
	 * @return the first to be returned
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the first name of the Student.
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Returns the last name of the Student.
	 * @return the last to be returned
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the last name of the Student.
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Returns the ID of the Student.
	 * @return the id to be returned
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the Student.
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the credit hours taken of the Student.
	 * @return the creditHNours to be returned
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the credit hours of the Student.
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Returns the GPA of the Student.
	 * @return the gpa to be returned
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the GPA of the Student.
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Returns the Unity ID of the Student.
	 * @return the unityID to be returned
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the Unity ID of the Student.
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(creditHours, first, gpa, id, last, unityID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(first, other.first) && id == other.id && Objects.equals(last, other.last);
	}

	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHNours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

	@Override
	public int compareTo(Student o) {
		if (o.equals(this)) {
			return 0;
		}
		
		String lastA = this.getLast();
		String lastB = o.getLast();
		
		if (lastA.compareTo(lastB) != 0) {
			return lastA.compareTo(lastB);
		}
		
		String firstA = this.getFirst();
		String firstB = o.getFirst();
		
		if (firstA.compareTo(firstB) != 0) {
			return firstA.compareTo(firstB);
		}
		
		int idA = this.getId();
		int idB = o.getId();
		
		if (idA > idB) {
			return 1;
		}
		else if (idB > idA) {
			return -1;
		}
		
		return 0;
		
	}
		

	
	
}
