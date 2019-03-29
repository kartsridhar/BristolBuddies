package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.karthik.mvp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class questionaire2 extends AppCompatActivity {
    CheckBox outgo,nervous,curi,orig,nones;
    CheckBox int1,int2,int3,int4;
    CheckBox pers1,pers2,pers3,pers4;
    CheckBox cours1,cours2,cours3,cours4;
    CheckBox nat1, nat2,nat3,nat4;
    Button question2;
    private RetroAPI retroAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire2);
        outgo =(CheckBox) findViewById(R.id.outgoing);
        nervous= (CheckBox)findViewById(R.id.nervous);
        curi=(CheckBox)findViewById(R.id.curious);
        orig =(CheckBox) findViewById(R.id.original);
        nones = (CheckBox)findViewById(R.id.none2);

        int1 = (CheckBox) findViewById(R.id.Interestslimport);
        int2 = (CheckBox) findViewById(R.id.Interestsnrimport);
        int3 = (CheckBox) findViewById(R.id.Interestsfairimport);
        int4 = (CheckBox) findViewById(R.id.Interestsmostimport);

        pers1 = (CheckBox) findViewById(R.id.Personalitylimport);
        pers2 = (CheckBox) findViewById(R.id.Personalitynrimport);
        pers3 = (CheckBox) findViewById(R.id.Personalityfairimport);
        pers4 = (CheckBox) findViewById(R.id.Personalitymostimport);

        cours1 = (CheckBox) findViewById(R.id.Courselimport);
        cours2 = (CheckBox) findViewById(R.id.Coursenrimport);
        cours3 = (CheckBox) findViewById(R.id.Coursefairimport);
        cours4 = (CheckBox) findViewById(R.id.Coursemostimport);

        nat1 = (CheckBox) findViewById(R.id.Nationalitylimport);
        nat2 = (CheckBox) findViewById(R.id.Nationalitynrimport);
        nat3 = (CheckBox) findViewById(R.id.Nationalityfairimport);
        nat4 = (CheckBox) findViewById(R.id.Nationalitymostimport);

        question2 = findViewById(R.id.question2);


        //----------------------------------------------------------
        //TESTING PUTTING DATA
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);
        //----------------------------------------------------------

        nones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    outgo.setChecked(false);
                    nervous.setChecked(false);
                    curi.setChecked(false);
                    orig.setChecked(false);

                }
            }
        });

        outgo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nones.setChecked(false);
                }
            }
        });
        nervous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nones.setChecked(false);
                }
            }
        });

        curi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nones.setChecked(false);
                }
            }
        });

        orig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nones.setChecked(false);
                }
            }
        });



        int1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    int2.setChecked(false);
                    int3.setChecked(false);
                    int4.setChecked(false);

                }
            }
        });
        int2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    int1.setChecked(false);
                    int3.setChecked(false);
                    int4.setChecked(false);
                }
            }
        });
        int3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    int1.setChecked(false);
                    int2.setChecked(false);
                    int4.setChecked(false);
                }
            }
        });
        int4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    int1.setChecked(false);
                    int2.setChecked(false);
                    int3.setChecked(false);
                }
            }
        });

        pers1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    pers2.setChecked(false);
                    pers3.setChecked(false);
                    pers4.setChecked(false);

                }
            }
        });
        pers2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    pers1.setChecked(false);
                    pers3.setChecked(false);
                    pers4.setChecked(false);
                }
            }
        });
        pers3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    pers1.setChecked(false);
                    pers2.setChecked(false);
                    pers4.setChecked(false);
                }
            }
        });
        pers4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    pers1.setChecked(false);
                    pers2.setChecked(false);
                    pers3.setChecked(false);
                }
            }
        });

        cours1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    cours2.setChecked(false);
                    cours3.setChecked(false);
                    cours4.setChecked(false);

                }
            }
        });
        cours2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    cours1.setChecked(false);
                    cours3.setChecked(false);
                    cours4.setChecked(false);
                }
            }
        });
        cours3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    cours1.setChecked(false);
                    cours2.setChecked(false);
                    cours4.setChecked(false);
                }
            }
        });
        cours4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    cours1.setChecked(false);
                    cours2.setChecked(false);
                    cours3.setChecked(false);
                }
            }
        });

        nat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nat2.setChecked(false);
                    nat3.setChecked(false);
                    nat4.setChecked(false);

                }
            }
        });
        nat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nat1.setChecked(false);
                    nat3.setChecked(false);
                    nat4.setChecked(false);
                }
            }
        });
        nat3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nat1.setChecked(false);
                    nat2.setChecked(false);
                    nat4.setChecked(false);
                }
            }
        });
        nat4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nat1.setChecked(false);
                    nat2.setChecked(false);
                    nat3.setChecked(false);
                }
            }
        });


        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
                Intent l = new Intent(getApplicationContext(),MainPage.class);
            }
        });

    }

    private void createPost() {
        final Student student = (Student)getIntent().getSerializableExtra("serialize_data2");

        String personality="";
        String pref = "";

        if (outgo.isChecked()){
            personality +="1";
        }
        else personality +="0";
        if (nervous.isChecked()){
            personality +="1";
        }
        else personality +="0";
        if (curi.isChecked()){
            personality +="1";
        }
        else personality +="0";
        if (orig.isChecked()){
            personality +="1";
        }
        else personality +="0";
        if (nones.isChecked()){
            personality ="0000";
        }


        if (int4.isChecked()){
            pref += "3";
        }
        if (int3.isChecked()){
            pref += "2";
        }
        if (int2.isChecked()){
            pref += "1";
        }
        if (int1.isChecked()){
            pref += "0";
        }

        if (pers4.isChecked()){
            pref += "3";
        }
        if (pers3.isChecked()){
            pref += "2";
        }
        if (pers2.isChecked()){
            pref += "1";
        }
        if (pers1.isChecked()){
            pref += "0";
        }

        if (cours4.isChecked()){
            pref += "3";
        }
        if (cours3.isChecked()){
            pref += "2";
        }
        if (cours2.isChecked()){
            pref += "1";
        }
        if (cours1.isChecked()){
            pref += "0";
        }

        if (nat4.isChecked()){
            pref += "3";
        }
        if (nat3.isChecked()){
            pref += "2";
        }
        if (nat2.isChecked()){
            pref += "1";
        }
        if (nat1.isChecked()){
            pref += "0";
        }


        Student student2 = new Student(student.getFirstName(),student.getLastName(),student.getGender(),
                student.getUserName(),student.getPassword(),student.getDepartment(),student.getYearofStudy(),
                student.getNationality(),student.getInterests(),personality,pref, "");
        Intent k = new Intent(getApplicationContext(),Matching.class);
        k.putExtra("serialize_data3",student2);
        startActivity(k);
        finish();

    }

}
