package com.example.brisbuds.BristolBuddies.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.brisbuds.BristolBuddies.Buddy;
import com.example.brisbuds.BristolBuddies.ao.BuddyDAO;
import com.example.brisbuds.BristolBuddies.ao.BuddyDbDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/buddies")
public class BuddyController {

    BuddyDAO edao = new BuddyDbDAO();


    @RequestMapping(method = RequestMethod.GET)
    public Buddy[] getAll() {
        return edao.getAllBuddies().toArray(new Buddy[0]);
    }

    // Add a buddy
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody Buddy student) {

        if (edao.add(student)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "{username}")
    public ResponseEntity getByUsername(@PathVariable String username){

        List<Buddy> buddyList = edao.getByUsername(username);

        if (buddyList.size() > 0) {
            return new ResponseEntity<>(buddyList.toArray(new Buddy[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // Delete a student
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {

        boolean result = edao.delete(id);

        if (result) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}