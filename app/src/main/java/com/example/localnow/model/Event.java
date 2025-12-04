package com.example.localnow.model;

public class Event {
    private String title;
    private String date;
    private String type; // "Music", "Marathon", "Food"
    private boolean isBookmarked;
    private String startDate; // Format: "2025-05-03"
    private String endDate; // Format: "2025-05-07"

    private int id;
    private double lat;
    private double lng;
    private String image;
    private String location;

    public Event() {
    } // No-args constructor for Gson

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
