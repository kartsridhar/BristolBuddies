package com.example.karthik.mvp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    TextView personality,interests;

    List<Buddy> buddies;

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


                    Log.d("XXXX", student.getUserName());

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


                     if (commonint.charAt(0) == '1' ){
                            printint += "Arts and Museums, ";

                        }
                        if (commonint.charAt(1) == '1' ){
                            printint += "Television, ";
                        }
                        if (commonint.charAt(2) == '1' ){
                            printint += "Video Games, ";
                        }
                        if (commonint.charAt(3) == '1' ){
                            printint += "Live Music, ";
                        }
                        if (commonint.charAt(4) == '1' ){
                            printint += "Club Nights, ";
                        }
                        if (commonint.charAt(5) == '1' ){
                            printint += "Painting or Writing, ";
                        }
                        if (commonint.charAt(6) == '1' ){
                            printint += "Sport, ";
                        }

                        else if (commonint.equals("000000")){
                            printint = "Only time will tell..";

                        }

                        if (commonpers.charAt(0) == '1' ){
                            printpers += "Outgoing and Social, ";

                        }
                        if (commonpers.charAt(1) == '1' ){
                            printpers += "Introverted, ";
                        }
                        if (commonpers.charAt(2) == '1' ){
                            printpers += "Curious and Open-minded , ";
                        }
                        if (commonpers.charAt(3) == '1' ){
                            printpers += "Creative , ";
                        }
                        else if (commonpers.equals("0000")){
                            printpers = "Only time will tell..";

                        }


                    if (personality != null) {

                        personality.setText(printpers);
                        interests.setText(printint);

                    }








                }

                @Override
                public void onFailure(Call<List<Buddy>> call, Throwable t) {


                }
            });

        }

    }


    private static String hamming(String s1, String s2){

        String same = "";

        Log.d("YYYY",s1);
        Log.d("ZZZZ",s2);


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

