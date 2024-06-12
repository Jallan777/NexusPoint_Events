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
public class UserManagement {



    //Function to return  a single user. Functions like these served as the base for 
    //the functions that use the Database. Rather than file input, it uses sql commands
    //The logic of collecting into a list and reading from that remains pretty much
    //the same

    public static User getUser(String username) {

        List<User> allUsers = FileHandling.readUsersFromFile();

        boolean hasFound = false;
        for (User u : allUsers) {

            if (u.getUsername().equals(username)) {

                System.out.println("\n");

                hasFound = true;

                return u;
            }
        }

        if (!hasFound) {

            System.out.println("User " + username + " not found!\n");

        }
        return null;
    }

    //User search function. Takes searchKey and finds user.
    //key can be first name, last name or username and doesn't have to
    //match exactly. Returns list of matching events
    public static List<User> search(String searchKey) {

        UsersDB users = new UsersDB();
        List<User> matchingEvents = new ArrayList<>();
        List<User> allUsers = users.getAllUserInDB();

        for (User user : allUsers) {

            if (user.getUsername().toLowerCase().contains(searchKey.toLowerCase())
                    || user.getfName().toLowerCase().contains(searchKey.toLowerCase())
                    || user.getlName().toLowerCase().contains(searchKey.toLowerCase())) {

                matchingEvents.add(user);
            }

        }
        return matchingEvents;
    }


    
}
