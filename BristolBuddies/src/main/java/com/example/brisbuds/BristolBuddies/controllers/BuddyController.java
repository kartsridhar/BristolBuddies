package com.example.brisbuds.BristolBuddies.controllers;

import com.example.brisbuds.BristolBuddies.Buddy;
import com.example.brisbuds.BristolBuddies.ao.BuddyDAO;
import com.example.brisbuds.BristolBuddies.ao.BuddyDbDAO;

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


}