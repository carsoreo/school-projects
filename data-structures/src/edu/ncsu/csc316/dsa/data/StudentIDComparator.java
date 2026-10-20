package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number. Implements Comparator
 * The code in this class was created using the pseudo code outlined in
 * the Workshop 1 assignment page.
 * @author Dr. King
 * @author clorejol
 *
 */
public class StudentIDComparator implements Comparator<Student> {
	
	/**
	 * Constructor for StudentIDComparator
	 */
	public StudentIDComparator() {
		//Empty
	}
	
	/**
	 * Compares students based on id number in ascending order
	 */
	@Override
	public int compare(Student one, Student two) {
		
		int idA = one.getId();
		int idB = two.getId();
		
		if (idA == idB) {
			return 0;
		}
		else if (idA > idB) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
