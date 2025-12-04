package com.example.localnow.model;

public class Event {
    private String title;
    private String date;
    private String type; // "Music", "Marathon", "Food"
    private boolean isBookmarked;
    private String startDate; // Format: "2025-05-03"
    private String endDate;   // Format: "2025-05-07"

    public Event(String title, String date, String type, boolean isBookmarked) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.isBookmarked = isBookmarked;
        this.startDate = "";
        this.endDate = "";
    }

    public Event(String title, String date, String type, boolean isBookmarked, String startDate, String endDate) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.isBookmarked = isBookmarked;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public boolean isBookmarked() { return isBookmarked; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
}
