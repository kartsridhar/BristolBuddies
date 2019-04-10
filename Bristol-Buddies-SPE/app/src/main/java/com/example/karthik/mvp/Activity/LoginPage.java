package com.example.karthik.mvp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karthik.mvp.R;
import com.facebook.login.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static android.widget.Toast.LENGTH_LONG;

public class LoginPage extends AppCompatActivity {

    TextInputLayout username, password;
    String u, p;
    Button login;
    List<Buddy> buddies;
    Switch switch1;
    TextView switchtext;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://132.145.45.239/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetroAPI retroAPI = retrofit.create(RetroAPI.class);

//    public static void setDefaults(String key, String value, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(key, value);
//        editor.commit();
//    }
//
//    public static String getDefaults(String key, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        return preferences.getString(key, null);



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);






        username = findViewById(R.id.studID);
        password = findViewById(R.id.studPass);
        login = findViewById(R.id.loginButton);
        switch1 = (Switch) findViewById(R.id.switch1);
        switchtext = findViewById(R.id.switchtext);
        p = password.getEditText().getText().toString().trim();

        SharedPreferences mPrefs = getSharedPreferences("CHECK",0);
        final SharedPreferences.Editor editor = mPrefs.edit();


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!switch1.isChecked()){
                    switchtext.setText(" Student ");

                }
                else {
                    switchtext.setText("   Buddy ");



                }

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!switch1.isChecked()) {
                    editor.putInt("ISBUDDY",-1);
                    editor.commit();
                    studentLogin();
                }
                else {
                    editor.putInt("ISBUDDY",1);
                    editor.commit();
                    buddyLogin();
                }

            }
        });

    }

    private void studentLogin(){
        u = username.getEditText().getText().toString().trim();
        p = password.getEditText().getText().toString().trim();

        if (u.isEmpty()){
            username.setError("Username is required");
            username.requestFocus();
            return;
        }

        if (p.isEmpty()){
            password.setError("Password required");
            password.requestFocus();
            return;
        }


        Call<List<Student>> call = retroAPI.getStudents();
        call.enqueue(new Callback<List<Student>>() {

            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                Student tony = students.get(0);
                int found = 0;
                Log.d("STUDENTLISTSS",tony.getUserName().toString());
                Student matchstudent = new Student("", "","","","","","","","","","","");
                for (Student a :students){
                    if ((u.equals(a.getUserName().trim())) && (p.equals(a.getPassword().trim()))){
                        Log.d("ENTEREDIFSTATEMENT","ENTERED IF STATEMENT");
                        matchstudent = a;
                        Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_LONG).show();

                        Intent m = new Intent(getApplicationContext(),MyProfile.class);
                        m.putExtra("serialize_data3",matchstudent);
                        m.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        Intent mess = new Intent(getApplicationContext(),Messaging.class);
                        mess.putExtra("messagingData", matchstudent);
                        mess.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(m);
                        Log.d("SENTSTUDENT",matchstudent.getFirstName());
                        found = 1;
                        Intent k = new Intent(getApplicationContext(),MainPage.class);
                        startActivity(k);
                        finish();
                    }
                }
                if (found == 0) {
                    password.setError("Incorrect username or password");
                    password.requestFocus();
                    username.requestFocus();
                }
                Log.d("MATCHSTUDENT",matchstudent.getFirstName());

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {      //when something goes wrong in communication
                Toast.makeText(LoginPage.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }



    private void buddyLogin(){
        u = username.getEditText().getText().toString().trim();
        p = password.getEditText().getText().toString().trim();

        if (u.isEmpty()){
            username.setError("Username is required");
            username.requestFocus();
            return;
        }

        if (p.isEmpty()){
            password.setError("Password required");
            password.requestFocus();
            return;
        }
        Call<List<Buddy>> call1 = retroAPI.getBuddies();
        call1.enqueue(new Callback<List<Buddy>>() {
            @Override
            public void onResponse(Call<List<Buddy>> call, Response<List<Buddy>> response) {
                buddies = response.body();
                Buddy matchbuddy = new Buddy("", "","","","","","","","",0,"","","");
                int found = 0;
                for (Buddy a :buddies){
                    if ((u.equals(a.getUsername().trim())) && (p.equals(a.getPassword().trim()))){
                        Log.d("ENTEREDIFSTATEMENT","ENTERED IF STATEMENT");
                        matchbuddy = a;
                        Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_LONG).show();

                        Intent m = new Intent(getApplicationContext(),MyProfile.class);
                        m.putExtra("budlogin",matchbuddy);
                        m.putExtra("CheckBud",1);
                        m.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        Intent mess = new Intent(getApplicationContext(),Messaging.class);
                        mess.putExtra("messagingData", matchbuddy);
                        mess.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(m);
                        Log.d("SENTSTUDENT",matchbuddy.getFirstName());
                        found = 1;
                        Intent k = new Intent(getApplicationContext(),MainPage.class);
                        startActivity(k);
                        finish();
                    }
                    Log.d("JJJJ","NOTHING FOUND");
                }
                Log.d(" KKKK","NOTHING FOUND");

                if (found == 0) {
                    password.setError("Incorrect username or password");
                    password.requestFocus();
                    username.requestFocus();
                }
                Log.d("MATCHSTUDENT",matchbuddy.getFirstName());

            }
            @Override
            public void onFailure(Call<List<Buddy>> call, Throwable t) {
            }
        });

    }
}
