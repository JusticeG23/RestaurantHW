// EGR 326, Homework 3 (Restaurant)
// Instructor-provided code.
// You SHOULD heavily modify this file to make it interface with your own classes.

import java.io.File;
import java.io.FileNotFoundException;
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
	private final Restaurant restaurant;

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
		// Can uncomment to get input from user instead. Seems unnecessary
//		File restaurantFile = ValidInputReader.getValidFile(
//				"File name for restaurant data [" + DEFAULT_RESTAURANT_FILENAME + "]?",
//				DEFAULT_RESTAURANT_FILENAME);
		File restaurantFile = new File("tables.txt");
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
		label:
		while (true) {
			String choice = ValidInputReader.getValidString(
					"Main menu, enter your choice:",
					"^[sSaAdDrRpPtTcCwWqQ!?]$").toUpperCase();
			switch (choice) {
				case "S":
					serversOnDuty();
					break;
				case "A":
					addServer();
					break;
				case "D":
					dismissServer();
					break;
				case "R":
					cashRegister();
					break;
				case "P":
					partyToBeSeated();
					break;
				case "T":
					tableStatus();
					break;
				case "C":
					checkPlease();
					break;
				case "W":
					waitingList();
					break;
				case "Q":
					break label;
				case "?":
					displayOptions();
					break;
				case "!":
					getContent();
					break;
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
		System.out.println(restaurant.printServerList());
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
		System.out.println(restaurant.printCashRegister());
	}

	// Called when T key is pressed from main menu.
	// Displays the current status of all tables.
	private void tableStatus() {
		System.out.println(restaurant.printTableList());
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
		System.out.println(restaurant.printWaitlist());
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
		int partySize = ValidInputReader.getValidInt("How many people in the party?", 1, 8);
		// todo create party here to pass one object rather than mutliple?
		// try to seat this party
		boolean partyWaitlisted = restaurant.seatParty(partyName, partySize);

		// when all tables large enough to accommodate this party are taken,
		if (partyWaitlisted) {
			boolean wait = ValidInputReader.getYesNo("Place this party onto the waiting list? (y/n)");
			if (wait) {
				restaurant.addPartyToWaitlist(partyName, partySize);
			}
		}
	}

	/**
	 * Nondescript utility function to call a project method for implementation of program structure
	 */
	private void getContent() {
		System.out.println("""
                ⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠻⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣄⡀⠀⢻⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⠃⢰⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⢶⣶⣶⣾⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⢠⡀⠐⠀⠀⠀⠻⢿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⢸⣷⡄⠀⠣⣄⡀⠀⠉⠛⢿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⣿⣿⣦⠀⠹⣿⣷⣶⣦⣼⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣷⣄⣸⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                ⣿⣿⡿⢛⡙⢻⠛⣉⢻⣉⢈⣹⣿⣿⠟⣉⢻⡏⢛⠙⣉⢻⣿⣿⣿
                ⣿⣿⣇⠻⠃⣾⠸⠟⣸⣿⠈⣿⣿⣿⡀⠴⠞⡇⣾⡄⣿⠘⣿⣿⣿
                ⣿⣿⣟⠛⣃⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿""");
	}

}
