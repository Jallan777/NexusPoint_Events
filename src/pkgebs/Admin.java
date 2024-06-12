/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

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
    
    public Admin(){
    
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

    
    public static String[] adminGreeting(){
    
        String[] adMenuAsks = {"\tHello Administrator. What would you like to do today?",
            "\tGreetings, honored one! What task will you choose today?",
            "\tWelcome back, Grandest User! What's on your agenda?",
            "\tYour Administrator powers, on display!",
            "\tGood Day Admin! What option do you want to select?",
            "\tSelecteth thine task of preference, ye Lord."};
        
        return adMenuAsks;
    }
    
    public static void adminMenu() {

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;

        

        String adminGreet = Main.generateMenuAsk(adminGreeting());
        System.out.println(adminGreet);
        Tools.generateLine(adminGreet.length(), "*");

        System.out.println();
        System.out.println("1: Event Management\n"
                + "2: User Management\n"
                + "3: Admin Details\n"
                + "4: Main Menu\n"
                + "5: Generate Report\n\n"
                + "0: Exit Program");

        while (!validInput) {

            Admin adminOW = FileHandling.readAdminDetailsFromFile();
            String newAdName = "";
            String newAdPass = "";
            String pWord = "";
            String name = "";
            System.out.println("Please enter a number: ");
            int input;

            try {
                input = scan.nextInt();
                validInput = true;
            } catch (InputMismatchException ohno) {
                System.out.println("Invalid Input. Please enter a valid number");
                scan.next();
                validInput = false;
                continue;
            }

            switch (input) {

                case 1:

                    System.out.println("\n\n\n\tEvent Management");
                    Tools.generateLine(30, "*");
                    EventManagement.adminEventMenu();

                    break;

                case 2:

                    System.out.println("\n\n\n\tUser Management");
                    Tools.generateLine(30, "*");
                    UserManagement.adminUserMenu();

                    break;

                case 3:
                    boolean safeIf = false;
                    String subInput;
                    System.out.println("\n\tAdministrator Details");
                    Tools.generateLine(35, "*");
                    System.out.println("Admin Name:\t" + adminOW.getAdminName() + "\n");
                    System.out.println("Admin Password:\t" + adminOW.getPassword() + "\n\n");
                    System.out.println("Change Details?[Y/N]");

                    while (!safeIf) {

                        try {
                            subInput = scan.nextLine();
                            safeIf = true;
                        } catch (InputMismatchException oops) {

                            System.out.println("Invalid Input. Please enter a valid number");
                            scan.next();
                            safeIf = false;
                            continue;
                        }

                        switch (subInput.toUpperCase()) {

                            case "Y":
                                System.out.println("New Admin Name: ");
                                newAdName = scan.nextLine();

                                System.out.println("New Admin Password: ");
                                newAdPass = scan.nextLine();

                                adminOW.setAdminName(newAdName);
                                adminOW.setPassword(newAdPass);
                                FileHandling.writeAdminToFile(adminOW);
                                safeIf = true;
                                System.out.println("\nAdmin Name and Password Successfully Changed\n");

                                adminMenu();
                                break;

                            case "N":
                                safeIf = true;
                                adminMenu();
                                break;

                            case "0":
                                System.exit(0);

                            default:
                                System.out.println("Please input \"Y\" or \"N\", or \"0\" to exit the Program\n");

                                safeIf = false;
                                break;
                        }

                    }

                    break;

                case 4:
                    System.out.println("\nReturning to the Main Menu\n\n\n");
                    Tools.generateLine(35, "*");
                    Main.menu();
                    return;

                case 5:
                    System.out.println("\nGenerating Admin Report\n\n");

                    Tools.generateLine(30, "*");
                    System.out.println();
                    FileHandling.writeAdminToFile(adminOW);
                    //FileHandling.readAdminDetailsFromFile();
                    System.out.println("Admin Name:\t" + adminOW.getAdminName() + "\t|\tAdmin Password:\t" + adminOW.getPassword() + "\n");
                    FileHandling.readAdminReport();
                    Tools.generateLine(30, "*");
                    System.out.println("Report successfully generated\n");
                    System.out.println("\n\n");
                    adminMenu();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Please Input a Valid Option");

                    validInput = false;
                    break;
            }
        }
    }

    
   
}
