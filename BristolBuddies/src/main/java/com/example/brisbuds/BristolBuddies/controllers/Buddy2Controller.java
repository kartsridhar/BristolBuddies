package com.example.brisbuds.BristolBuddies.controllers;

import com.example.brisbuds.BristolBuddies.Buddy;
import com.example.brisbuds.BristolBuddies.ao.Buddy2DAO;
import com.example.brisbuds.BristolBuddies.ao.Buddy2DbDAO;
import com.example.brisbuds.BristolBuddies.ao.BuddyDAO;
import com.example.brisbuds.BristolBuddies.ao.BuddyDbDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/buddies2")
public class Buddy2Controller {

    Buddy2DAO edao = new Buddy2DbDAO();

    // Update a buddy
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody Buddy student) {
        if (edao.update(student)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}