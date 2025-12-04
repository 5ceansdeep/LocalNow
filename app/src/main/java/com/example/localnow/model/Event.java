package com.example.localnow.model;

public class Event {
    private String id;
    private String title;
    private String date;
    private String type;
    private boolean isBookmarked;
    private String startDate;
    private String endDate;

    public Event(String id, String title, String date, String type, boolean isBookmarked) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.type = type;
        this.isBookmarked = isBookmarked;
        this.startDate = "";
        this.endDate = "";
    }

    public Event(String id, String title, String date, String type, boolean isBookmarked, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.type = type;
        this.isBookmarked = isBookmarked;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public boolean isBookmarked() { return isBookmarked; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}