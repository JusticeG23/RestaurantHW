import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<Table> tableList;
    private List<Server> serverList;
    private List<Party> waitlist;
    private double cashRegister;

    /**
     * Constructor
     */
    public Restaurant() {
        this.tableList = new ArrayList<>();
        this.serverList = new ArrayList<>();
        this.waitlist = new ArrayList<>();
        this.cashRegister = 0.0;
    }

    public void setUpRestaurant() {
        // TODO add tables based on txt file
    }

    public void addParty() {
        // TODO add a Party to the waitlist
    }


    public void removeParty() {
        // TODO remove a Party from the waitlist
    }


    // Getters below:

    /**
     * Gets the current Table list
     * @return the list of tables
     */
    public List<Table> getTableList() {
        return tableList;
    }

    /**
     * Gets the current Server list
     * @return the list of servers
     */
    public List<Server> getServerList() {
        return serverList;
    }

    /**
     * Gets the current waitlist of Parties
     * @return the list of parties
     */
    public List<Party> getWaitlist() {
        return waitlist;
    }

    /**
     * Gets the current amount of money in the cash register
     * @return the double amount of money in the cash register
     */
    public double getCashRegister() {
        return cashRegister;
    }
}
