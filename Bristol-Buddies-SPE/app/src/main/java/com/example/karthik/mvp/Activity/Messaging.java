package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.karthik.mvp.R;

public class Messaging extends AppCompatActivity {

    TextView TV;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.events:
                    Intent h = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(h);
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
        setContentView(R.layout.activity_messaging);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        TV = findViewById(R.id.textView);
    }
}
