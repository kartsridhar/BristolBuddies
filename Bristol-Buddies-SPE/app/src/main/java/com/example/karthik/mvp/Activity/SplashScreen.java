package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.karthik.mvp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    Thread myThread = new Thread() {
        @Override
        public void run() {
            try {
                sleep(3000);            //sleep after 3 seconds
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);        //starting the next activity
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
        myThread.start();
    }
}
