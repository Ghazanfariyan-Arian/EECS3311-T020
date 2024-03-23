package com.eecs3311.YorkULibraryManagement.business.model.Item;

public class Item {
    private int itemId;
    private String title;
    private String location;
    private boolean available;

    public Item(int itemId, String title, String location) {
        this.itemId = itemId;
        this.title = title;
        this.location = location;
        this.available = true;
    }

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}