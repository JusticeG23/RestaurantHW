public class Server {
    private int serverID;
    private double totalTips;

    public Server(int id) {
        this.serverID = id;
        this.totalTips = 0.0;
    }

    /**
     * Adds the tip to the server's total tips
     * @param tip the amount to add
     */
    public void addTip(Double tip) {
        totalTips+= tip;
    }

    /**
     * TODO COMMENT
     * @return
     */
    @Override
    public String toString() {
        return "Server #" + serverID + " ($" + String.format("%.02f", totalTips) + " in total tips)";
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
     * Gets the current amount of money the Server has made in tips
     * @return the double amount of tips the server has collected
     */
    public double getTotalTips() {
        return totalTips;
    }
}
