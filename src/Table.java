public class Table {
    private boolean filled;
    private final int capacity;
    private Party seatedParty;
    private Server tableServer;

    public Table(int capacity) {
        filled = false;
        this.capacity = capacity;
        this.seatedParty = null;
        this.tableServer = null;
    }

    public void setServer(Server newServer) {
        tableServer = newServer;
    }

    /**
     * Assigns a party and server to table, so that it is filled
     * @param party group assigned to table
     * @param server attendant assigned to table
     * */
    // todo refactor to not contain server (seatTable(party, server) vs seatParty(party))
    public void seatParty(Party party, Server server) {
        filled = true;
        seatedParty = party;
        tableServer = server;
    }

    /**
     * If current table has a party, removes party and server. Else does nothing
     */
    public void removeParty() {
        if (this.getSeatedParty() != null) {
            filled = false;
            seatedParty = null;
            tableServer = null;
        }
    }

    /**
     * Similar to toString, it prints the table's ID, capacity, and vacancy status
     * @param party Party object assigned to table
     * @return String representation of pertinent data
     */
    // todo refactor to not need extra parameter
    public String printTableStatus(int tableID, Party party) {
        String tableStatus = party != null ? party.printParty(getTableServer()) : "empty";
        return "Table " + tableID + " (" + capacity + "-top): " + tableStatus;
    }

    // Getters below:

    /**
     * Gets whether the table is filled
     * @return boolean to represent vacancy
     */
    public boolean isFilled() {
        return filled;
    }
    /**
     * Gets the capacity of the Table
     * @return int the maximum number of people the table can hold
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * returns Party assigned to table
     * @return Party object
     */
    public Party getSeatedParty() {
        return seatedParty;
    }
    /**
     * returns Server assigned to table
     * @return server object
     */
    public Server getTableServer() {
        return tableServer;
    }
}