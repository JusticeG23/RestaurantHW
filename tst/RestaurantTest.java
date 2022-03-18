import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant restaurant = new Restaurant("tables.txt");
    // Need this to throw exception in case tables.txt gets moved
    RestaurantTest() throws FileNotFoundException {}


    @Test
    void RestaurantConstructor() {
//        assertEquals(, restaurant.getTableList());

//        this.tableList = new ArrayList<>();
//        this.serverList = new ArrayList<>();
//        this.waitlist = new ArrayList<>();
//        this.seatedParties = new ArrayList<>();
//        this.cashRegister = 0.0;
//        this.nextNewServerID = 1; // for creating/adding new servers to the restaurant
//        this.nextServer = -1; // for getting the round-robin server
//        this.RESTAURANT_FILE_NAME = fileName;
    }

    @Test
    void addParty() {

    }

    @Test
    void testAddParty() {
    }

    @Test
    void addServer() {
        assertEquals(restaurant.getServerList().size(), 0);
        addHelper(restaurant, 3);
        assertEquals(restaurant.getServerList().size(), 3);
    }

    @Test
    void removeServer() {
        assertEquals(restaurant.getServerList().size(), 0);
        addHelper(restaurant, 3);
        assertEquals(restaurant.getServerList().size(), 3);
    }

    @Test
    void newPartyWaitlisted() {
    }

    @Test
    void waitlistParty() {
    }

    @Test
    void checkOutParty() {
    }

    @Test
    void partyNameIsSeated() {
    }

    @Test
    void printWaitlist() {
    }

    @Test
    void printTableList() {
    }

    @Test
    void printServerList() {
    }

    @Test
    void printCashRegister() {
    }

    @Test
    void getTableList() {
    }

    @Test
    void getServerList() {
    }

    @Test
    void getWaitlist() {
    }

    @Test
    void getCashRegister() {
    }

    @Test
    void isSuccessfulGeneration() {
    }

    void addHelper(Restaurant r, int num) {
        for (int i = 0; i < num; i++) {
            r.addServer();
        }
    }
}