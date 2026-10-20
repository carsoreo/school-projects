package edu.ncsu.csc216.pack_scheduler.course;


import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;


import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Extends an Activity to represent a course with a name, section, credit hours, and instructor.
 * Meeting days can be Monday through Friday or arranged.
 * Calling {@link #toString()} converts this object to the course record format.
 * @author Zane Taylor
 */
public class Course extends Activity implements Comparable<Course> {

//    /**
//     * Regex that matches valid course names. The entire string must match, not just a subsequence.
//     * If a course name matches, then it also satisfies the 5-8 length requirement.
//     * The requirements do not define what a "letter" is, but since all real NCSU course names and all valid tests
//     * consist of only uppercase ASCII letters, this program will only accept uppercase ASCII letters.
//     */
    /** The number of characters in a course section */
    private static final int SECTION_LENGTH = 3;
    /** The minimum number of credit hours */
    private static final int MIN_CREDITS = 1;
    /** The maximum number of credit hours */
    private static final int MAX_CREDITS = 5;
    /** A string with all valid meeting day characters */
    private static final String VALID_MEETING_DAYS = "MTWHF";

    /** Course's name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    /** Course's validator */
    private CourseNameValidator validator = new CourseNameValidator();
    /** The course roll **/
    private CourseRoll roll;
    /**
     * Creates a new course.
     * @param name the course name
     * @param title the course title
     * @param section the course section
     * @param credits the number of credit hours
     * @param instructorId the instructor ID
     * @param meetingDays the course meeting days
     * @param startTime the course start time
     * @param endTime the course end time
     * @param enrollmentCap is the enrollment cap 
     * @throws IllegalArgumentException if any parameters are invalid, see the setter methods for details
     */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
                  int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        setName(name);
        try {
        	
			if(!validator.isValid(name)) {
				throw new IllegalArgumentException("Invalid course name.");
			}
			
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        
        roll = new CourseRoll(this, enrollmentCap);
    }
    /**
     * Creates a new course. Start and end time will default to 0.
     * @param name the course name
     * @param title the course title
     * @param section the course section
     * @param credits the number of credit hours
     * @param instructorId the instructor ID
     * @param meetingDays the course meeting days
     * @param enrollmentCap is the enrollment cap
     * @throws IllegalArgumentException if any parameters are invalid, see the setter methods for details
     */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
        this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
    }

    /**
     * Gets the course name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the course name.
     * @param name the name, must be 1-4 letters, then a space, then 3 numbers
     * @throws IllegalArgumentException if the course name is null, empty, or invalid
     */
    private void setName(String name) {
        // Length check included in regex match
    	// uses CourseNameValidator to check if is a Valid Course Name
    	if(name == null || "".equals(name)) {
        	throw new IllegalArgumentException("Invalid course name.");
        }
    	try {
			validator.isValid(name);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
    	
        this.name = name;
    }

    /**
     * Gets the course section.
     * @return the section
     */
    public String getSection() {
        return section;
    }
    /**
     * Sets the course section.
     * @param section the section, must be 3 digits
     * @throws IllegalArgumentException if the course section is not 3 digits
     */
    public void setSection(String section) {
        if (section == null || section.length() != SECTION_LENGTH) {
            throw new IllegalArgumentException("Invalid section.");
        }
        // Rejecting non-ASCII characters for consistency with setName()
        if (!section.chars().allMatch(ch -> '0' <= ch && ch <= '9')) {
            throw new IllegalArgumentException("Invalid section.");
        }
        this.section = section;
    }

    /**
     * Gets the number of credit hours.
     * @return the number of credit hours
     */
    public int getCredits() {
        return credits;
    }
    /**
     * Sets the number of credit hours.
     * @param credits the number of credit hours, must be from 1 to 5
     * @throws IllegalArgumentException if the number of credit hours is out of bounds
     */
    public void setCredits(int credits) {
        if (credits < MIN_CREDITS || MAX_CREDITS < credits) {
            throw new IllegalArgumentException("Invalid credits.");
        }
        this.credits = credits;
    }

    /**
     * Gets the instructor ID.
     * @return the instructor ID
     */
    public String getInstructorId() {
        return instructorId;
    }
    /**
     * Sets the instructor ID.
     * @param instructorId the instructor ID
     * @throws IllegalArgumentException if the instructor ID is null or empty
     */
    public void setInstructorId(String instructorId) {
        if ("".equals(instructorId)) {
            throw new IllegalArgumentException("Invalid instructor id.");
        }
        this.instructorId = instructorId;
    }

    /**
     * Sets the course meeting time and days.
     * @param meetingDays the days a course meets, as a string with the characters MTWHF corresponding
     *                    to Monday through Friday, or simply {@code "A"} for arranged meetings
     * @param startTime the start time of the course in 24-hour {@code hhmm} format
     * @param endTime the end time of the course in 24-hour {@code hhmm} format
     * @throws IllegalArgumentException if the meeting days is null, empty, or have invalid or duplicate characters;
     *                                  the course has arranged meetings but the times are nonzero;
     *                                  the start or end time is not valid military time;
     *                                  or the start time is not before the end time
     */
    @Override
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
        if ("A".equals(meetingDays)) {
            if (startTime != 0 || endTime != 0) {
                throw new IllegalArgumentException("Invalid meeting days and times.");
            }
            super.setMeetingDaysAndTime("A", 0, 0);
            return;
        }
        if (!isValidMeetingDays(meetingDays, VALID_MEETING_DAYS)) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }
        super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

    /**
     * Checks if this course is arranged.
     * @return true if this course is arranged
     */
    private boolean isArranged() {
        return "A".equals(getMeetingDays());
    }

    /**
     * Generates an array with the information in this course to be presented in the schedule GUI.
     * @return an array with the name, section, title, and meeting times
     */
    @Override
    public String[] getShortDisplayArray() {
        return new String[]{name, section, getTitle(), getMeetingString(), String.valueOf(roll.getOpenSeats())};
    }

    /**
     * Generates an array with the information in this course to be presented in the full schedule GUI.
     * The array consists of a name, section, title, credit hours, instructor ID, meeting string, and empty string.
     * @return an array with all course information
     */
    @Override
    public String[] getLongDisplayArray() {
        return new String[]{name, section, getTitle(), String.valueOf(credits), instructorId, getMeetingString(), ""};
    }

    /**
     * Checks if this course is a duplicate of another activity.
     * @param activity the other activity
     * @return true if the other activity is a course with the same name
     */
    @Override
    public boolean isDuplicate(Activity activity) {
        if (this == activity) {
            return true;
        }
        if (!(activity instanceof Course)) {
            return false;
        }
        Course course = (Course) activity;
        return course.getName().equals(name);
    }

    /**
     * Checks if this course has a time conflict with another activity, even if only one minute overlaps.
     * This method overrides the one in Activity to ensure that arranged courses can never conflict.
     * @param possibleConflictingActivity an activity
     * @throws ConflictException if the two activities conflict
     */
    @Override
    public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
        if (isArranged()) {
            return;
        }
        super.checkConflict(possibleConflictingActivity);
    }

    /**
     * Checks if this course and another object are equal.
     * @param o the other object
     * @return true if the other object is a course and all fields are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Course course = (Course) o;

        if (credits != course.credits) {
            return false;
        }
        if (name != null ? !name.equals(course.name) : course.name != null) {
            return false;
        }
        if (section != null ? !section.equals(course.section) : course.section != null) {
            return false;
        }
        return instructorId != null ? instructorId.equals(course.instructorId) : course.instructorId == null;
    }

    /**
     * Generates a hash code for use in hash-based structures. No fields are ignored.
     * @return a constant hash code
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + credits;
        result = 31 * result + (instructorId != null ? instructorId.hashCode() : 0);
        return result;
    }

    /**
     * Returns a comma separated string of all course fields.
     * @return a string representation of the course
     */
    @Override
    public String toString() {
        String meetingDays = getMeetingDays();
        if (isArranged()) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + meetingDays;
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + meetingDays +
                "," + getStartTime() + "," + getEndTime();
    }
	@Override
	public int compareTo(Course o) {
		int value = 0;
		if(this.getName().compareTo(o.getName()) == 0) {
			value = 0;
		} else if(this.getName().compareTo(o.getName()) < 0) {
			value = -1;
			return value;
		} else if(this.getName().compareTo(o.getName()) > 0) {
			value = 1;
			return value;
		}
		if(this.getSection().compareTo(o.getSection()) == 0) {
			value = 0;
		} else if(this.getSection().compareTo(o.getSection()) < 0) {
			value = -1;
		} else if(this.getSection().compareTo(o.getSection()) > 0) {
			value = 1;
		}
		return value;
	}
	
	/**
	 * Gets the course roll list
	 * @return roll is the course roll list
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
	

}
