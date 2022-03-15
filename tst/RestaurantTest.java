import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    void addHelper(Restaurant rest, int num) {
        for (int i = 0; i < num; i++) {
            rest.addServer();
        }
    }
    @org.junit.jupiter.api.Test
    void addParty() {

    }

    @org.junit.jupiter.api.Test
    void testAddParty() {
    }

    @org.junit.jupiter.api.Test
    void addServer() throws FileNotFoundException {
        Restaurant tst = new Restaurant("tables.txt");
        assertEquals(tst.getServerList().size(), 0);
        addHelper(tst, 3);
        assertEquals(tst.getServerList().size(), 3);
    }

    @org.junit.jupiter.api.Test
    void removeServer() throws FileNotFoundException {
        Restaurant tst = new Restaurant("tables.txt");
        assertEquals(tst.getServerList().size(), 0);
        addHelper(tst, 3);
        assertEquals(tst.getServerList().size(), 3);
    }

    @org.junit.jupiter.api.Test
    void newPartyWaitlisted() {
    }

    @org.junit.jupiter.api.Test
    void waitlistParty() {
    }

    @org.junit.jupiter.api.Test
    void checkOutParty() {
    }

    @org.junit.jupiter.api.Test
    void partyNameSeated() {
    }

    @org.junit.jupiter.api.Test
    void printWaitlist() {
    }

    @org.junit.jupiter.api.Test
    void printTableList() {
    }

    @org.junit.jupiter.api.Test
    void printServerList() {
    }

    @org.junit.jupiter.api.Test
    void printCashRegister() {
    }

    @org.junit.jupiter.api.Test
    void getTableList() {
    }

    @org.junit.jupiter.api.Test
    void getServerList() {
    }

    @org.junit.jupiter.api.Test
    void getWaitlist() {
    }

    @org.junit.jupiter.api.Test
    void getCashRegister() {
    }

    @org.junit.jupiter.api.Test
    void isSuccessfulGeneration() {
    }
}