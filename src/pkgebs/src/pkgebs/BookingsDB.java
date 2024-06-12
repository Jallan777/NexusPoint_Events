/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jacob
 */
public class BookingsDB {

    DatabaseManager dbManager;
    Connection conn;
    Statement statement;

    //Initializes database connection through DatabaseManager class
    public BookingsDB() {
        dbManager = new DatabaseManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    //Creates table for bookings
    public static void makeBookingsTable() {

        BookingsDB booking = new BookingsDB();

        try {
            booking.statement.addBatch("CREATE TABLE BOOKINGS (REF VARCHAR(20) PRIMARY KEY, USERNAME VARCHAR(20), \n"
                    + "EVENTNAME VARCHAR(40), EVENTCOST DOUBLE, FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME), \n"
                    + "FOREIGN KEY (EVENTNAME) REFERENCES EVENTS(EVENTNAME))");

            booking.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        booking.closeConnection();
    }

    //Function to populate bookings table when program runs
    public static void preloadBookings() {
        BookingsDB booking = new BookingsDB();

        try {

            booking.statement.addBatch("INSERT INTO BOOKINGS VALUES ('UT251822T-GI','gingrams5972','The Lego Expo',35.5), \n"
                    + "('KZ870251B-GI','gingrams5972','Build Dayz',40.0),\n"
                    + "('DY914319T-JA','jallan7288','The Lego Expo',35.5),\n"
                    + "('ZU045999T-LG','lgarcia5678','The Office Trivia Night',10.0),\n"
                    + "('IV695324C-NW','nwilson7890','Coachella Music Festival',300.0),\n"
                    + "('CQ415336B-BW','bwonderman8214','Build Dayz',40.0),\n"
                    + "('EB035191V-JA','jallan7288','Vals Leaving Party',0.20),\n"
                    + "('RU317622C-DC','dclark9876','CrossFit Competition',50.0),\n"
                    + "('HB178910S-ET','etaylor5678','Songify Sessions',25.0),\n"
                    + "('AU800250P-GS','gsnail8164','Pixar Animation Studio Showcase',50.0),\n"
                    + "('OY903809C-LS','lsmith6575','CrossFit Competition',50.0)");
            booking.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        booking.closeConnection();
    }
    
//Search for a username in bookings DB
    public List<String> findUsernameInDB(String searchKey) {

        Connection conn = dbManager.getConnection();

        List<String> bookedEvents = new ArrayList<>();

        String query = "SELECT * FROM BOOKINGS WHERE USERNAME = ?";

        try ( PreparedStatement prepst = conn.prepareStatement(query)) {

            prepst.setString(1, searchKey);
            String eventName = "";
            ResultSet rs = prepst.executeQuery();

            while (rs.next()) {

                eventName = rs.getString("EVENTNAME");
                bookedEvents.add(eventName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            closeConnection();

        }

        return bookedEvents;
    }

    //Returns total bookings
    public int getBookingsCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM BOOKINGS";

        try ( Connection conn = dbManager.getConnection();  PreparedStatement prepst = conn.prepareStatement(query);  ResultSet rs = prepst.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;
    }

    //Add booking to table
    public void addbookingToDB(String reference, String username, String eventName, double eventCost, Connection con) {

        try {

            String query = "INSERT INTO BOOKINGS (REF, USERNAME, EVENTNAME,"
                    + " EVENTCOST) VALUES (?, ?, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, reference);
            prepStatement.setString(2, username);
            prepStatement.setString(3, eventName);
            prepStatement.setDouble(4, eventCost);

            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {

                JOptionPane.showMessageDialog(null, "Booking Made Successfully!");

            } else {

                JOptionPane.showMessageDialog(null, "Failed Creating New Booking!");
            }

        } catch (NumberFormatException ohno) {

            JOptionPane.showMessageDialog(null, "Invalid Input for Numbers: " + ohno.getMessage());

        } catch (SQLException oops) {
            JOptionPane.showMessageDialog(null, "Error: " + oops.getMessage());

        }
    }

    //Little function to close connections
    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}
