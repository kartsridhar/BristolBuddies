package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.karthik.mvp.R;

public class LoginPage extends AppCompatActivity {

    EditText studNum, studPass;
    String uname, upass;
    TextView register;
    Button login, json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        studNum = findViewById(R.id.studentID);
        studPass = findViewById(R.id.pass);
        register = findViewById(R.id.loginText);
        login = findViewById(R.id.login);
        json = findViewById(R.id.button1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname = studNum.getText().toString();
                upass = studPass.getText().toString();
                String method = "login";
//                BackgroundTask backgroundTask = new BackgroundTask(this);
//                backgroundTask.execute(method, uname, upass);

                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(intent);
            }
        });

        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), JSONActivity.class);
                startActivity(i);
            }
        });
    }
}
