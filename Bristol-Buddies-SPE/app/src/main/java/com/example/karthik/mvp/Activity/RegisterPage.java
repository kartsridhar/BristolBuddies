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

    EditText firstName, lastName, uName, uGender, uPass, uDept, uYos;
    String db_fname, db_lname, db_uname, db_ugender, db_upass, db_udept, db_uyos;
    Button register, googForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        firstName = findViewById(R.id.fName);
        lastName = findViewById(R.id.lName);
        uName = findViewById(R.id.studID);
        uGender = findViewById(R.id.gender);
        uPass = findViewById(R.id.studPass);
        uDept = findViewById(R.id.dept);
        uYos = findViewById(R.id.yos);
        register = findViewById(R.id.regButton);
        googForm = findViewById(R.id.googButton);

        googForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GForm.class);
                startActivity(intent);
            }
        });
    }
    public void userReg(View view){
        db_fname = firstName.getText().toString();
        db_lname = lastName.getText().toString();
        db_uname = uName.getText().toString();
        db_ugender = uGender.getText().toString();
        db_upass = uPass.getText().toString();
        db_udept = uDept.getText().toString();
        db_uyos = uYos.getText().toString();

        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, db_fname, db_lname, db_uname, db_ugender, db_upass, db_udept, db_uyos);
        Intent i = new Intent(getApplicationContext(), LoginPage.class);
        startActivity(i);
        finish();
    }
}
