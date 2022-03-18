import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    Table table = new Table(8);
    Server server = new Server(0);
    Party party = new Party(3, "Macklemore");
    @Test
    void TableConstructor() {
        assertFalse(table.isFilled());
        assertEquals(8, table.getCapacity());
        assertNull(table.getSeatedParty());
        assertNull(table.getTableServer());
    }

    @Test
    void setServer() {
        table.setServer(server);
        assertNotNull(table.getTableServer());
    }

    @Test
    void seatParty() {
        table.seatParty(party, server);
        assertTrue(table.isFilled());
        assertEquals(party, table.getSeatedParty());
        assertEquals(server, table.getTableServer());
    }

    @Test
    void removeParty() {
        table.removeParty();
        assertFalse(table.isFilled());
        assertNull(table.getSeatedParty());
        assertNull(table.getTableServer());
    }

    @Test
    void printTableStatus() {
        assertEquals("Table 13 (8-top): empty", table.printTableStatus(13, null));
    }

    @Test
    void isFilled() {
        assertFalse(table.isFilled());
    }

    @Test
    void getCapacity() {
        assertEquals(8, table.getCapacity());
    }

    @Test
    void getSeatedParty() {
        table.seatParty(party, server);
        assertEquals(party, table.getSeatedParty());
    }

    @Test
    void getTableServer() {
        table.setServer(server);
        assertEquals(server, table.getTableServer());
    }
}