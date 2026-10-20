package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA. Implements Comparator.
 * The code in this class was created using the pseudo code outlined in
 * the Workshop 1 assignment page.
 * @author Dr. King
 * @author clorejol
 */
public class StudentGPAComparator implements Comparator<Student> {
	
	/**
	 * Constructor for StudentGPAComparator
	 */
	public StudentGPAComparator() {
		//Empty for now
	}
	
	
	/**
	 * Compares students based on GPA in descending order
	 */
	@Override
	public int compare(Student one, Student two) {
		double gpaA = one.getGpa();
		double gpaB = two.getGpa();
		
		if (gpaA == gpaB) {
			return one.compareTo(two);
		}
		else if (gpaA < gpaB) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
