package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {
	
	/** First name */
	private static final String FIRSTNAME = "Tuffy";
	/** Last name */
	private static final String LASTNAME = "Pack";
	/** Student id */
	private static final String ID = "123456789";
	/** Student email */
	private static final String EMAIL = "twpack@ncsu.edu";
	/** Student password */
	private static final String PASSWORD = "gopack";
	/** Max class credits */
	private static final int MAXCREDITS = 18;
	/** Lower bound invalid for credits */
	private static final int LOW_CREDITS = 2;
	/** Upper bound invalid for credits */
	private static final int HIGH_CREDITS = 19;
	
	@Test
	void testCompareTo() {
		Student s2 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		Student s3 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		Student s4 = new Student ("Willy", LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		Student s5 = new Student (FIRSTNAME, LASTNAME, "123456788", EMAIL, PASSWORD, MAXCREDITS);
		
		assertEquals(0, s3.compareTo(s2));
		assertEquals(0, s2.compareTo(s3));
		
		assertEquals(1, s4.compareTo(s3));
		assertEquals(-1, s3.compareTo(s4));
		
		assertEquals(-1, s5.compareTo(s3));
		assertEquals(1, s3.compareTo(s5));
		
		
		
		
	}	
	
    /**
     * Tests hash code for Student to ensure that the 
     * hash code is different for students with differing fields and
     * the same for equal fields.
     */
	@Test
	void testHashCode() {
		User s1 = new Student ("Randy", LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s2 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s3 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s4 = new Student (FIRSTNAME, LASTNAME, "123455678", EMAIL, PASSWORD, MAXCREDITS);
		User s5 = new Student (FIRSTNAME, "Woodson", ID, "tuffy@ncsu.edu", PASSWORD, MAXCREDITS);
		User s6 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, "tuffstuff", MAXCREDITS);
		User s7 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, 8);	
		
		//Test for the same hash code for the same values
		assertEquals(s2.hashCode(), s3.hashCode());
		
		//Test for each of the fields.
		assertNotEquals(s2.hashCode(), s1.hashCode());
		assertNotEquals(s2.hashCode(), s4.hashCode());
		assertNotEquals(s2.hashCode(), s5.hashCode());
		assertNotEquals(s2.hashCode(), s6.hashCode());
		assertNotEquals(s2.hashCode(), s7.hashCode());

	}

	/**
	 * Tests that an exception is not thrown when a student's
	 * fields are valid, and that an exception is thrown when
	 * a student's fields are invalid for each differing field
	 */
	@Test
	void testStudentNoMaxCredit() {
		Student s = assertDoesNotThrow(
				() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD),
				"Should not throw exception");
		assertAll("Course",
						() -> assertEquals(FIRSTNAME, s.getFirstName(), "incorrect first name"),
						() -> assertEquals(LASTNAME, s.getLastName(), "incorrect last name"),
						() -> assertEquals(ID, s.getId(), "incorrect ID"),
						() -> assertEquals(EMAIL, s.getEmail(), "incorrect email"),
						() -> assertEquals(PASSWORD, s.getPassword(), "incorrect password"),
						() -> assertEquals(MAXCREDITS, s.getMaxCredits(), "incorrect max credits"));
	}
	
	/**
	 * Tests that an exception is not thrown when the field is valid, 
	 * and an exception is thrown when a field for student is not valid for 
	 * the constructor that includes maxCredits field.
	 */
	@Test
	void testValidStudent() {
		Student s = assertDoesNotThrow(
				() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS),
				"Should not throw exception");
		assertAll("Course",
						() -> assertEquals("Tuffy", s.getFirstName()),
						() -> assertEquals("Pack", s.getLastName()),
						() -> assertEquals("123456789", s.getId(), "incorrect ID"),
						() -> assertEquals("twpack@ncsu.edu", s.getEmail(), "incorrect email"),
						() -> assertEquals("gopack", s.getPassword(), "incorrect password"),
						() -> assertEquals(18, s.getMaxCredits(), "incorrect max credits"));
	}

	/**
	 * Tests that an exception is thrown when the student id is 
	 * an empty string with the correct exception message.
	 */
	@Test
	void testStudentInvalidId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, "", EMAIL, PASSWORD));
		assertEquals("Invalid id", e1.getMessage(), "Incorrect exception thrown with empty ID");
	}
	
	/**
	 * Tests that an exception is thrown when the student id is
	 * null with correct exception message.
	 */
	@Test
	void testStudentNullId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, null, EMAIL, PASSWORD));
		assertEquals("Invalid id", e1.getMessage(), "Incorrect exception thrown with null ID");
	}

	/**
	 * Tests if an exception is thrown when the first name is null
	 * or an empty string with correct exception message.
	 */
	@Test
	void testSetFirstNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, LASTNAME, ID, EMAIL, PASSWORD));
		assertEquals("Invalid first name", e1.getMessage(), "Incorrect exception thrown with null firstname");
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", LASTNAME, ID, EMAIL, PASSWORD));
		assertEquals("Invalid first name", e2.getMessage(), "Incorrect exception thrown with empty first name");
	}
	
	/**
	 * Tests if an exception is thrown when the last name is null or 
	 * an empty string with correct exception message.
	 */

	@Test
	void testSetLastNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, null, ID, EMAIL, PASSWORD));
		assertEquals("Invalid last name", e1.getMessage(), "Incorrect exception thrown with null last name");
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, "", ID, EMAIL, PASSWORD));
		assertEquals("Invalid last name", e2.getMessage(), "Incorrect exception thrown with empty last name");
	}

	/**
	 * Tests if an exception is thrown is the email is null, an empty
	 * string, does not contain an "@" symbol, does not contain ".", or
	 * the symbol "." comes before the "@" symbol.
	 */
	@Test
	void testSetEmail() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, null, PASSWORD));
		assertEquals("Invalid email", e1.getMessage(), "Incorrect exception thrown with null password");
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, "", PASSWORD));
		assertEquals("Invalid email", e2.getMessage(), "Incorrect exception thrown with empty password");
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, "twpackncsu.edu", PASSWORD));
		assertEquals("Invalid email", e3.getMessage(), "Incorrect exception thrown with empty password");
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, "twpack@ncsuedu", PASSWORD));
		assertEquals("Invalid email", e4.getMessage(), "Incorrect exception thrown with empty password");
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, "tw.pack@ncsuedu", PASSWORD));
		assertEquals("Invalid email", e5.getMessage(), "Incorrect exception thrown with empty password");
		
	}

	/**
	 * Tests that an exception is thrown when the password is null or empty
	 * with correct exception message.
	 */
	@Test
	void testSetPasswordInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, null));
		assertEquals("Invalid password", e1.getMessage(), "Incorrect exception thrown with null password");
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, ""));
		assertEquals("Invalid password", e2.getMessage(), "Incorrect exception thrown with empty password");
	}

	/**
	 * Tests that an exception is thrown when the credits are too low or too high with
	 * the correct exception message.
	 */
	@Test
	void testSetMaxCredits() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, LOW_CREDITS));
		assertEquals("Invalid max credits", e1.getMessage(), "Incorrect exception thrown with invalid credits");
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, HIGH_CREDITS));
		assertEquals("Invalid max credits", e2.getMessage(), "Incorrect exception thrown with invalid credits");
	}

	/** 
	 * Tests that when two students have differing fields, the equals method 
	 * asserts that the two students are unique.  In addition, it tests that 
	 * two student objects are the same if all of their fields are the same.
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Student ("Randy", LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s2 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s3 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s4 = new Student (FIRSTNAME, LASTNAME, "123455678", EMAIL, PASSWORD, MAXCREDITS);
		User s5 = new Student (FIRSTNAME, "Woodson", ID, "tuffy@ncsu.edu", PASSWORD, MAXCREDITS);
		User s6 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, "tuffstuff", MAXCREDITS);
		User s7 = new Student (FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, 8);

		
		//Test for equality in both directions
		assertTrue(s2.equals(s3));
		assertTrue(s3.equals(s2));
		
		//Test for inequality for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s3.equals(s4));
		assertFalse(s3.equals(s5));
		assertFalse(s3.equals(s6));
		assertFalse(s3.equals(s7));


	}
	

	/**
     * Test toString() method to ensure that the correct string is returned.
     */
    @Test
    public void testToString() {
        User s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
        assertEquals("Tuffy,Pack,123456789,twpack@ncsu.edu,gopack," + 18, s1.toString());
    }

}
