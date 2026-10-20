/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Test class for CourseNameValidatorFSM
 * @author angodwi2
 *
 */
class CourseNameValidatorFSMTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
	 */
	@Test
	void testStateInitialValid() {
		CourseNameValidatorFSM cn = new CourseNameValidatorFSM();
		try {
			assertTrue(cn.isValid("CSCS116"));
			assertTrue(cn.isValid("HE123E"));
			assertTrue(cn.isValid("H234"));
		} catch (InvalidTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
	 */
	@Test
	void testStateInitialInValid() {
		CourseNameValidatorFSM cn = new CourseNameValidatorFSM();

		Exception e1 = assertThrows(InvalidTransitionException.class,
					() -> cn.isValid("123"));
					assertEquals("Course name must start with a letter.", e1.getMessage());
					
		Exception e2 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid("CCCCC"));
				assertEquals("Course name cannot start with more than 4 letters.", e2.getMessage());
				
		Exception e3 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid("CCC1A"));
				assertEquals("Course name must have 3 digits.", e3.getMessage());
				
		Exception e4 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid("CCC12A"));
				assertEquals("Course name must have 3 digits.", e4.getMessage());
						
		Exception e5 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid("CCC1234"));
				assertEquals("Course name can only have 3 digits.", e5.getMessage());
		
		Exception e6 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid("CSC234AS"));
				assertEquals("Course name can only have a 1 letter suffix.", e6.getMessage());
		Exception e7 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid("CSC234A2"));
				assertEquals("Course name cannot contain digits after the suffix.", e7.getMessage());

		Exception e8 = assertThrows(InvalidTransitionException.class,
				() -> cn.isValid(".sga"));
				assertEquals("Course name can only contain letters and digits.", e8.getMessage());

		 

	}

}
