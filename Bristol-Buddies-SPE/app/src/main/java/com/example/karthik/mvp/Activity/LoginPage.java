package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karthik.mvp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;

public class LoginPage extends AppCompatActivity {

    EditText username, password;
    String u, p;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.studID);
        password = findViewById(R.id.studPass);
        login = findViewById(R.id.loginButton);

        u = username.getText().toString();
        p = password.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroAPI retroAPI = retrofit.create(RetroAPI.class);
        Call<List<Student>> call = retroAPI.getStudents();

        call.enqueue(new Callback<List<Student>>() {

            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(LoginPage.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Student> students = response.body();

                for(Student student : students) {
                    if(u.isEmpty() || p.isEmpty()) {
                        Toast.makeText(LoginPage.this, "Please enter login details!", Toast.LENGTH_LONG).show();
                    }
                    else if(u.equalsIgnoreCase(student.getUserName()) && p.equals(student.getPassword())) {
                        Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {      //when something goes wrong in communication
                Toast.makeText(LoginPage.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

