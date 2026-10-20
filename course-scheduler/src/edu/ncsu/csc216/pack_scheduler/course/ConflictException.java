package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An exception that is thrown when two activities have a conflict.
 * @author Zane Taylor
 */
public class ConflictException extends Exception {
    /** ID used for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new ConflictException with the default message "Schedule conflict."
     */
    public ConflictException() {
        this("Schedule conflict.");
    }

    /**
     * Creates a new ConflictException with a custom message.
     * @param message the message
     */
    public ConflictException(String message) {
        super(message);
    }

}
