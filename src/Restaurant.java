import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<Table> tableList;
    private List<Server> serverList;
    private List<Party> waitlist;
    private List<Party> seatedParties;
    private double cashRegister;
    private int nextNewServerID;
    private int nextServer;
    private final String RESTAURANT_FILE_NAME;
    private boolean successfulGeneration;

    /**
     * Constructor
     */
    public Restaurant(String fileName) throws FileNotFoundException {
        this.tableList = new ArrayList<>();
        this.serverList = new ArrayList<>();
        this.waitlist = new ArrayList<>();
        this.seatedParties = new ArrayList<>();
        this.cashRegister = 0.0;
        nextNewServerID = 1; // for creating/adding new servers to the restaurant
        this.nextServer = -1; // for getting the round robin server
        this.RESTAURANT_FILE_NAME = fileName;

        // initialize restaurant with fileName details
        this.successfulGeneration = setUpRestaurant();
    }

    private boolean setUpRestaurant() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(RESTAURANT_FILE_NAME));
        if (scanner.hasNextLine()) {
            String restaurantName = scanner.nextLine();
            if (scanner.hasNextLine()) {
                while (scanner.hasNextInt()) {
                    tableList.add(new Table(scanner.nextInt()));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the next round robin Server, either for waiting a table or clocking out
     * @return the Server who is next
     */
    private Server nextRoundRobinServer() {
        nextServer++;
        return serverList.get(nextServer % serverList.size());
    }

    public void addServer() {
        System.out.println("Adding a new server to our workforce:");
        serverList.add(new Server(nextNewServerID));
        System.out.println("Current server count: " + serverList.size());
        nextNewServerID++;
    }

    public void removeServer() {
        if (serverList.isEmpty()) {
            System.out.println("No servers to dismiss.");
        } else if (serverList.size() == 1 && !restaurantIsEmpty()) {
            // last server, not all tables are empty
            System.out.println("Sorry, the server cannot cash out now;");
            System.out.println("there are still tables remaining and this is the only server left.");
        } else {
            System.out.println("Dismissing a server:");
            Server serverRemoving = nextRoundRobinServer();
            System.out.println("Server #" + serverRemoving.getServerID() +
                    " cashes out with $" + serverRemoving.getTotalTips() +
                    " in total tips.");
            // remove the server from the serverList
            serverList.remove(serverRemoving);
            // assign any tables the server has to the other servers
            for (Table table : tableList) {
                if (table.getTableServer() == serverRemoving) {
                    table.setServer(nextRoundRobinServer());
                }
            }
            // update the number of servers available
            System.out.println("Servers now available: " + serverList.size());
        }
    }

    private boolean restaurantIsEmpty() {
        for (Table table : tableList) {
            if (table.getSeatedParty() != null) {
                return false;
            }
        }
        return true;
    }

    private boolean partyNameIsTaken(String partyName) {
        // check waitlist for the name
        for (Party party : waitlist) {
            if (party.getPartyName().equals(partyName)) {
                return true;
            }
        }
        // check seated parties for the name
        return partyNameIsSeated(partyName);
    }

    // TODO comment. assume there is at least one server
    // seats the party if they fit and there is an empty table
    // returns true if the party is put into the waitlist
    // adds them to the seated tables if they fit
    // prints an error if the party cannot fit
    // FIXME adjust so that the party is seated at the smallest fitting table
    public boolean newPartyWaitlisted(String partyName, int partySize) {
        if (partyNameIsTaken(partyName)) {
            // party name already exists
            System.out.println("We already have a party with that name in the restaurant.");
            System.out.println("Please try again with a unique party name.");
            return true; // they are not waitlisted since not a valid name
        } else {
            Party party = new Party(partySize, partyName);
            boolean partyTooBig = true;
            for (Table table : tableList) {
                if (partySize <= table.getCapacity()) {
                    // there is a table big enough to seat the party
                    partyTooBig = false;
                   if (table.getSeatedParty() == null) {
                       // empty table
                        seatedParties.add(party);
                        table.seatParty(party, nextRoundRobinServer());
                        return false;
                   }
                }
            }

            if (partyTooBig) {
                // the party cannot fit at a table in the restaurant
                System.out.println("Sorry, the restaurant is unable to seat a party of this size.");
                return false;
            }

            // party can fit, but there is no available table, return control to textUI
            // to see if they'd like to be added to the waitlist
            System.out.println("Sorry, there is no open table that can seat this party now.");
            return true;
        }
    }

    public void addPartyToWaitlist(String partyName, int partySize) {
        waitlist.add(new Party(partySize, partyName));
    }

    // TODO comment: method assumes that the party is a valid party that is seated (see partyNameSeated)
    public void checkOutParty(String partyName, double subtotal, double tip) {
        for (Table table : tableList) {
            if (table.getSeatedParty().getPartyName().equals(partyName)) {
                // server's tips
                table.getTableServer().addTip(tip);
                System.out.println("Gave tip of $" + String.format("%.02f", tip) + " to Server #" +
                        table.getTableServer().getServerID());

                // party's bill
                double total = subtotal * 1.10; // tax is 10%
                cashRegister += total;
                System.out.println("Gave total of $" + String.format("%.02f", total) + " to cash register.");

                // remove party from the table
                seatedParties.remove(table.getSeatedParty());
                table.removeParty();

                return;
            }
        }
    }

    public boolean partyNameIsSeated(String partyName) {
        for (Table table : tableList) {
            if (table.getSeatedParty() != null &&
                    table.getSeatedParty().getPartyName().equals(partyName)) {
                return true;
            }
        }
        return false;
    }

    public void printWaitlist() {
        System.out.println("Waiting list:");
        if (waitlist.isEmpty()) {
            System.out.println("empty");
        } else {
            for (Party party : waitlist) {
                System.out.println(party.toString());
            }
        }
    }

    public void printTableList() {
        System.out.println("Tables status:");
        for (int i = 0; i < tableList.size(); i++) {
            Table currTable = tableList.get(i);
            System.out.println(currTable.printTableStatus(i + 1, currTable.getSeatedParty()));
        }
    }

    public void printServerList() {
        System.out.println("Servers currently on duty:");
        for (Server server : serverList) {
            System.out.println(server);
        }
        System.out.println("Current server count: " + serverList.size());
    }

    public void printCashRegister() {
        System.out.println("Cash register:");
        System.out.println("Total money earned = $" + String.format("%.02f", cashRegister));
    }

    // Getters below:

    /**
     * Gets the current Table list
     *
     * @return the list of tables
     */
    public List<Table> getTableList() {
        return tableList;
    }

    /**
     * Gets the current Server list
     *
     * @return the list of servers
     */
    public List<Server> getServerList() {
        return serverList;
    }

    /**
     * Gets the current waitlist of Parties
     *
     * @return the list of parties
     */
    public List<Party> getWaitlist() {
        return waitlist;
    }

    /**
     * Gets the current amount of money in the cash register
     *
     * @return the double amount of money in the cash register
     */
    public double getCashRegister() {
        return cashRegister;
    }

    public boolean isSuccessfulGeneration() {
        return successfulGeneration;
    }
}