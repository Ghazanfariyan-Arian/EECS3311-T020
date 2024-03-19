package com.eecs3311.YorkULibraryManagement.business.model.Item;

public class Magazine extends Item {
    private String issueDate;

    public Magazine(int itemId, String title, String location, String issueDate) {
        super(itemId, title, location);
        this.issueDate = issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
