public class Table {
    private int capacity;
    private boolean isOccupied;

    public Table(int capacity) {
        this.capacity = capacity;
        this.isOccupied = false;
    }

    /**
     * Changes the status of the Table so that isOccupied is true when a Party is seated
     */
    public void seatParty() {
        this.isOccupied = true;
    }

    /**
     * Changes the status of the Table so that isOccupied is false when a Party leaves
     */
    public void removeParty() {
        this.isOccupied = false;
    }

    // Getters below:

    /**
     * Gets the capacity of the Table
     * @return int the maximum number of people the table can hold
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns true if the Table is occupied, false if the Table is not occupied
     * @return boolean if the table is occupied or not
     */
    public boolean isOccupied() {
        return isOccupied;
    }
}
