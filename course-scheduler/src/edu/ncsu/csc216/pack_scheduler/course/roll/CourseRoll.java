package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Class used in tandem with a Course object to manage enrollment into
 * a course. This is done by setting the enrollment cap, enrolling and dropping students,
 * getting the number of students on a waitlists, and checking if a student can enroll
 * 
 * @author NathN
 * @author clorejol
 *
 */
public class CourseRoll {

	/** Enrollment cap for a course */
	private int enrollmentCap;
	/** Maximum enrollment for a course */
	private static final int MAX_ENROLLMENT = 250;
	/** Minimum enrollment for a course */
	private static final int MIN_ENROLLMENT = 10;
	/** roll of students */
	private LinkedAbstractList<Student> roll;
	/** wait list of students */
	private LinkedQueue<Student> waitlist;
	/** course that roll is attached to */
	private Course course;
	
	/**
	 * Sets roll to an empty list
	 * 
	 * @param c Course that is associated with
	 * @param enrollmentCap enrollment cap of the course
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
		if(c == null) {
			throw new IllegalArgumentException();
		}
		waitlist = new LinkedQueue<Student>(enrollmentCap);
		setCourse(c);
	}
	/**
	 * gets enrollment cap of course
	 * @return enrollment cap of course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
		
	}
	
	/**
	 * sets enrollment cap to the value passed through
	 * 
	 * @param cap number to be passed through
	 * @throws IllegalArgumentException if cap is lower than the 10, higher than 250 or 
	 * smaller than the current amount of students in the course
	 */
	public void setEnrollmentCap(int cap) {
		if(cap < MIN_ENROLLMENT || cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if(cap < roll.size()) {
			throw new IllegalArgumentException();
		}
		roll.setCapacity(cap);
		
		this.enrollmentCap = cap;
	}
	
	/**
	 * adds student to roll of it is a valid student that is not already in the roll 
	 * or if there is no open seats
	 * 
	 * @param s student to be added
	 * @throws IllegalArgumentException if the student is null, class has no more open seats
	 * or student is already in the roll
	 */
	public void enroll(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).equals(s)) {
				throw new IllegalArgumentException();
			}
			//check for duplicates
		}
		
		if(roll.size() == enrollmentCap) {
			waitlist.enqueue(s);
		}	
		
		try {
			roll.add(s);
		} catch(IllegalArgumentException e) {
			//Just dont add
		}
			
				
	}
	
	/**
	 * drops student form the roll
	 * 
	 * @param s student to be dropped
	 */
	public void drop(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				try {
					roll.remove(s);
					if(waitlist.size() > 0) {
						Student studentAddedStudent = waitlist.dequeue();
						roll.add(studentAddedStudent);
						studentAddedStudent.getSchedule().addCourseToSchedule(course);
					}
					return;
				} catch(Exception e) {
					throw new IllegalArgumentException();
				}
			}
		}
		LinkedQueue<Student> current = new LinkedQueue<Student>(waitlist.size());
		int size = waitlist.size();
		for(int j = 0; j < size; j++) {
			Student studentDupe = waitlist.dequeue();
			waitlist.enqueue(studentDupe);
			current.enqueue(studentDupe);
		}
		for(int j = 0; j < size; j++) {
			Student tempStudent = current.dequeue();
			if(tempStudent.equals(s)) {
				for(int i = 0; i < j; i++) {
					Student temp = waitlist.dequeue();
					waitlist.enqueue(temp);
				}
				waitlist.dequeue();
				for(int i = 0; i < waitlist.size() - j; i++) {
					Student temp = waitlist.dequeue();
					waitlist.enqueue(temp);
				}
				return;
			}
			
			
		}
		
		
	}
	
	/**
	 * gets open seats left in course
	 * 
	 * @return amount of open seats in course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * boolean to find if the passed student can enroll into a course
	 * 
	 * @param s student to be passed
	 * @return true if student can be enrolled
	 */
	public boolean canEnroll(Student s) {
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).equals(s)) {
				return false;
			}
		}
		LinkedQueue<Student> current = new LinkedQueue<Student>(waitlist.size());
		int size = waitlist.size();
		for(int i = 0; i < size; i++) {
			Student temp = waitlist.dequeue();
			current.enqueue(temp);
			waitlist.enqueue(temp);
		}
		for(int i = 0; i < waitlist.size(); i++) {
			Student tempStudent = current.dequeue();
			if(tempStudent.equals(s)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the size of the waitlist.
	 * @return size of the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	/**
	 * Returns the course associated with the CourseRoll
	 * @return course Course object to be returned.
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * Sets the course associated with the CourseRoll.
	 * @param course Course object to be set.
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
}
