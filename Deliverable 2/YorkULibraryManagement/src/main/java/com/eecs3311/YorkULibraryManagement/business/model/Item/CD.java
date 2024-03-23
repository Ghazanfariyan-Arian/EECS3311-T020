package com.eecs3311.YorkULibraryManagement.business.model.Item;

public class CD extends Item {
    private String artist;
    private int numberOfTracks;

    public CD(int itemId, String title, String location, String artist, int numberOfTracks) {
        super(itemId, title, location);
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }
}
