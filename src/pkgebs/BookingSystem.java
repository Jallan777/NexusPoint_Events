/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jacob
 */
public class BookingSystem {

    private List<User> users;
    private List<Event> events;
    private Map<User, List<Event>> bookings;

    public static void linkUserToEvent(User user, Event event) {
        // Decrease event capacity by 1
        event.decreaseCapacity();
        // Increment the number of events booked by the user
        user.incrementEventsBooked();

        FileHandling.editUserInFile(user);
        FileHandling.updateEventInFile(event);
    }

    public static String generateReference(String eventName, String fName, String lName) {
        String randomLetters = Tools.generateRandomLetters();
        String randomNumbers = Tools.generateRandomNumbers();
        String eventFirstLetter = eventName.substring(0, 1).toUpperCase();
        String userInitials = fName.substring(0, 1).toUpperCase() + lName.substring(0, 1).toUpperCase();

        return randomLetters + randomNumbers + eventFirstLetter + "-" + userInitials;
    }

    public static void bookEvent() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        Event event = null;

        // Prompt the user for the username until they choose to stop
        String username;
        while (true) {
            System.out.println("Please enter your username (or enter ! to stop): ");
            username = scanner.nextLine();

            if (username.equals("!")) {
                System.out.println("Exiting username input.\n");
                return;
            }

            // Find the user
            user = UserManagement.getUser(username);
            if (user == null) {
                System.out.println("User not found! Please check your username.\n");
            } else {
                break; // Break the loop if the user is found
            }
        }

        // Prompt the user to choose an event and book it
        String eventName;

        System.out.println("Available events:");
        EventManagement.viewAllEvents();

        while (true) {

            System.out.println("Please enter the name of the event you want to book (or enter \"!\" to stop): ");
            System.out.println("(You can copy + paste the Event Name to make life easier)\n");
            eventName = scanner.nextLine();

            if (eventName.equals("!")) {
                System.out.println("Returning you to Main Menu...\n\n");
                Main.menu();
                break;
            }
            event = EventManagement.getEvent(eventName);
            if (event == null) {
                System.out.println("Event not found! Please check the event name.\n");
            } else {
                break;
            }
        }
        // Check if there's space available in the event
        if (event.getTotalCapacity() <= 0) {
            System.out.println("Sorry, this event is full.\n");
            return;
        }

        // Book the event
        event.setTotalCapacity(event.getTotalCapacity() - 1);

        user.setEventsBooked(user.getEventsBooked() + 1);
        user.setMoneySpent(user.getMoneySpent() + event.getEventCost());
        String ref = generateReference(event.getEventName(), user.getfName(), user.getlName());

        FileHandling.writeBookingToFile(user.getUsername(), event.getEventName(), ref, event.getEventCost());
        // Update the user and event information in the file
        FileHandling.editUserInFile(user);
        FileHandling.updateEventInFile(event);

        System.out.println("Event booked successfully! Thank you, " + username + "!\n");
        System.out.println("Your Reference is: " + ref + ".\n\n");
    }

}
