package edu.ncsu.csc216.pack_scheduler.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests Activity conflicts.
 */
class ActivityTest {

    /**
     * Tests that two activities with the same time but at different days do not conflict.
     */
    @Test
    public void testCheckConflictNoConflict() {
        Activity a1 = newSampleCourse("MW", 1330, 1445);
        Activity a2 = newSampleCourse("TH", 1330, 1445);
        assertNoConflict(a1, a2);
    }
    /**
     * Tests that two activities with the same time and overlapping days do conflict.
     */
    @Test
    public void testCheckConflictWithConflict() {
        Activity a1 = newSampleCourse("MW", 1330, 1445);
        Activity a2 = newSampleCourse("M", 1330, 1445);
        assertConflict(a1, a2);
    }

    /**
     * Tests that two activities, with one "outside" the other, do not conflict on different days.
     */
    @Test
    public void testCheckConflictNoOutside() {
        Activity a1 = newSampleCourse("M", 1400, 1500);
        Activity a2 = newSampleCourse("W", 1300, 1600);
        assertNoConflict(a1, a2);
    }
    /**
     * Tests that two activities, with one "outside" the other, conflict on the same day.
     */
    @Test
    public void testCheckConflictOutside() {
        Activity a1 = newSampleCourse("M", 1400, 1500);
        Activity a2 = newSampleCourse("M", 1300, 1600);
        assertConflict(a1, a2);
    }

    /**
     * Tests that two activities that overlap each other do not conflict on different days.
     */
    @Test
    public void testCheckConflictNoOverlap() {
        Activity a1 = newSampleCourse("M", 1400, 1600);
        Activity a2 = newSampleCourse("W", 1500, 1700);
        assertNoConflict(a1, a2);
    }
    /**
     * Tests that two activities that overlap each other conflict on the same day.
     */
    @Test
    public void testCheckConflictOverlap() {
        Activity a1 = newSampleCourse("M", 1400, 1600);
        Activity a2 = newSampleCourse("M", 1500, 1700);
        assertConflict(a1, a2);
    }

    /**
     * Tests that two activities one minute apart do not conflict.
     */
    @Test
    public void testCheckConflictNoEdgeCase() {
        Activity a1 = newSampleCourse("M", 1400, 1500);
        Activity a2 = newSampleCourse("M", 1501, 1600);
        assertNoConflict(a1, a2);
    }
    /**
     * Tests that two activities that start and end on the same minute conflict.
     */
    @Test
    public void testCheckConflictEdgeCase() {
        Activity a1 = newSampleCourse("M", 1400, 1500);
        Activity a2 = newSampleCourse("M", 1500, 1600);
        assertConflict(a1, a2);
    }

    /**
     * Tests that one full-week course and a Monday course during the same time conflict.
     */
    @Test
    public void testCheckConflictMultipleDaysStart() {
        Activity a1 = newSampleCourse("MTWHF", 1330, 1445);
        Activity a2 = newSampleCourse("M", 1330, 1445);
        assertConflict(a1, a2);
    }
    /**
     * Tests that one full-week course and a Wednesday course during the same time conflict.
     */
    @Test
    public void testCheckConflictMultipleDaysMiddle() {
        Activity a1 = newSampleCourse("MTWHF", 1330, 1445);
        Activity a2 = newSampleCourse("W", 1330, 1445);
        assertConflict(a1, a2);
    }
    /**
     * Tests that one full-week course and a Friday course during the same time conflict.
     */
    @Test
    public void testCheckConflictMultipleDaysEnd() {
        Activity a1 = newSampleCourse("MTWHF", 1330, 1445);
        Activity a2 = newSampleCourse("F", 1330, 1445);
        assertConflict(a1, a2);
    }

    /**
     * Tests that arranged courses do not conflict.
     */
    @Test
    public void testCheckConflictArranged() {
        Activity a1 = newSampleCourse("A", 0, 0);
        Activity a2 = newSampleCourse("A", 0, 0);
        assertNoConflict(a1, a2);
    }

    /**
     * Asserts that two activities do not have a conflict.
     * @param a1 the first activity
     * @param a2 the second activity
     * @throws AssertionError if there is a conflict
     */
    private static void assertNoConflict(Activity a1, Activity a2) {
        assertDoesNotThrow(() -> a1.checkConflict(a2));
        assertDoesNotThrow(() -> a2.checkConflict(a1));
    }

    /**
     * Asserts that two activities have a conflict.
     * @param a1 the first activity
     * @param a2 the second activity
     * @throws AssertionError if there is not a conflict
     */
    private static void assertConflict(Activity a1, Activity a2) {
        Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
        assertEquals("Schedule conflict.", e1.getMessage());

        Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
        assertEquals("Schedule conflict.", e2.getMessage());
    }

    /**
     * Creates a sample course with a specific schedule.
     * @param meetingDays the meeting days
     * @param startTime the start time
     * @param endTime the end time
     * @return a course for use in testing
     */
    private static Activity newSampleCourse(String meetingDays, int startTime, int endTime) {
        return new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, meetingDays, startTime, endTime);
    }

}