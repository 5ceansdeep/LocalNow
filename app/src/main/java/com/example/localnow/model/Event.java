package com.example.localnow.model;

public class Event {
    private String title;
    private String date;
    private String type; // "Music", "Marathon", "Food"
    private boolean isBookmarked;

    public Event(String title, String date, String type, boolean isBookmarked) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.isBookmarked = isBookmarked;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public boolean isBookmarked() { return isBookmarked; }
}
