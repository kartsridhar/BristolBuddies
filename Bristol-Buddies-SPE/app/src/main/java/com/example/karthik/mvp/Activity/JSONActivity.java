package com.example.karthik.mvp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karthik.mvp.R;

public class JSONActivity extends AppCompatActivity {

    Button b;
    public static TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

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
