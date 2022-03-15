import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    // Use static to retain changes
    Server tst = new Server(5);

    @Test
    void serverConstructor() {
        assertEquals(tst.getServerID(), 5);
        assertTrue(tst.isWorking());
        assertEquals(tst.getTotalTips(), 0.0, 0.01);
    }

    @Test
    void addTip() {
        assertEquals(tst.getTotalTips(), 0, 0.01);
        tst.addTip(5.00);
        assertEquals(tst.getTotalTips(), 5.0, 0.01);
        tst.addTip(10.0);
        assertEquals(tst.getTotalTips(), 15.0, 0.01);

    }

    @Test
    void clockOut() {
        assertTrue(tst.isWorking());
        tst.clockOut();
        assertFalse(tst.isWorking());
    }

    @Test
    void testToString() {
        String expectedStr = "Server #5 ($0.00 in total tips)";
        assertEquals(expectedStr, tst.toString());
    }

    @Test
    void getServerID() {
        assertEquals(tst.getServerID(), 5);
    }

    @Test
    void isWorking() {
        Server tst1 = new Server(1);
        assertTrue(tst1.isWorking());
    }

    @Test
    void getTotalTips() {
        assertEquals(0, tst.getTotalTips());
        tst.addTip(15.00);
        assertEquals(15.00, tst.getTotalTips());
    }
}