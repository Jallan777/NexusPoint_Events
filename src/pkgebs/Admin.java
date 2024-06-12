/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;


/**
 *
 * @author jacob
 */
public class Admin {

    // A class that can view all user details from within the program, and see booking amounts for events.
    // Class should also be able to add an event that users are then able to see, and also delete events. 
    private String password;
    private String adminName;
    private int totalEvents;
    private int totalBookings;
    private int totalUsers;
    private int totalBookedUsers;

    public Admin(String uName, String pWord) {
        this.password = pWord;
        this.adminName = uName;
        setTotalUsers();
        setTotalEvents();
        setTotalBookings();
        setTotalBookedUsers();

    }

    public Admin() {

        this.password = null;
        this.adminName = null;
        setTotalUsers();
        setTotalEvents();
        setTotalBookings();
        setTotalBookedUsers();
    }

    public void setTotalUsers() {
        UsersDB users = new UsersDB();
        int tUsers = users.getUserCount(); //FileHandling.countLines("users.txt");
        this.totalUsers = tUsers;
    }

    public void setTotalEvents() {
        EventsDB events = new EventsDB();
        int tEvents = events.getEventCount(); //FileHandling.countLines("events.txt");
        this.totalEvents = tEvents;
    }

    public void setTotalBookings() {
        BookingsDB bookings = new BookingsDB();
        int tBookings = bookings.getBookingsCount(); //FileHandling.countLines("bookings.txt");
        this.totalBookings = tBookings;
    }

    public void setTotalBookedUsers() {
        UsersDB users = new UsersDB();
        int tBookedUsers = users.getBookedUserCount(); //FileHandling.countLineWithSomething("users.txt", 5);
        this.totalBookedUsers = tBookedUsers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getTotalBookedUsers() {
        return totalBookedUsers;
    }

    public String getPassword() {
        return password;
    }

    public static boolean adminLogin(String adminUname, String adminPassword) {

        Admin admin1 = FileHandling.readAdminDetailsFromFile();

        String adminName = admin1.getAdminName();
        String adminPass = admin1.getPassword();

        return adminUname.equals(adminName) && adminPassword.equals(adminPass);

    }

    public static String[] adminGreeting() {

        String[] adMenuAsks = {"\tHello Administrator. What would you like to do today?",
            "\tGreetings, honored one! What task will you choose today?",
            "\tWelcome back, Grandest User! What's on your agenda?",
            "\tYour Administrator powers, on display!",
            "\tGood Day Admin! What option do you want to select?",
            "\tSelecteth thine task of preference, ye Lord."};

        return adMenuAsks;
    }

}
