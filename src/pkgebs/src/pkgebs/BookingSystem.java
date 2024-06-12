/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.List;
import java.util.Map;

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

    //Creates a unique reference of the booking by mixing event and user info, 
    //and inputting random number elements
    public static String generateReference(String eventName, String fName, String lName) {
        String randomLetters = Tools.generateRandomLetters();
        String randomNumbers = Tools.generateRandomNumbers();
        String eventFirstLetter = eventName.substring(0, 1).toUpperCase();
        String userInitials = fName.substring(0, 1).toUpperCase() + lName.substring(0, 1).toUpperCase();

        return randomLetters + randomNumbers + eventFirstLetter + "-" + userInitials;
    }


}
