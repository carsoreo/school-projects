package edu.ncsu.csc316.cleaning.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.cleaning.manager.ReportManager;

/**
 * UI class which uses the terminal as the UI and handles
 * user input and interacts with the manager classes to accomplish
 * what the user requests.
 * 
 * @author clorejol
 *
 */
public class CleaningManagerUI {
	
	/**
	 * Empty constructor
	 */
	public CleaningManagerUI() {
		//Left empty
	}
	
	/**
	 * Prints the menu
	 */
	public static void initializeUI() {
		System.out.println("What would you like to do? Type the number.");
		System.out.println("View the Most Frequently Cleaned Rooms - 1");
		System.out.println("View a Report of Cleanings by Room - 2");
		System.out.println("View Estimated Remaining Vacuum Bag Life - 3");
		System.out.println("Quit - 4");
		System.out.println();
	}
	
	/**
	 * Handles all of UI. Calls methods from other classes and
	 * prompts user for info. Prints info user requests.
	 * 
	 * @param string command line arguements
	 * @throws FileNotFoundException if files user specifies is not found.
	 */
	public static void main(String[] string) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		ReportManager reportManager = null;
		while(true) {
			System.out.print("Enter room information file: ");
			
			String roomInfo = scan.nextLine();
			System.out.println();
			
			System.out.print("Enter cleaning information file: ");
			String cleaningInfo = scan.nextLine();
			System.out.println();
			
			try {
				reportManager = new ReportManager(roomInfo, cleaningInfo);
				break;
			} catch(Exception e) {
				System.out.println("At least one file was invalid.\n");
			}
		}

		String userInput = "";
		while(!"4".equals(userInput)) {
			initializeUI();
			userInput = scan.nextLine();
			System.out.println();
			
			if("1".equals(userInput)) {
				System.out.println("How many rooms to view?");
				System.out.println();
				int userInt = scan.nextInt();
				System.out.println();
				System.out.println(reportManager.getFrequencyReport(userInt));
				System.out.println();
				userInput = scan.nextLine();
				continue;
			}
			else if ("2".equals(userInput)) {
				System.out.println(reportManager.getRoomReport());
				System.out.println();
				userInput = "";
				continue;
			}
			else if ("3".equals(userInput)) {
				System.out.println("Please enter date and time in format MM/dd/yyyy HH:mm:ss");
				System.out.println();
				String dateAndTime = scan.nextLine();
				System.out.println();
				System.out.println(reportManager.getVacuumBagReport(dateAndTime));
				System.out.println();
				userInput = "";
				continue;
			}
			else if ("4".equals(userInput)) {
				break;
			}
			else {
				System.out.println("Please enter 1, 2, 3, or 4");
				System.out.println();
				userInput = "";
				continue;
			}
			
		}
		
		
		scan.close();
		System.exit(0);
	}
}
