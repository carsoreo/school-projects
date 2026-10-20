package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ReportManager
 * 
 * @author clorejol
 *
 */
public class ReportManagerTest {
	
	/** ReportManager type to test */
	private ReportManager manager;
	
	/**
	 * Sets up ReportManager with test files
	 * @throws Exception if files cannot be found
	 */
	@Before
	public void setUp() throws Exception {
		manager = new ReportManager("input/sample-rooms.csv", "input/sample-logs.csv");
	}
	
	/**
	 * Tests the getVacuumBagReport method
	 */
	@Test
	public void testGetVacuumBagReport() {
		assertEquals("Vacuum Bag Report (last replaced 07/22/2022 00:00:00) [\n"
				+ "   Bag is due for replacement in 5280 SQ FT\n"
				+ "]", manager.getVacuumBagReport("07/22/2022 00:00:00"));
		
		assertEquals("Vacuum Bag Report (last replaced 06/01/2021 13:39:00) [\n"
				+ "   Bag is due for replacement in 5149 SQ FT\n"
				+ "]", manager.getVacuumBagReport("06/01/2021 13:39:00"));
		
		assertEquals("Vacuum Bag Report (last replaced 04/30/2021 13:39:00) [\n"
				+ "   Bag is overdue for replacement!\n"
				+ "]", manager.getVacuumBagReport("04/30/2021 13:39:00"));
		
	}
	
	/**
	 * Tests the getFrequenceReport method
	 */
	@Test
	public void testGetFrequencyReport() {
		assertEquals("Frequency of Cleanings [\n"
				+ "   Living Room has been cleaned 6 times\n"
				+ "   Dining Room has been cleaned 3 times\n"
				+ "   Guest Bathroom has been cleaned 2 times\n"
				+ "   Guest Bedroom has been cleaned 2 times\n"
				+ "   Foyer has been cleaned 1 times\n"
				+ "   Office has been cleaned 1 times\n"
				+ "   Kitchen has been cleaned 0 times\n"
				+ "]", manager.getFrequencyReport(10));
	}
	
	/**
	 * Tests the getRoomReport method
	 */
	@Test
	public void testGetRoomReport() {
		assertEquals("Room Report [\n"
				+ "   Dining Room was cleaned on [\n"
				+ "      05/31/2021 09:27:45\n"
				+ "      05/23/2021 18:22:11\n"
				+ "      05/21/2021 09:16:33\n"
				+ "   ]\n"
				+ "   Foyer was cleaned on [\n"
				+ "      05/01/2021 10:03:11\n"
				+ "   ]\n"
				+ "   Guest Bathroom was cleaned on [\n"
				+ "      05/17/2021 04:37:31\n"
				+ "      05/08/2021 07:01:51\n"
				+ "   ]\n"
				+ "   Guest Bedroom was cleaned on [\n"
				+ "      05/23/2021 11:51:19\n"
				+ "      05/13/2021 22:20:34\n"
				+ "   ]\n"
				+ "   Kitchen was cleaned on [\n"
				+ "      (never cleaned)\n"
				+ "   ]\n"
				+ "   Living Room was cleaned on [\n"
				+ "      05/30/2021 10:14:41\n"
				+ "      05/28/2021 17:22:52\n"
				+ "      05/12/2021 18:59:12\n"
				+ "      05/11/2021 19:00:12\n"
				+ "      05/09/2021 18:44:23\n"
				+ "      05/03/2021 17:22:52\n"
				+ "   ]\n"
				+ "   Office was cleaned on [\n"
				+ "      06/01/2021 13:39:01\n"
				+ "   ]\n"
				+ "]", manager.getRoomReport());
	}

}
