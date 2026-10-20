/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Exception class for Invalid transitions within the CourseNameValidatorFSM
 * @author Wil, Ashley, Nathan
 *
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization **/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parameterless constructor for the InvalidTransitionException
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	/**
	 * Constructor with message parameter taken from the
	 * parameterless constructor
	 * @param message is the message "Invalid FSM Transition"
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
}
