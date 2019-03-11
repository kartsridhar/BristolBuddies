package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karthik.mvp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    EditText firstName, lastName, uName, uGender, uPass, uDept, uYos;
    String db_fname, db_lname, db_uname, db_ugender, db_upass, db_udept, db_uyos, db_unat, db_uint,db_upers,db_upref;
    Button register;
    private RetroAPI retroAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.fName);
        lastName = findViewById(R.id.lName);
        uName = findViewById(R.id.studID);
        uGender = findViewById(R.id.gender);
        uPass = findViewById(R.id.studPass);
        uDept = findViewById(R.id.dept);
        uYos = findViewById(R.id.yos);
        register = findViewById(R.id.regButton);





        //----------------------------------------------------------
        //TESTING PUTTING DATA
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);
        //----------------------------------------------------------

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_fname = firstName.getText().toString();
                db_lname = lastName.getText().toString();
                db_uname = uName.getText().toString();
                db_ugender = uGender.getText().toString();
                db_upass = uPass.getText().toString();
                db_udept = uDept.getText().toString();
                db_uyos = uYos.getText().toString();
                db_unat = "";
                db_uint = "";
                db_upers="";
                db_upref="";
                final Student student = new Student(db_fname, db_lname, db_ugender, db_uname, db_upass, db_udept, db_uyos,db_unat,db_uint,db_upers,db_upref);
                Intent j = new Intent(getApplicationContext(), Questionaire.class);
                j.putExtra("serialize_data1",student);
                startActivity(j);
                finish();
            }
        });
    }


}
