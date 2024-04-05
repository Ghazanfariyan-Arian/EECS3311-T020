import java.util.ArrayList;
import java.util.List;

public class Client {
    // Attributes
    private int id;
    private String email;
    private String password;
    private String type;
    private List<Item> borrowedItems;
    private List<Item> overdueItems;
    private List<String> courses;

    // Constructors
    public Client(int id, String email, String password, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
        this.borrowedItems = new ArrayList<>();
        this.overdueItems = new ArrayList<>();
    }
    public Client() {
    	 // Default constructor
        this.id = 0;
        this.email = "default@example.com";
        this.password = "password";
        this.type = "Student";
        this.courses = new ArrayList<>(); 

    }
   

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
    	
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Additional methods
    public void register(String email, String password, String type) {
    }

    public boolean validate() {
        // Logic for validating client information
        return true;
    }

    public void login(String email, String password) {
        // Logic for client login
    }

    public void logout() {
        // Logic for client logout
    }
    public List<String> getCourses() {
        return courses;
    }

}

