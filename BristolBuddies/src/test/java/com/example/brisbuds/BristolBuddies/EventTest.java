package com.example.brisbuds.BristolBuddies;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
    private Event e = new Event(100, "2019-04-17", "Due Date", "The D Day", "MVB", "1pm");

    @Test
    public void getId() {
        assertEquals(100, e.getId());
    }

    @Test
    public void getDate() {
        assertEquals("2019-04-17", e.getDate());
    }

    @Test
    public void getTitle() {
        assertEquals("Due Date", e.getTitle());
    }

    @Test
    public void getDescription() {
        assertEquals("The D Day", e.getDescription());
    }

    @Test
    public void getVenue() {
        assertEquals("MVB", e.getVenue());
    }

    @Test
    public void getTime() {
        assertEquals("1pm", e.getTime());
    }
}