public class Party {
    private int partySize;
    private String partyName;
    private double billSubtotal; //FIXME needed?

    public Party(int size, String name) {
        this.partySize = size;
        this.partyName = name;
        this.billSubtotal = 0.0;
    }

    /**
     * TODO COMMENT
     * @param server
     * @return
     */
    public String printParty(Server server) {
        return this.toString() + " - Server #" + server.getServerID();
    }

    @Override
    public String toString() {
        return partyName + " party of " + partySize;
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
     * Gets the subtotal of the bill for the Party
     * @return double the subtotal
     */
    public double getBillSubtotal() {
        return billSubtotal;
    }

    /**
     * Calculates the total bill not including tips (subtotal and 10% of the subtotal
     * as the sales tax)
     * @return double the subtotal and tax
     */
    public double getTotal() {
        return billSubtotal * 1.10;
    }
}
