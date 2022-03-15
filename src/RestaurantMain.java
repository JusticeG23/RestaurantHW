// Restaurant Homework 
// Instructor-provided code; do not modify.
// Justice - https://github.com/cbu-egr326-sp22/hw3-JusticeG23.git

import java.io.FileNotFoundException;

/**
 * This class contains the main method to run the overall program.
 */
public class RestaurantMain {
	public static void main(String[] args) throws FileNotFoundException {
		// read restaurant data and then show main menu
		RestaurantTextUI textUI = new RestaurantTextUI();
		if (textUI.readRestaurantData()) {
			textUI.mainMenu();
			System.out.println();
			System.out.println("Goodbye!");
		} else {
			System.out.println("Exiting.");
		}
	}
}
