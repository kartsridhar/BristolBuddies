package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Match;
import com.example.brisbuds.BristolBuddies.Student;

import java.util.List;

public interface MatchDAO {
     List<Match> getAllMatches();
     Match getMatch(long id);
     Match getByBuddyID(String id);
     Match getByStudentID(String id);
     boolean add(Match match);
     boolean update(long id, Match match);
}
