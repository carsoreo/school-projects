package edu.ncsu.csc216.pack_schedule.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.user.Student;

class CourseRollTest {

	@Test
	void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "A");
		CourseRoll roll = c.getCourseRoll();
		assertEquals(11, roll.getOpenSeats());
		assertEquals(11, roll.getEnrollmentCap());
		
		assertThrows(IllegalArgumentException.class,
                () -> new CourseRoll(c, 9));
		assertThrows(IllegalArgumentException.class,
                () -> new CourseRoll(c, 251));
		assertThrows(IllegalArgumentException.class,
                () -> new CourseRoll(c, -1));
		assertThrows(IllegalArgumentException.class,
                () -> new CourseRoll(c, 3000));
		
	}

	@Test
	void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		CourseRoll roll = c.getCourseRoll();
		Student s2 = new Student ("Eli", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);	
		roll.enroll(s2);
		assertEquals(19, roll.getOpenSeats());
		assertThrows(IllegalArgumentException.class,
                () -> roll.enroll(s2));
		assertThrows(IllegalArgumentException.class,
                () -> roll.enroll(null));
		
		
	}

	@Test
	void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		CourseRoll roll = c.getCourseRoll();
		Student s2 = new Student ("Eli", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);	
		roll.enroll(s2);
		assertEquals(19, roll.getOpenSeats());
		roll.drop(s2);
		assertEquals(20, roll.getOpenSeats());
	}
	
	@Test
	void testDropWithWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student s2 = new Student ("Eli", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s3 = new Student ("Eli2", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s4 = new Student ("Eli3", "Carter2", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s5 = new Student ("Eli4", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s6 = new Student ("Eli5", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s7 = new Student ("Eli6", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s8 = new Student ("Eli7", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s9 = new Student ("Eli8", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s10 = new Student ("Eli9", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s1 = new Student ("Eli10", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student sWaitlist = new Student ("Eli67", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(sWaitlist);
		roll.drop(sWaitlist);
		assertEquals(0, roll.getNumberOnWaitlist());
	}



	@Test
	void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		CourseRoll roll = c.getCourseRoll();
		Student s2 = new Student ("Eli", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		assertTrue(roll.canEnroll(s2));
		roll.enroll(s2);
		assertFalse(roll.canEnroll(s2));
	}
	
	@Test
	void testWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student s2 = new Student ("Eli", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s3 = new Student ("Eli2", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s4 = new Student ("Eli3", "Carter2", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s5 = new Student ("Eli4", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s6 = new Student ("Eli5", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s7 = new Student ("Eli6", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s8 = new Student ("Eli7", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s9 = new Student ("Eli8", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s10 = new Student ("Eli9", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student s1 = new Student ("Eli10", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		Student sWaitlist = new Student ("Eli67", "Carter", "emcarter", "emcarter@ncsu.edu", "YUI", 12);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(sWaitlist);
		assertEquals(1, roll.getNumberOnWaitlist());
		assertFalse(roll.canEnroll(sWaitlist));
		roll.drop(s10);
		assertEquals(0, roll.getNumberOnWaitlist());
		assertEquals(0, roll.getOpenSeats());
	}

}
