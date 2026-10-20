package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

class ScheduleTest {

	/** Course used for testing*/
	private Course c = new Course("CSC116", "Test Course", "001", 2, "ntcampbe", 10, "M", 1000, 1100);
	
	@Test
	void testSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}

	@Test
	void testAddCourseToSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
		schedule.addCourseToSchedule(c);
		String[][] courses = schedule.getScheduledCourses();
		assertEquals("CSC116", courses[0][0]);
		assertEquals("001", courses[0][1]);
		assertEquals("Test Course", courses[0][2]);
		assertEquals("M 10:00AM-11:00AM", courses[0][3]);
	}

	@Test
	void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
		schedule.addCourseToSchedule(c);
		assertEquals(1, schedule.getScheduledCourses().length);
		schedule.removeCourseFromSchedule(c);
		assertEquals(0, schedule.getScheduledCourses().length);
		assertFalse(schedule.removeCourseFromSchedule(c));
	}

	@Test
	void testResetSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		schedule.setTitle("Not Reset");
		schedule.addCourseToSchedule(c);
		assertEquals(1, schedule.getScheduledCourses().length);
		schedule.resetSchedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
		
	}

	@Test
	void testSetTitle() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		Exception e1 = assertThrows(IllegalArgumentException.class,
					() -> schedule.setTitle(null));
	    	assertEquals("Title cannot be null.", e1.getMessage());
	}
	
	@Test
	void testAddInvalidCourse() {
		Schedule schedule = new Schedule();
		schedule.addCourseToSchedule(c);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(c));
    	assertEquals("You are already enrolled in CSC116", e1.getMessage());
    	Course c2 = new Course("CSC114", "Test Course", "001", 2, "ntcampbe", 10, "M", 1000, 1100);
    	Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(c2));
    	assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
	}


}
