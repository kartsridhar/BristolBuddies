package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.karthik.mvp.R;

public class LoginPage extends AppCompatActivity {

    EditText studNum, studPass;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        studNum = findViewById(R.id.studentID);
        studPass = findViewById(R.id.pass);
        register = findViewById(R.id.loginText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(intent);
            }
        });
    }
}
