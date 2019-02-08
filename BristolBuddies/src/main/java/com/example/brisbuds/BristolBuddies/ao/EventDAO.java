package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Event;

import java.util.List;

public interface EventDAO {
    List<Event> getAllEvents();
    Event getEventById(long id);
    boolean add(Event event);
    boolean update(long id, Event event);
    boolean delete(long id);
}