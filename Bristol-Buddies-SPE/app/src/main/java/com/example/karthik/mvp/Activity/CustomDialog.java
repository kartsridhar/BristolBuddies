package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karthik.mvp.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomDialog extends AppCompatActivity {

    Button interested;
    ImageView cross;
    public static TextView desc;
    public static TextView venue;
    public static TextView tiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        interested = findViewById(R.id.button_dialog);
        desc = findViewById(R.id.description_dialog);
        venue = findViewById(R.id.venue_dialog);
        tiem = findViewById(R.id.time_dialog);
        cross = findViewById(R.id.close_dialog);

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(),MainPage.class);
                startActivity(o);
                finish();
            }
        });

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomDialog.this, "Marked as interested", Toast.LENGTH_LONG).show();
            }
        });

        String e="";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            e = extras.getString("cevent");
        }
        Event events = new Gson().fromJson(e,Event.class);
        desc.setText(events.getDescription());
        venue.setText(events.getVenue());
        tiem.setText(events.getTime());
    }
}
