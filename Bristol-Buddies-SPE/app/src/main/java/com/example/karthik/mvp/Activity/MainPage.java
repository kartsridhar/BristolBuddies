package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karthik.mvp.R;

public class MainPage extends AppCompatActivity {

    Button b;
    public static TextView details;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.events:
                    break;
                case R.id.messaging:
                    Intent i = new Intent(getApplicationContext(), Messaging.class);
                    startActivity(i);
                    break;
                case R.id.myProfile:
                    Intent j = new Intent(getApplicationContext(), MyProfile.class);
                    startActivity(j);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        b = findViewById(R.id.button);
        details = findViewById(R.id.details);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetJSON process = new GetJSON();
                process.execute();
            }
        });
    }
}
