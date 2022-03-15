// EGR 326, Homework 3 (Restaurant)
// Instructor-provided code.
// You SHOULD heavily modify this file to make it interface with your own classes.

import java.io.*;
// import java.util.*;

/**
 * This class represents the text user interface (UI) for the restaurant
 * program, allowing the user to view and manage the restaurant and its objects.
 * 
 * @author Marty Stepp
 * @version Spring 2011 v1.0
 */
public class RestaurantTextUI {
	// file name from which to read the restaurant data
	private static final String DEFAULT_RESTAURANT_FILENAME = "tables.txt";
	private Restaurant restaurant;
	
	/**
	 * Constructs a new text user interface for managing a restaurant.
	 */
	public RestaurantTextUI() throws FileNotFoundException {
		System.out.println("EGR 326 Restaurant Simulator");
		restaurant = new Restaurant(DEFAULT_RESTAURANT_FILENAME);
	}
	
	/**
	 * Reads the information about the restaurant from the default restaurant
	 * file.
	 * @return true if the data was read successfully; false if there were any errors
	 */
	public boolean readRestaurantData() {
		File restaurantFile = ValidInputReader.getValidFile(
				"File name for restaurant data [" + DEFAULT_RESTAURANT_FILENAME + "]?",
				DEFAULT_RESTAURANT_FILENAME);

		boolean successfulGeneration = restaurant.isSuccessfulGeneration();
		if (successfulGeneration) {
			System.out.println("read restaurant info from tables file: " + restaurantFile);
		} else {
			// when there is an error reading the file,
			System.out.println("Unable to read restaurant data: file not found.");
		}
		
		System.out.println();
		return successfulGeneration;
	}
	
	/**
	 * Displays the main menu of choices and prompts the user to enter a choice.
	 * Once a valid choice is made, initiates other code to handle that choice.
	 */
	public void mainMenu() {
		// main menu
		displayOptions();
		while (true) {
			String choice = ValidInputReader.getValidString(
					"Main menu, enter your choice:",
					"^[sSaAdDrRpPtTcCwWqQ!?]$").toUpperCase();
			if (choice.equals("S")) {
				serversOnDuty();
			} else if (choice.equals("A")) {
					addServer();
			} else if (choice.equals("D")) {
				dismissServer();
			} else if (choice.equals("R")) {
				cashRegister();
			} else if (choice.equals("P")) {
				partyToBeSeated();
			} else if (choice.equals("T")) {
				tableStatus();
			} else if (choice.equals("C")) {
				checkPlease();
			} else if (choice.equals("W")) {
				waitingList();
			} else if (choice.equals("Q")) {
				break;
			} else if (choice.equals("?")) {
				displayOptions();
			} else if (choice.equals("!")) {
				rickRoll();
			}
			System.out.println();
		}
	}
	
	// Displays the list of key commands the user can use.
	private void displayOptions() {
		System.out.println();
		System.out.println("Main System Menu");
		System.out.println("----------------");
		System.out.println("S)ervers on duty");
		System.out.println("A)dd server");
		System.out.println("D)ismiss server");
		System.out.println("R)egister");
		System.out.println("P)arty has arrived");
		System.out.println("T)ables status");
		System.out.println("C)heck, please");
		System.out.println("W)aiting list");
		System.out.println("?) display this menu of choices again");
		System.out.println("Q)uit");
	}
	
	// Called when S key is pressed from main menu.
	// Displays all servers who are currently working.
	private void serversOnDuty() {
		restaurant.printServerList();
	}
	
	// Called when A key is pressed from main menu.
	// Adds one more server to the system.
	private void addServer() {
		restaurant.addServer();
	}
	
	// Called when D key is pressed from main menu.
	// Sends one server home for the night (if possible).
	private void dismissServer() {
		restaurant.removeServer();
	}
	
	// Called when R key is pressed from main menu.
	// Displays how much money is in the restaurant's cash register.
	private void cashRegister() {
		restaurant.printCashRegister();
	}
	
	// Called when T key is pressed from main menu.
	// Displays the current status of all tables.
	private void tableStatus() {
		restaurant.printTableList();
	}
	
	// Called when C key is pressed from main menu.
	// Helps process a party's check to leave the restaurant.
	private void checkPlease() {
		System.out.println("Send the check to a party that has finished eating:");
		String partyName = ValidInputReader.getValidString("Party's name?", "^[a-zA-Z '-]+$");
		if (restaurant.partyNameIsSeated(partyName)) {
			// when such a party is sitting at a table in the restaurant,
			double subtotal = ValidInputReader.getValidDouble("Bill subtotal?", 0.0, 9999.99);
			double tip = ValidInputReader.getValidDouble("Tip?", 0.0, 9999.99);

			restaurant.checkOutParty(partyName, subtotal, tip);
		} else {
			// a party with partyName is NOT sitting at a table in the restaurant
			System.out.println("There is no party by that name.");
		}
	}
	
	// Called when W key is pressed from main menu.
	// Displays the current waiting list, if any.
	private void waitingList() {
		restaurant.printWaitlist();
	}
	
	// Called when P key is pressed from main menu.
	// Helps seat a newly arriving party at a table in the restaurant.
	private void partyToBeSeated() {
		// when there are no servers,
		if (restaurant.getServerList().isEmpty()) {
			System.out.println("Sorry, there are no servers here yet to seat this party");
			System.out.println("and take their orders.  Add servers and try again.");
			return;
		}

		// when there is at least one server,
		String partyName = ValidInputReader.getValidString("Party's name?", "^[a-zA-Z '-]+$");
		int partySize = ValidInputReader.getValidInt("How many people in the party?", 1, 99999);

		// try to seat this party
		boolean partyWaitlisted = restaurant.newPartyWaitlisted(partyName, partySize);
		
		// when all tables large enough to accommodate this party are taken,
		if (partyWaitlisted) {
			boolean wait = ValidInputReader.getYesNo("Place this party onto the waiting list? (y/n)");
			if (wait) {
				restaurant.addPartyToWaitlist(partyName, partySize);
			}
		}
	}
	
	
	// You know what this method does.
	private void rickRoll() {
		// tell you how I'm feeling; make you understand
		System.out.println("We're no strangers to love");
		System.out.println("You know the rules and so do I");
		System.out.println("A full commitment's what I'm thinking of");
		System.out.println("You wouldn't get this from any other guy");
		System.out.println("I just wanna tell you how I'm feeling");
		System.out.println("Gotta make you understand");
		System.out.println();
		System.out.println("Never gonna give you up");
		System.out.println("Never gonna let you down");
		System.out.println("Never gonna run around and desert you");
		System.out.println("Never gonna make you cry");
		System.out.println("Never gonna say goodbye");
		System.out.println("Never gonna tell a lie and hurt you");
	}
}
