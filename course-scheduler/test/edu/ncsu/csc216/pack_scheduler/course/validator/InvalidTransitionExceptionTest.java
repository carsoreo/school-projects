/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



/**
 * Test class for InvalidTransitionException
 * @author angodwi2
 *
 */
class InvalidTransitionExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException()}.
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException ie = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ie.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException(java.lang.String)}.
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		InvalidTransitionException ie = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ie.getMessage());
	}

}
