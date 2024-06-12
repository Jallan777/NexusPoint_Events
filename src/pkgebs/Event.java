/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;


/**
 *
 * @author jacob
 */
public class Event {

    private String eventName;
    private String eventImage;
    private int numDaysActive;
    private int numSessionsPerDay;
    private int sessionDuration;

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
    private double eventCost;
    private int eventCapacity;
    private int totalCapacity;
    private boolean isBooked;

    public Event(String eventName, double cost, int capacity, int numDays, int sessions, int duration, String eventImg) {
        this.eventName = eventName;
        this.numDaysActive = (numDays >= 0) ? numDays : 0;
        this.numSessionsPerDay = (sessions >= 0) ? sessions : 0;
        this.sessionDuration = (duration >= 0) ? duration : 0;
        this.eventCost = cost;
        this.eventCapacity = (capacity >= 0) ? capacity : 0;
        this.eventImage = eventImg;
        this.totalCapacity = this.eventCapacity * (this.numSessionsPerDay * this.numDaysActive);
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public String getEventName() {
        return eventName;
    }

    public int getNumDaysActive() {
        return numDaysActive;
    }

    public int getNumSessionsPerDay() {
        return numSessionsPerDay;
    }

    public int getSessionDuration() {
        return sessionDuration;
    }

    public double getEventCost() {
        return eventCost;
    }

    public int getEventCapacity() {
        return eventCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    //call decreaseTotalCap somewhere each time a user books an event (probs bookEvent)
    //decreases each events totalCapacity by the number of people booked
    //will need to use identifier with countLineWithSomething to find who is booked into what events
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    public void decreaseCapacity() {
        if (eventCapacity > 0) {
            eventCapacity--;
        }
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName + "\n"
                + "Event Cost: $" + eventCost + "\n"
                + "Event Capacity: " + eventCapacity + "\n"
                + "Number of Days Active: " + numDaysActive + "\n"
                + "Sessions per Day: " + numSessionsPerDay + "\n"
                + "Session Duration: " + sessionDuration + " minutes\n"
                + "Total Capacity: " + totalCapacity + "\n";
    }

}
