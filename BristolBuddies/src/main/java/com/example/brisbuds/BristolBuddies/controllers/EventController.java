package com.example.brisbuds.BristolBuddies.controllers;

import com.example.brisbuds.BristolBuddies.Event;
import com.example.brisbuds.BristolBuddies.ao.EventDAO;
import com.example.brisbuds.BristolBuddies.ao.EventDbDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class EventController {

    EventDAO eventDAO = new EventDbDAO();

    //Get all the events
    @RequestMapping(method = RequestMethod.GET)
    public Event[] getAll() {
        return eventDAO.getAllEvents().toArray(new Event[0]);
    }

    //Get an event with ID
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {
        Event match = null;
        match = eventDAO.getEventById(id);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //Add an event
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Event event) {
        if (eventDAO.add(event)) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update a current event
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Event event) {
        if (eventDAO.update(id, event)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //Delete an existing event
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {
        boolean result = eventDAO.delete(id);

        if (result) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

