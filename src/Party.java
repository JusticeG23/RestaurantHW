public class Party {
    private int partySize;
    private String partyName;
    private boolean isSeated;
    private double billSubtotal;

    public Party(int size, String name) {
        this.partySize = size;
        this.partyName = name;
        this.isSeated = false;
        this.billSubtotal = 0.0;
    }

    // Getters below:

    /**
     * Gets the Party size
     * @return the int of the party size
     */
    public int getPartySize() {
        return partySize;
    }

    /**
     * Gets the Party name
     * @return the String of the party name
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * Returns true if the Party is seated (in the Restaurant at a Table and not on a waitlist),
     * false if the Party is not seated (in the waitlist)
     * @return boolean if the party is seated or not
     */
    public boolean isSeated() {
        return isSeated;
    }

    /**
     * Gets the subtotal of the bill for the Party
     * @return double the subtotal
     */
    public double getBillSubtotal() {
        return billSubtotal;
    }
}
