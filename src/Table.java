public class Table {
    private final int capacity;
    private Party seatedParty;
    private Server tableServer;

    public Table(int capacity) {
        this.capacity = capacity;
        this.seatedParty = null;
        this.tableServer = null;
    }

    public void seatParty(Party party, Server server) {
        seatedParty = party;
        tableServer = server;
    }

    public void removeParty() {
        seatedParty = null;
        tableServer = null;
    }

    public String printTableStatus(int tableID, Party party) {
        String tableStatus = party != null ? party.printParty(getTableServer()) : "empty";
        return "Table " + tableID + " (" + capacity + "-top): " + tableStatus;
    }

    public void setServer(Server newServer) {
        tableServer = newServer;
    }

    // Getters below:
    /**
     * Gets the capacity of the Table
     * @return int the maximum number of people the table can hold
     */
    public int getCapacity() {
        return capacity;
    }

    public Party getSeatedParty() {
        return seatedParty;
    }

    public Server getTableServer() {
        return tableServer;
    }
}
