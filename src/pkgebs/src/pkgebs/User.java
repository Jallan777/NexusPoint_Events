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

//User class stores data for the User
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

    //Getter and Setter methods for control of user data
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

    //Legacy file i/o code
    public static void writeToFile(String nameF, String nameL, String email, String phNum, String uName, String pWord, int numEvent, float money) {

        User userToWrite = new User(nameF, nameL, email, phNum, uName, pWord, numEvent, money);
        FileHandling.writeUserToFile(userToWrite);
    }

    //Call this method to create username through static method newUsername
    public String newUsername(String fName, String lName) {

        String username = generateUsername(fName, lName);
        return username;
    }

    //Username generator using first name, last name, and some random numbers
    private static String generateUsername(String fName, String lName) {

        String firstLetter = fName.substring(0, 1).toLowerCase();
        String lastName = lName.toLowerCase();

        Random randNum = new Random();
        int randomNumber = randNum.nextInt(9000) + 1000;
        return firstLetter + lastName + randomNumber;
    }

    //User login validator
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
