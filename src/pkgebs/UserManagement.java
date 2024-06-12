/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jacob
 */
public class UserManagement {

    public static void adminUserMenu() {

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;

        System.out.println("1: View all Users\n"
                + "2: Find User\n"
                + "3: Add User\n"
                + "4: Delete User\n"
                + "5: Back to Admin Menu\n\n"
                + "0: Exit Program");
        System.out.println("Please enter a number: ");

        while (!validInput) {

            int caseInput;

            try {
                caseInput = scan.nextInt();
                validInput = true;
                scan.nextLine();
            } catch (InputMismatchException ohno) {
                System.out.println("Invalid Input. Please enter a valid number");
                scan.next();
                validInput = false;
                continue;
            }

            switch (caseInput) {

                case 1:
                    List<User> allUsers = FileHandling.readUsersFromFile();

                    System.out.println("\tAll Users");
                    Tools.generateLine(30, "*");

                    for (User u : allUsers) {

                        System.out.println("Name: \t\t" + u.getNameFull());
                        System.out.print("****************");
                        for (int i = 0; i < u.getNameFull().length(); i++) {
                            System.out.print("*");
                        }
                        System.out.print("****************\n");
                        System.out.println("Email: \t\t|" + u.getEmail());
                        System.out.println("Phone Number: \t|" + u.getPhoneNum());
                        System.out.println("Username: \t|" + u.getUsername());
                        System.out.println("Events Booked: \t|" + u.getEventsBooked());
                        System.out.printf("Money Spent: \t|$%.2f%n", u.getMoneySpent());
                        for (int j = 0; j < 50; j++) {
                            System.out.print("_");
                        }
                        System.out.println("\n");
                    }
                    System.out.println("\n");
                    Tools.generateLine(80, "_");
                    System.out.println("1: Find User \t 2: Add User \t 3: Delete User \t 0: Return to Admin Menu\n");
                    System.out.println("Please Input an Option: ");

                    boolean deepValidInput = false;

                    while (!deepValidInput) {

                        int deepCaseInput;

                        try {
                            deepCaseInput = scan.nextInt();
                            deepValidInput = true;
                            scan.nextLine();
                        } catch (InputMismatchException ohno) {
                            System.out.println("Invalid Input. Please enter a valid number\n");
                            scan.next();
                            deepValidInput = false;
                            continue;
                        }

                        switch (deepCaseInput) {

                            case 1:
                                System.out.println("\n\n");
                                //search();
                                Admin.adminMenu();
                                break;

                            case 2:
                                System.out.println("\n\n");
                                User.createUser();
                                Admin.adminMenu();
                                break;

                            case 3:
                                System.out.println("\tDelete User");
                                Tools.generateLine(20, "*");
                                String username;
                                System.out.println("Please enter the Username of the User you want to delete: ");
                                username = scan.nextLine();
                                removeUser(username);
                                break;

                            case 0:
                                System.out.println("Returning to Admin Menu\n\n");
                                Admin.adminMenu();
                                break;
                        }
                    }
                    Admin.adminMenu();
                    break;

                case 2:

                    //search();
                    break;

                case 3:
                    System.out.println("\tAdd User");
                    Tools.generateLine(20, "*");
                    User.createUser();
                    Admin.adminMenu();

                case 4:
                    System.out.println("\tDelete User");
                    Tools.generateLine(20, "*");
                    String username;
                    System.out.println("Please enter the Username of the User you want to delete: ");
                    username = scan.nextLine();
                    removeUser(username);
                    break;

                case 5:
                    System.out.println("Returning to Admin Menu");
                    Admin.adminMenu();
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

    public static User viewSingleUser(String identifier) {

        List<User> allUsers = FileHandling.readUsersFromFile();

        boolean hasFound = false;
        for (User u : allUsers) {

            if (u.getUsername().equals(identifier) || u.getfName().equals(identifier)) {

                System.out.println("Name: \t\t" + u.getNameFull());
                System.out.print("****************");
                for (int i = 0; i < u.getNameFull().length(); i++) {
                    System.out.print("*");
                }
                System.out.print("****************\n");
                System.out.println("Email: \t\t|" + u.getEmail());
                System.out.println("Phone Number: \t|" + u.getPhoneNum());
                System.out.println("Username: \t|" + u.getUsername());
                System.out.println("Events Booked: \t|" + u.getEventsBooked());
                System.out.printf("Money Spent: \t|$%.2f%n", u.getMoneySpent());
                Tools.generateLine(50, "_");

                hasFound = true;

                return u;
            }
        }

        if (!hasFound) {

            System.out.println("User " + identifier + " not found!\n");

        }
        return null;
    }

    public static User getUser(String username) {

        List<User> allUsers = FileHandling.readUsersFromFile();

        boolean hasFound = false;
        for (User u : allUsers) {

            if (u.getUsername().equals(username)) {

                System.out.println("\n");

                hasFound = true;

                return u;
            }
        }

        if (!hasFound) {

            System.out.println("User " + username + " not found!\n");

        }
        return null;
    }

    public static List<User> search(String searchKey) {

        UsersDB users = new UsersDB();
        List<User> matchingEvents = new ArrayList<>();
        List<User> allUsers = users.getAllUserInDB();

        for (User user : allUsers) {

            if (user.getUsername().toLowerCase().contains(searchKey.toLowerCase())
                    || user.getfName().toLowerCase().contains(searchKey.toLowerCase())
                    || user.getlName().toLowerCase().contains(searchKey.toLowerCase())) {

                matchingEvents.add(user);
            }

        }
        return matchingEvents;
    }

    public static void userDetails(User user) {

        Scanner scan = new Scanner(System.in);

        System.out.println("What would you like to do with user " + user.getNameFull() + "\n");
        System.out.println("1: Edit User Details\n"
                + "2: Delete User\n"
                + "3: Back to Admin Menu\n"
                + "0: Exit Program");

        boolean validInput = false;

        while (!validInput) {

            int caseInput;

            try {
                caseInput = scan.nextInt();
                validInput = true;
            } catch (InputMismatchException ohno) {
                System.out.println("Invalid Input. Please enter a valid number\n");
                scan.next();
                validInput = false;
                continue;
            }

            switch (caseInput) {

                case 1:
                    System.out.println("\n\n");

                    userEditDetails(user);
                    adminUserMenu();
                    break;

                case 2:
                    System.out.println("\tDelete User");
                    Tools.generateLine(20, "*");
                    String username;
                    System.out.println("Please enter the Username of the User you want to delete: ");
                    username = scan.nextLine();
                    removeUser(username);
                    break;

                case 3:
                    System.out.println("Returning to Admin Menu\n\n");
                    Admin.adminMenu();
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

    public static User userEditDetails(User userToEdit) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the new first name (Enter '!' to skip): ");
        String newFName = scan.nextLine();
        if (newFName.equals("!")) {
            newFName = userToEdit.getfName();
        }

        System.out.print("Enter the new last name (Enter '!' to skip): ");
        String newLName = scan.nextLine();
        if (newLName.equals("!")) {
            newLName = userToEdit.getlName();
        }

        System.out.print("Enter the new email (Enter '!' to skip): ");
        String newEmail = scan.nextLine();
        if (newEmail.equals("!")) {
            newEmail = userToEdit.getEmail();
        } else {

            if (!Tools.emailValidator(newEmail)) {

                System.out.println("Invalid Email. It must contain the \'@\' symbol, and end with eith \'.com\', \'.co.nz\', \'.ac.nz\'");

            }
        }

        System.out.print("Enter the new phone number (Enter '!' to skip): ");
        String newPhNum = scan.nextLine();
        if (newPhNum.equals("!")) {
            newPhNum = userToEdit.getPhoneNum();
        } else {

            if (!Tools.phNumValidator(newPhNum)) {

                System.out.println("Invalid Phone Number. Your Phone Number must begin with either \'021\', \'022\', \'027\', \'+64\', \'09\'\n");

            }
        }

        System.out.print("Enter the new username (Enter '!' to skip): ");
        String newUName = scan.nextLine();
        if (newUName.equals("!")) {
            newUName = userToEdit.getUsername();
        }

        System.out.print("Enter the new password (Enter '!' to skip): ");
        String newPWord = scan.nextLine();
        if (newPWord.equals("!")) {
            newPWord = userToEdit.getPassword();
        } else {

            if (!Tools.passwordValidator(newPWord)) {

                System.out.println("Password must contain at least one uppercase letter, one digit, and one special character among @#$%^&+=!");

            }
        }

        System.out.print("Enter the new number of events (Enter '!' to skip): ");
        int newNumEvents = userToEdit.getEventsBooked();

        while (true) {
            if (scan.hasNextInt()) {
                newNumEvents = scan.nextInt();
                if (newNumEvents >= 0) {
                    break;
                } else {
                    System.out.println("Number of events must be a Positive number!\n");
                }
            } else {
                String tempInput = scan.next();
                if (tempInput.equals("!")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid number or '!' to skip.\n");
                }
            }
        }
        scan.nextLine();

        System.out.print("Enter the new money spent (Enter '!' to skip): ");
        double newMoneySpent = userToEdit.getMoneySpent();

        while (true) {
            if (scan.hasNextFloat()) {
                newMoneySpent = scan.nextDouble();
                if (newMoneySpent >= 0) {
                    break;
                } else {
                    System.out.println("Money Spent must be Positive or 0!\n");
                }
            } else {
                String tempInput = scan.next();
                if (tempInput.equals("!")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid number or '!' to skip.\n");
                }
            }
        }
        scan.nextLine();

        User tempUser = new User(newFName, newLName, newEmail, newPhNum, newUName, newPWord, newNumEvents, newMoneySpent);

        FileHandling.editUserInFile(tempUser);

        return userToEdit;
    }

    public static User getUserDetails(User existingUser) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new user details:");
        System.out.print("Enter the new first name (Enter '!' to skip): ");
        String fNameInput = scanner.nextLine();
        String fName = fNameInput.equals("!") ? existingUser.getfName() : fNameInput;

        System.out.print("Enter the new last name (Enter '!' to skip): ");
        String lNameInput = scanner.nextLine();
        String lName = lNameInput.equals("!") ? existingUser.getlName() : lNameInput;

        String email = null;
        boolean isValidEmailInput = false;
        while (!isValidEmailInput) {

            System.out.print("Enter the new email (Enter '!' to skip): ");
            String emailInput = scanner.nextLine();

            if (emailInput.equals("!")) {
                email = existingUser.getEmail();
                isValidEmailInput = true;
            } else {

                if (Tools.emailValidator(emailInput)) {

                    email = emailInput;
                    isValidEmailInput = true;

                } else {

                    System.out.println("Invalid Email. It must contain the \'@\' symbol, and end with eith \'.com\', \'.co.nz\', \'.ac.nz\'");

                }
            }
        }

        String phoneNum = null;
        boolean isValidPhNumInput = false;
        while (!isValidPhNumInput) {

            System.out.print("Enter the new phone number (Enter '!' to skip): ");
            String phoneNumInput = scanner.nextLine();

            if (phoneNumInput.equals("!")) {
                phoneNum = existingUser.getEmail();
                isValidPhNumInput = true;
            } else {

                if (Tools.phNumValidator(phoneNumInput)) {

                    phoneNum = phoneNumInput;
                    isValidPhNumInput = true;

                } else {

                    System.out.println("Invalid Phone Number. Your Phone Number must begin with either \'021\', \'022\', \'027\', \'+64\', \'09\'\n");

                }
            }
        }

        String password = null;
        boolean isValidPasswordInput = false;
        while (!isValidPasswordInput) {

            System.out.print("Enter the new password (Enter '!' to skip): ");
            String passwordInput = scanner.nextLine();

            if (passwordInput.equals("!")) {
                password = existingUser.getPassword();
                isValidPasswordInput = true;
            } else {

                if (Tools.passwordValidator(passwordInput)) {

                    password = passwordInput;
                    isValidPasswordInput = true;

                } else {

                    System.out.println("Password must contain at least one uppercase letter, one digit, and one special character among @#$%^&+=!");

                }
            }
        }

        return new User(fName, lName, email, phoneNum, existingUser.getUsername(), password, existingUser.getEventsBooked(), existingUser.getMoneySpent());

    }

    public static void removeUser(String username) {

        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        String input;
        int userLine = FileHandling.searchLineInFile(username, "users.txt");
        FileHandling.searchInFile(username, "users.txt");
        System.out.println("\n");
        System.out.println("Is this the User you wish to Delete? [Y/N]");

        while (!isValid) {

            try {
                input = scan.nextLine();
                isValid = true;
            } catch (InputMismatchException oops) {

                System.out.println("Invalid Input. Please enter a valid number");
                scan.next();
                isValid = false;
                continue;
            }

            switch (input.toUpperCase()) {

                case "Y":

                    FileHandling.removeLineFromFile("users.txt", userLine);
                    isValid = true;
                    System.out.println("\nUser Succeessfully Deleted\n");

                    Admin.adminMenu();
                    break;

                case "N":
                    isValid = true;
                    System.out.println("\nReturning to Admin Menu\n");
                    Admin.adminMenu();
                    break;

                case "0":
                    System.exit(0);

                default:
                    System.out.println("Please input \"Y\" or \"N\", or \"0\" to exit the Program\n");

                    isValid = false;
                    break;
            }

        }
    }
}
