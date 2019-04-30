package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karthik.mvp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showMatch extends AppCompatActivity {

    private RetroAPI retroAPI;
    TextView personality, interests, personality1, interests1, personality2, interests2, bud1, bud2, bud3;
    Button close;

    List<Buddy> buddies;
    List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_match);


        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);

        final Student student = (Student)getIntent().getSerializableExtra("User");
        final Buddy buddy = (Buddy)getIntent().getSerializableExtra("Buddy");

        personality = (TextView) findViewById(R.id.INT);
        interests = (TextView) findViewById(R.id.PERS);
        personality1 = (TextView) findViewById(R.id.INT1);
        interests1 = (TextView) findViewById(R.id.PERS1);
        personality2 = (TextView) findViewById(R.id.INT2);
        interests2 = (TextView) findViewById(R.id.PERS2);
        bud1 = findViewById(R.id.MATCH1);
        bud2 = findViewById(R.id.MATCH2);
        bud3 = findViewById(R.id.MATCH3);
        close = findViewById(R.id.closeDetails);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyProfile.class);
                startActivity(i);
                finish();
            }
        });

        if (student != null) {
            Call<List<Buddy>> call = retroAPI.getBuddies();
            call.enqueue(new Callback<List<Buddy>>() {
                @Override
                public void onResponse(Call<List<Buddy>> call, Response<List<Buddy>> response) {
                    buddies = response.body();
                    Buddy foundbuddy = new Buddy("","","","","","","","","",0, "","", "");
                    for (Buddy b: buddies){
                        if (student.getBuddy().trim().equals(b.getUsername())){
                            foundbuddy = b;
                        }
                    }

                    String buddyint = foundbuddy.getInterests();
                    String buddypers = foundbuddy.getPersonality();

                    String studentint = student.getInterests();
                    String studentpers = student.getPersonality();

                    String commonint = "";
                    String printint = "Common Interests: ";

                    String commonpers = "";
                    String printpers = "Common Personality Traits: ";


                    commonint = hamming(buddyint,studentint);
                    commonpers = hamming(buddypers,studentpers);

                    if (!commonint.equals("") && !commonpers.equals("")) {

                        printint += buildInt(commonint);

                        printpers += buildPers(commonpers);

                    }
                    else {
                        printpers += "Only time will tell!";
                        printint += "Only time will tell!";
                    }


                    if (personality != null) {

                        printint = printint.trim().substring(0,printint.length() -2);
                        printpers = printpers.trim().substring(0,printpers.length() -2);

                        personality.setText(printpers);
                        interests.setText(printint);
                        String fullbudname = foundbuddy.getFirstName()+" "+foundbuddy.getLastName();
                        String display= "Buddy 1 name: " + fullbudname;
                        bud1.setText(display);
                    }

                }

                @Override
                public void onFailure(Call<List<Buddy>> call, Throwable t) {


                }
            });

        }

        else if (buddy != null){

            Call<List<Student>> call1 = retroAPI.getStudents();
            call1.enqueue(new Callback<List<Student>>() {
                @Override
                public void onResponse(Call<List<Student>> call1, Response<List<Student>> response) {
                    students = response.body();
                    String buddyint = "";
                    String buddypers = "";
                    String studentint = "";
                    String studentpers = "";
                    String studentint1 = "";
                    String studentpers1 = "";
                    String studentint2 = "";
                    String studentpers2 = "";

                    String commonint = "";
                    String commonpers = "";


                    if (buddy.getNumberOfMatches() > 0){
                        buddyint = buddy.getInterests();
                        buddypers = buddy.getPersonality();
                        Student foundstudent1 = new Student();
                        Student foundstudent2 = new Student();
                        Student foundstudent3 = new Student();
                        buddyint = buddy.getInterests();
                        buddypers = buddy.getPersonality();

                        for (Student s:students){
                            if (buddy.getStudent1ID().trim().equals(s.getUserName())){
                                foundstudent1 = s;
                                studentint = foundstudent1.getInterests();
                                studentpers = foundstudent1.getPersonality();
                                String printint = "Common Interests: ";
                                String printpers = "Common Personality Traits: ";

                                commonint = hamming(studentint,buddyint);
                                commonpers = hamming(studentpers,buddypers);

                                printint += buildInt(commonint);
                                printpers += buildPers(commonpers);

                                printint = printint.trim().substring(0,printint.length() -2);
                                printpers = printpers.trim().substring(0,printpers.length() -2);

                                personality.setText(printpers);
                                interests.setText(printint);
                                String fullbudname = foundstudent1.getFirstName()+" "+foundstudent1.getLastName();
                                String display= "Student 1 name: " + fullbudname;
                                bud1.setText(display);

                            }

                            if (buddy.getNumberOfMatches() > 1){
                                if (buddy.getStudent2ID().trim().equals(s.getUserName())) {

                                    foundstudent2 = s;
                                    studentint1 = foundstudent2.getInterests();
                                    studentpers1 = foundstudent2.getPersonality();
                                    String printint = "Common Interests: ";
                                    String printpers = "Common Personality Traits: ";

                                    if (studentint1.length() == buddyint.length()) {

                                        commonint = hamming(studentint1, buddyint);
                                        commonpers = hamming(studentpers1, buddypers);
                                    }

                                    printint += buildInt(commonint);
                                    printpers += buildPers(commonpers);

                                    printint = printint.trim().substring(0,printint.length() -2);
                                    printpers = printpers.trim().substring(0,printpers.length() -2);

                                    personality1.setText(printpers);
                                    interests1.setText(printint);
                                    String fullbudname = foundstudent2.getFirstName()+" "+foundstudent2.getLastName();
                                    String display= "Student 2 name: " + fullbudname;
                                    bud2.setText(display);
                                }

                            }
                            if (buddy.getNumberOfMatches() > 2){

                                if (buddy.getStudent3ID().trim().equals(s.getUserName())) {

                                    foundstudent3 = s;
                                    studentint2 = foundstudent3.getInterests();
                                    studentpers2 = foundstudent3.getPersonality();
                                    String printint = "Common Interests: ";
                                    String printpers = "Common Personality Traits: ";

                                    if (studentint2.length() == buddyint.length()) {

                                        commonint = hamming(studentint2, buddyint);
                                        commonpers = hamming(studentpers2, buddypers);
                                    }

                                    printint += buildInt(commonint);
                                    printpers += buildPers(commonpers);

                                    printint = printint.trim().substring(0,printint.length() -2);
                                    printpers = printpers.trim().substring(0,printpers.length() -2);

                                    personality2.setText(printpers);
                                    interests2.setText(printint);
                                    String fullbudname = foundstudent3.getFirstName()+" "+foundstudent3.getLastName();
                                    String display= "Student 3 name: " + fullbudname;
                                    bud2.setText(display);

                                }

                            }

                        }

                    }

                }

                @Override
                public void onFailure(Call<List<Student>> call, Throwable t) {

                }
            });
        }
    }

    private static String buildPers(String commonpers){

        String printpers = "";

        if (commonpers.charAt(0) == '1') {
            printpers += "Outgoing and Social, ";

        }
        if (commonpers.charAt(1) == '1') {
            printpers += "Introverted, ";
        }
        if (commonpers.charAt(2) == '1') {
            printpers += "Curious and Open-minded, ";
        }
        if (commonpers.charAt(3) == '1') {
            printpers += "Creative, ";
        } else if (commonpers.equals("0000")) {
            printpers = "No common traits yet...";

        }

        return printpers;

    }

    private static String buildInt(String commonint){
        String printint = "";
        if (commonint.charAt(0) == '1') {
            printint += "Arts and Museums, ";

        }
        if (commonint.charAt(1) == '1') {
            printint += "Television, ";
        }
        if (commonint.charAt(2) == '1') {
            printint += "Video Games, ";
        }
        if (commonint.charAt(3) == '1') {
            printint += "Live Music, ";
        }
        if (commonint.charAt(4) == '1') {
            printint += "Club Nights, ";
        }
        if (commonint.charAt(5) == '1') {
            printint += "Painting or Writing, ";
        }
        if (commonint.charAt(6) == '1') {
            printint += "Sport, ";
        } else if (commonint.equals("000000")) {
            printint = "No common traits yet...";

        }

        return printint;
    }


    private static String hamming(String s1, String s2){

        String same = "";

        if(s1.length() != s2.length()) return same;

        for(int i = 0 ; i < s1.length() ; i++) {
            if (s1.charAt(i) == s2.charAt(i) && (s1.charAt(i) == '1'))
                same += "1";

            else {
                same += "0";
            }
        }
        return same;
    }
   }
