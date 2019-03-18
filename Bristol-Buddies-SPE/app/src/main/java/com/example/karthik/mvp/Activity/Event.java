package com.example.karthik.mvp.Activity;

public class Event {
    private final long id;
    private final String date;
    private final String title;
    private final String description;
    private final String venue;
    private final String time;

    public Event() {
        super();
        id = 0;
        date = "";
        title = "";
        description = "";
        venue = "";
        time = "";
    }

    public Event(long id, String date, String title, String description, String venue, String time) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ID" + id
                + " Date: " + date
                + " Title: " + title
                + " Description: " + description
                + " Venue: " + venue
                + " Time: " + time;
    }
}

