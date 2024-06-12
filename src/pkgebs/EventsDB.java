/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jacob
 */
public class EventsDB {

    DatabaseManager dbManager;
    Connection conn;
    Statement statement;

    public EventsDB() {
        dbManager = new DatabaseManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void makeEventsTable() {

        EventsDB event = new EventsDB();

        try {
            event.statement.addBatch("CREATE TABLE EVENTS (EVENTNAME VARCHAR(40) PRIMARY KEY,\n"
                    + " EVENTCOST DOUBLE, TOTALCAPACITY INT, NUMDAYSACTIVE INT, \n"
                    + "NUMSESSIONSPERDAY INT, SESSIONDURATION INT, \n"
                    + "EVENTIMGPATH VARCHAR(20))");

            event.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        event.closeConnection();
    }

    public static void preloadEvents() {
        EventsDB event = new EventsDB();

        try {

            event.statement.addBatch("INSERT INTO EVENTS VALUES ('All Blacks vs Wallabies',70.0,42000,1,1,5,'Wals_v_ABs'),\n"
                    + "('The Lego Expo',35.5,500,3,1,9,'Lego_Xpo'),\n"
                    + "('Build Dayz',40.0,50,5,2,3,'Build_Dayz'),\n"
                    + "('Yoga in the Park',10.0,100,1,1,2,'Yoga'),\n"
                    + "('The Office Trivia Night',10.0,50,1,1,2,'Office_Trivia'),\n"
                    + "('The Walking Dead Escape Room Experience',25.0,100,5,3,2,'Walking_Dead'),\n"
                    + "('Coachella Music Festival',300.0,50000,3,2,10,'Coachella'),\n"
                    + "('Beyoncé Formation World Tour',200.0,30000,3,2,8,'Beyonce'),\n"
                    + "('Pixar Animation Studio Showcase',50.0,500,2,1,7,'Pixar'),\n"
                    + "('5K Charity Run',25.0,500,1,1,3,'5k_Run'),\n"
                    + "('The Legend of Zelda Symphony Orchestra',60.0,3000,1,2,6,'Zelda'),\n"
                    + "('Skyrim LARP Day',0.0,300,1,1,6,'Skyrim'),\n"
                    + "('Jurassic Park 30th Anniversary Event',30.0,500,1,1,6,'Jurassic_Park'),\n"
                    + "('Comic-Con International',150.0,100000,4,2,10,'Comic_Con'),\n"
                    + "('The Gatsby Gala',250.0,500,1,1,6,'Gatsby_Gala'),\n"
                    + "('Songify Sessions',25.0,30,7,5,2,'Songify'),\n"
                    + "('CrossFit Competition',50.0,200,1,1,4,'Crossfit_Comp'),\n"
                    + "('Dog Walking Get-Together',0.0,12,1,2,4,'Dog_Walking'),\n"
                    + "('Val''s Leaving Party',0.2,9,1,1,3,'NADA'),\n"
                    + "('Shrek Rave',35.0,3000,1,1,4,'Shrek_Rave')"
            );
            event.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        event.closeConnection();
    }

    public void updateEventCapacity(int totalCapacity, String eventName) {

        EventsDB events = new EventsDB();
        Connection conn = events.conn;

        try {

            String query = "UPDATE EVENTS SET TOTALCAPACITY = ? WHERE EVENTNAME = ?";

            try ( PreparedStatement prepstat = conn.prepareStatement(query)) {

                prepstat.setInt(1, totalCapacity);
                prepstat.setString(2, eventName);

                int rowsUpdated = prepstat.executeUpdate();

                if (rowsUpdated > 0) {

                    System.out.println("Event Updated Successfully");
                } else {
                    System.out.println("No event found for name: " + eventName);
                }
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            events.dbManager.closeConnections();
        }
    }

    public void addEventToDB(JTextField eName, JTextField eCost, JTextField eCap, JTextField eDays,
            JTextField eSessions, JTextField eSessionDur, JTextField imgPath, Connection con) {

        String eventName = eName.getText();
        double eventCost = Double.parseDouble(eCost.getText());
        int eventCapacity = Integer.parseInt(eCap.getText());
        int eventDays = Integer.parseInt(eDays.getText());
        int eventSessions = Integer.parseInt(eSessions.getText());
        int eventSesDur = Integer.parseInt(eSessionDur.getText());
        String eventImgPath = imgPath.getText();

        try {

            String query = "INSERT INTO EVENTS (EVENTNAME, EVENTCOST, TOTALCAPACITY, NUMDAYSACTIVE,"
                    + "NUMSESSIONSPERDAY, SESSIONDURATION, EVENTIMGPATH) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, eventName);
            prepStatement.setDouble(2, eventCost);
            prepStatement.setInt(3, eventCapacity);
            prepStatement.setInt(4, eventDays);
            prepStatement.setInt(5, eventSessions);
            prepStatement.setInt(6, eventSesDur);
            prepStatement.setString(7, eventImgPath);

            int rowsAffected = prepStatement.executeUpdate();

            if (rowsAffected > 0) {

                JOptionPane.showMessageDialog(null, "Event Added Successfully!");

            } else {

                JOptionPane.showMessageDialog(null, "Failed Adding Event!");
            }

        } catch (NumberFormatException ohno) {

            JOptionPane.showMessageDialog(null, "Invalid Input for Numbers: " + ohno.getMessage());

        } catch (SQLException oops) {
            JOptionPane.showMessageDialog(null, "Error: " + oops.getMessage());

        }
    }

    public List<Event> getAllEventInDB() {

        List<Event> events = new ArrayList<>();

        Connection conn = dbManager.getConnection();

        String query = "SELECT * FROM EVENTS";

        try ( PreparedStatement prepst = conn.prepareStatement(query)) {

            ResultSet rs = prepst.executeQuery();

            while (rs.next()) {

                Event event = new Event(
                        rs.getString("EVENTNAME"),
                        rs.getInt("EVENTCOST"),
                        rs.getInt("TOTALCAPACITY"),
                        rs.getInt("NUMDAYSACTIVE"),
                        rs.getInt("NUMSESSIONSPERDAY"),
                        rs.getInt("SESSIONDURATION"),
                        rs.getString("EVENTIMGPATH")
                );
                events.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            closeConnection();

        }

        return events;
    }

    public int getEventCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM EVENTS";

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
