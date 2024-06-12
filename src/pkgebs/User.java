/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

/**
 *
 * @author jacob
 */
public class User {

    private String fName;
    private String lName;
    private String nameFull;
    private String email;
    private String phoneNum;
    private String username;
    private String password;
    private int eventsBooked = 0;
    private double moneySpent = 0.00f;

    public User(String fName, String lName, String email, String phoneNum, String username, String password, int numEvents, double money) {
        this.fName = fName;
        this.lName = lName;
        this.nameFull = fName + " " + lName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.eventsBooked = numEvents;
        this.moneySpent = money;

    }

    public User() {
        this.fName = "";
        this.lName = "";
        this.nameFull = fName + " " + lName;
        this.email = "";
        this.phoneNum = "";
        this.username = "";
        this.password = "";
        this.eventsBooked = 0;
        this.moneySpent = 0.0;

    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public int getEventsBooked() {
        return eventsBooked;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getNameFull() {

        return nameFull;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEventsBooked(int eventsBooked) {
        this.eventsBooked = eventsBooked;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    public void incrementEventsBooked() {
        eventsBooked++;
    }

    public static void writeToFile(String nameF, String nameL, String email, String phNum, String uName, String pWord, int numEvent, float money) {

        User userToWrite = new User(nameF, nameL, email, phNum, uName, pWord, numEvent, money);
        FileHandling.writeUserToFile(userToWrite);
    }

    public String newUsername(String fName, String lName) {

        String username = generateUsername(fName, lName);
        return username;
    }

    private static String generateUsername(String fName, String lName) {

        String firstLetter = fName.substring(0, 1).toLowerCase();
        String lastName = lName.toLowerCase();

        Random randNum = new Random();
        int randomNumber = randNum.nextInt(9000) + 1000;
        return firstLetter + lastName + randomNumber;
    }

    public static User createUser() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter a First Name: ");
        String nameF = scan.nextLine();

        System.out.println("Please enter a Last Name: ");
        String nameL = scan.nextLine();

        String email = null;
        boolean isValidEmail = false;
        while (!isValidEmail) {

            System.out.println("Please enter a Email Address: ");
            email = scan.nextLine();

            if (Tools.emailValidator(email)) {

                System.out.println("\nEmail is valid\n");
                isValidEmail = true;
            } else {

                System.out.println("\nInvalid Email. It must contain the \'@\' symbol, and end with \'.com\', \'.co.nz\', \'.ac.nz\'\n");
            }
        }

        String phNum = null;
        boolean isValidPhNum = false;
        while (!isValidPhNum) {

            System.out.println("Please enter a Phone Number: ");
            phNum = scan.nextLine();

            if (Tools.phNumValidator(phNum)) {

                System.out.println("\nPhone Number is valid\n");
                isValidPhNum = true;
            } else {

                System.out.println("\nInvalid Phone Number. Your Phone Number must begin with \'021\', \'022\', \'027\', \'+64\', \'09\'");
                //System.out.println("Your Phone Number must shorter than 14 numbers long\n");
            }
        }

        String pWord = null;
        boolean isValidPWord = false;
        while (!isValidPWord) {

            System.out.println("Please enter a Password: ");
            pWord = scan.nextLine();

            if (Tools.passwordValidator(pWord)) {

                System.out.println("\nPassword is valid\n");
                isValidPWord = true;
            } else {

                System.out.println("\nInvalid Password. It must contain at least one capital letter, one number, and one special character\n");
            }
        }
        int numEvents = 0;
        float moneySpent = 0.0f;
        String uName = generateUsername(nameF, nameL);
        System.out.println("Thankyou! Your Username is: " + uName + "\n");

        User temp = new User(nameF, nameL, email, phNum, uName, pWord, numEvents, moneySpent);
        writeToFile(nameF, nameL, email, phNum, uName, pWord, numEvents, moneySpent);
        return temp;
    }

    public static boolean userLogin(String userUsername, String userPassword) {

        UsersDB usersDB = new UsersDB();
        User user = usersDB.findUsernameInDB(userUsername);
        GUI gui = new GUI();
        if (user != null) {

            String foundUser = "User found: " + user.getUsername() + ". Logging In";
            gui.popupWindow(foundUser, 1);

        } else {

            String userNotFound = "Incorrect Username or Password.\nPlease try again.";
            gui.popupWindow(userNotFound, 2);

        }
        String userName = user.getUsername();
        String userPass = user.getPassword();

        return userUsername.equals(userName) && userPassword.equals(userPass);

    }

    public static void userLoggedInMenu(String username) {

        List<User> users = FileHandling.readUsersFromFile();
        Event tempEvent = null;
        User loggedUser = null;
        for (User u : users) {

            if (u.getUsername().equals(username)) {

                loggedUser = u;
            }

        }

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        System.out.println("\n\n");
        System.out.println("Login successful! Welcome, " + username);
        System.out.print("***************************");
        for (int i = 0; i < username.length(); i++) {
            System.out.print("*");
        }
        System.out.println("\n");
        System.out.println("Please select an option:");
        System.out.println("1: My Details\n"
                + "2: My Events\n"
                + "3: Log Out\n\n"
                + "0: Exit Program");

        while (!validInput) {
            System.out.println("Please enter a number: ");
            int input;

            try {
                input = scan.nextInt();
                validInput = true;
                scan.nextLine();
            } catch (InputMismatchException ohno) {
                System.out.println("\nInvalid Input. Please enter a valid number\n");
                scan.next();
                validInput = false;
                continue;
            }

            switch (input) {

                case 1:

                    System.out.println("\tMy Details!");
                    Tools.generateLine(30, "-");
                    UserManagement.viewSingleUser(username);
                    String newInput;
                    boolean valid = false;
                    System.out.println("Change Details?[Y/N]");

                    while (!valid) {

                        try {
                            newInput = scan.nextLine();
                            valid = true;
                        } catch (InputMismatchException oops) {

                            System.out.println("Invalid Input. Please enter a valid number");
                            scan.next();
                            valid = false;
                            continue;
                        }

                        switch (newInput.toUpperCase()) {

                            case "Y":
                                FileHandling.editUserInFile(UserManagement.getUserDetails(loggedUser));

                                userLoggedInMenu(username);
                                break;

                            case "N":
                                valid = true;
                                userLoggedInMenu(username);
                                break;

                            case "0":
                                System.exit(0);

                            default:
                                System.out.println("Please input \"Y\" or \"N\", or \"0\" to exit the Program\n");

                                valid = false;
                                break;
                        }

                    }
                    break;

                case 2:
                    System.out.println("\tMy Events!");
                    Tools.generateLine(45, "-");
                    FileHandling.readUserBookings(loggedUser.getfName(), loggedUser.getlName(), "bookings.txt");

                    System.out.println("\n");
                    System.out.println("Press enter to continue...");
                    scan.nextLine();
                    userLoggedInMenu(username);

                case 3:
                    System.out.println("Logging Out and Returning to Main Menu\n");
                    Tools.generateLine(30, "*");
                    //Main.menu();
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("\nPlease Input a Valid Option\n");

                    validInput = false;
                    break;
            }
        }

    }

    public static void userMenu() {

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;

        try {
            System.out.println("Please select an option:");
            System.out.println("1: Make an Account\n"
                    + "2: Log In to Existing Account\n"
                    + "3: Back\n\n"
                    + "0: Exit Program");

            while (!validInput) {
                System.out.println("Please enter a number: ");
                int input;

                try {
                    input = scan.nextInt();
                    validInput = true;
                    scan.nextLine();
                } catch (InputMismatchException ohno) {
                    System.out.println("\nInvalid Input. Please enter a valid number\n");
                    scan.next();
                    validInput = false;
                    continue;
                }

                switch (input) {  //this needs letter error handlnig

                    case 1:
                        createUser();
                        userMenu();
                        break;

                    case 2:

                        System.out.println("Please Enter your Username: ");
                        String uName;
                        uName = scan.nextLine();
                        //username search 
                        //FileHandling.searchInFile(uName, "users.txt");

                        //userLogin(uName);
                        break;

                    case 3:
                        System.out.println("Returning to Main Menu\n");
                        //Main.menu();
                        break;

                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("\nPlease Input a Valid Option\n");

                        validInput = false;
                        break;

                }
            }
        } catch (InputMismatchException woops) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception woops) {
            System.out.println("An error has occured! " + woops.getMessage());
        } finally {
            scan.close();
        }
    }

}
