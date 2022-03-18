import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    Party tstParty = new Party(5, "Class of 2050");
    Server tstServer = new Server(9);

    @Test
    void testToString() {
        Assertions.assertEquals("Class of 2050 party of 5", tstParty.toString());
    }
    @Test
    void printParty() {
        assertEquals("Class of 2050 party of 5 - Server #9", tstParty.printParty(tstServer));
    }

    @Test
    void getPartySize() {
        assertEquals(5, tstParty.getPartySize());
    }

    @Test
    void getPartyName() {
        assertEquals("Class of 2050", tstParty.getPartyName());
    }

}