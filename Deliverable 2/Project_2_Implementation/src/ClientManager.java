import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private List<String> subscribedNewsletters;
	private CSVWriter CSVWriter;
    private CSVReader CSVReader;

	private static final String CSV_FILE_PATH = "user_database.csv";

    public ClientManager() throws IOException {
        this.clients = new ArrayList<>();
        this.subscribedNewsletters = new ArrayList<>();
        this.CSVWriter = new CSVWriter(CSV_FILE_PATH);
        this.CSVReader = new CSVReader (CSV_FILE_PATH);;


    }
    
    public List<String> getAvailableNewsletters() {
        List<String> newsletters = new ArrayList<>();
        newsletters.add("NY Times");
        newsletters.add("National Geographic");
        newsletters.add("Tech Crunch");
        return newsletters;
    }
    
    // Method to subscribe a client to a newsletter
    public void subscribeNewsletter(Client client, String newsletter) {
        if (!subscribedNewsletters.contains(newsletter)) {
            subscribedNewsletters.add(newsletter);
            System.out.println(client.getEmail() + " subscribed to newsletter: " + newsletter);
        }
    }

    // Method to unsubscribe a client from a newsletter
    public void cancelSubscription(Client client, String newsletter) {
        if (subscribedNewsletters.contains(newsletter)) {
            subscribedNewsletters.remove(newsletter);
            System.out.println(client.getEmail() + " unsubscribed from newsletter: " + newsletter);
        }
    }
    
    // Method to register a new user
    public boolean registerUser(String email, String password, String type) {
    	 // Check if the email already exists in the database
        if (isEmailRegistered(email)) {
            System.out.println("Error: Email already registered.");
            return false;
        }
        Client newClient = new Client(0, email, password, type);
        clients.add(newClient);
        
     // Write the new user data to the CSV database
        String[] userData = {email, password, type};
        List<String[]> userDataList = new ArrayList<>();
        userDataList.add(userData);
        try {
            CSVWriter.writeData(userDataList);
            System.out.println("User registered successfully.");
        } catch (IOException e) {
            System.err.println("Error writing user data to CSV: " + e.getMessage());
        }
        return true;
    }
    
 // Method to check if an email is already registered
    private boolean isEmailRegistered(String email) {
        for (Client client : clients) {
            if (client.getEmail().equals(email)) {
                return true; 
            }
        }
        return false;
    }
    // Method to log in a user
    public boolean loginUser(String email, String password) {
        try {
            List<String[]> userDataList = CSVReader.readData();
            for (String[] userData : userDataList) {
                if (userData[0].equals(email) && userData[1].equals(password)) {
                    System.out.println("User logged in: " + email);
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user data from CSV: " + e.getMessage());
        }
        // User not found or password incorrect
        System.out.println("Invalid email or password");
        return false;
    }

    // Method to validate a client
    public boolean validate(Client client) {
       
        return true;
    }

    // Method to log out a user
    public void logoutUser(Client client) {
        // Perform logout logic here
        System.out.println("User logged out: " + client.getEmail());
    }
    

   
}
