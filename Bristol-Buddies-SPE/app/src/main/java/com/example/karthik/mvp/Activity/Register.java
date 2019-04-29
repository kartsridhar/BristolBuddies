package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.karthik.mvp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    TextInputLayout firstName, lastName, uName, uPass;
    String db_fname, db_lname, db_uname, db_upass, db_unat, db_uint,db_upers,db_upref;
    Button register;
    Switch IsBuddy;
    private RetroAPI retroAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.fName);
        lastName = findViewById(R.id.lName);
        uName = findViewById(R.id.studID);
        uPass = findViewById(R.id.studPass);
        register = findViewById(R.id.regButton);
        IsBuddy = findViewById(R.id.switch3);


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
                db_fname = firstName.getEditText().getText().toString().trim();
                db_lname = lastName.getEditText().getText().toString().trim();
                db_uname = uName.getEditText().getText().toString().trim();
                db_upass = uPass.getEditText().getText().toString().trim();
                db_unat = "";
                db_uint = "";
                db_upers = "";
                db_upref = "";

                Call<List<Student>> call = retroAPI.getStudents();

                call.enqueue(new Callback<List<Student>>() {

                    @Override
                    public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                        List<Student> students = response.body();
                        int errorCount = 0;

                        for (Student s : students) {
                            if (db_fname.isEmpty()) {
                                firstName.setError("Field can't be empty");
                                errorCount += 1;
                            } else if(!checkName(db_fname)) {
                                firstName.setError("Only letters!");
                                errorCount += 1;
                            }
                            else {
                                firstName.setError(null);
                            }

                            if (db_lname.isEmpty()) {
                                lastName.setError("Field can't be empty");
                                errorCount += 1;
                            } else if(!checkName(db_lname)) {
                                lastName.setError("Only letters!");
                                errorCount += 1;
                            }
                            else {
                                lastName.setError(null);
                            }

                            if (db_uname.isEmpty()) {
                                uName.setError("Field can't be empty");
                                errorCount += 1;
                            } else if (!checkUsername(db_uname)) {
                                uName.setError("Enter right format");
                                errorCount += 1;
                            } else if (db_uname.length() > 7) {
                                uName.setError("Username too long");
                                errorCount += 1;
                            } else if(db_uname.equals(s.getUserName().trim())) {
                                uName.setError("Username Exists");
                                errorCount += 1;
                            } else {
                                uName.setError(null);
                            }


                            if (db_upass.isEmpty()) {
                                uPass.setError("Field can't be empty");
                                errorCount += 1;
                            } else {
                                uPass.setError(null);
                            }
                        }

                        if (IsBuddy.isChecked()) {
                            Toast.makeText(Register.this, "MADE IT TO BUDDY REG", Toast.LENGTH_LONG).show();
                            final Buddy buddy = new Buddy(db_fname, db_lname, db_uname, "", db_unat, "", "", "", db_upass, 0, null, null, null);
                            if (errorCount != 0) {
                                Toast.makeText(getApplicationContext(), "Check Again!", Toast.LENGTH_LONG).show();
                            } else {
                                Intent j = new Intent(getApplicationContext(), Questionaire.class);
                                j.putExtra("buddy_data", buddy);
                                startActivity(j);
                                finish();
                            }
                        } else {
                            final Student student = new Student(db_fname, db_lname, db_uname, db_upass, "", "", db_unat, db_uint, db_upers, db_upref, "");

                            if (errorCount != 0) {
                                Toast.makeText(getApplicationContext(), "Check Again!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Intent j = new Intent(getApplicationContext(), Questionaire.class);
                                j.putExtra("serialize_data1", student);
                                startActivity(j);
                                finish();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {
                        Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public static boolean checkUsername(String str) {

        if(Character.isLetter(str.charAt(0)) && Character.isLetter(str.charAt(1))
                && Character.isDigit(str.charAt(2)) && Character.isDigit(str.charAt(3))
                && Character.isDigit(str.charAt(4)) && Character.isDigit(str.charAt(5))
                && Character.isDigit(str.charAt(6)))
            return true;
        else return false;
    }

    public static boolean checkName(String str) {

        for(int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))) return false;
            else return true;
        }
        return false;
    }

    public static boolean checkWhiteSpace(String str) {
        for(int i = 0; i < str.length(); i++) {
            if(Character.isWhitespace(str.charAt(i))) return false;
            else return true;
        }
        return false;
    }

}
