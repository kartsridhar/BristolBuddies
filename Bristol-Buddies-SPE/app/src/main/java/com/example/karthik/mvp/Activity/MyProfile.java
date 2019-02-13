package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.karthik.mvp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProfile extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    Button sign_out, form, post, getBuddy;
    TextView fullName, email, id;
    ImageView pic;
    private RetroAPI retroAPI;

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
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        sign_out = findViewById(R.id.signOutBtn);
        form = findViewById(R.id.form);
        pic = findViewById(R.id.photo);
        fullName = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        id = findViewById(R.id.userID);
        post = findViewById(R.id.postData);
        getBuddy = findViewById(R.id.getBuddy);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //----------------------------------------------------------
        //TESTING PUTTING DATA
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);
        //----------------------------------------------------------

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount deets = GoogleSignIn.getLastSignedInAccount(MyProfile.this);

        if(deets != null) {
            String name = deets.getDisplayName();
            String idd = deets.getId();
            String mail = deets.getEmail();
            Uri dp = deets.getPhotoUrl();

            Glide.with(this).load(dp).into(pic);
            fullName.setText("Name: " + name);
            email.setText("Email: " + mail);
            id.setText("User ID: " + idd);
        }

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                send();
                createPost();
            }
        });
        getBuddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Messaging.class);
                startActivity(i);
            }
        });
    }

    //----------------------------------------------------
    //TO POST THE DATA TEST
    public void createPost() {
        Post post = new Post("Ronald", "Costa", "Male", "rc17067", "overwatch", "ENG", "2");

        Call<Post> call = retroAPI.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MyProfile.this, response.code(), Toast.LENGTH_LONG).show();
                }
                Post postResponse = response.body();

                fullName.setText(postResponse.getFname() + " " + postResponse.getLname());
                email.setText(postResponse.getUname() + "@my.bristol.ac.uk");
                id.setText(postResponse.getPwd());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MyProfile.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //----------------------------------------------------

//    public void send() {
//        retrofit2.Call<ResponseBody> call = RetroClient.getInstance().getAPI()
//                .createStudent("Ronald", "Costa", "Male", "rc17067", "overwatch", "ENG", "2");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String s = response.body().string();
//                    Toast.makeText(MyProfile.this, s, Toast.LENGTH_LONG).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(MyProfile.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    public void openForm(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfUHn6_GoD39DKDGn_uqjoEUvoLTB4wM3Eiv1uIPN5oAIfnwQ/viewform?usp=sf_link"));
        startActivity(i);
    }

    private void signOut() {
        googleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MyProfile.this, "Successfully signed out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MyProfile.this, GoogleLogin.class));
                finish();
            }
        });
    }
}
