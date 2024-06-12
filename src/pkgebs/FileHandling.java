/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jacob
 */
public class FileHandling {

    public static void writeUserToFile(User user) {

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {

            writer.write(user.getNameFull() + "," + user.getEmail() + "," + user.getPhoneNum() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getEventsBooked() + "," + user.getMoneySpent());
            writer.newLine();

            System.out.println("User Added Successfully!\n\n");

        } catch (IOException noo) {

            System.out.println("Error Adding User\n\n");
            noo.printStackTrace();
        }
    }

    public static List<User> readUsersFromFile() {

        List<User> users = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] userData = line.split(",");
                if (userData.length == 7) {

                    String[] nameParts = userData[0].split(" ");
                    String fName = nameParts[0];
                    String lName = nameParts.length > 1 ? nameParts[1] : "";
                    String email = userData[1];
                    String phNum = userData[2];
                    String username = userData[3];
                    String password = userData[4];
                    int numEvents = Integer.parseInt(userData[5]);
                    float moneySpent = Float.parseFloat(userData[6]);

                    User user = new User(fName, lName, email, phNum, username, password, numEvents, moneySpent);
                    users.add(user);
                } else {

                    System.out.println("Invalid user data: " + line);
                }
            }
        } catch (IOException | NumberFormatException uhoh) {

            System.out.println("Error reading Users from File");
            uhoh.printStackTrace();
        }

        return users;
    }

    public static void removeLineFromFile(String filePath, int lineNumber) {

        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");

        try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile));  BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int lineNum = 1;

            while ((currentLine = reader.readLine()) != null) {
                if (lineNum != lineNumber) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                lineNum++;
            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            //System.out.println("Line " + lineNumber + " removed successfully!");
        } catch (IOException e) {
            System.out.println("Error removing line from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeEventToFile(Event event) {

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true))) {

            writer.write(event.getEventName() + "," + event.getEventCost() + "," + event.getEventCapacity() + "," + event.getNumDaysActive() + "," + event.getNumSessionsPerDay() + "," + event.getSessionDuration());
            writer.newLine();

            //System.out.println("Event Added Successfully!");
        } catch (IOException ohno) {

            System.out.println("Error Adding Event");
            ohno.printStackTrace();
        }
    }

    public static List<Event> readEventsFromFile() {

        List<Event> events = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("events.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] eventData = line.split(",");
                if (eventData.length == 7) {

                    String eventName = eventData[0];
                    double cost = Double.parseDouble(eventData[1]);
                    int capacity = Integer.parseInt(eventData[2]);
                    int days = Integer.parseInt(eventData[3]);
                    int sessions = Integer.parseInt(eventData[4]);
                    int hrLength = Integer.parseInt(eventData[5]);
                    String eventImg = eventData[6];
                    //int totalCap = Integer.parseInt(eventData[6]);

                    Event event = new Event(eventName, cost, capacity, days, sessions, hrLength, eventImg);
                    events.add(event);
                } else {

                    System.out.println("Invalid event data: " + line);
                }
            }
        } catch (IOException | NumberFormatException uhoh) {

            System.out.println("Error reading Events from File");
            uhoh.printStackTrace();
        }

        return events;
    }

    public static void searchInFile(String keyword, String fileName) {

        keyword = keyword.toLowerCase();
        boolean isFound = false;
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {

                if (line.toLowerCase().contains(keyword)) {

                    String[] parts = line.split(",");
                    System.out.println("User Found: \n");
                    System.out.println("Name: \t" + parts[0] + "\t\tUsername: " + parts[3] + "\n\n");
                    isFound = true;
                }

                lineNum++;
            }

        } catch (IOException oops) {

            System.out.println("Error reading file: " + fileName);
            oops.printStackTrace();
        }

        if (!isFound) {

            System.out.println("User not found!");
        }
    }

    public static void searchInEventFile(String keyword, String fileName) {

        keyword = keyword.toLowerCase();
        boolean isFound = false;
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {

                if (line.toLowerCase().contains(keyword)) {

                    String[] parts = line.split(",");
                    System.out.println("Event Found: \n");
                    System.out.println("Name: \t" + parts[0] + "\n");
                    isFound = true;
                }

                lineNum++;
            }

        } catch (IOException oops) {

            System.out.println("Error reading file: " + fileName);
            oops.printStackTrace();
        }

        if (!isFound) {

            System.out.println("Event not found!");
        }
    }

    public static String returnEventName(String keyword, String fileName) {

        String name = null;
        int lineNum = 1;
        keyword = keyword.toLowerCase();
        boolean isFound = false;
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.toLowerCase().contains(keyword)) {

                    String[] parts = line.split(",");
                    isFound = true;
                    name = parts[0];
                    return name + "|" + lineNum;
                }

                lineNum++;
            }

        } catch (IOException oops) {

            //System.out.println("Error reading file: " + fileName);
            oops.printStackTrace();
        }

        if (!isFound) {

            //System.out.println("Event not found!");
            name = null;
        }
        return name + "|" + lineNum;
    }

    public static int searchLineInFile(String keyword, String fileName) {

        keyword = keyword.toLowerCase();
        boolean isFound = false;
        int lineNum = 1;
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.toLowerCase().contains(keyword)) {

                    isFound = true;
                    return lineNum;
                }

                lineNum++;
            }

        } catch (IOException oops) {

            System.out.println("Error reading file: " + fileName);
            oops.printStackTrace();
        }

        if (!isFound) {

        }
        return 0;
    }

    public static void loginSearch(String keyword, String fileName) {

        keyword = keyword.toLowerCase();
        boolean isFound = false;
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {

                if (line.toLowerCase().contains(keyword)) {

                    String[] parts = line.split(",");
                    System.out.println("User Found: \n");
                    System.out.println("Name: \t" + parts[0] + "\t\tUsername: " + parts[3] + "\n\n");
                    isFound = true;
                }

                lineNum++;
            }

        } catch (IOException oops) {

            System.out.println("Error reading file: " + fileName);
            oops.printStackTrace();
        }

        if (!isFound) {

            System.out.println("User not found!");
        }
    }

    public static int countLines(String filePath) {

        int lineCount = 0;

        try ( BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while (br.readLine() != null) {

                lineCount++;
            }
        } catch (IOException e) {

            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return lineCount;
    }

    public static int countLineWithSomething(String filePath, int position) {
        int count = 0;

        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > position && Integer.parseInt(parts[position]) > 0) {
                    count++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static Admin readAdminDetailsFromFile() {

        Admin admin = null;

        try ( BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {

            String line;
            if ((line = br.readLine()) != null) {
                String[] adminData = line.split("\t\t"); // Assuming admin data is separated by tabs
                if (adminData.length == 2) {
                    String adminName = adminData[0].split(": ")[1]; // Extract admin name from line
                    String adminPassword = adminData[1].split(": ")[1]; // Extract admin password from line
                    admin = new Admin(adminName, adminPassword); // Create admin object
                } else {
                    System.out.println("Invalid admin data: " + line);
                }
            }
        } catch (IOException oops) {

            System.out.println("Error reading admin data from file: " + oops.getMessage());

        }
        return admin;
    }

    public static Admin readAdminReport() {

        Admin admin = null;

        try ( BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {

            br.readLine();
            String line;
            int totalEvents = 0;
            int totalBookings = 0;
            int totalUsers = 0;
            int totalBookedUsers = 0;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("Total Events:")) {
                    totalEvents = Integer.parseInt(line.split(": ")[1]);
                } else if (line.startsWith("Total Bookings:")) {
                    totalBookings = Integer.parseInt(line.split(": ")[1]);
                } else if (line.startsWith("Total Users:")) {
                    totalUsers = Integer.parseInt(line.split(": ")[1]);
                } else if (line.startsWith("Total Booked Users:")) {
                    totalBookedUsers = Integer.parseInt(line.split(": ")[1]);
                }
            }

            Tools.generateLine(85, "-");
            System.out.println("\n");
            System.out.println("Total Events:\t" + totalEvents + "\t|\tTotal Bookings:\t" + totalBookings);
            System.out.println("Total Users:\t" + totalUsers + "\t|\tBooked Users:\t" + totalBookedUsers);
            System.out.println("\n");
            Tools.generateLine(85, "-");
            System.out.println("\n");

        } catch (IOException oops) {

            System.out.println("Error reading admin data from file: " + oops.getMessage());

        }
        return admin;
    }

    public static List<User> readAdminFile() {

        List<User> users = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] userData = line.split(",");
                if (userData.length == 7) {

                    String[] nameParts = userData[0].split(" ");
                    String fName = nameParts[0];
                    String lName = nameParts.length > 1 ? nameParts[1] : "";
                    String email = userData[1];
                    String phNum = userData[2];
                    String username = userData[3];
                    String password = userData[4];
                    int numEvents = Integer.parseInt(userData[5]);
                    float moneySpent = Float.parseFloat(userData[6]);

                    User user = new User(fName, lName, email, phNum, username, password, numEvents, moneySpent);
                    users.add(user);
                } else {

                    System.out.println("Invalid user data: " + line);
                }
            }
        } catch (IOException | NumberFormatException uhoh) {

            System.out.println("Error reading Users from File");
            uhoh.printStackTrace();
        }

        return users;
    }

    public static void writeAdminToFile(Admin admin) {

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("admin.txt"))) {

            writer.write("Admin Name: " + admin.getAdminName() + "\t\tAdmin Password: " + admin.getPassword());
            writer.newLine();
            writer.write("Total Events: " + admin.getTotalEvents());
            writer.newLine();
            writer.write("Total Bookings: " + admin.getTotalBookings());
            writer.newLine();
            writer.write("Total Users: " + admin.getTotalUsers());
            writer.newLine();
            writer.write("Total Booked Users: " + admin.getTotalBookedUsers());
            writer.newLine();

        } catch (IOException noo) {

            System.out.println("Error generating Report\n\n");
            noo.printStackTrace();
        }
    }

    public static void editUserInFile(User userToEdit) {

        List<User> allUsers = readUsersFromFile();

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i).getUsername().equals(userToEdit.getUsername())) {
                allUsers.set(i, userToEdit);
                break;
            }
        }

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {

            for (User user : allUsers) {

                writer.write(user.getNameFull() + "," + user.getEmail() + "," + user.getPhoneNum() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getEventsBooked() + "," + user.getMoneySpent());

                writer.newLine();
            }
            System.out.println("User updated successfully!\n");

        } catch (IOException ohno) {

            System.out.println("Error writing users to file: " + ohno.getMessage());
            ohno.printStackTrace();
        }
    }

    public static void updateEventInFile(Event eventToUpdate) {

        List<Event> allEvents = readEventsFromFile();

        for (int i = 0; i < allEvents.size(); i++) {

            if (allEvents.get(i).getEventName().equals(eventToUpdate.getEventName())) {
                allEvents.set(i, eventToUpdate);
                break;
            }
        }

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"))) {

            for (Event event : allEvents) {

                writer.write(event.getEventName() + "," + event.getEventCost() + "," + event.getEventCapacity() + "," + event.getNumDaysActive() + "," + event.getNumSessionsPerDay() + "," + event.getSessionDuration());
                writer.newLine();
            }
            System.out.println("Event updated successfully!\n");

        } catch (IOException ohno) {

            System.out.println("Error writing event to file: " + ohno.getMessage());
            ohno.printStackTrace();
        }
    }

    public static void writeBookingToFile(String username, String eventName, String uniqueIdentifier, double price) {
        try ( PrintWriter writer = new PrintWriter(new FileWriter("bookings.txt", true))) {
            // Append the booking information to the file
            writer.println(uniqueIdentifier + "," + username + "," + eventName + "," + price);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readUserBookings(String firstName, String lastName, String filePath) {

        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {

                    String refCode = parts[0];
                    String user = parts[1];
                    String event = parts[2];
                    double price = Double.parseDouble(parts[3]);

                    String userInitials = refCode.substring(refCode.indexOf('-') + 1, refCode.indexOf('-') + 3);
                    String expectedInitials = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();

                    if (userInitials.equals(expectedInitials)) {

                        System.out.println("Event Name: " + event);
                        System.out.println("Event Price: $" + price + "\n");
                        System.out.println("Reference Code: " + refCode);

                        System.out.println("\n");
                        Tools.generateLine(50, "-");
                        System.out.println();
                    }

                }
            }
        } catch (IOException oops) {

            oops.printStackTrace();
        }
    }
}
