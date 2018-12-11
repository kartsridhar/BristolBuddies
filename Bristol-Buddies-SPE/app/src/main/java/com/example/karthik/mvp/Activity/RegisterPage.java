package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karthik.mvp.R;

public class RegisterPage extends AppCompatActivity {

    EditText studNum, firstName, lastName, studPass;
    Button register, proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        studNum = findViewById(R.id.studID);
        firstName = findViewById(R.id.fName);
        lastName = findViewById(R.id.lName);
        studPass = findViewById(R.id.studPass);
        register = findViewById(R.id.regButton);
        proceed = findViewById(R.id.procButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_LONG).show();      //to notify the user about successful registration
                proceed.setEnabled(true);       //enabling the proceed button to true
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
            }
        });
    }
}
