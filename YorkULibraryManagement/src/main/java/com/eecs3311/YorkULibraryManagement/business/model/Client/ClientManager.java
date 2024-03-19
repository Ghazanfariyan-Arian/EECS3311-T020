package com.eecs3311.YorkULibraryManagement.business.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private List<Client> clients;

    public ClientManager() {
        clients = new ArrayList<>();
    }

    public void register(String email, String password, String type) {

        Client client = new Client(email, password, type);
        clients.add(client);
    }

    public boolean validate(Client client) {

        return true;
    }

    public Client login(String email, String password) {
        for (Client client : clients) {
            if (client.getEmail().equals(email) && client.getPassword().equals(password)) {
                return client;
            }
        }
        return null; 
    }

    public void logout(Client client) {

    }
}

