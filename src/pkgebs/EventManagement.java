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
public class EventManagement {

    public static void adminEventMenu() {

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;

        System.out.println("1: View all Events\n"
                + "2: Add Event\n"
                + "3: Delete Event\n"
                + "4: Find Event\n"
                + "5: Return to Admin Menu\n\n"
                + "0: Exit Program");

        while (!validInput) {

            System.out.println("Please enter a number: ");
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
                    viewAllEvents();
                    adminEventMenu();
                    break;

                case 2:
                    System.out.println("\tAdd Event");
                    Tools.generateLine(20, "*");
                    //Event.createEvent();
                    adminEventMenu();

                    break;

                case 3:
                    System.out.println("\tDelete Event");
                    Tools.generateLine(20, "*");
                    System.out.println("Please enter the Name of the Event you want to delete:");
                    String eName = scan.nextLine();

                    removeEvent(eName);
                    break;

                case 4:
                    System.out.println("\tEvent Search");
                    Tools.generateLine(30, "*");
                    //search();
                    break;

                case 5:
                    System.out.println("Returning to Admin Menu\n");
                    Tools.generateLine(35, "*");
                    Admin.adminMenu();
                case 0:
                    System.exit(0);

                default:
                    System.out.println("Please Input a Valid Option");

                    validInput = false;
                    break;
            }
        }
    }

    public static void userEventMenu() {

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;

        System.out.println("1: Book an Event\n"
                + "2: Back to Main Menu\n\n"
                + "0: Exit Program");

        while (!validInput) {

            System.out.println("Please enter a number: ");
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
                    BookingSystem.bookEvent();
                    userEventMenu();
                    break;

                case 2:
                    System.out.println("Returning to Main Menu\n\n");

                    Main.menu();

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

    public static Event viewSingleEvent(String identifier) {

        List<Event> allEvents = FileHandling.readEventsFromFile();

        boolean hasFound = false;
        for (Event e : allEvents) {

            if (e.getEventName().equals(identifier)) {

                hasFound = true;

                return e;
            }
        }

        if (!hasFound) {

            System.out.println("Event " + identifier + " not found!\n");

        }
        return null;
    }

    public static List<Event> search(String eventName) {

        EventsDB events = new EventsDB();
        List<Event> matchingEvents = new ArrayList<>();
        List<Event> allEvents = events.getAllEventInDB();

        for (Event event : allEvents) {

            if (event.getEventName().toLowerCase().contains(eventName.toLowerCase())) {

                matchingEvents.add(event);
            }
        }
        return matchingEvents;
    }

    public static Event getEvent(String name) {

        EventsDB events = new EventsDB();
        List<Event> allEvents = events.getAllEventInDB();

        boolean hasFound = false;
        for (Event e : allEvents) {

            if (e.getEventName().equals(name)) {

                System.out.println("\n");

                hasFound = true;

                return e;
            }
        }

        if (!hasFound) {

            System.out.println("Event " + name + " not found!\n");

        }
        return null;
    }

    public static void addEvent() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter Event Name: ");
        String eventName = scan.nextLine();

        System.out.println("Please enter the Date the Event Begins:");
        int startDay = scan.nextInt();
        scan.nextLine();
        //LocalDateTime startTime = getDateTime(startDay);

        System.out.println("Please enter the Number of Days the Event runs for:");
        int numDays = scan.nextInt();
        scan.nextLine();

        System.out.println("Please enter the Hour of the First Showing [24hr]:");
        int startHour = scan.nextInt();
        System.out.println("Please enter the Minutes of the Hour that the First Showing Starts:");
        int startMinute = scan.nextInt();

        //LocalDateTime firstShowingTime
        //Need to finish taking event details input
        // Event newEvent = new Event(eventName, startTime, endTime);
        // FileHandling.writeEventToFile(newEvent);
    }

    public static void viewAllEvents() {

        List<Event> allEvents = FileHandling.readEventsFromFile();

        for (Event e : allEvents) {

            System.out.println("Name: \t\t" + e.getEventName());
            System.out.print("****************");
            for (int i = 0; i < e.getEventName().length(); i++) {
                System.out.print("*");
            }
            System.out.print("****************\n");
            System.out.println("Price: \t\t\t|$" + e.getEventCost());
            System.out.println("Session Capacity: \t|" + e.getEventCapacity() + " people");
            System.out.println("Total Capacity: \t|" + e.getTotalCapacity() + " people");

            System.out.println("\n");
            if (e.getNumDaysActive() == 1) {
                System.out.println("This event will only run for " + e.getNumDaysActive() + " day.");
            } else if (e.getNumDaysActive() > 1) {
                System.out.println("This event will run for " + e.getNumDaysActive() + " days.");
            }

            if (e.getNumSessionsPerDay() == 1) {
                System.out.println("There will be " + e.getNumSessionsPerDay() + " Session per day.");
            } else if (e.getNumSessionsPerDay() > 1) {
                System.out.println("There will be " + e.getNumSessionsPerDay() + " Sessions each day.");
            }

            if (e.getSessionDuration() == 1) {
                System.out.println("Each Session will be " + e.getSessionDuration() + " hour long.");
            } else if (e.getSessionDuration() > 1) {
                System.out.println("Each Session will be " + e.getSessionDuration() + " hours long.");
            }

            Tools.generateLine(50, "_");
            System.out.println("\n");

        }
    }

    public static void removeEvent(String eventName) {

        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        String input;
        int eventLine = FileHandling.searchLineInFile(eventName, "events.txt");
        FileHandling.searchInEventFile(eventName, "events.txt");
        System.out.println("\n");
        System.out.println("Is this the Event you wish to Delete? [Y/N]");

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

                    FileHandling.removeLineFromFile("events.txt", eventLine);
                    isValid = true;
                    System.out.println("Event Succeessfully Deleted\n");
                    //call file handling method
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

    public static void eventDetails(Event event) {

        Scanner scan = new Scanner(System.in);

        System.out.println("What would you like to do with event: " + event.getEventName() + "\n");
        System.out.println("1: Edit Event Details\n"
                + "2: Delete Event\n"
                + "3: Back to Admin Menu\n"
                + "0: Exit Program");

        boolean validInput = false;

        while (!validInput) {

            int caseInput;

            try {
                caseInput = scan.nextInt();
                validInput = true;
                scan.nextLine();
            } catch (InputMismatchException ohno) {
                System.out.println("Invalid Input. Please enter a valid number\n");
                scan.next();
                validInput = false;
                continue;
            }

            switch (caseInput) {

                case 1:
                    System.out.println("\n\n");

                    //userEditDetails(user);
                    break;

                case 2:
                    System.out.println("\tDelete Event");
                    Tools.generateLine(35, "*");
                    System.out.println("Please enter the Name of the Event you want to delete:");
                    String eName = scan.nextLine();

                    removeEvent(eName);
                    break;

                case 3:
                    System.out.println("Returning to Admin Menu\n\n");
                    Tools.generateLine(45, "*");
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

    public static void displayEventDetails(Event event) {

        System.out.println("Event Name: \t\t" + event.getEventName());
        Tools.generateLine(85, "-");
        System.out.println();
        System.out.println("Event Price: \t| $" + event.getEventCost());
        System.out.println("Session Capacity: \t| " + event.getEventCapacity() + " people\t\tTotal Sessions: \t| " + (event.getNumSessionsPerDay() * event.getNumDaysActive()));
        System.out.println("Total Capacity: \t| " + event.getTotalCapacity() + " people\t\tEvent Total Days: \t| " + event.getNumDaysActive());
        System.out.println("Session Duration: \t|" + event.getSessionDuration() + ".00 hours");
        Tools.generateLine(85, "-");
        System.out.println("\n");

    }
}
