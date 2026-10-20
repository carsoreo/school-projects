package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FacultyTest {

	@Test
	void testHashCode() {
		Faculty f = new Faculty("first", "last", "flast", "flast@ncsu.edu", "pw", 1);
		assertEquals(f.hashCode(), 1587848833);
	}

	@Test
	void testEqualsObject() {
		Faculty f = new Faculty("first", "last", "flast", "flast@ncsu.edu", "pw", 3);
		Faculty f1 = new Faculty("first", "last", "flast", "flast@ncsu.edu", "pw", 3);
		Faculty f2 = new Faculty("first2", "last2", "flast2", "flast2@ncsu.edu", "pw", 3);
		assertFalse(f.equals(f2));
		assertTrue(f1.equals(f));
	}

	@Test
	void testFaculty() {
		assertDoesNotThrow(() -> new Faculty("first", "last", "flast", "flast@ncsu.edu", "pw", 2));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(null, null, null, null, null, -1));
	}

	@Test
	void testSetMaxCourses() {
		Faculty f = new Faculty("first", "last", "flast", "flast@ncsu.edu", "pw", 3);
		f.setMaxCourses(2);
		assertEquals(2, f.getMaxCourses());
		assertThrows(IllegalArgumentException.class, () -> f.setMaxCourses(0));
		assertThrows(IllegalArgumentException.class, () -> f.setMaxCourses(5));
	}

	@Test
	void testToString() {
		Faculty f = new Faculty("first", "last", "flast", "flast@ncsu.edu", "pw", 3);
		assertEquals(f.toString(), "first,last,flast,flast@ncsu.edu,pw,3");
	}

}
