/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author jacob
 */
public class Main {

    BookingSystem bookingSystem = new BookingSystem();

    public static void main(String[] args) {

        BookingsDB bookings = new BookingsDB();
        GUI gui = new GUI();

        UsersDB.makeUserTable();
        UsersDB.preloadUsers();
        //System.out.println("yee 1");
        EventsDB.makeEventsTable();
        EventsDB.preloadEvents();
        //System.out.println("yee 2");
        BookingsDB.makeBookingsTable();
        BookingsDB.preloadBookings();
        //System.out.println("yee 3");

        //bookings.fetchAllBookings();
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(0); // Wait for 2 seconds

                gui.displayEventsGUI(true);
                //GUI.displayEventsGUI(true);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        thread2.start();

        //GUI.displayEventsGUI(true);
    }

    public static void menuSelection(String menuChoice) {
        Admin adminOnline = FileHandling.readAdminDetailsFromFile();
        GUI gui = new GUI();

        switch (menuChoice) {
            case "EVNT":
                gui.displayEventsGUI(false);
                break;
            case "BKEVNT":

                //gui.displayLoginWindow(false, true);
                //gui.displaySearchWindow(false, true);
                GUI.bookingFrame(null);
                break;
            case "USRLOG":
                gui.displayLoginWindow(false, false);
                break;
            case "ADLOG":
                gui.displayLoginWindow(true, false);
                break;
            case "EXIT":
                System.exit(0);
                break;
            case "MAINMNU":
                gui.displayEventsGUI(true);
                break;
            case "EVNTMNG":
                //Event Management Page
                gui.eventManagementGUI();
                break;
            case "USRMNG":
                //User Management Page
                gui.userManagementGUI();
                break;
            case "ADINFO":
                //Admin info from file
                gui.displayAdminDeets(adminOnline, gui.getTextSubPanel());
                break;
            case "GENREP":
                //generate admin report
                FileHandling.writeAdminToFile(adminOnline);
                gui.displayReport(adminOnline, gui.getTextSubPanel());
                break;

            case "ALLUSR":
                GUI.adminUsersGUI();
                break;
            case "FNDUSR":
                gui.displaySearchWindow(true, false);
                break;
            case "ADDUSR":
                gui.newUserFrame();
                break;
            case "DELUSR":

                gui.deleteFrame();
                break;
            case "ADPRELOG":
                gui.adminMenuGUI();
                break;

            case "ALLEVNT":
                gui.adminEventsGUI();
                break;
            case "FNDEVNT":
                gui.displaySearchWindow(true, true);
                break;
            case "ADDEVNT":
                gui.addEventFrame();
                break;

            case "ACCTDETS":
                //my details
                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid Menu Selection");
        }
    }

    public static void displayMenu(JTextArea menuTextArea) {
        menuTextArea.setText(getMenuText());
    }

    private static String getMenuText() {
        return "\tAll Events\n"
                + //Tools.generateLine(30, "*") + "\n\n" +
                "1: Browse Events\n"
                + "2: Book an Event\n"
                + "3: My Account\n"
                + "4: Admin Menu\n\n"
                + "0: Exit Program\n";
    }

    public static void menu() {

        Admin admin = new Admin("admin1", "adPass4477");
        FileHandling.writeAdminToFile(admin);
        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        int loginAttempts = 3;

        String[] menuAsks = {"Please select what you would like to do!",
            "Your input matters. What action are you considering?",
            "In order to proceed, you must decide. What will it be?",
            "Please choose from the available options for your next step!",
            "Could you kindly indicate your preferred action?",
            "Choose what you want to do, input that number, and there you go!",
            "Your choice is all that matters. Take your time"};

        System.out.println("Welcome back to your favourite Event Booking System!");
        String menuAsk = generateMenuAsk(menuAsks);
        System.out.println("\n" + menuAsk);
        Tools.generateLine(menuAsk.length(), "_");
        System.out.println();
        System.out.println("1: Browse Events\n"
                + "2: Book an Event\n"
                + "3: My Account\n"
                + "4: Admin Menu\n\n"
                + "0: Exit Program");
        while (!validInput) {
            System.out.println("Please enter a number: ");
            int input;

            try {
                input = scan.nextInt();
                validInput = true;
                scan.nextLine();
            } catch (InputMismatchException ohno) {
                System.out.println("Invalid Input. Please enter a valid number");
                scan.next();
                validInput = false;
                continue;
            }

            switch (input) {

                case 1:

                    System.out.println("\tAll Events");
                    Tools.generateLine(30, "*");
                    System.out.println("\n");
                    EventManagement.viewAllEvents();
                    System.out.println("Please scroll up to view Events\n");
                    System.out.println("Press Enter to return to continue...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();

                    System.out.println("What would you like to do: ");
                    EventManagement.userEventMenu();

                    break;

                case 2:

                    BookingSystem.bookEvent();

                    menu();
                    break;

                case 3:
                    System.out.println("Taking you to your account.\n\n");
                    User.userMenu();

                    break;

                case 4:

                    String adminPass = admin.getPassword();
                    String adminUser = admin.getAdminName();

                    Admin.adminLogin(adminUser, adminPass);
                    break;

                case 0:
                    System.exit(0);
                default:

                    validInput = false;
                    break;

            }
        }
    }

    public static String generateMenuAsk(String[] strings) {

        if (strings == null || strings.length == 0) {
            return null;
        }

        Random selectRand = new Random();
        int randIndex = selectRand.nextInt(strings.length);

        return strings[randIndex];
    }
}
