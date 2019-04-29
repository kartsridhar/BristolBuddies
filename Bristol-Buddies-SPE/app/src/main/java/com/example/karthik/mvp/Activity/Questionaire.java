package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karthik.mvp.R;

public class Questionaire extends AppCompatActivity {
    TextInputLayout faculty,Gradyear,Nationality;
    CheckBox ArtandMus,Tele,Vidgames,Gigs,clubs,none,creat,sport;
    String fac,gY,nat;
    Button question1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);
        faculty = findViewById(R.id.faculty);
        Gradyear = findViewById(R.id.gradyear);
        Nationality = findViewById(R.id.nat);


        ArtandMus = findViewById(R.id.artmus);
        Tele = findViewById(R.id.tele);
        Vidgames = findViewById(R.id.games);
        Gigs = findViewById(R.id.gigs);
        clubs = findViewById(R.id.clubs);
        none = findViewById(R.id.none1);
        creat = findViewById(R.id.creative);
        sport = findViewById(R.id.Sport);
        question1 = findViewById(R.id.question1);

        final Student student = (Student)getIntent().getSerializableExtra("serialize_data1");
        final Buddy buddy = (Buddy) getIntent().getSerializableExtra("buddy_data");

        none.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    ArtandMus.setChecked(false);
                    Tele.setChecked(false);
                    Vidgames.setChecked(false);
                    Gigs.setChecked(false);
                    clubs.setChecked(false);
                    creat.setChecked(false);
                    sport.setChecked(false);
                }
            }
        });
        ArtandMus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });
        Tele.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });

        Gigs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });

        clubs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });
        creat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });

        sport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });

        Vidgames.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    none.setChecked(false);
                }
            }
        });


        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fac = faculty.getEditText().getText().toString();
                gY = Gradyear.getEditText().getText().toString();
                nat = Gradyear.getEditText().getText().toString();
                String interests = "";
                if (ArtandMus.isChecked()){
                    interests += "1";
                }
                else interests +="0";
                if (Tele.isChecked()){
                    interests += "1";
                }
                else interests +="0";
                if (Vidgames.isChecked()){
                    interests += "1";
                }
                else interests +="0";
                if (Gigs.isChecked()){
                    interests += "1";
                }
                else interests +="0";
                if (creat.isChecked()){
                    interests += "1";
                }
                else interests +="0";

                if (sport.isChecked()){
                    interests += "1";
                }
                if (clubs.isChecked()){
                    interests += "1";
                }
                else interests +="0";
                if (none.isChecked()){
                    interests = "000000";
                }


            if (student != null) {
                final Student student1 = new Student(student.getFirstName(), student.getLastName(), student.getGender(), student.getUserName(), student.getPassword(), fac, student.getYearofStudy(), nat, interests, "", "", "");
                Intent k = new Intent(getApplicationContext(), questionaire2.class);
                k.putExtra("serialize_data2", student1);
                startActivity(k);
                finish();
            }

            if (buddy != null){
                final Buddy buddy1 = new Buddy(buddy.getFirstName(),buddy.getLastName(),buddy.getUsername(),fac,nat,interests,"","",buddy.getPassword(),0,"","","");
                Intent k = new Intent(getApplicationContext(), questionaire2.class);
                k.putExtra("buddy_data1", buddy1);
                startActivity(k);
                finish();
            }
            }



        });

    }
}
