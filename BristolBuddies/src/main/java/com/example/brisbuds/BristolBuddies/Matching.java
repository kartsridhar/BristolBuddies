package com.example.brisbuds.BristolBuddies;

import com.example.brisbuds.BristolBuddies.ao.BuddyDbDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Matching {

    private static int hamming(String s1, String s2){
        if(s1.length() != s2.length()) return -1;

        int hamm = 0;
        for(int i = 0 ; i < s1.length() ; i++){
            if(s1.charAt(i) != s2.charAt(i)) hamm+=1 ;
        }
        return hamm;
    }

    private static double similarity(Buddy b, Buddy s){
        int interestPref = Character.getNumericValue(s.getPreferences().charAt(0));
        int personalityPref = Character.getNumericValue(s.getPreferences().charAt(1));
        int facultyPref = Character.getNumericValue(s.getPreferences().charAt(2));
        int nationalityPref = Character.getNumericValue(s.getPreferences().charAt(3));
        double interest = (7 - hamming(b.getInterests() , s.getInterests())) / 7.0;
        double personality = (4 - hamming(b.getPersonality(), s.getPersonality())) / 4.0 ;
        double faculty = b.getCourse().equals(s.getCourse()) ? 1 : 0;
        double nationality = b.getNationality().equals(s.getNationality()) ? 1 : 0;

        double similarity = interest * interestPref + personality * personalityPref +
                            faculty * facultyPref + nationality * nationalityPref ;

        return similarity;
    }


    private static Buddy bestMatch(List<Buddy> buddies, Buddy student){
        double bestSim = -1;
        Buddy bestBuddy = null;
        for (Buddy b : buddies) {
            double s =  similarity(b,student);
            if( s > bestSim) {
                bestSim = s;
                bestBuddy = b;
            }
        }
        return bestBuddy;
    }


    public static void main(String[] args) {

        BuddyDbDAO dao = new BuddyDbDAO();
        List<Buddy> buddies = dao.getAllBuddies();
        Buddy intro = new Buddy("a", "b", "ab", "Arts", "Scd", "1001010", "0110", "1300", "");
        Buddy m = bestMatch(buddies, intro);
        System.out.println(m.getFirstName());
    }
}
