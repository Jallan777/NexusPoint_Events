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

                gui.deleteUserFrame();
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

            case "DELEVNT":
                gui.deleteEventFrame(false, "");
                break;
            case "ACCTDETS":
                //my details
                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid Menu Selection");
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
