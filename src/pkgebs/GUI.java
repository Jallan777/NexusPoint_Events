/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgebs;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.util.Collections;
import java.util.ArrayList;

public class GUI extends JFrame {

    String adminUser = "";
    String adminPass = "";
    static String windowName;
    private static int attempts = 3;

    public static final Font font1 = new Font("Arial", Font.PLAIN, 30);
    public static final Font font2 = new Font("Arial", Font.BOLD, 36);
    public static final Font titleFont = new Font("Arial", Font.BOLD, 60);
    private static final String BG_COLOUR = "#232D3F";
    private static final String TEXT_BG_COLOUR = "#0F0F0F";
    private static final String BUTTON_COLOUR = "#63ebeb";
    private static final String HOVER_COLOUR = "#4fbcbc";
    private static final Color initialColour = Color.decode(BUTTON_COLOUR);
    private static final Color hoverColour = Color.decode(HOVER_COLOUR);

    private static JFrame frame;
    private static JPanel mainPanel = new JPanel();
    private static JPanel textSubPanel = new JPanel();
    private static JLabel greeting = new JLabel(Main.generateMenuAsk(Admin.adminGreeting()));

    public static void displayEventsGUI(boolean hasBanner) {

        EventsDB events = new EventsDB();

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            if (hasBanner) {

                // Banner Image
                ImageIcon bannerIcon = new ImageIcon("banner2.png"); // Adjust the path as needed
                JLabel bannerLabel = new JLabel(bannerIcon);
                bannerLabel.setBackground(Color.decode(BG_COLOUR));
                bannerLabel.setHorizontalAlignment(JLabel.CENTER);
                mainPanel.add(bannerLabel, BorderLayout.NORTH);

            }

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));
            textSubPanel.add(new JLabel("Sample Text")); // Placeholder content

            if (hasBanner) {

                textSubPanelWrapper.add(textSubPanel, BorderLayout.CENTER);
                textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);

            } else {
                textPanel.add(textSubPanel, BorderLayout.CENTER);
            }

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            mainPanel.add(textPanel, BorderLayout.CENTER);

            if (hasBanner) {

                displayEventsFromDB(textSubPanel, 3, false, false);
                //displayEventsFromFile(textSubPanel, 3, false, false);
            } else {

                displayEventsFromDB(textSubPanel, events.getEventCount(), true, false);
                //displayEventsFromFile(textSubPanel, FileHandling.countLines("events.txt"), true, false);
            }

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            if (hasBanner) {

                addMenuButton(buttonPanel, "Browse Events", "EVNT");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Book an Event", "BKEVNT");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "My Account", "USRLOG");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Admin Menu", "ADLOG");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Exit Program", "EXIT");
            } else {

                addMenuButton(buttonPanel, "My Account", "USRLOG");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Back to Main Menu", "MAINMNU");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Exit Program", "EXIT");
            }

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });
    }

    public static void adminUsersGUI() {

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));
            textSubPanel.add(new JLabel("Sample Text")); // Placeholder content

            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);

            textPanel.add(textSubPanel, BorderLayout.CENTER);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            mainPanel.add(textPanel, BorderLayout.CENTER);
            UsersDB allUsers = new UsersDB();
            displayAllUsers(textSubPanel);
            //displayEventsFromFile(textSubPanel, FileHandling.countLines("events.txt"), true, false);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            addMenuButton(buttonPanel, "View all Users", "ALLUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Find User", "FNDUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Add User", "ADDUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Delete User", "DELUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Back to Admin Menu", "ADPRELOG");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });
    }

    public static void adminMenuGUI() {

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            textSubPanel.removeAll();
            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));
            textSubPanel.add(greeting);

            textSubPanelWrapper.add(textSubPanel);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);
            mainPanel.add(textPanel, BorderLayout.CENTER);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            addMenuButton(buttonPanel, "Event Management", "EVNTMNG");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "User Management", "USRMNG");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Admin Details", "ADINFO");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Generate Report", "GENREP");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Back to Main Menu", "MAINMNU");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            greeting.setFont(font2);
            greeting.setForeground(Color.WHITE);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });
    }

    public static void adminEventsGUI() {

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        EventsDB events = new EventsDB();

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));
            textSubPanel.add(new JLabel("Sample Text")); // Placeholder content

            textSubPanelWrapper.add(textSubPanel, BorderLayout.CENTER);
            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            mainPanel.add(textPanel, BorderLayout.CENTER);

            displayEventsFromDB(textSubPanel, events.getEventCount(), true, true);
            //displayEventsFromFile(textSubPanel, FileHandling.countLines("events.txt"), true, true);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            addMenuButton(buttonPanel, "View All Events", "ALLEVNT");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Find an Event", "FNDEVNT");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Add an Event", "ADDEVNT");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Back to Admin Menu", "ADPRELOG");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });
    }

    public static void userMenuGUI(String username) {

        UsersDB allUsers = new UsersDB();
        User user = allUsers.findUsernameInDB(username);

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            textSubPanel.removeAll();
            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));
            //textSubPanel.add(greeting);

            textSubPanelWrapper.add(textSubPanel);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);
            mainPanel.add(textPanel, BorderLayout.CENTER);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            JButton myEventsButton = new JButton("My Events");

            Dimension buttonSize = new Dimension(200, 50);
            myEventsButton.setPreferredSize(buttonSize);

            myEventsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    myEventsGUI(username);
                }
            });

            styleButton(myEventsButton, Color.decode(BUTTON_COLOUR), Color.decode(HOVER_COLOUR), true);
            myEventsButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Align buttons to the left
            myEventsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, myEventsButton.getPreferredSize().height)); // Set maximum width

            buttonPanel.add(myEventsButton);
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Edit Details", "ACCTDETS");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Log Out", "MAINMNU");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            //JPanel TEST = createProfileTitlePanel(username);
            JPanel userPanel = createUserProfilePanel(user, font2, initialColour, hoverColour, false);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);
            mainPanel.add(userPanel);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });
    }

    public static void myEventsGUI(String username) {

        UsersDB allUsers = new UsersDB();
        User user = allUsers.findUsernameInDB(username);
        BookingsDB userBookings = new BookingsDB();

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            textSubPanel.removeAll();
            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));

            textSubPanelWrapper.add(textSubPanel);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);
            mainPanel.add(textPanel, BorderLayout.CENTER);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Edit Details", "ACCTDETS");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Log Out", "MAINMNU");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");
            addGlue(buttonPanel);

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            List<String> bookings = userBookings.findUsernameInDB(username);
            List<Event> bookedEvents = new ArrayList<>();

            //System.out.println("Bookings found for user: " + username); debugging
            for (String eventName : bookings) {

                List<Event> foundEvents = (EventManagement.search(eventName));

                System.out.println(foundEvents.size());
                if (foundEvents != null && !foundEvents.isEmpty()) {

                    for (Event event : foundEvents) {

                        //System.out.println("Found event: " + event); debugging
                        bookedEvents.add(event);
                    }

                } else {

                    System.out.println("No events found for name " + eventName);
                }
            }
            //System.out.println("Final list of booked events: " + bookedEvents); debugging

            if (bookedEvents.isEmpty()) {

                JLabel noEvents = new JLabel("No Events Booked for this User!");
                textSubPanel.add(noEvents);
            } else {

                for (Event event : bookedEvents) {

                    //System.out.println("Adding event: " + event.getEventName()); debugging
                    //System.out.println(bookedEvents.size()); debugging
                    JPanel eventPanel = createEventPanel(event, font1, initialColour, hoverColour, false, true);
                    JLabel eventLabel = new JLabel(event.toString());
                    eventLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    textSubPanel.add(eventPanel);
                }
            }

            JScrollPane scrollPane = new JScrollPane(textSubPanel);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollBar vertScrollBar = scrollPane.getVerticalScrollBar();
            vertScrollBar.setUnitIncrement(16);
            vertScrollBar.setBlockIncrement(100);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
            SwingUtilities.invokeLater(() -> vertScrollBar.setValue(0));

        });
    }

    public static void displayReport(Admin admin, JPanel textSubPanel) {

        String reportInfo = "Admin Name:\t\t" + admin.getAdminName() + "\n\n"
                + "Total Events:\t\t" + admin.getTotalEvents() + "\n"
                + "Total Bookings:\t" + admin.getTotalBookings() + "\n"
                + "Total Users:\t\t" + admin.getTotalUsers() + "\n"
                + "Total Booked Users:\t" + admin.getTotalBookedUsers() + "\n";

//        JLabel adminReport = new JLabel("Admin Report");
//        adminReport.setFont(font2);
//        adminReport.setForeground(Color.WHITE);
        JTextArea textArea = new JTextArea(reportInfo);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(titleFont);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 100, 0, 0));
        textArea.setEditable(false);

        //JPanel wrapperPanel = new JPanel();
        //Dimension wrapperPanelDimension = new Dimension(textSubPanel.getSize());
        //wrapperPanel.setSize(wrapperPanelDimension);
        //wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //wrapperPanel.setBackground(textSubPanel.getBackground());
        //wrapperPanel.add(adminReport);
        //wrapperPanel.add(textArea, BorderLayout.WEST);
        textSubPanel.removeAll();
        textSubPanel.add(textArea);
        textSubPanel.revalidate();
        textSubPanel.repaint();
    }

    public static void displayAdminDeets(Admin admin, JPanel textSubPanel) {

        String reportInfo = "Admin Username:\t" + admin.getAdminName() + "\n\n"
                + "Admin Password:\t" + admin.getPassword() + "\n\n";

        JTextArea textArea = new JTextArea(reportInfo);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(font2);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 100, 0, 0));
        textArea.setEditable(false);

        JButton changeDeets = new JButton();
        changeDeets.setText("Change Details?");
        styleButton(changeDeets, initialColour, hoverColour, false);
        changeDeets.setSize(400, 250);

        changeDeets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                displayAdminDetailsWindow();
            }

        });

        textSubPanel.removeAll();
        textSubPanel.add(textArea);
        textSubPanel.add(changeDeets);
        textSubPanel.revalidate();
        textSubPanel.repaint();
    }

    public static JPanel getTextSubPanel() {
        return textSubPanel;
    }

    private static void frameSetup() {

        if (frame == null) {

            frame = new JFrame("NexusPoint Events");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    private static void addMenuButton(JPanel buttonPanel, String label, String menuChoice) {
        JButton button = new JButton(label);

        Dimension buttonSize = new Dimension(200, 50);
        button.setPreferredSize(buttonSize);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.menuSelection(menuChoice);
            }
        });

        styleButton(button, Color.decode(BUTTON_COLOUR), Color.decode(HOVER_COLOUR), true);
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align buttons to the left
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height)); // Set maximum width

        buttonPanel.add(button);
    }

    private static void styleButton(JButton button, Color initialColour, Color hoverColour, boolean withBorder) {

        if (!withBorder) {

            button.setBackground(initialColour);
            button.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(java.awt.event.MouseEvent event) {

                    button.setBackground(hoverColour);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent event) {

                    button.setBackground(initialColour);
                }
            });
        } else {

            Border defBorder = button.getBorder();
            Border glowBorder = BorderFactory.createLineBorder(hoverColour, 10);

            button.setBackground(initialColour);
            button.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(java.awt.event.MouseEvent event) {

                    button.setBackground(hoverColour);
                    button.setBorder(glowBorder);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent event) {

                    button.setBackground(initialColour);
                    button.setBorder(defBorder);
                }
            });
        }

    }

    private static void addGlue(JPanel panel) {
        panel.add(Box.createVerticalGlue());
    }

    public static void displayLoginWindow(boolean isAdmin, boolean toEvents) {

        if (isAdmin) {

            windowName = "Admin Login";
        } else if (!isAdmin) {

            windowName = "User Login";
        }
        JFrame loginFrame = new JFrame(windowName);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension loginFrameSize = new Dimension(350, 200);
        loginFrame.setPreferredSize(loginFrameSize);
        loginFrame.setBackground(Color.decode(BG_COLOUR));

        JPanel loginPanel = new JPanel(new GridLayout(5, 2)); // 3 rows, 2 columns
        loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        loginPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        JTextField usernameField = new JTextField(20); // Set preferred width

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(20); // Set preferred width

        JLabel incorrectAttempt = new JLabel("Invalid Username or Password.");
        incorrectAttempt.setForeground(Color.WHITE);
        incorrectAttempt.setVisible(false);

        JLabel triesRemaining = new JLabel("You have " + attempts + " login attempts remaining.");
        triesRemaining.setForeground(Color.WHITE);
        triesRemaining.setVisible(false);

        JPanel errorPanel = new JPanel(new BorderLayout());
        errorPanel.setBackground(Color.decode(BG_COLOUR));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode(BG_COLOUR));

        JButton submitButton = new JButton("Login");
        styleButton(submitButton, initialColour, hoverColour, false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Check if username and password are correct (add your logic here)
                errorPanel.remove(incorrectAttempt);
                errorPanel.remove(triesRemaining);

                if (isValidCredentials(username, password, isAdmin)) {
                    // Credentials are correct, perform actions based on isAdmin flag
                    loginFrame.dispose(); // Close the login window
                    onSuccessfulLogin(isAdmin, username, toEvents);
                } else {
                    attempts--;
                    usernameField.setText("");
                    passwordField.setText("");
                    if (attempts > 0) {

                        errorPanel.repaint();
                        triesRemaining.setText("You have " + attempts + " login attempts remaining.");
                        errorPanel.add(incorrectAttempt, BorderLayout.NORTH);
                        errorPanel.add(triesRemaining, BorderLayout.SOUTH);
                        incorrectAttempt.setVisible(true);
                        triesRemaining.setVisible(true);

                    } else {

                        JOptionPane.showMessageDialog(loginFrame, "Login Attempts Exceeded. Please try again later."
                                + " Returning to Main Menu.", "Error", JOptionPane.ERROR_MESSAGE);
                        loginFrame.dispose();
                        new Thread(() -> resetAttempts(30)).start();
                        displayEventsGUI(true);

                    }
                }
            }
        });

        JLabel temp = new JLabel();
        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                loginFrame.dispose();
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(submitButton);
        loginPanel.add(temp); // Empty label for spacing

        if (!isAdmin) {

            JButton newUserButton = new JButton("Create Account");
            styleButton(newUserButton, initialColour, hoverColour, false);
            newUserButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    newUserFrame();
                    //loginFrame.dispose();
                }
            });
            loginPanel.remove(temp);
            loginPanel.add(newUserButton);
        }

        loginPanel.add(cancelButton);

        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.add(errorPanel, BorderLayout.SOUTH);

        loginFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null); // Center the window
        loginFrame.setVisible(true);

    }

    private static void resetAttempts(int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000); // Wait for the specified delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        attempts = 3;
    }

    public static void displaySearchWindow(boolean isAdmin, boolean isEvent) {

        if (isAdmin) {

            windowName = isEvent ? "Admin Event Search" : "Admin User Search";

        } else if (!isAdmin) {

            windowName = "User Event Search";
        }
        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        eventsPanel.setOpaque(false);

        JFrame searchFrame = new JFrame(windowName);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension searchFrameSize = new Dimension(350, 200);
        searchFrame.setPreferredSize(searchFrameSize);

        JPanel searchPanel = new JPanel(new GridLayout(5, 1)); // 3 rows, 2 columns
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel searchLabel = new JLabel();
        if (isEvent) {

            searchLabel.setText("Event Search: ");

        } else {

            searchLabel.setText("User Search: ");
        }

        searchLabel.setForeground(Color.WHITE);
        JTextField nameField = new JTextField(20); // Set preferred width

        JLabel searchFail = new JLabel("No Results for Search: " + nameField.getText());
        searchFail.setForeground(Color.WHITE);
        searchFail.setVisible(false);

        JPanel errorPanel = new JPanel(new BorderLayout());
        errorPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        errorPanel.setBackground(Color.decode(BG_COLOUR));

        JPanel tempMainPanel = new JPanel(new BorderLayout());

        JButton searchButton = new JButton("Search");
        styleButton(searchButton, initialColour, hoverColour, false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();

                if (isEvent) {

                    if (isAdmin) {

                        displayEventSearchResult(mainPanel, name, true);
                    } else {

                        displayEventSearchResult(mainPanel, name, false);
                    }
                } else {

                    displayUserSearchResult(mainPanel, name);
                }
                searchFrame.dispose();
            }

        });
        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                searchFrame.dispose();
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(nameField);
        if (!isAdmin) {

            JLabel searchInfo = new JLabel("Enter the name of an Event");
            searchInfo.setForeground(Color.WHITE);
            searchPanel.add(searchInfo);
        } else {
            searchPanel.add(new JLabel()); // Empty label for spacing
        }
        searchPanel.add(searchButton);
        searchPanel.add(cancelButton);

        errorPanel.add(searchFail, BorderLayout.NORTH);

        tempMainPanel.add(searchPanel, BorderLayout.CENTER);
        tempMainPanel.add(errorPanel, BorderLayout.SOUTH);

        searchFrame.getContentPane().add(tempMainPanel, BorderLayout.CENTER);
        searchFrame.pack();
        searchFrame.setLocationRelativeTo(null); // Center the window
        searchFrame.setVisible(true);

    }

    public static void displayEventsFromDB(JPanel contentPanel, int eventsToLoad, boolean allEvents, boolean isAdmin) {
        startContentPanel(contentPanel);

        // Use a separate thread to wait for 2 seconds before continuing with the rest of the code
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                contentPanel.removeAll();
                contentPanel.revalidate();
                contentPanel.repaint();

                List<Event> selectedEvents = loadEvents(eventsToLoad);

                JPanel titlePanel = null;
                JPanel eventsPanel = new JPanel();
                eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
                eventsPanel.setOpaque(false);

                JTextArea firstLine = new JTextArea("__________________________________________________________________________________________________\n");
                firstLine.setOpaque(false);
                firstLine.setForeground(Color.WHITE);
                firstLine.setFont(font1);
                eventsPanel.add(firstLine);

                if (!isAdmin) {

                    titlePanel = createEventTitlePanel(contentPanel, allEvents, false);
                    for (Event event : selectedEvents) {
                        JPanel eventPanel = createEventPanel(event, font1, initialColour, hoverColour, true, false);
                        eventsPanel.add(eventPanel);
                    }

                } else {

                    titlePanel = createEventTitlePanel(contentPanel, allEvents, true);
                    for (Event event : selectedEvents) {
                        JPanel eventPanel = createEventPanel(event, font1, initialColour, hoverColour, false, false);
                        eventsPanel.add(eventPanel);
                    }

                }

                addComponentsToContentPanel(contentPanel, titlePanel, eventsPanel);
            });
        }).start();
    }

    public static void displayAllUsers(JPanel contentPanel) {
        startContentPanel(contentPanel);

        UsersDB users = new UsersDB();

        SwingUtilities.invokeLater(() -> {

            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            List<User> allUsers = users.getAllUserInDB();

            JPanel titlePanel = null;
            JPanel usersPanel = new JPanel();
            usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
            usersPanel.setOpaque(false);

            JTextArea firstLine = new JTextArea("__________________________________________________________________________________________________\n");
            firstLine.setOpaque(false);
            firstLine.setForeground(Color.WHITE);
            firstLine.setFont(font1);
            usersPanel.add(firstLine);

            titlePanel = createUserTitlePanel(contentPanel, true, true);

            for (User user : allUsers) {
                JPanel userPanel = createUserPanel(user, font1, initialColour, hoverColour, false);
                usersPanel.add(userPanel);
            }

            addComponentsToContentPanel(contentPanel, titlePanel, usersPanel);
        });
    }

    public static void displayAllEvents(JPanel contentPanel, int eventsToLoad, boolean all, boolean isAdmin) {
        startContentPanel(contentPanel);

        EventsDB events = new EventsDB();

        SwingUtilities.invokeLater(() -> {

            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            List<Event> allEvents = events.getAllEventInDB();

            JPanel titlePanel = null;
            JPanel eventsPanel = new JPanel();
            eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
            eventsPanel.setOpaque(false);

            JTextArea firstLine = new JTextArea("__________________________________________________________________________________________________\n");
            firstLine.setOpaque(false);
            firstLine.setForeground(Color.WHITE);
            firstLine.setFont(font1);
            eventsPanel.add(firstLine);

            titlePanel = createEventTitlePanel(contentPanel, true, isAdmin);

            for (Event event : allEvents) {
                JPanel eventPanel = createEventPanel(event, font1, initialColour, hoverColour, false, false);
                eventsPanel.add(eventPanel);
            }

            addComponentsToContentPanel(contentPanel, titlePanel, eventsPanel);
        });
    }

    public static void displayUserSearchResult(JPanel contentPanel, String userKey) {

        startContentPanel(contentPanel);

        UsersDB userDB = new UsersDB();
        // Use a separate thread to wait for 2 seconds before continuing with the rest of the code
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                contentPanel.removeAll();
                contentPanel.revalidate();
                contentPanel.repaint();

                List<User> allUsers = loadUsers(userDB.getUserCount());
                List<User> filteredUsers = UserManagement.search(userKey);

                JPanel titlePanel = null;
                JPanel usersPanel = new JPanel();
                usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
                usersPanel.setOpaque(false);

                JTextArea firstLine = new JTextArea("__________________________________________________________________________________________________\n");
                firstLine.setOpaque(false);
                firstLine.setForeground(Color.WHITE);
                firstLine.setFont(font1);
                usersPanel.add(firstLine);

                titlePanel = createSearchTitlePanel(contentPanel, filteredUsers.size(), 1, userKey);

                for (User user : filteredUsers) {
//                    
                    JPanel userPanel = createUserPanel(user, font1, initialColour, hoverColour, false);
                    usersPanel.add(userPanel);

                }

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                buttonPanel.setBackground(Color.decode(BG_COLOUR));

                addMenuButton(buttonPanel, "View All Users", "ALLUSR");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Find a User", "FNDUSR");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Add a User", "ADDUSR");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Delete a User", "DELUSR");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Back to Admin Menu", "ADPRELOG");
                addGlue(buttonPanel);
                addMenuButton(buttonPanel, "Exit Program", "EXIT");

                buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

                JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
                buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
                buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
                buttonPanelWrapper.add(buttonPanel);

                mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

                frame.revalidate();
                frame.repaint();

                addComponentsToContentPanel(contentPanel, titlePanel, usersPanel);
            });
        }).start();
    }

    public static void displayEventSearchResult(JPanel contentPanel, String eventName, boolean isAdmin) {

        startContentPanel(contentPanel);

        EventsDB events = new EventsDB();
        // Use a separate thread to wait for 2 seconds before continuing with the rest of the code
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                contentPanel.removeAll();
                contentPanel.revalidate();
                contentPanel.repaint();

                List<Event> allEvents = loadEvents(events.getEventCount());
                List<Event> filteredEvents = EventManagement.search(eventName);

                JPanel titlePanel = null;
                JPanel eventsPanel = new JPanel();
                eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
                eventsPanel.setOpaque(false);

                JTextArea firstLine = new JTextArea("__________________________________________________________________________________________________\n");
                firstLine.setOpaque(false);
                firstLine.setForeground(Color.WHITE);
                firstLine.setFont(font1);
                eventsPanel.add(firstLine);

                titlePanel = createSearchTitlePanel(contentPanel, filteredEvents.size(), 2, eventName);

                for (Event event : filteredEvents) {
                    if (event.getEventName().toLowerCase().contains(eventName)) {

                        if (isAdmin) {

                            JPanel eventPanel = createEventPanel(event, font1, initialColour, hoverColour, false, false);
                            eventsPanel.add(eventPanel);
                        } else {

                            JPanel eventPanel = createEventPanel(event, font1, initialColour, hoverColour, true, false);
                            eventsPanel.add(eventPanel);
                        }

                    }

                }

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                buttonPanel.setBackground(Color.decode(BG_COLOUR));

                if (isAdmin) {

                    addMenuButton(buttonPanel, "View All Events", "ALLEVNT");
                    addGlue(buttonPanel);
                    addMenuButton(buttonPanel, "Find an Event", "FNDEVNT");
                    addGlue(buttonPanel);
                    addMenuButton(buttonPanel, "Add an Event", "ADDEVNT");
                    addGlue(buttonPanel);
                    addMenuButton(buttonPanel, "Back to Admin Menu", "ADPRELOG");
                    addGlue(buttonPanel);
                    addMenuButton(buttonPanel, "Exit Program", "EXIT");
                } else {

                    addMenuButton(buttonPanel, "My Account", "USRLOG");
                    addGlue(buttonPanel);
                    addMenuButton(buttonPanel, "Back to Main Menu", "MAINMNU");
                    addGlue(buttonPanel);
                    addMenuButton(buttonPanel, "Exit Program", "EXIT");
                }

                buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

                JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
                buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
                buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
                buttonPanelWrapper.add(buttonPanel);

                mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

                frame.revalidate();
                frame.repaint();

                addComponentsToContentPanel(contentPanel, titlePanel, eventsPanel);
            });
        }).start();
    }

    public static void userManagementGUI() {

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            textSubPanel.removeAll();
            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));

            textSubPanelWrapper.add(textSubPanel);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);
            mainPanel.add(textPanel, BorderLayout.CENTER);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            addMenuButton(buttonPanel, "View All Users", "ALLUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Find a User", "FNDUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Add a User", "ADDUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Delete a User", "DELUSR");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Back to Admin Menu", "ADPRELOG");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });

    }

    public static void eventManagementGUI() {

        Component[] pageParts = mainPanel.getComponents();
        for (Component component : pageParts) {

            if (component instanceof JLabel) {

                mainPanel.remove(component);
            }
        }

        SwingUtilities.invokeLater(() -> {

            frameSetup();

            textSubPanel.removeAll();
            mainPanel.removeAll();

            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.decode(BG_COLOUR));
            frame.getContentPane().add(mainPanel);

            // Text Content Panel (to seperate area from button panel)
            JPanel textPanel = new JPanel(new BorderLayout());

            // Wrapper Panel for textSubPanel
            JPanel textSubPanelWrapper = new JPanel(new BorderLayout());
            textSubPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); // Adjust padding as needed
            textSubPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));

            // SubPanel for Event Content
            textSubPanel.setBackground(Color.decode(BG_COLOUR));

            // Size and content of textSubPanel
            textSubPanel.setLayout(new BoxLayout(textSubPanel, BoxLayout.Y_AXIS));

            textSubPanelWrapper.add(textSubPanel);

            textPanel.setBackground(Color.decode(TEXT_BG_COLOUR)); // Set background color
            textPanel.add(textSubPanelWrapper, BorderLayout.CENTER);
            mainPanel.add(textPanel, BorderLayout.CENTER);

            // Button Panel (Vertical Layout on the left)
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.decode(BG_COLOUR));

            addMenuButton(buttonPanel, "View All Events", "ALLEVNT");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Find an Event", "FNDEVNT");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Add an Event", "ADDEVNT");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Back to Admin Menu", "ADPRELOG");
            addGlue(buttonPanel);
            addMenuButton(buttonPanel, "Exit Program", "EXIT");

            buttonPanel.add(Box.createVerticalGlue()); // Adds flexible space after the final button
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Adds another rigid space after the glue space

            JPanel buttonPanelWrapper = new JPanel(new BorderLayout());
            buttonPanelWrapper.setBackground(Color.decode(TEXT_BG_COLOUR));
            buttonPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); // Adjust padding as needed
            buttonPanelWrapper.add(buttonPanel);

            mainPanel.add(buttonPanelWrapper, BorderLayout.WEST);

            frame.revalidate();
            frame.repaint();

            if (!frame.isVisible()) {

                frame.setVisible(true);

            }
        });
    }

    private static void startContentPanel(JPanel contentPanel) {

        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();

        ImageIcon loadingGIF = new ImageIcon("Intersection.gif");
        JLabel loadingLabel = new JLabel(loadingGIF);
        loadingLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(loadingLabel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

    }

    private static void displayAdminDetailsWindow() {

        windowName = "Change Admin Details";
        Admin admin = FileHandling.readAdminDetailsFromFile();

        JFrame detailsFrame = new JFrame(windowName);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension detailsFrameSize = new Dimension(350, 200);
        detailsFrame.setPreferredSize(detailsFrameSize);
        detailsFrame.setBackground(Color.decode(BG_COLOUR));

        JPanel detailsPanel = new JPanel(new GridLayout(5, 2)); // 3 rows, 2 columns
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        detailsPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel usernameLabel = new JLabel("New Username:");
        usernameLabel.setForeground(Color.WHITE);
        JTextField usernameField = new JTextField(20); // Set preferred width

        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(20); // Set preferred width

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode(BG_COLOUR));

        JButton submitButton = new JButton("Change");
        styleButton(submitButton, initialColour, hoverColour, false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Check if username and password are correct (add your logic here)

                admin.setAdminName(username);
                admin.setPassword(password);

                FileHandling.writeAdminToFile(admin);
                detailsFrame.dispose();
                displayAdminDeets(admin, getTextSubPanel());

            }
        });

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                detailsFrame.dispose();
            }
        });

        detailsPanel.add(usernameLabel);
        detailsPanel.add(usernameField);
        detailsPanel.add(passwordLabel);
        detailsPanel.add(passwordField);
        detailsPanel.add(new JLabel()); // Empty label for spacing
        detailsPanel.add(submitButton);
        detailsPanel.add(new JLabel()); // Empty label for spacing
        detailsPanel.add(cancelButton);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(detailsPanel, BorderLayout.SOUTH);

        detailsFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        detailsFrame.pack();
        detailsFrame.setLocationRelativeTo(null); // Center the window
        detailsFrame.setVisible(true);

    }

    private static List<Event> loadEvents(int numEvents) {

        EventsDB allEvents = new EventsDB();
        List<Event> events = allEvents.getAllEventInDB();    //Create event list from file of events
        Collections.shuffle(events);                                //Use collections to shuffle list
        return events.subList(0, Math.min(events.size(), numEvents));       //Return a sublist of 3 randomly selected events 
    }

    private static List<User> loadUsers(int numUsers) {
        UsersDB userDB = new UsersDB();

        List<User> users = userDB.getAllUserInDB();

//        if(numUsers <= 0){
//        
//            return new ArrayList<>();
//        }
        return users.subList(0, Math.min(users.size(), numUsers));

    }

    private static JPanel createEventTitlePanel(JPanel contentPanel, boolean all, boolean isAdmin) {

        EventsDB events = new EventsDB();
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("There are " + events.getEventCount() + " Events on Now!");

        if (!all && !isAdmin) {

            titleLabel = new JLabel("Events on Now!");
            JButton refreshButton = new JButton("Refresh Events");
            refreshButton.setPreferredSize(new Dimension(150, 0));

            refreshButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    displayEventsFromDB(contentPanel, 3, false, isAdmin);
                    //displayEventsFromFile(contentPanel, 3, false, isAdmin);
                }
            });

            styleButton(refreshButton, Color.decode(BUTTON_COLOUR), Color.decode(HOVER_COLOUR), true);

            titlePanel.add(refreshButton, BorderLayout.EAST);
        }

        titleLabel.setFont(font2);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        return titlePanel;
    }

    private static JPanel createSearchTitlePanel(JPanel contentPanel, int numResults, int searchType, String searchKey) {

        EventsDB events = new EventsDB();
        UsersDB users = new UsersDB();
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = null;
        switch (searchType) {
            //Case 1 = Search for Users
            //Case 2 = Search for Events
            //Case 3 = Search for Bookings (if implemented)
            case 1:
                titleLabel = new JLabel("There are " + numResults + " Users matching: \"" + searchKey + "\"");
                break;
            case 2:
                titleLabel = new JLabel("There are " + numResults + " Events matching: \"" + searchKey + "\"");
                break;
            case 3:
                titleLabel = new JLabel("There are " + numResults + " Bookings matching: \"" + searchKey + "\"");

            default:
                titleLabel = new JLabel("Results: ");

        }

        titleLabel.setFont(font2);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        return titlePanel;
    }

    private static JPanel createUserTitlePanel(JPanel contentPanel, boolean all, boolean isAdmin) {

        UsersDB usersdb = new UsersDB();
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("There are " + usersdb.getUserCount() + " Users!");

        titleLabel.setFont(font2);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        return titlePanel;
    }

    private static JPanel createProfileTitlePanel(String username) {

        UsersDB usersdb = new UsersDB();
        User currentUser = usersdb.findFNameInDB(username);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Welcome Back " + currentUser.getfName() + " !");

        titleLabel.setFont(font2);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        return titlePanel;
    }

    private static JPanel createEventPanel(Event event, Font font1, Color initialColour, Color hoverColour, boolean isAdmin, boolean isUserView) {

        JTextArea line = new JTextArea("__________________________________________________________________________________________________\n");
        line.setOpaque(false);
        line.setForeground(Color.WHITE);
        line.setFont(font1);

        JPanel singleEventPanel = new JPanel(new BorderLayout());
        singleEventPanel.setOpaque(false);
        singleEventPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        String imgPath = event.getEventImage();
        JLabel eventImageLabel = new JLabel(new ImageIcon("Event_Images/" + imgPath + ".png"));
        eventImageLabel.setPreferredSize(new Dimension(300, 300));
        singleEventPanel.add(eventImageLabel, BorderLayout.WEST);

        JPanel textWithButtonPanel = new JPanel(new BorderLayout());
        textWithButtonPanel.setOpaque(false);

        String eventInfo = " Event Name:\t\t" + event.getEventName() + "\n"
                + " Cost:\t\t$" + event.getEventCost() + "0\n"
                + " Days Active:\t\t" + event.getNumDaysActive() + "\n"
                + " Sessions:\t\t" + event.getNumSessionsPerDay() + "\n"
                + " Duration:\t\t" + event.getSessionDuration() + " hours\n\n"
                + " Tickets Available:\t" + event.getEventCapacity() + "\n";

        JTextArea textArea = new JTextArea(eventInfo);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(font1);
        textArea.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        textWithButtonPanel.add(textArea, BorderLayout.CENTER);

        JButton bookNowButton = new JButton();

        if (!isUserView) {
            if (!isAdmin) {

                bookNowButton.setText("Delete Booking");
                bookNowButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Delete booking!
                    }
                });
            } else {

                bookNowButton.setText("Book Now!");
                bookNowButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //JOptionPane.showMessageDialog(frame, "Please Log In to make a Booking!", "Booking", JOptionPane.INFORMATION_MESSAGE);
                        bookingFrame(event.getEventName());
                    }
                });
            }
        } else {

            bookNowButton.setVisible(false);
        }
        bookNowButton.setPreferredSize(new Dimension(200, 50));
        bookNowButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Align button to the left
        bookNowButton.setMaximumSize(new Dimension(bookNowButton.getPreferredSize().width, bookNowButton.getPreferredSize().height)); // Set maximum width

        styleButton(bookNowButton, initialColour, hoverColour, true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(bookNowButton);

        textWithButtonPanel.add(buttonPanel, BorderLayout.SOUTH);
        singleEventPanel.add(textWithButtonPanel, BorderLayout.CENTER);

        JPanel eventContainer = new JPanel();
        eventContainer.setLayout(new BoxLayout(eventContainer, BoxLayout.Y_AXIS));
        eventContainer.setOpaque(false);
        eventContainer.add(line);
        eventContainer.add(singleEventPanel);
        eventContainer.add(line);

        return eventContainer;
    }

    private static JPanel createUserPanel(User user, Font font1, Color initialColour, Color hoverColour, boolean isAdmin) {

        JTextArea line = new JTextArea("__________________________________________________________________________________________________\n");
        line.setOpaque(false);
        line.setForeground(Color.WHITE);
        line.setFont(font1);

        JPanel singleUserPanel = new JPanel(new BorderLayout());
        singleUserPanel.setOpaque(false);
        singleUserPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        singleUserPanel.setForeground(Color.WHITE);

        JPanel textWithButtonPanel = new JPanel(new BorderLayout());
        textWithButtonPanel.setOpaque(false);

        String userInfo = " Name:\t\t" + user.getNameFull() + "\n"
                + " Email:\t\t" + user.getEmail() + "\n"
                + " Phone:\t\t" + user.getPhoneNum() + "\n"
                + " Username:\t\t" + user.getUsername() + "\n"
                + " Events Booked:\t" + user.getEventsBooked() + "\n\n"
                + " Money Spent:\t\t$" + user.getMoneySpent() + "0\n";

        JTextArea textArea = new JTextArea(userInfo);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(font1);
        textArea.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        textWithButtonPanel.add(textArea, BorderLayout.CENTER);

        JButton editDeetsButton = new JButton();
        editDeetsButton.setPreferredSize(new Dimension(200, 50));
        editDeetsButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Align button to the left
        editDeetsButton.setMaximumSize(new Dimension(editDeetsButton.getPreferredSize().width, editDeetsButton.getPreferredSize().height)); // Set maximum width

        styleButton(editDeetsButton, initialColour, hoverColour, true);

        if (!isAdmin) {

            editDeetsButton.setText("Edit Details");
        } else {

            editDeetsButton.setVisible(false);
        }

        editDeetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //edit user details
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(editDeetsButton);

        textWithButtonPanel.add(buttonPanel, BorderLayout.SOUTH);
        singleUserPanel.add(textWithButtonPanel, BorderLayout.CENTER);

        JPanel userContainer = new JPanel();
        userContainer.setLayout(new BoxLayout(userContainer, BoxLayout.Y_AXIS));
        userContainer.setOpaque(false);
        userContainer.add(line);
        userContainer.add(singleUserPanel);
        userContainer.add(line);

        return userContainer;
    }

    private static JPanel createUserProfilePanel(User user, Font font1, Color initialColour, Color hoverColour, boolean isAdmin) {

        JTextArea line = new JTextArea("__________________________________________________________________________________________________\n");
        line.setOpaque(false);
        line.setForeground(Color.WHITE);
        line.setFont(font1);

        JPanel singleUserPanel = new JPanel(new BorderLayout());
        singleUserPanel.setOpaque(false);
        singleUserPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        singleUserPanel.setForeground(Color.WHITE);

        JPanel textWithButtonPanel = new JPanel(new BorderLayout());
        textWithButtonPanel.setOpaque(false);

        String userHome = "Welcome Back " + user.getfName() + "!";

        String userInfo = " Name:\t\t" + user.getNameFull() + "\n"
                + " Email:\t\t" + user.getEmail() + "\n"
                + " Phone:\t\t" + user.getPhoneNum() + "\n"
                + " Username:\t\t" + user.getUsername() + "\n"
                + " Events Booked:\t" + user.getEventsBooked() + "\n\n"
                + " Money Spent:\t\t$" + user.getMoneySpent() + "0\n";

        JTextArea textArea = new JTextArea(userHome);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(font2);
        textArea.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        JLabel userDetails = new JLabel("My Details");
        userDetails.setFont(font1);
        userDetails.setForeground(Color.WHITE);

        JTextArea textArea2 = new JTextArea(userInfo);
        textArea2.setOpaque(false);
        textArea2.setForeground(Color.WHITE);
        textArea2.setFont(font2);
        textArea2.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        textWithButtonPanel.add(textArea, BorderLayout.CENTER);
        //textWithButtonPanel.add(userDetails);
        //textWithButtonPanel.add(textArea2);

        singleUserPanel.add(textWithButtonPanel, BorderLayout.CENTER);

        JPanel userContainer = new JPanel();
        userContainer.setLayout(new BoxLayout(userContainer, BoxLayout.Y_AXIS));
        userContainer.setOpaque(false);
        userContainer.add(singleUserPanel);
        userContainer.add(line);
        userContainer.add(textArea2);

        return userContainer;
    }

    public static void displayUsersProfile(User user, JPanel contentPanel) {
        startContentPanel(contentPanel);

        UsersDB users = new UsersDB();

        SwingUtilities.invokeLater(() -> {

            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            List<User> allUsers = users.getAllUserInDB();

            JPanel titlePanel = null;
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
            userPanel.setOpaque(false);

            JTextArea firstLine = new JTextArea("__________________________________________________________________________________________________\n");
            firstLine.setOpaque(false);
            firstLine.setForeground(Color.WHITE);
            firstLine.setFont(font1);
            userPanel.add(firstLine);

            titlePanel = createUserTitlePanel(contentPanel, true, true);

            JPanel proilePanel = createUserPanel(user, font1, initialColour, hoverColour, false);
            userPanel.add(userPanel);

            addComponentsToContentPanel(contentPanel, titlePanel, proilePanel);
        });
    }

    private static void addComponentsToContentPanel(JPanel contentPanel, JPanel titlePanel, JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JScrollBar vertScrollBar = scrollPane.getVerticalScrollBar();
        vertScrollBar.setUnitIncrement(16);
        vertScrollBar.setBlockIncrement(100);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        SwingUtilities.invokeLater(() -> vertScrollBar.setValue(0));
    }

    private static boolean isValidCredentials(String username, String password, boolean isAdmin) {

        if (isAdmin) {
            return Admin.adminLogin(username, password);
        } else {
            return User.userLogin(username, password);
        }

    }

    public void popupWindow(String message, int windowType) {

        String windowName = "";
        switch (windowType) {

            case 1:
                windowName = "Information";
                JOptionPane.showMessageDialog(frame, message, windowName, JOptionPane.INFORMATION_MESSAGE);

                break;

            case 2:
                windowName = "Error";
                JOptionPane.showMessageDialog(frame, message, windowName, JOptionPane.ERROR_MESSAGE);

                break;
            default:
                windowName = "";
                break;
        }

    }

    private static void onSuccessfulLogin(boolean isAdmin, String username, boolean toEvents) {
        // Actions to perform after successful login
        attempts = 3;

        if (isAdmin) {
            System.out.println("Admin login successful!");
            // Perform admin actions
            greeting.setText(Main.generateMenuAsk(Admin.adminGreeting()));
            adminMenuGUI();
        } else {
            System.out.println("User login successful!");
            if (toEvents) {

                displaySearchWindow(false, true);
            } else {

                userMenuGUI(username);

            }
        }
    }

    public void addEventFrame() {

        EventsDB events = new EventsDB();

        Connection conn = events.conn;
        windowName = "Add Event";

        JFrame addEventFrame = new JFrame(windowName);
        addEventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEventFrame.setBackground(Color.decode(BG_COLOUR));
        Dimension addEventFrameSize = new Dimension(350, 275);
        addEventFrame.setPreferredSize(addEventFrameSize);

        JButton addButton;
        JTextField eventNameField;
        JTextField eventCostField;
        JTextField numDaysActiveField;
        JTextField numSessionsPerDayField;
        JTextField sessionDurationField;
        JTextField eventCapacityField;
        JTextField eventImgPNameField;

        JPanel addEventMainPanel = new JPanel(new GridLayout(9, 2));
        addEventMainPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel eventNameLabel = new JLabel("Event Name: ");
        eventNameField = new JTextField();
        eventNameLabel.setForeground(Color.WHITE);
        addEventMainPanel.add(eventNameLabel);
        addEventMainPanel.add(eventNameField);

        JLabel eventCostLabel = new JLabel("Event Cost: ");
        eventCostField = new JTextField();
        eventCostLabel.setForeground(Color.WHITE);

        addEventMainPanel.add(eventCostLabel);
        addEventMainPanel.add(eventCostField);

        JLabel daysActiveLabel = new JLabel("Days Active: ");
        numDaysActiveField = new JTextField();
        daysActiveLabel.setForeground(Color.WHITE);

        addEventMainPanel.add(daysActiveLabel);
        addEventMainPanel.add(numDaysActiveField);

        JLabel dailySessionsLabel = new JLabel("Sessions per Day: ");
        numSessionsPerDayField = new JTextField();
        dailySessionsLabel.setForeground(Color.WHITE);

        addEventMainPanel.add(dailySessionsLabel);
        addEventMainPanel.add(numSessionsPerDayField);

        JLabel sessionDurationLabel = new JLabel("Session Duration: ");
        sessionDurationField = new JTextField();
        sessionDurationLabel.setForeground(Color.WHITE);

        addEventMainPanel.add(sessionDurationLabel);
        addEventMainPanel.add(sessionDurationField);

        JLabel eventCapacityLabel = new JLabel("Event Total Capacity: ");
        eventCapacityField = new JTextField();
        eventCapacityLabel.setForeground(Color.WHITE);

        addEventMainPanel.add(eventCapacityLabel);
        addEventMainPanel.add(eventCapacityField);

        JLabel eventImgPNameLabel = new JLabel("Event Image Name: ");
        eventImgPNameField = new JTextField();
        eventImgPNameLabel.setForeground(Color.WHITE);

        addEventMainPanel.add(eventImgPNameLabel);
        addEventMainPanel.add(eventImgPNameField);

        addButton = new JButton("Add Event");
        styleButton(addButton, initialColour, hoverColour, false);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                events.addEventToDB(eventNameField, eventCostField, eventCapacityField,
                        numDaysActiveField, numSessionsPerDayField, sessionDurationField,
                        eventImgPNameField, conn);
                addEventFrame.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                addEventFrame.dispose();
            }
        });

        JPanel addEventWrapperPanel = new JPanel();
        addEventWrapperPanel.setSize(400, 275);
        addEventWrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addEventWrapperPanel.add(addEventMainPanel);
        addEventWrapperPanel.setBackground(Color.decode(BG_COLOUR));

        addEventMainPanel.add(addButton);
        addEventMainPanel.add(cancelButton);

        addEventMainPanel.add(new JLabel()); // Empty label for spacing
        addEventMainPanel.add(new JLabel()); // Empty label for spacing

        addEventFrame.getContentPane().add(addEventWrapperPanel, BorderLayout.CENTER);
        addEventFrame.pack();
        addEventFrame.setLocationRelativeTo(null); // Center the window
        addEventFrame.setVisible(true);

    }

    public static void newUserFrame() {

        UsersDB users = new UsersDB();

        Connection conn = users.conn;
        windowName = "Create New User";

        JFrame newUserFrame = new JFrame(windowName);
        newUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newUserFrame.setBackground(Color.decode(BG_COLOUR));
        Dimension newUserFrameSize = new Dimension(500, 350);
        newUserFrame.setPreferredSize(newUserFrameSize);

        JButton addButton;
        JTextField firstNameField;
        JTextField lastNameField;
        JTextField emailField;
        JTextField phNumField;
        JTextField usernameField;
        JTextField passwordField;

        JPanel newUserMainPanel = new JPanel(new GridLayout(9, 2));
        newUserMainPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel firstNameLabel = new JLabel("First Name: ");
        firstNameField = new JTextField();
        firstNameLabel.setForeground(Color.WHITE);
        newUserMainPanel.add(firstNameLabel);
        newUserMainPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameField = new JTextField();
        lastNameLabel.setForeground(Color.WHITE);
        newUserMainPanel.add(lastNameLabel);
        newUserMainPanel.add(lastNameField);

        JLabel emailLabel = new JLabel("Email: ");
        emailField = new JTextField();
        emailLabel.setForeground(Color.WHITE);
        String newEmail = emailField.getText();

        newUserMainPanel.add(emailLabel);
        newUserMainPanel.add(emailField);

        JLabel phoneNumLabel = new JLabel("Phone Number: ");
        phNumField = new JTextField();
        phoneNumLabel.setForeground(Color.WHITE);
        newUserMainPanel.add(phoneNumLabel);
        newUserMainPanel.add(phNumField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JTextField();
        passwordLabel.setForeground(Color.WHITE);

        newUserMainPanel.add(passwordLabel);
        newUserMainPanel.add(passwordField);

        addButton = new JButton("Create User");
        styleButton(addButton, initialColour, hoverColour, false);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String newEmail = emailField.getText();
                String newPassword = passwordField.getText();

                if (!Tools.emailValidator(newEmail)) {

                    JOptionPane.showMessageDialog(newUserFrame, "Invalid Email. Must contain '@' symbol. Must contain '.com', '.co.nz' or '.ac.nz'.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    emailField.setText("");
                    emailField.requestFocus();
                    return;
                }

                if (!Tools.passwordValidator(newPassword)) {

                    JOptionPane.showMessageDialog(newUserFrame, "Invalid Password. Must contain a Number, a Symbol, and a Capital Letter.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    passwordField.requestFocus();
                    return;

                }

                users.addUserToDB(firstNameField, lastNameField, emailField, phNumField, passwordField, conn);

                User user = users.findFNameInDB(firstNameField.getText());
                String showUsername = user.getUsername();

                JOptionPane.showMessageDialog(null, "Your Username is: " + showUsername + ". Remember it!\nIf you forget please contact Admin", "Information", JOptionPane.INFORMATION_MESSAGE);

                newUserFrame.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                newUserFrame.dispose();
            }
        });

        JPanel newUserWrapperPanel = new JPanel();
        newUserWrapperPanel.setSize(400, 275);
        newUserWrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        newUserWrapperPanel.add(newUserMainPanel);
        newUserWrapperPanel.setBackground(Color.decode(BG_COLOUR));

        newUserMainPanel.add(addButton);
        newUserMainPanel.add(cancelButton);

        newUserMainPanel.add(new JLabel()); // Empty label for spacing
        newUserMainPanel.add(new JLabel()); // Empty label for spacing

        newUserFrame.getContentPane().add(newUserWrapperPanel, BorderLayout.CENTER);
        newUserFrame.pack();
        newUserFrame.setLocationRelativeTo(null); // Center the window
        newUserFrame.setVisible(true);

    }

    public static void bookingFrame(String eventName) {

        UsersDB users = new UsersDB();
        EventsDB events = new EventsDB();
        BookingsDB bookings = new BookingsDB();

        Connection conn = bookings.conn;
        windowName = "Book Event";

        JFrame bookEventFrame = new JFrame(windowName);
        bookEventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookEventFrame.setBackground(Color.decode(BG_COLOUR));
        Dimension bookEventFrameSize = new Dimension(500, 350);
        bookEventFrame.setPreferredSize(bookEventFrameSize);

        JButton addButton;
        JTextField eventNameField;
        JTextField usernameField;

        JPanel bookEventMainPanel = new JPanel(new GridLayout(9, 2));
        bookEventMainPanel.setBackground(Color.decode(BG_COLOUR));

        if (eventName != null || eventName != "") {
            JLabel eventNameLabel = new JLabel("Event Name: ");
            eventNameField = new JTextField(eventName);
            eventNameLabel.setForeground(Color.WHITE);
            bookEventMainPanel.add(eventNameLabel);
            bookEventMainPanel.add(eventNameField);
        } else {

            JLabel eventNameLabel = new JLabel("Please enter the Event Name: ");
            eventNameField = new JTextField();
            eventNameLabel.setForeground(Color.WHITE);
            bookEventMainPanel.add(eventNameLabel);
            bookEventMainPanel.add(eventNameField);
        }

        JLabel usernameLabel = new JLabel("Enter Your Username: ");
        usernameField = new JTextField();
        usernameLabel.setForeground(Color.WHITE);
        bookEventMainPanel.add(usernameLabel);
        bookEventMainPanel.add(usernameField);

        BookingSystem getBooked = new BookingSystem();

        addButton = new JButton("Book!");
        styleButton(addButton, initialColour, hoverColour, false);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String eventToBookName = eventNameField.getText().trim();
                String username = usernameField.getText().trim();

                if (eventToBookName.isEmpty() || username.isEmpty()) {

                    JOptionPane.showMessageDialog(bookEventFrame, "Event name and username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<User> filteredUsers = UserManagement.search(username);
                List<Event> filteredEvents = EventManagement.search(eventToBookName);

                if (filteredUsers.isEmpty()) {

                    JOptionPane.showMessageDialog(bookEventFrame, "User not found with username: " + username, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean eventFound = false;
                boolean userFound = false;

                for (Event event : filteredEvents) {

                    if (event.getEventName().toLowerCase().equalsIgnoreCase(eventToBookName.toLowerCase())) {

                        eventFound = true;
                        eventToBookName = event.getEventName();
                        for (User user : filteredUsers) {

                            if (user.getUsername().equals(username)) {

                                userFound = true;
                                username = user.getUsername();
                                double eventPrice = event.getEventCost();
                                String bookingRef = BookingSystem.generateReference(event.getEventName(), user.getfName(), user.getlName());
                                //System.out.println(bookingRef); debugging
                                int preBookingCapacity = event.getEventCapacity();
                                int userBookings = user.getEventsBooked();
                                if (preBookingCapacity > 0) {

                                    bookings.addbookingToDB(bookingRef, username, eventToBookName, eventPrice, conn);
                                    event.setTotalCapacity(preBookingCapacity - 1);
                                    events.updateEventCapacity(preBookingCapacity - 1, event.getEventName());
                                    users.updateUserMoneySpent(eventPrice, username);
                                    users.updateUserBookings(username, userBookings);
                                    System.out.println("Event Total Cap: " + event.getTotalCapacity());
                                    JOptionPane.showMessageDialog(bookEventFrame, "Event Booked!\n\nYou can see your booked events in your account page", "Information", JOptionPane.INFORMATION_MESSAGE);

                                } else {

                                    JOptionPane.showMessageDialog(bookEventFrame, "Event has no more available spaces!", "Error", JOptionPane.ERROR_MESSAGE);

                                }
                                //generate reference from genreference function in bookingsystem
                                //push new booking to bookingsDB
                                bookEventFrame.dispose();
                                return;
                            }

                        }

                        if (!userFound) {

                            JOptionPane.showMessageDialog(bookEventFrame, "User not found with username: " + username, "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }

                }

                if (!eventFound) {

                    JOptionPane.showMessageDialog(bookEventFrame, "Event not found with name: " + eventToBookName, "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bookEventFrame.dispose();
            }
        });

        JPanel bookEventWrapperPanel = new JPanel();
        bookEventWrapperPanel.setSize(400, 275);
        bookEventWrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookEventWrapperPanel.add(bookEventMainPanel);
        bookEventWrapperPanel.setBackground(Color.decode(BG_COLOUR));

        bookEventMainPanel.add(addButton);
        bookEventMainPanel.add(cancelButton);

        bookEventMainPanel.add(new JLabel()); // Empty label for spacing
        bookEventMainPanel.add(new JLabel()); // Empty label for spacing

        bookEventFrame.getContentPane().add(bookEventWrapperPanel, BorderLayout.CENTER);
        bookEventFrame.pack();
        bookEventFrame.setLocationRelativeTo(null); // Center the window
        bookEventFrame.setVisible(true);

    }

    public static void deleteFrame() {

        UsersDB users = new UsersDB();

        Connection conn = users.conn;
        windowName = "Delete User";

        JFrame delUserFrame = new JFrame(windowName);
        delUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        delUserFrame.setBackground(Color.decode(BG_COLOUR));
        Dimension delUserFrameSize = new Dimension(500, 350);
        delUserFrame.setPreferredSize(delUserFrameSize);

        JButton deleteButton;

        JTextField usernameField;

        JPanel delUserMainPanel = new JPanel(new GridLayout(9, 2));
        delUserMainPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel usernameLabel = new JLabel("Enter Username of user to Delete: ");
        usernameField = new JTextField();
        usernameLabel.setForeground(Color.WHITE);
        delUserMainPanel.add(usernameLabel);
        delUserMainPanel.add(usernameField);

        deleteButton = new JButton("Delete User");
        styleButton(deleteButton, initialColour, hoverColour, false);
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText().trim();

                if (username.isEmpty()) {

                    JOptionPane.showMessageDialog(delUserFrame, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<User> filteredUsers = UserManagement.search(username);

                if (filteredUsers.isEmpty()) {

                    JOptionPane.showMessageDialog(delUserFrame, "User not found with username: " + username, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    int option = JOptionPane.showConfirmDialog(delUserFrame, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {

                        users.deleteUser(username);
                        JOptionPane.showMessageDialog(delUserFrame, "User Deleted Successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        delUserFrame.dispose();
                        return;

                    } else {
                        delUserFrame.dispose();
                        return;
                    }

                }

            }
            
        });

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                delUserFrame.dispose();
            }
        });

        JPanel delUserWrapperPanel = new JPanel();
        delUserWrapperPanel.setSize(400, 275);
        delUserWrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        delUserWrapperPanel.add(delUserMainPanel);
        delUserWrapperPanel.setBackground(Color.decode(BG_COLOUR));

        delUserMainPanel.add(deleteButton);
        delUserMainPanel.add(cancelButton);

        delUserMainPanel.add(new JLabel()); // Empty label for spacing
        delUserMainPanel.add(new JLabel()); // Empty label for spacing

        delUserFrame.getContentPane().add(delUserWrapperPanel, BorderLayout.CENTER);
        delUserFrame.pack();
        delUserFrame.setLocationRelativeTo(null); // Center the window
        delUserFrame.setVisible(true);

    }
        
    public static void quickBookFrame() {

        UsersDB users = new UsersDB();
        EventsDB events = new EventsDB();
        BookingsDB bookings = new BookingsDB();

        Connection conn = users.conn;
        windowName = "Book Event";

        JFrame bookEventFrame = new JFrame(windowName);
        bookEventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookEventFrame.setBackground(Color.decode(BG_COLOUR));
        Dimension bookEventFrameSize = new Dimension(500, 350);
        bookEventFrame.setPreferredSize(bookEventFrameSize);

        JButton addButton;
        JTextField usernameField;

        JPanel bookEventMainPanel = new JPanel(new GridLayout(9, 2));
        bookEventMainPanel.setBackground(Color.decode(BG_COLOUR));

        JLabel usernameLabel = new JLabel("Enter Your Username: ");
        usernameField = new JTextField();
        usernameLabel.setForeground(Color.WHITE);
        bookEventMainPanel.add(usernameLabel);
        bookEventMainPanel.add(usernameField);

        List<User> filteredUsers = UserManagement.search(usernameField.getText());
        BookingSystem getBooked = new BookingSystem();

        addButton = new JButton("Book!");
        styleButton(addButton, initialColour, hoverColour, false);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                double eventPrice = 0;

                for (User user : filteredUsers) {

                    if (user.getUsername().equals(usernameField.getText())) {

                        //eventPrice = event.getEventCost();
                        //String bookingRef = BookingSystem.generateReference(event.getEventName(), user.getfName(), user.getlName());
                        //System.out.println(bookingRef);
                        //generate reference from genreference function in bookingsystem
                        //push new booking to bookingsDB
                        JOptionPane.showMessageDialog(bookEventFrame, "Event Booked!\n\nYou can see your booked events in your account page", "Information", JOptionPane.INFORMATION_MESSAGE);
                        bookEventFrame.dispose();
                        break;
                    }

                }

                JOptionPane.showMessageDialog(bookEventFrame, "Could not find event with name: ", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        bookEventFrame.dispose();

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, initialColour, hoverColour, false);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bookEventFrame.dispose();
            }
        });

        JPanel bookEventWrapperPanel = new JPanel();
        bookEventWrapperPanel.setSize(400, 275);
        bookEventWrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookEventWrapperPanel.add(bookEventMainPanel);
        bookEventWrapperPanel.setBackground(Color.decode(BG_COLOUR));

        bookEventMainPanel.add(addButton);
        bookEventMainPanel.add(cancelButton);

        bookEventMainPanel.add(new JLabel()); // Empty label for spacing
        bookEventMainPanel.add(new JLabel()); // Empty label for spacing

        bookEventFrame.getContentPane().add(bookEventWrapperPanel, BorderLayout.CENTER);
        bookEventFrame.pack();
        bookEventFrame.setLocationRelativeTo(null); // Center the window
        bookEventFrame.setVisible(true);

    }
}
