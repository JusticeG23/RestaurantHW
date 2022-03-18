import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {


    Restaurant restaurant = new Restaurant("tables.txt");
    Party party = new Party(5, "Sam's Club");
    // Need this to throw exception in case tables.txt gets moved
    RestaurantTest() throws FileNotFoundException {}


    @Test
    void RestaurantConstructor() {
        assertNotNull(restaurant.getTableList());
        assertNotNull(restaurant.getServerList());
        assertNotNull(restaurant.getWaitlist());
        assertNotNull(restaurant.getSeatedParties());
        assertEquals(0.0, restaurant.getCashRegister(), 0.01);
    }

    @Test
    void addParty() {
        addHelper(restaurant, 3);
        assertEquals(3, restaurant.getServerList().size());
    }

    @Test
    void testAddParty() {
        assertTrue(restaurant.getWaitlist().isEmpty());
        restaurant.addServer();
        restaurant.addParty(party);
        assertEquals(1, restaurant.getWaitlist().size());
    }

    @Test
    void addServer() {
        assertEquals(0, restaurant.getServerList().size());
        addHelper(restaurant, 3);
        assertEquals(restaurant.getServerList().size(), 3);
    }

    @Test
    void removeServer() {
        assertEquals(restaurant.getServerList().size(), 0);
        addHelper(restaurant, 3);
        assertEquals(restaurant.getServerList().size(), 3);
    }
    // todo test isEmpty
    // todo test partyName taken

    @Test
    // todo more coverage
    void seatPartyTest() {
        assertThrows(Exception.class, () -> {
            restaurant.seatParty(party.getPartyName(), party.getPartySize());
        });
        restaurant.addServer();
        assertFalse(restaurant.seatParty(party.getPartyName(), party.getPartySize()));
    }

    @Test
    void waitlistParty() {
        addHelper(restaurant, 8);
        assertEquals("""
                Servers currently on duty:Server #1 ($0.00 in total tips)
                Server #2 ($0.00 in total tips)
                Server #3 ($0.00 in total tips)
                Server #4 ($0.00 in total tips)
                Server #5 ($0.00 in total tips)
                Server #6 ($0.00 in total tips)
                Server #7 ($0.00 in total tips)
                Server #8 ($0.00 in total tips)
                Current server count: 8""", restaurant.printServerList());
    }

    @Test
    void checkOutParty() {
        // todo ensure seated parties is being edited
        restaurant.addServer();
        restaurant.seatParty("a", 2);
        ArrayList<Party> numParties = (ArrayList<Party>) restaurant.getSeatedParties();
        restaurant.checkOutParty("a", 80, 5);
        assertEquals("Cash register:\nTotal money earned = $88.00", restaurant.printCashRegister());
        assertEquals(numParties, (ArrayList<Party>) restaurant.getSeatedParties());

    }

    @Test
    void partyNameIsSeated() {
        restaurant.addServer();
        restaurant.seatParty("a", 2);
        assertTrue(restaurant.partyNameIsSeated("a"));
    }

    @Test
    void printWaitlist() {
        assertEquals("""
                Waiting list:
                empty
                """, restaurant.printWaitlist());
        restaurant.addPartyToWaitlist("a", 2);
        assertEquals("Waiting list:\na party of 2", restaurant.printWaitlist());

    }

    @Test
    void printTableList() {
        assertEquals("""
                Tables status:
                Table 1 (4-top): empty
                Table 2 (4-top): empty
                Table 3 (8-top): empty
                Table 4 (2-top): empty
                Table 5 (2-top): empty
                Table 6 (6-top): empty
                Table 7 (4-top): empty
                Table 8 (2-top): empty
                """, restaurant.printTableList());
        fillRestaurant(restaurant);
        assertEquals("""
                Tables status:
                Table 1 (4-top): d party of 2 - Server #4
                Table 2 (4-top): e party of 2 - Server #5
                Table 3 (8-top): h party of 2 - Server #8
                Table 4 (2-top): a party of 2 - Server #1
                Table 5 (2-top): b party of 2 - Server #2
                Table 6 (6-top): g party of 2 - Server #7
                Table 7 (4-top): f party of 2 - Server #6
                Table 8 (2-top): c party of 2 - Server #3
                """, restaurant.printTableList());

    }

    @Test
    void printServerList() {
        assertEquals("Waiting list:\nempty\n", restaurant.printWaitlist());
        addHelper(restaurant, 8);
        fillRestaurant(restaurant);
        // this will be put on waitinglist
        restaurant.addPartyToWaitlist("i", 2);
        assertEquals("Waiting list:\ni party of 2", restaurant.printWaitlist());


    }

    @Test
    void printCashRegister() {
        assertEquals("Cash register:\nTotal money earned = $0.00", restaurant.printCashRegister());
        restaurant.addServer();
        restaurant.seatParty("Macklemore", 5);
        restaurant.checkOutParty("Macklemore", 40, 5);
        assertEquals("Cash register:\nTotal money earned = $44.00", restaurant.printCashRegister());

    }

    @Test
    void getTableList() {
        assertNotNull(restaurant.getTableList());
    }

    @Test
    void getServerList() {
        assertNotNull(restaurant.getServerList());
    }

    @Test
    void getWaitlist() {
        // todo add to waitlist
        assertNotNull(restaurant.getWaitlist());
    }

    @Test
    void getCashRegister() {
        assertEquals(0.0, restaurant.getCashRegister());
    }

    @Test
    void isSuccessfulGeneration() {
        assertTrue(restaurant.isSuccessfulGeneration());
    }

    void addHelper(Restaurant r, int num) {
        for (int i = 0; i < num; i++) {
            r.addServer();
        }
    }

    void fillRestaurant(Restaurant r) {
        r.addServer();
        r.addServer();
        r.addServer();
        r.addServer();
        r.addServer();
        r.addServer();
        r.addServer();
        r.addServer();
        r.seatParty("a", 2);
        r.seatParty("b", 2);
        r.seatParty("c", 2);
        r.seatParty("d", 2);
        r.seatParty("e", 2);
        r.seatParty("f", 2);
        r.seatParty("g", 2);
        r.seatParty("h", 2);
    }
}