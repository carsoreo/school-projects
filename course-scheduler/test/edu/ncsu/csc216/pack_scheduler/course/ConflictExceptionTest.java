package edu.ncsu.csc216.pack_scheduler.course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests ConflictException.
 * @author Zane Taylor
 */
class ConflictExceptionTest {

    /**
     * Tests the default constructor.
     */
    @Test
    public void testConflictException() {
        ConflictException ce = new ConflictException();
        assertEquals("Schedule conflict.", ce.getMessage());
    }

    /**
     * Tests the constructor with custom message.
     */
    @Test
    public void testConflictExceptionString() {
        ConflictException ce = new ConflictException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

}