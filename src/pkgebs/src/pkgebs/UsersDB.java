/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jacob
 */
public class UsersDB {

    DatabaseManager dbManager;
    Connection conn;
    Statement statement;

    //Starts connection through DatabaseManager class
    //The database classes are pretty much all the same
    //most functions either push or pull from the table
    public UsersDB() {
        dbManager = new DatabaseManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //Make Table
    public static void makeUserTable() {

        UsersDB user = new UsersDB();

        try {
            user.statement.addBatch("CREATE TABLE USERS (FNAME VARCHAR(20), LNAME VARCHAR(20), \n"
                    + "EMAIL VARCHAR(30), PHONENUM VARCHAR(15), USERNAME VARCHAR(20) PRIMARY KEY,\n"
                    + "PASSWORD VARCHAR(20), EVENTSBOOKED INT, MONEYSPENT DOUBLE)");

            user.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        user.closeConnection();
    }

    //Populate Table
    public static void preloadUsers() {
        UsersDB user = new UsersDB();

        try {

            user.statement.addBatch("INSERT INTO USERS VALUES ('Jacob', 'Allan','jal@gmail.com','0276973227','jallan7288','ezA5!',2,35.7),\n"
                    + "('NathaliE', 'Rausis','natech@gmail.com','023456789','necher8522','maybe!123',0,0.0),\n"
                    + "('Cindy', 'Brown','cinderblox@gmail.com','021987654','cbrown1234','P@ssw0rd',0,0.0),\n"
                    + "('Dale', 'Hearst','davey@gmail.com','022445566','dhearst5314','b0bbOy!',0,0.0),\n"
                    + "('Alice', 'Johnson','alicejohnson@gmail.com','022894567','ajohnson3456','Rainb0w!',0,0.0),\n"
                    + "('Julius', 'Caesar','empyjules01@romemail.com','022558899','jcaesar6991','rome4EVA!',0,0.0),\n"
                    + "('Guy', 'Ingrams','gingerman@gmail.com','022222222','gingrams5972','he5G#d',2,75.5),\n"
                    + "('Boy', 'Wonderman','bobby1@gmail.com','021548754','bwonderman8214','5uperB@d',1,40.0),\n"
                    + "('Bob', 'Smith','bobbyBOYY1@yahoo.com','027456789','bsmith6789','Sunsh1ne!',0,0.0),\n"
                    + "('David', 'Clark','davidclark@hotmail.com','022654789','dclark9876','Fl0wer$',1,50.0),\n"
                    + "('Megan', 'Nguyen','megannguyen@yahoo.com','090654321','mnguyen1234','Sunfl0wer$',0,0.0),\n"
                    + "('Harry', 'Nguyen','nguyenX@gmail.com','021987456','hnguyen5678','C@rrot!',0,0.0),\n"
                    + "('Emily', 'Taylor','emilytaytay@gmail.com','022369874','etaylor5678','S@ndcastl3',1,25.0),\n"
                    + "('Noah', 'Wilson','WIIIILSOOOON@gmail.com','027123456','nwilson7890','Butt3rfly!',1,300.0),\n"
                    + "('Grace', 'Martinez','martiPersonal@hotmail.com','027654321','gmartinez2345','M00nl!ght',0,0.0),\n"
                    + "('Olivia', 'Martinez','oliviamartinez55@yahoo.com','021456789','omartinez2345','St@rburst$',0,0.0),\n"
                    + "('Liam', 'Garcia','yeahboiii@gmail.com','021454545','lgarcia5678','DAY7@',1,10.0),\n"
                    + "('Jack', 'Davis','jackie01@gmail.com','022456789','jdavis6789','S@ltySe@!',0,0.0),\n"
                    + "('Karen', 'Rodriguez','karenrodriguez1@yahoo.com','022987654','krodriguez3456','P@lmTr33!',0,0.0),\n"
                    + "('Dmitri', 'Antonov','bulldog@madmail.com','0221548445','dantonov9484','sweetA5!',0,0.0),\n"
                    + "('Joao', 'Perigosa','itsgood@gmail.com','0215485485','jperigosa8888','thisPass5%',0,0.0),\n"
                    + "('Gary', 'Snail','garythesnail@gmail.com','0225454875','gsnail8164','garY2!',1,50.0),\n"
                    + "('Logan', 'Smith','logan01@gmail.com','02173337465','lsmith6575','l0ganPass!',1,50.0)");
            user.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        user.closeConnection();
    }

    //Add user to Database
    public void addUserToDB(JTextField fName, JTextField lName, JTextField email, JTextField phNum,
            JTextField password, Connection con) {

        //I tried passing the whole text field in this method
        //Surprising it works fine
        UsersDB users = new UsersDB();
        User user = new User();

        String newFirstName = fName.getText();
        String newLastName = lName.getText();
        String newEmail = email.getText();
        String newPhoneNum = phNum.getText();
        String newPassword = password.getText();

        String newUsername = user.newUsername(newFirstName, newLastName);

        try {

            String query = "INSERT INTO USERS (FNAME, LNAME, EMAIL, PHONENUM,"
                    + "USERNAME, PASSWORD, EVENTSBOOKED, MONEYSPENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, newFirstName);
            prepStatement.setString(2, newLastName);
            prepStatement.setString(3, newEmail);
            prepStatement.setString(4, newPhoneNum);
            prepStatement.setString(5, newUsername);
            prepStatement.setString(6, newPassword);
            prepStatement.setInt(7, 0);
            prepStatement.setDouble(8, 0.0);

            //Above code runs java-sql interfacing
            //Code below executes commands and confirms with pop-up windows
            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {

                JOptionPane.showMessageDialog(null, "User Created Successfully!");

            } else {

                JOptionPane.showMessageDialog(null, "Failed Creating New User!");
            }

        } catch (NumberFormatException ohno) {

            JOptionPane.showMessageDialog(null, "Invalid Input for Numbers: " + ohno.getMessage());

        } catch (SQLException oops) {
            JOptionPane.showMessageDialog(null, "Error: " + oops.getMessage());

        }
    }

    //Find user by username
    public User findUsernameInDB(String searchKey) {

        UsersDB newUser = new UsersDB();
        Connection conn = dbManager.getConnection();
        User user = null;

        String query = "SELECT * FROM USERS WHERE USERNAME = ?";

        try ( PreparedStatement prepst = conn.prepareStatement(query)) {

            prepst.setString(1, searchKey);

            ResultSet rs = prepst.executeQuery();

            if (rs.next()) {

                user = new User(
                        rs.getString("FNAME"),
                        rs.getString("LNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONENUM"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getInt("EVENTSBOOKED"),
                        rs.getDouble("MONEYSPENT")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            closeConnection();

        }

        return user;
    }

    //Called to update the money spent value of the user when they make
    //a booking
    public void updateUserMoneySpent(double moneySpent, String username) {

        UsersDB users = new UsersDB();
        Connection conn = users.conn;

        try {

            String query = "UPDATE USERS SET MONEYSPENT = ? WHERE USERNAME = ?";

            try ( PreparedStatement prepstat = conn.prepareStatement(query)) {

                prepstat.setDouble(1, moneySpent);
                prepstat.setString(2, username);

                int rowsUpdated = prepstat.executeUpdate();

                if (rowsUpdated > 0) {

                    System.out.println("User Updated Successfully");
                } else {
                    System.out.println("No User found for username: " + username);
                }
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            users.dbManager.closeConnections();
        }
    }

    //Called to update the amount of bookings of the user when they make
    //a booking
    public void updateUserBookings(String username, int currentBookings) {

        UsersDB users = new UsersDB();

        Connection conn = users.conn;

        try {

            int updatedBookings = currentBookings + 1;
            String query = "UPDATE USERS SET EVENTSBOOKED = ? WHERE USERNAME = ?";

            try ( PreparedStatement prepstat = conn.prepareStatement(query)) {

                prepstat.setInt(1, updatedBookings);
                prepstat.setString(2, username);

                int rowsUpdated = prepstat.executeUpdate();

                if (rowsUpdated > 0) {

                    System.out.println("User Updated Successfully");
                } else {
                    System.out.println("No User found for username: " + username);
                }
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            users.dbManager.closeConnections();
        }
    }

    //Find in user database by First Name
    public User findFNameInDB(String searchKey) {

        UsersDB newUser = new UsersDB();
        Connection conn = dbManager.getConnection();
        User user = null;

        String query = "SELECT * FROM USERS WHERE FNAME = ?";

        try ( PreparedStatement prepst = conn.prepareStatement(query)) {

            prepst.setString(1, searchKey);

            ResultSet rs = prepst.executeQuery();

            if (rs.next()) {

                user = new User(
                        rs.getString("FNAME"),
                        rs.getString("LNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONENUM"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getInt("EVENTSBOOKED"),
                        rs.getDouble("MONEYSPENT")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            closeConnection();

        }

        return user;
    }

    //Function to return a list of all the users in the table
    public List<User> getAllUserInDB() {

        List<User> users = new ArrayList<>();

        Connection conn = dbManager.getConnection();

        String query = "SELECT * FROM USERS";

        try ( PreparedStatement prepst = conn.prepareStatement(query)) {

            ResultSet rs = prepst.executeQuery();

            while (rs.next()) {

                User user = new User(
                        rs.getString("FNAME"),
                        rs.getString("LNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONENUM"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getInt("EVENTSBOOKED"),
                        rs.getDouble("MONEYSPENT")
                );
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            closeConnection();

        }

        return users;
    }

    //Delete user by username
    public void deleteUser(String username){
    
        Connection conn = dbManager.getConnection();
        
        try{
        
            String query = "DELETE FROM USERS WHERE USERNAME = ?";
            PreparedStatement prepstat = conn.prepareStatement(query);
            
            prepstat.setString(1, username);
            
            int rowsAffected = prepstat.executeUpdate();
            
            if(rowsAffected > 0){
            
                System.out.println("User " + username + " deleted.");
            } else {
            
                System.out.println("No user deleted." + username + " did not match any users");
            }
        } catch (SQLException oops){
        
            oops.printStackTrace();
        } finally {
        
            closeConnection();
        }
    } 
    
    //Returns total number of unique users that have a booking
    public int getBookedUserCount() {
        int count = 0;

        Connection conn = dbManager.getConnection();

        String query = "SELECT * FROM USERS WHERE EVENTSBOOKED > 0";

        try ( PreparedStatement prepst = conn.prepareStatement(query);  ResultSet rs = prepst.executeQuery()) {

            while (rs.next()) {

                if (rs.getInt("EVENTSBOOKED") > 0) {

                    count++;
                }
                //count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;
    }

    //Returns total number of users
    public int getUserCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM USERS";

        try ( Connection conn = dbManager.getConnection();  PreparedStatement prepst = conn.prepareStatement(query);  ResultSet rs = prepst.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;
    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }
}
