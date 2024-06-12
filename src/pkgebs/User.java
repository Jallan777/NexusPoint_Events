/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.Random;

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

//    public static User createUser() {
//
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Please enter a First Name: ");
//        String nameF = scan.nextLine();
//
//        System.out.println("Please enter a Last Name: ");
//        String nameL = scan.nextLine();
//
//        String email = null;
//        boolean isValidEmail = false;
//        while (!isValidEmail) {
//
//            System.out.println("Please enter a Email Address: ");
//            email = scan.nextLine();
//
//            if (Tools.emailValidator(email)) {
//
//                System.out.println("\nEmail is valid\n");
//                isValidEmail = true;
//            } else {
//
//                System.out.println("\nInvalid Email. It must contain the \'@\' symbol, and end with \'.com\', \'.co.nz\', \'.ac.nz\'\n");
//            }
//        }
//
//        String phNum = null;
//        boolean isValidPhNum = false;
//        while (!isValidPhNum) {
//
//            System.out.println("Please enter a Phone Number: ");
//            phNum = scan.nextLine();
//
//            if (Tools.phNumValidator(phNum)) {
//
//                System.out.println("\nPhone Number is valid\n");
//                isValidPhNum = true;
//            } else {
//
//                System.out.println("\nInvalid Phone Number. Your Phone Number must begin with \'021\', \'022\', \'027\', \'+64\', \'09\'");
//                //System.out.println("Your Phone Number must shorter than 14 numbers long\n");
//            }
//        }
//
//        String pWord = null;
//        boolean isValidPWord = false;
//        while (!isValidPWord) {
//
//            System.out.println("Please enter a Password: ");
//            pWord = scan.nextLine();
//
//            if (Tools.passwordValidator(pWord)) {
//
//                System.out.println("\nPassword is valid\n");
//                isValidPWord = true;
//            } else {
//
//                System.out.println("\nInvalid Password. It must contain at least one capital letter, one number, and one special character\n");
//            }
//        }
//        int numEvents = 0;
//        float moneySpent = 0.0f;
//        String uName = generateUsername(nameF, nameL);
//        System.out.println("Thankyou! Your Username is: " + uName + "\n");
//
//        User temp = new User(nameF, nameL, email, phNum, uName, pWord, numEvents, moneySpent);
//        writeToFile(nameF, nameL, email, phNum, uName, pWord, numEvents, moneySpent);
//        return temp;
//    }

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

    

}
