package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.karthik.mvp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.StatementEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class Matching extends AppCompatActivity {
    private RetroAPI retroAPI;
    private Buddy bestBud;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);


        student = (Student) getIntent().getSerializableExtra("serialize_data3");
        Call<List<Buddy>> call = retroAPI.getBuddies();
        call.enqueue(new Callback<List<Buddy>>() {
            @Override
            public void onResponse(Call<List<Buddy>> call, Response<List<Buddy>> response) {
                List<Buddy> buddies = response.body();
                bestBud = bestMatch(buddies,student);

                Student student2 = new Student(student.getFirstName(),student.getLastName(),
                        student.getGender(),student.getUserName(),student.getPassword(),student.getDepartment(),
                        student.getYearofStudy(),student.getNationality(),student.getInterests(),
                        student.getPersonality(), student.getPreferences(), bestBud.getUsername());
                /*Call<Student> call2 = retroAPI.createStudent(student2);
                call2.enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call2, Response<Student> response) {

                    }

                    @Override
                    public void onFailure(Call<Student> call2, Throwable t) {}
                });*/


                matchingBuddy();
            }

            @Override
            public void onFailure(Call<List<Buddy>> call, Throwable t) {
            }
        });


        Intent l = new Intent(getApplicationContext(),MainPage.class);
        startActivity(l);
        finish();
    }

    private static int hamming(String s1, String s2){
        if(s1.length() != s2.length()) return -1;

        int hamm = 0;
        for(int i = 0 ; i < s1.length() ; i++){
            if(s1.charAt(i) != s2.charAt(i)) hamm+=1 ;
        }
        return hamm;
    }

    private static double similarity(Buddy b, Student s){
        int interestPref = Character.getNumericValue(s.getPreferences().charAt(0));
        int personalityPref = Character.getNumericValue(s.getPreferences().charAt(1));
        int facultyPref = Character.getNumericValue(s.getPreferences().charAt(2));
        int nationalityPref = Character.getNumericValue(s.getPreferences().charAt(3));
        double interest = (7 - hamming(b.getInterests() , s.getInterests())) / 7.0;
        double personality = (4 - hamming(b.getPersonality(), s.getPersonality())) / 4.0 ;
        double faculty = b.getCourse().equals(s.getDepartment()) ? 1 : 0;
        double nationality = b.getNationality().equals(s.getNationality()) ? 1 : 0;

        double similarity = interest * interestPref + personality * personalityPref +
                faculty * facultyPref + nationality * nationalityPref ;

        return similarity;
    }


    private static Buddy bestMatch(List<Buddy> buddies, Student student){
        double bestSim = -1;
        Buddy bestBuddy = null;
        for (Buddy b : buddies) {
            double s =  similarity(b,student);
            if(b.getNumberOfMatches() <= 1 && s > bestSim) {
                bestSim = s;
                bestBuddy = b;
            }
        }
        if(bestBuddy == null){
            for (Buddy b : buddies) {
                double s =  similarity(b,student);
                if(b.getNumberOfMatches() <= 2 && s > bestSim) {
                    bestSim = s;
                    bestBuddy = b;
                }
            }
        }
        return bestBuddy;
    }

    private void matchingBuddy(){
        int newMatches = bestBud.getNumberOfMatches() + 1;
        String firstMatch = bestBud.getStudent1ID();
        String secondMatch = bestBud.getStudent2ID();
        String thirdMatch = bestBud.getStudent3ID();
        switch (bestBud.getNumberOfMatches()) {
            case 0 :
                firstMatch = student.getUserName();
                break;
            case 1 :
                secondMatch = student.getUserName();
                break;
            case 2 :
                thirdMatch = student.getUserName();
                break;
        }
        Buddy newBuddy = new Buddy(bestBud.getFirstName(), bestBud.getLastName(), bestBud.getUsername(), bestBud.getCourse(),
                bestBud.getNationality(), bestBud.getInterests(), bestBud.getPersonality(), bestBud.getPreferences(),
                bestBud.getPassword(), newMatches, firstMatch, secondMatch, thirdMatch);
        Log.d("debug", newBuddy.getStudent1ID());
        String strippedUsername = bestBud.getUsername().replace("\"","");
        Log.d("debug", strippedUsername);
        Call<Buddy> call3 = retroAPI.updateBuddy(strippedUsername, newBuddy);
        call3.enqueue(new Callback<Buddy>() {
            @Override
            public void onResponse(Call<Buddy> call3, Response<Buddy> response) {

            }

            @Override
            public void onFailure(Call<Buddy> call3, Throwable t) {}
        });
    }

}
