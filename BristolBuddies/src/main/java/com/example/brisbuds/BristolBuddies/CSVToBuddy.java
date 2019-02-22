package com.example.brisbuds.BristolBuddies;

import com.example.brisbuds.BristolBuddies.ao.BuddyDbDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVToBuddy {

    private static final int NUMBEROFARGS = 13;

    public static void main(String[] args) {

        String csvFile = "Questionnaire.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> buddies = new ArrayList<>();
        BuddyDbDAO dao = new BuddyDbDAO();
        int i=0;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                i++;
                if(i>=1) {
                    // use comma as separator
                    String[] answers = line.split(cvsSplitBy);
                    buddies.add(answers);
                }
            }
            
            List<Buddy> b = stringListToBuddyList(buddies);
            for (Buddy bud : b) {
                dao.add(bud);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    static private List<Buddy> stringListToBuddyList(List<String[]> buddies){
        List<Buddy> buddyList = new ArrayList<>();
        for(int i = 1 ; i < buddies.size() ; i++){
            String firstName = buddies.get(i)[2];
            String lastName = buddies.get(i)[3];
            String username = buddies.get(i)[4];
            String course = buddies.get(i)[5];
            String nationality = buddies.get(i)[6];

            String interests = getInterestString(buddies,i);
            String personality = getPersonalityString(buddies, i);
            String preferences = getPreferencesString(buddies, i);
            String password = "";

            buddyList.add(new Buddy(firstName,lastName,username,
                    course,nationality,interests,personality,
                    preferences,password));
        }

    return buddyList;
    }

    static private String getInterestString(List<String[]> buddies, int i) {
        String raw = buddies.get(i)[7];
        if(raw.contains("None")) {
            return "0000000";
        }
        StringBuilder interests = new StringBuilder();
        if(raw.contains("museums")) interests.append('1');
        else interests.append('0');
        if(raw.contains("Television")) interests.append('1');
        else interests.append('0');
        if(raw.contains("Video Games")) interests.append('1');
        else interests.append('0');
        if(raw.contains("Gigs")) interests.append('1');
        else interests.append('0');
        if(raw.contains("Club")) interests.append('1');
        else interests.append('0');
        if(raw.contains("Creative")) interests.append('1');
        else interests.append('0');
        if(raw.contains("Sport")) interests.append('1');
        else interests.append('0');

        return interests.toString();
    }


    static private String getPersonalityString(List<String[]> buddies, int i) {
        String raw = buddies.get(i)[8];
        if(raw.contains("None")) {
            return "0000";
        }
        StringBuilder interests = new StringBuilder();
        if(raw.contains("outgoing")) interests.append('1');
        else interests.append('0');
        if(raw.contains("nervous")) interests.append('1');
        else interests.append('0');
        if(raw.contains("curious")) interests.append('1');
        else interests.append('0');
        if(raw.contains("original")) interests.append('1');
        else interests.append('0');

        return interests.toString();
    }

    static private String getPreferencesString(List<String[]> buddies, int i) {
        StringBuilder preferences = new StringBuilder();
        for(int j = 9 ; j < NUMBEROFARGS ; j++) {
            String raw = buddies.get(i)[j];
            if (raw.contains("Least")) { preferences.append('0'); }
            else if (raw.contains("Not really")) { preferences.append('1'); }
            else if (raw.contains("Fairly")) { preferences.append('2'); }
            else preferences.append('3');
        }
       return preferences.toString();
    }


}
