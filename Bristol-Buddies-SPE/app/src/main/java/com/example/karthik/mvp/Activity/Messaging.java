package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.mesibo.api.Mesibo;
import com.example.karthik.mvp.R;

public class Messaging extends AppCompatActivity implements Mesibo.MessageListener, Mesibo.ConnectionListener {

    private static final String TAG = "Messaging";
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

        Mesibo api = Mesibo.getInstance();
        api.init(this);

        api.addListener(this);

        // set user authentication token obtained by creating user
        api.setAccessToken("17mbrheqhuugfbgo8n50dqye6ioom5p8zzkzqr2oihyw4vbi7uvhza0d7ridkas1");
        api.start();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void Mesibo_onConnectionStatus(int status) {
        // You will receive the connection status here
        Log.d(TAG, "on Mesibo Connection: " + status);
    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] data) {
        return true;
    }

    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {

    }

    @Override
    public void Mesibo_onActivity(Mesibo.MessageParams messageParams, int i) {

    }

    @Override
    public void Mesibo_onLocation(Mesibo.MessageParams messageParams, Mesibo.Location location) {

    }

    @Override
    public void Mesibo_onFile(Mesibo.MessageParams messageParams, Mesibo.FileInfo fileInfo) {

    }

    void sendTextMessage(String to, String message) {
        Mesibo.MessageParams p = new Mesibo.MessageParams();
        p.peer = to;
        p.flag = Mesibo.FLAG_READRECEIPT | Mesibo.FLAG_DELIVERYRECEIPT;

        Mesibo.sendMessage(p, Mesibo.random(), message);
    }
}
