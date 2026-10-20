package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
/**
 * Schedule object that holds and interacts with courses
 * 
 * @author Nathan Campbell
 * @author angodwi2
 *
 */
public class Schedule {
	/** title of schedule */
	private String title;
	/** list of courses in students schedule */
	private ArrayList<Course> schedule;
	/** Default name of schedule */
	private static final String DEFAULT_TITLE = "My Schedule";
	
	/**
	 * Constructs a schedule with an empty list of courses 
	 * and title being set to My Schedule 
	 */
	public Schedule() {
		setTitle(DEFAULT_TITLE);
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * adds a course to schedule
	 * 
	 * @param c course to added
	 * @throws IllegalArgumentException if course is a duplicate or has a conflict
	 * @return true if course is added
	 */
	public boolean addCourseToSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(c)) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			try {
				schedule.get(i).checkConflict(c);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(c);
		return true;
	}
	
	/**
	 * removes Course from schedule
	 * 
	 * @param c course to be removed
	 * @return true if course is removed
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if(c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) {
			if(c.equals(schedule.get(i))) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * resets schedule to back to the title as My Schedule and empties schedule.
	 */
	public void resetSchedule() {
		setTitle(DEFAULT_TITLE);
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * gets courses name, section, title, and meeting time in the schedule 
	 * 
	 * @return the courses name, section, title, and meeting time in the schedule 
	 */
	public String[][] getScheduledCourses() {
		String[][] courses = new String[schedule.size()][5];
		for(int i = 0; i < schedule.size(); i++) {
			courses[i][0] = schedule.get(i).getName();
			courses[i][1] = schedule.get(i).getSection();
			courses[i][2] = schedule.get(i).getTitle();
			courses[i][3] = schedule.get(i).getMeetingString();
			courses[i][4] = String.valueOf(schedule.get(i).getCourseRoll().getOpenSeats());
		}	
		return courses;
		
	}
	
	/**
	 * Sets title given the title
	 * 
	 * @param titleName possible name to be set
	 * @throws IllegalArgumentException if title is null
	 */
	public void setTitle(String titleName) {
		if(titleName == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = titleName;
	}
	
	/**
	 * gets current title of schedule
	 * 
	 * @return current title of schedule
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the total credits in the schedule
	 * @return the total credits in the schedules
	 */
	public int getScheduleCredits() {
		int creditCounter = 0;
		for(int i = 0; i < schedule.size(); i++) {
			creditCounter = creditCounter + schedule.get(i).getCredits();
		}
		return creditCounter;
	}

	/**
	 * Returns true if the course can be added to the schedule
	 * @param c is the course
	 * @return true if the course can be added, false if null or already in schedule
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(c)) {
				return false;
			}
			try {
				schedule.get(i).checkConflict(c);
			} catch(ConflictException e) {
				return false;	
			}
		}
		return true;
	}
}
