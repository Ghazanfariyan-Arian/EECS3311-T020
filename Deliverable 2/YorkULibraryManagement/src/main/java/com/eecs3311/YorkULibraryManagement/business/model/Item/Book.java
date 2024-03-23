package com.eecs3311.YorkULibraryManagement.business.model.Item;

public class Book extends Item {
    private String author;
    private int pageCount;

    public Book(int itemId, String title, String location, String author, int pageCount) {
        super(itemId, title, location);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
