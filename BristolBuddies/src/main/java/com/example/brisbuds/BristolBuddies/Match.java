package com.example.brisbuds.BristolBuddies;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private final long id;
    private final int numberOfMatches;
    private final String buddyId;
    private final String student1ID;
    private final String student2ID;
    private final String student3ID;


    public Match(long id, int numberOfMatches,String buddyId, String student1ID, String student2ID, String student3ID){
        this.id = id;
        this.numberOfMatches = numberOfMatches;
        this.buddyId = buddyId;
        this.student1ID = student1ID;
        this.student2ID = student2ID;
        this.student3ID = student3ID;
    }

    public long getId() {return id;}

    public int getNumberOfMatches(){ return numberOfMatches;}
    public String getBuddyId() { return buddyId;}
    public String getStudent1ID() { return student1ID;}
    public String getStudent2ID() { return student2ID;}
    public String getStudent3ID() { return student3ID;}


    private List<String> getMatches(){
        List<String> matches = new ArrayList<>();
        if(numberOfMatches >= 1) matches.add(student1ID);
        if(numberOfMatches >= 2) matches.add(student2ID);
        if(numberOfMatches >= 3) matches.add(student3ID);
        return matches;
    }

}


