package com.example.karthik.mvp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.karthik.mvp.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private static final String TAG = "MainPage";
    private static final String url = "http://132.145.45.239/events";
    private ProgressDialog dialog;
    private List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private ItemAdapter adapter;

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
        listView = findViewById(R.id.list_item);
        adapter = new ItemAdapter(this, array);
        listView.setAdapter(adapter);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Events...");
        dialog.show();

        //Creating a volley request object
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();

                //Pasing the JSON
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Item item = new Item();
                        item.setTitle(obj.getString("title"));
                        item.setDate(obj.getString("date"));
                        item.setDescription(obj.getString("description"));
                        item.setVenue(obj.getString("venue"));
                        item.setTime(obj.getString("time"));

                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        EventController.getmInstance().addToRequestQueue(jsonArrayRequest);

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item clickeditem = array.get(position);
        Intent j = new Intent(getApplicationContext(),CustomDialog.class);
        Event event = new Event(clickeditem.getId(),clickeditem.getDate(),clickeditem.getTitle(),clickeditem.getDescription(),clickeditem.getVenue(),clickeditem.getTime());
        j.putExtra("cevent",new Gson().toJson(event));
        startActivity(j);
    }
});


    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

//    public void customDialog(String description, String venue, String time, final String okMethod) {
//        final android.support.v7.app.A
//    }
//
//    public void checkDetails(View view) {
//
//    }
}
