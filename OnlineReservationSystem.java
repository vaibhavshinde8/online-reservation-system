import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OnlineReservationSystem extends JFrame {
    private JTextField usernameField, trainNumberField, fromField, toField, pnrField, dateField;
    private JPasswordField passwordField;
    private JComboBox<String> classTypeComboBox;
    private JTextArea displayArea;
    private List<Reservation> reservations;

    public OnlineReservationSystem() {
       
        reservations = new ArrayList<>();

        
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        
        JLabel trainNumberLabel = new JLabel("Train Number:");
        JLabel classTypeLabel = new JLabel("Class Type:");
        JLabel dateLabel = new JLabel("Date of Journey:");
        trainNumberField = new JTextField();
        classTypeComboBox = new JComboBox<>(new String[]{"First Class", "Second Class"});
        dateField = new JTextField();
        JButton insertButton = new JButton("Insert");

       
        JLabel pnrLabel = new JLabel("PNR Number:");
        pnrField = new JTextField();
        JButton cancelButton = new JButton("Cancel");

        
        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);

       
        setLayout(null);

       
        usernameLabel.setBounds(10, 10, 80, 25);
        passwordLabel.setBounds(10, 40, 80, 25);
        usernameField.setBounds(100, 10, 160, 25);
        passwordField.setBounds(100, 40, 160, 25);
        loginButton.setBounds(10, 70, 80, 25);

        
        trainNumberLabel.setBounds(10, 110, 120, 25);
        classTypeLabel.setBounds(10, 140, 120, 25);
        dateLabel.setBounds(10, 170, 120, 25);
        trainNumberField.setBounds(140, 110, 120, 25);
        classTypeComboBox.setBounds(140, 140, 120, 25);
        dateField.setBounds(140, 170, 120, 25);
        insertButton.setBounds(10, 200, 80, 25);

        
        pnrLabel.setBounds(10, 240, 120, 25);
        pnrField.setBounds(140, 240, 120, 25);
        cancelButton.setBounds(10, 270, 80, 25);

        
        scrollPane.setBounds(280, 10, 300, 300);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReservation();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancellation();
            }
        });

        
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(loginButton);

        add(trainNumberLabel);
        add(classTypeLabel);
        add(dateLabel);
        add(trainNumberField);
        add(classTypeComboBox);
        add(dateField);
        add(insertButton);

        add(pnrLabel);
        add(pnrField);
        add(cancelButton);

        add(scrollPane);

        
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleLogin() {
        
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if ("admin".equals(username) && "admin123".equals(password)) {
            displayArea.setText("Login successful!");
        } else {
            displayArea.setText("Invalid username or password.");
        }
    }

    private void handleReservation() {
        
        String trainNumber = trainNumberField.getText();
        String classType = (String) classTypeComboBox.getSelectedItem();
        String date = dateField.getText();

        Reservation reservation = new Reservation(trainNumber, classType, date);
        reservations.add(reservation);

        displayArea.setText("Reservation successful!\n" + reservation);
    }

    private void handleCancellation() {
        
        String pnr = pnrField.getText();

        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                reservations.remove(reservation);
                displayArea.setText("Cancellation successful!\n" + reservation);
                return;
            }
        }

        displayArea.setText("No reservation found with PNR: " + pnr);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnlineReservationSystem();
            }
        });
    }

   
    private class Reservation {
        private static final int PNR_LENGTH = 6;

        private String pnr;
        private String trainNumber;
        private String classType;
        private String date;

        public Reservation(String trainNumber, String classType, String date) {
            this.trainNumber = trainNumber;
            this.classType = classType;
            this.date = date;
            generatePNR();
        }

        private void generatePNR() {
           
            long currentTime = System.currentTimeMillis();
            this.pnr = String.valueOf(currentTime).substring(0, PNR_LENGTH);
        }

        public String getPnr() {
            return pnr;
        }

        @Override
        public String toString() {
            return "PNR: " + pnr + "\nTrain Number: " + trainNumber + "\nClass Type: " + classType + "\nDate: " + date;
        }
    }
}
