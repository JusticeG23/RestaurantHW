public class Party {
    private final int partySize;
    private final String partyName;

    public Party(int size, String name) {
        this.partySize = size;
        this.partyName = name;
    }

    public String printParty(Server server) {
        return this + " - Server #" + server.getServerID();
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
}