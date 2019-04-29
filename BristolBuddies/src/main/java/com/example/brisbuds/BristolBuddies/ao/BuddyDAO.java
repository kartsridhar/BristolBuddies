package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Buddy;

import java.util.List;

public interface BuddyDAO {
     List<Buddy> getAllBuddies();
     boolean add(Buddy buddy);
     boolean update(long id, Buddy buddy);
     List<Buddy> getByUsername(String id);
     boolean delete(long id);
}
