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

public class MyProfile extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    Button sign_out, form, getBuddy;
    TextView fullName, email, id;
    ImageView pic;

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
        getBuddy = findViewById(R.id.getBuddy);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

        else{
            final Student student = (Student)getIntent().getSerializableExtra("serialize_data3");
            String idd = String.valueOf(5000);
            String name = student.getFirstName()+" "+ student.getLastName();
            String username = student.getUserName();
            fullName.setText("Name: " + name);
            email.setText("Email: " + username);
            id.setText("User ID: " + idd);

        }

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
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
