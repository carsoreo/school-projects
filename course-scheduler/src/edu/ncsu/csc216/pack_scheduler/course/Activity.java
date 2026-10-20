package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Holds information about an activity, including title and meeting times.
 * The meeting times can be formatted as a human-readable string.
 * Use the display methods to get a string array for use in GUIs.
 * Calling {@link #toString()} converts this activity to the format used to export to file.
 * @author Zane Taylor
 */
public abstract class Activity implements Conflict {

    /**
     * Activity's title.
     */
    private String title;
    /**
     * Activity's meeting days
     */
    private String meetingDays;
    /**
     * Activity's starting time
     */
    private int startTime;
    /**
     * Activity's ending time
     */
    private int endTime;

    /**
     * Creates a new activity.
     * @param title the activity title
     * @param meetingDays the activity meeting days
     * @param startTime the activity start time
     * @param endTime the activity end time
     * @throws IllegalArgumentException if any parameters are invalid, see the setter methods for details
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

    /**
     * Gets the activity title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the activity title.
     * @param title the title
     * @throws IllegalArgumentException if the activity title is null or empty
     */
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid title.");
        }
        this.title = title;
    }

    /**
     * Gets the activity meeting days.
     * @return the meeting days
     */
    public String getMeetingDays() {
        return meetingDays;
    }

    /**
     * Gets the activity start time.
     * @return the start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets the activity end time.
     * @return the end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets the activity meeting time and days.
     * @param meetingDays the days an activity meets, as a string with each character corresponding to a day
     * @param startTime the start time of the activity in 24-hour {@code hhmm} format
     * @param endTime the end time of the activity in 24-hour {@code hhmm} format
     * @throws IllegalArgumentException if the meeting days is null or empty;
     *                                  the start or end time is not valid military time;
     *                                  or the start time is not before the end time
     */
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
        if (meetingDays == null || meetingDays.isEmpty()) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }
        if (!isValidMilitaryTime(startTime) || !isValidMilitaryTime(endTime)) {
            throw new IllegalArgumentException("Invalid meeting days and times.");
        }
        this.meetingDays = meetingDays;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Checks if an integer is a valid military time.
     * @param time an integer
     * @return true if the integer can be interpreted as hhmm time
     */
    private static boolean isValidMilitaryTime(int time) {
        if (time < 0) {
            return false;
        }
        int hours = time / 100;
        int minutes = time % 100;
        return hours < 24 && minutes < 60;
    }

    /**
     * Checks if the meeting days string is valid.
     * @param meetingDays the meeting days string
     * @param validMeetingDays a string with all valid meeting day characters
     * @return true if the meeting days contains only valid meeting day characters with no duplicates
     */
    protected static boolean isValidMeetingDays(String meetingDays, String validMeetingDays) {
        if (meetingDays == null) {
            return false;
        }
        for (char meetingDay : meetingDays.toCharArray()) {
            // If the meeting day is invalid
            if (validMeetingDays.indexOf(meetingDay) == -1) {
                return false;
            }
            // If the first and last indexes of a character are different, that character must be a duplicate
            if (meetingDays.indexOf(meetingDay) != meetingDays.lastIndexOf(meetingDay)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts the meeting time and dates to a human-readable string in 12-hour format.
     * @return the meeting time and date as a string
     */
    public String getMeetingString() {
        if ("A".equals(meetingDays)) {
            return "Arranged";
        }
        return String.format("%s %s-%s", meetingDays, getTimeString(startTime), getTimeString(endTime));
    }
    /**
     * Converts a time to a human-readable string in 12-hour format.
     * @param time a military time, assumed to be valid
     * @return a time string such as 12:30AM
     */
    private static String getTimeString(int time) {
        int hours = time / 100;
        int minutes = time % 100;
        if (hours > 12) {
            return String.format("%d:%02dPM", hours - 12, minutes);
        }
        if (hours == 12) {
            return String.format("12:%02dPM", minutes);
        }
        // Because 00:32AM isn't correct
        if (hours == 0) {
            return String.format("12:%02dAM", minutes);
        }
        return String.format("%d:%02dAM", hours, minutes);
    }

    /**
     * Generates an array with the information in this activity to be presented in the schedule GUI.
     * The array consists of a name, section, title, and meeting string, though some values may be empty strings.
     * @return an array with length 4
     */
    public abstract String[] getShortDisplayArray();

    // name, section, getTitle(), String.valueOf(credits), instructorId, getMeetingString(), ""
    /**
     * Generates an array with the information in this activity to be presented in the full schedule GUI.
     * The array consists of a name, section, title, credit hours, instructor ID, meeting string, and event details,
     * though some values may be empty strings.
     * @return an array with length 7
     */
    public abstract String[] getLongDisplayArray();

    /**
     * Checks if this activity is a duplicate of another activity.
     * @param activity the other activity
     * @return true if the two activities are duplicates
     */
    public abstract boolean isDuplicate(Activity activity);

    /**
     * Checks if this activity has a time conflict with another activity, even if only one minute overlaps.
     * @param possibleConflictingActivity another activity
     * @throws ConflictException if the two activities conflict
     */
    @Override
    public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
        if (!hasOverlappingDay(getMeetingDays(), possibleConflictingActivity.getMeetingDays())) {
            return; // Two events on different days can never conflict
        }
        Activity first;
        Activity second;
        if (startTime < possibleConflictingActivity.getStartTime()) {
            first = this;
            second = possibleConflictingActivity;
        } else {
            first = possibleConflictingActivity;
            second = this;
        }
        // If the second activity starts before the first ends, they MUST be a conflict
        if (second.getStartTime() <= first.getEndTime()) {
            throw new ConflictException();
        }
    }
    /**
     * Checks if two meeting day strings both contain the same day, if two meeting day schedules overlap.
     * @param meetingDays1 the first string
     * @param meetingDays2 the second string
     * @return true if there is a character that is contained in string 1 and string 2
     */
    private static boolean hasOverlappingDay(String meetingDays1, String meetingDays2) {
        for (char c : meetingDays1.toCharArray()) {
            if (meetingDays2.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this activity and another object are equal.
     * @param o the other object
     * @return true if the other object is the same subclass of Activity and all Activity fields are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Activity activity = (Activity) o;

        if (startTime != activity.startTime) {
            return false;
        }
        if (endTime != activity.endTime) {
            return false;
        }
        if (title != null ? !title.equals(activity.title) : activity.title != null) {
            return false;
        }
        return meetingDays != null ? meetingDays.equals(activity.meetingDays) : activity.meetingDays == null;
    }

    /**
     * Generates a hash code for use in hash-based structures. No fields are ignored.
     * @return a constant hash code
     */
    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (meetingDays != null ? meetingDays.hashCode() : 0);
        result = 31 * result + startTime;
        result = 31 * result + endTime;
        return result;
    }

}
