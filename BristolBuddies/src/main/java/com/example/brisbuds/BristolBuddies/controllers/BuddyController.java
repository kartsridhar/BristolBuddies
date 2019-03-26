package com.example.brisbuds.BristolBuddies.controllers;

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

    // Update a buddy
    @RequestMapping(method = RequestMethod.POST, value = "{username}")
    public ResponseEntity update(@PathVariable String username, @RequestBody Buddy student) {

        if (edao.update(username, student)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}