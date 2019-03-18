package com.example.karthik.mvp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karthik.mvp.R;

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
                Toast.makeText(CustomDialog.this, "Should close!", Toast.LENGTH_LONG).show();
            }
        });

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomDialog.this, "Marked as interested", Toast.LENGTH_LONG).show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroAPI retroAPI = retrofit.create(RetroAPI.class);

        Call<List<Event>> call = retroAPI.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CustomDialog.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Event> events = response.body();
                for (Event e : events) {
                    //TODO: match the event ID and display the required details in the custom dialog.
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(CustomDialog.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
