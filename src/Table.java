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
    public void seatParty(Party party, Server server) {
        filled = true;
        seatedParty = party;
        tableServer = server;
    }

    /**
     * Removes party from current table, and therefore relieve the server too
     */
    public void removeParty() {
        filled = false;
        seatedParty = null;
        tableServer = null;
    }

    /**
     * Similar to toString, it prints the table's ID, capacity, and vacancy status
     * @param party Party object assigned to table
     * @return String representation of pertinent data
     */
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