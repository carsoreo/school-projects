package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;

/**
 * Test class for CleaningManager
 * @author clorejol
 *
 */
public class CleaningManagerTest {
	
	/** CleaningManager type to be tested */
	private CleaningManager manager;
	
	
	
	/**
	 * Sets up the manager
	 * @throws Exception exception that can be thrown
	 */
	@Before
	public void setUp() throws Exception {
		manager = new CleaningManager("input/sample-rooms.csv", "input/sample-logs.csv");
	}
	
	/**
	 * Tests the getEventsByRoom method
	 */
	@Test
	public void testGetEventsByRoom() {
		assertFalse(manager.getEventsByRoom().isEmpty());
		assertEquals(7, manager.getEventsByRoom().size());
		assertEquals(78, manager.getEventsByRoom().get("Office").get(0).getPercentCompleted());
		assertEquals(89, manager.getEventsByRoom().get("Dining Room").get(0).getPercentCompleted());
		assertEquals(68, manager.getEventsByRoom().get("Living Room").get(0).getPercentCompleted());
	}
	
	/**
	 * Tests the getCoverageSince method
	 */
	@Test
	public void testGetCoverageSince() {
		LocalDateTime dateTime = LocalDateTime.parse("07/22/2022 00:00:00", ReportManager.DATE_TIME_FORMAT);
		LocalDateTime dateTime2 = LocalDateTime.parse("06/01/2021 13:39:00", ReportManager.DATE_TIME_FORMAT);
		LocalDateTime dateTime3 = LocalDateTime.parse("04/01/2021 13:39:00", ReportManager.DATE_TIME_FORMAT);
		assertEquals(0, manager.getCoverageSince(dateTime));
		assertEquals(131, manager.getCoverageSince(dateTime2));
		assertEquals(5727, manager.getCoverageSince(dateTime3));
	}
	
	/**
	 * Tests the getEventsByRoom method
	 */
	@Test
	public void testGetEventsByRoom2() throws Exception{
		long startTime = System.currentTimeMillis();
		CleaningManager manager2 = new CleaningManager("input/rooms_22.csv", "input/cleaningEvents_22.csv");
		manager2.getEventsByRoom();
		long endTime = System.currentTimeMillis();
		long finalTime = endTime - startTime;
		//Easy way to check final time value
		assertEquals(finalTime, 0);
	}
		

}
