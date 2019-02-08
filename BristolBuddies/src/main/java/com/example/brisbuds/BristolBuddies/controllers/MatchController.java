package com.example.brisbuds.BristolBuddies.controllers;

import java.util.List;

import com.example.brisbuds.BristolBuddies.Match;
import com.example.brisbuds.BristolBuddies.Student;
import com.example.brisbuds.BristolBuddies.ao.MatchDAO;
import com.example.brisbuds.BristolBuddies.ao.MatchDbDAO;
import com.example.brisbuds.BristolBuddies.ao.StudentDAO;
import com.example.brisbuds.BristolBuddies.ao.StudentDbDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin
@RestController
@RequestMapping("/matches")
public class MatchController {

    MatchDAO edao = new MatchDbDAO();

    // Get all matches
    @RequestMapping(method = RequestMethod.GET)
    public Match[] getAll() {
        return edao.getAllMatches().toArray(new Match[0]);
    } /// ???????

    // Get a match
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {

        Match match = null;
        match = edao.getMatch(id);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get match by buddyID
    @RequestMapping(method = RequestMethod.GET, value = "/buddyID/{name}")
    public ResponseEntity getByFirstName(@PathVariable String name) {

        Match match = null;
        match = edao.getByBuddyID(name);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    //Get match by Student ID
    @RequestMapping(method = RequestMethod.GET, value = "/studentID/{name}")
    public ResponseEntity getByLastName(@PathVariable String name) {

        Match match = null;
        match = edao.getByStudentID(name);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // Update an employee
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Match match) {

        if (edao.update(id, match)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



}