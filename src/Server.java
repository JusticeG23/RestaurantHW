import java.util.ArrayList;
import java.util.List;

public class Server {
    private int serverID;
    private List<Table> tablesServing;
    private boolean isWorking;
    private double totalTips;

    public Server(int id, boolean isWorking) {
        this.serverID = id;
        this.tablesServing = new ArrayList<>();
        this.isWorking = isWorking;
        this.totalTips = 0.0;
    }

    /**
     * Adds a new Table to the list of tables the Server is helping
     * @param table the Table to add
     */
    public void addTable(Table table) {
        this.tablesServing.add(table);
    }

    /**
     * Removes the Table from the list of tables the Server is helping
     * @param table the Table to remove
     */
    public void removeTable(Table table) {
        this.tablesServing.remove(table);
    }

    /**
     * Adds the tip to the server's total tips
     * @param tip the amount to add
     */
    public void addTip(Double tip) {
        this.totalTips+= tip;
    }

    /**
     * Changes the current work status.
     * If currently working, makes isWorking false; if not working, makes isWorking true
     */
    public void changeWorkStatus() {
        this.isWorking = !this.isWorking;
    }

    // Getters below:

    /**
     * Gets the Server ID number
     * @return the int of the server's id
     */
    public int getServerID() {
        return serverID;
    }

    /**
     * Gets the current list of Tables the Server is helping
     * @return the list of tables
     */
    public List<Table> getTablesServing() {
        return tablesServing;
    }

    /**
     * Returns true if the Server is working, false if the Server is not working
     * @return boolean if the server is working or not
     */
    public boolean isWorking() {
        return isWorking;
    }

    /**
     * Gets the current amount of money the Server has made in tips
     * @return the double amount of tips the server has collected
     */
    public double getTotalTips() {
        return totalTips;
    }
}
