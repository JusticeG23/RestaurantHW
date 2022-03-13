public class Table {
    private int capacity;
    private Party seatedParty;
    private Server tableServer;

    public Table(int capacity) {
        this.capacity = capacity;
        this.seatedParty = null;
        this.tableServer = null;
    }

    // TODO comment
    public void seatParty(Party party, Server server) {
        seatedParty = party;
        tableServer = server;
    }

    // TODO COMMENT
    public void removeParty() {
        seatedParty = null;
        tableServer = null;
    }

    /**
     * TODO COMMENT
     * @param party
     * @return
     */
    public String printTableStatus(int tableID, Party party) {
        String tableStatus = party != null ? party.printParty(getTableServer()) : "empty";
        return "Table " + tableID + " (" + capacity + "-top): " + tableStatus;
    }

    // Getters below:

    /**
     * Gets the capacity of the Table
     * @return int the maximum number of people the table can hold
     */
    public int getCapacity() {
        return capacity;
    }

    // TODO COMMENT
    public Party getSeatedParty() {
        return seatedParty;
    }

    // TODO COMMENT
    public Server getTableServer() {
        return tableServer;
    }
}
