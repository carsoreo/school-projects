package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.data.RoomRecord;
import edu.ncsu.csc316.cleaning.factory.DSAFactory;
import edu.ncsu.csc316.cleaning.io.InputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * CleaningManager provides behaviors for retrieving information about cleaning
 * history, including lists of cleaning events for each room and the number of
 * square feet cleaned since a provided date.
 * 
 * @author Dr. King
 * @author clorejol
 *
 */
public class CleaningManager {
	
	/** List of CleaningLogEntry types to be used in methods */
	private List<CleaningLogEntry> cleaningLogs;
	
	/** List of RoomRecord types to be used in methods */
	private List<RoomRecord> roomRecords;

    /**
     * Constructs a new CleaningManager for processing cleaning history information
     * from the provided file with room information and the provided file with
     * cleaning log event information.
     * 
     * @param pathToRoomFile the path to the file that contains room information
     * @param pathToLogFile  the path to the file that contains cleaning event log
     *                       information
     * @throws FileNotFoundException if either the room file or the cleaning event
     *                               log file cannot be read
     */
    public CleaningManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
    	cleaningLogs = InputReader.readLogFile(pathToLogFile);
    	roomRecords = InputReader.readRoomFile(pathToRoomFile);
    }
    
    /**
     * Returns a map of cleaning event logs for each room. In the returned map, the
     * key of each entry is represented by the room ID. The value of each entry is
     * represented by a list of cleaning event log entries.
     * 
     * If there is no room information or there are no cleaning log events, returns
     * null.
     * 
     * @return a map of cleaning event logs for each room
     */
    public Map<String, List<CleaningLogEntry>> getEventsByRoom() {
        Map<String, List<CleaningLogEntry>> map = DSAFactory.getMap(new RoomComparator());
        int cleanSize = cleaningLogs.size();
        
        //Put rooms into a map with empty lists
        Iterator<RoomRecord> roomIt = roomRecords.iterator();
        while (roomIt.hasNext()) {
        	RoomRecord room = roomIt.next();
    		map.put(room.getRoomID(), DSAFactory.getIndexedList());
        }
        
        //Put all cleaning logs in a sortable array
        Iterator<CleaningLogEntry> cleanIt = cleaningLogs.iterator();
        CleaningLogEntry[] array = new CleaningLogEntry[cleanSize];
        int i = 0;
        while (cleanIt.hasNext()) {
        	CleaningLogEntry entry = cleanIt.next();
        	array[i] = entry;
        	i++;
        }
        
        //Sort cleaning logs by event precedence
        DSAFactory.getComparisonSorter(new EventComparator()).sort(array);
        
        //Add events in order to their designated Room
        int length = array.length;
        for (int j = 0; j < length; j++) {
        	map.get(array[j].getRoomID()).addFirst(array[j]);
        }
        
        //Return constructed map
        return map;
    }
    
    /**
     * Returns the square footage (as a whole number) cleaned since a provided date
     * and time. Partial square feet are rounded down to the nearest whole number.
     * 
     * @param time the time since which to calculate square footage cleaned
     * @return the square footage cleaned since the provided date and time
     */
    public int getCoverageSince(LocalDateTime time) {
        LocalDateTime tReplacement = time;
        int sum = 0;
        
        //Place all cleaning log entries into an array
        Iterator<CleaningLogEntry> cleanIt = cleaningLogs.iterator();
        CleaningLogEntry array[] = new CleaningLogEntry[cleaningLogs.size()];
        int i = 0;
        while(cleanIt.hasNext()) {
        	CleaningLogEntry log = cleanIt.next();
        	array[i] = log;
        	i++;
        }
        
        //Sort the cleaning log entries by event precedence
        DSAFactory.getComparisonSorter(new EventComparator()).sort(array);
        
        
        //Put all the rooms into a map for searching
        Map<String, RoomRecord> map = DSAFactory.getMap(null);
        Iterator<RoomRecord> roomIt2 = roomRecords.iterator();
        while (roomIt2.hasNext()) {
        	RoomRecord room = roomIt2.next();
    		map.put(room.getRoomID(), room);
        }
        
        
        //Index through the sorted array backwards, stopping if any events happen before
        int j = array.length - 1;
        RoomRecord room = null;
        while (array[j].getTimestamp().isAfter(tReplacement)) {
        	
        	//Only search for a room if the current room is not correct
        	if (room == null || !room.getRoomID().equals(array[j].getRoomID())) {
        		//Search and retrieve the correct room
        		room = map.get(array[j].getRoomID());
			}
        	
        	//Add the amount cleaned to the sum
        	sum += (room.getLength() * room.getWidth()) * ((double) array[j].getPercentCompleted() / 100.0);
        	j--;
        	
        	//Break if needed
        	if (j < 0) {
        		break;
        	}
        }
        
        //Return the sum
        return sum;
    }
    
    /**
     * Comparator used to determine the precedence between CleaningLogEntry types
     * @author clorejol
     *
     */
    private class EventComparator implements Comparator<CleaningLogEntry> {
		@Override
		public int compare(CleaningLogEntry o1, CleaningLogEntry o2) {
			return o1.compareTo(o2);
		}
    	
    }
    
    /**
     * Comparator used to determine the precedence between String types for Rooms
     * @author clorejol
     *
     */
    private class RoomComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
    	
    }
    
    
}