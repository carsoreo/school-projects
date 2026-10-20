package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.factory.DSAFactory;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;


/**
 * ReportManager handles behaviors associated with generating String reports for
 * the user interface, including (1) vacuum bag reports, (2) frequency reports,
 * and (3) room reports.
 * 
 * @author Dr. King
 * @author clorejol
 *
 */
public class ReportManager {
	
	/** Holds the CleaningManager type to be used to complete methods */
	private CleaningManager cleaningManager;
	
	/** Format to use when converting to date time */
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    /**
     * Constructs a new ReportManager for building reports of cleaning history
     * information from the provided file with room information and the provided
     * file with cleaning log event information.
     * 
     * @param pathToRoomFile the path to the file that contains room information
     * @param pathToLogFile  the path to the file that contains cleaning event log
     *                       information
     * @throws FileNotFoundException if either the room file or the cleaning event
     *                               log file cannot be read
     */
    public ReportManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
    	cleaningManager = new CleaningManager(pathToRoomFile, pathToLogFile);
    }

    /**
     * Returns a report that indicates how many square feet remain to be cleaned
     * until a vacuum bag change is recommended.
     * 
     * @param timestamp the date and time of the previous vacuum bag change
     * @return a report that indicates how many more square feet can be cleaned
     *         until a vacuum bag change is recommended
     */
    public String getVacuumBagReport(String timestamp) {
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(timestamp, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return "Date & time must be in the format: MM/DD/YYYY HH:MM:SS";
        }
        int sqFt = cleaningManager.getCoverageSince(dateTime);
        if (sqFt >= 5280) {
        	return "Vacuum Bag Report (last replaced " + timestamp + ") [\n   Bag is overdue for replacement!\n]";
        }
		return "Vacuum Bag Report (last replaced " + timestamp + ") [\n   Bag is due for replacement in " + (5280 - sqFt) + " SQ FT\n]";
    }

    /**
     * Returns a report of the top X rooms cleaned, sorted from most frequent to
     * least frequent.
     * 
     * @param number the number of rooms to include in the report
     * @return a report of the top X rooms cleaned
     */
    public String getFrequencyReport(int number) {
    	if (number <= 0) {
    		return "Number of rooms must be greater than 0.";
    	}
    	StringBuilder s = new StringBuilder("Frequency of Cleanings [\n");
    	Map<String, List<CleaningLogEntry>> map = cleaningManager.getEventsByRoom();
    	Map<Entry<String, List<CleaningLogEntry>>, Integer> map2 = DSAFactory.getMap(new FreqComparator());
    	for (Entry<String, List<CleaningLogEntry>> entry : map.entrySet()) {
    		map2.put(entry, null);
    	}
    	
    	int count = 0;
    	for (Entry<Entry<String, List<CleaningLogEntry>>, Integer> entry : map2.entrySet()) {
    		if (count >= number) {
    			break;
    		}
    		s.append("   " + entry.getKey().getKey() + " has been cleaned " + entry.getKey().getValue().size() + " times\n");
    		count++;
    	}
    	s.append("]");
    	
    	return s.toString();

    }

    /**
     * Returns a report that includes a list of cleaning log events associated with
     * each room. The report lists rooms in alphabetical (ascending) order, and
     * cleaning log entries for each room as sorted in descending chronological
     * order (most recent to oldest).
     * 
     * @return a report of cleaning log events for each room
     */
    public String getRoomReport() {
    	boolean cleanCheck = true;
        Map<String, List<CleaningLogEntry>> report = cleaningManager.getEventsByRoom();
        if (report.isEmpty()) {
        	return "No rooms have been cleaned.";
        }
        StringBuilder s = new StringBuilder("Room Report [\n");
        Iterable<Entry<String, List<CleaningLogEntry>>> it = report.entrySet();
        for(Entry<String, List<CleaningLogEntry>> room : it) {
    		s.append("   " + room.getKey() + " was cleaned on [\n");
    		if (room.getValue().size() == 0) {
    			s.append("      (never cleaned)\n");
    		}
    		else {
    			cleanCheck = false;
    			for (int i = 0; i < room.getValue().size(); i++) {
        			s.append("      " + room.getValue().get(i).getTimestamp().format(DATE_TIME_FORMAT) + "\n");
        		}
    		}
    		
    		 s.append("   ]\n");
    	}
        if (cleanCheck) {
        	return "No rooms have been cleaned.";
        }
        s.append("]");
        return s.toString();
        
    }
    
    /**
     * Comparator used to compare the amount of times a room is cleaned
     * @author clorejol
     *
     */
    private class FreqComparator implements Comparator<Entry<String, List<CleaningLogEntry>>> {

		@Override
		public int compare(Entry<String, List<CleaningLogEntry>> o1, Entry<String, List<CleaningLogEntry>> o2) {
			int o1Size = o1.getValue().size();
			int o2Size = o2.getValue().size();
			if (o2Size == o1Size) {
				if (o2.getKey().compareTo(o1.getKey()) == 0) {
					return 0;
				}
				else if (o2.getKey().compareTo(o1.getKey()) < 0) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else if (o2Size > o1Size) {
				return 1;
			}
			else {
				return -1;
			}
		}
    	
    }
}