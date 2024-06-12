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
