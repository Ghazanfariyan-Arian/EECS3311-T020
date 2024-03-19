package com.eecs3311.YorkULibraryManagement.business.model.Client;

public class Client {
    private int id;
    private String email;
    private String password;
    private String type;

    public Client(int id, String email, String password, String type) {
        setId(id);
        setEmail(email);
        setPassword(password);
        setType(type);
    }
    
    public Client(String email, String password, String type) {
        setEmail(email);
        setPassword(password);
        setType(type);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public void register(String email, String password, String type) {

    }

    public boolean validate() {

        return true;
    }

    public void login(String email, String password) {

    }

    public void logout() {
    }
}
