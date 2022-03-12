import java.util.ArrayList;
import java.util.List;

public class Server {
    private int serverID;
    private boolean isWorking;
    private double totalTips;

    public Server(int id) {
        this.serverID = id;
        this.isWorking = true;
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
     * Changes the current work status to make isWorking false when the Server clocks out
     */
    public void clockOut() {
        isWorking = false;
    }

    /**
     * TODO COMMENT
     * @return
     */
    @Override
    public String toString() {
        return "Server #" + serverID + " ($" + totalTips + " in total tips)";
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
