/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacob
 */
public class EventManagement {

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
