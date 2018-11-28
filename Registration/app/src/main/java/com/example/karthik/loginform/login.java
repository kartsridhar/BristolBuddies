package com.example.karthik.loginform;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button register;
    EditText studentNo, firstName, lastName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);

        //Getting the details
        register = findViewById(R.id.button);
        studentNo = findViewById(R.id.studentID);
        firstName = findViewById(R.id.fName);
        lastName = findViewById(R.id.lName);
        password = findViewById(R.id.pass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase(); //we want to write into the database
                String id = studentNo.getText().toString();
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();
                String pwd = password.getText().toString();
                insertData(id, fname, lname, pwd);
                Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_LONG).show(); //to notify the user about successful registration
            }
        });
    }
    public void insertData(String id, String fname, String lname, String pwd) {
        ContentValues contentValues = new ContentValues(); //to write the value in the database
        contentValues.put(DatabaseHelper.COL_1, id);
        contentValues.put(DatabaseHelper.COL_2, fname);
        contentValues.put(DatabaseHelper.COL_3, lname);
        contentValues.put(DatabaseHelper.COL_4, pwd);
        long table = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

    }
}
