package com.example.karthik.mvp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProfile extends AppCompatActivity {



    static boolean active = false;

    GoogleSignInClient googleSignInClient;
    Button sign_out, form, getBuddy;
    TextView fullName, email, id,BudName,BudMail,BudName1,BudMail1,BudName2,BudMail2;
    private RetroAPI retroAPI;

    List<Buddy> buddies;
    List <Student> students;

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
                    break;
                case R.id.myProfile:
                    break;
            }
            return false;
        }
    };
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        final Buddy regBuddy = (Buddy) getIntent().getSerializableExtra("buddy");
//        outState.putSerializable("buddy1",regBuddy);
//        Toast.makeText(MyProfile.this,"DATA STORED",Toast.LENGTH_LONG);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://132.145.45.239/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        final SharedPreferences sp = getSharedPreferences("Buddy",MODE_PRIVATE);
        SharedPreferences.Editor Ed = sp.edit();

        final SharedPreferences mPrefs = getSharedPreferences("CHECK",0);
        int isbud = mPrefs.getInt("ISBUDDY",0);

//        Intent b = new Intent(getApplicationContext(),MainPage.class);
//        b.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        b.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//        startActivity(b);


        sign_out = findViewById(R.id.signOutBtn);
        form = findViewById(R.id.form);
        pic = findViewById(R.id.photo);
        fullName = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        id = findViewById(R.id.userID);
        getBuddy = findViewById(R.id.getBuddy);
        BudName = findViewById(R.id.buddyName);
        BudMail = findViewById(R.id.buddyMail);
        BudName1 = findViewById(R.id.buddyName1);
        BudMail1 = findViewById(R.id.buddyMail1);
        BudName2 = findViewById(R.id.buddyName2);
        BudMail2 = findViewById(R.id.buddyMail2);
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
             final Student regstudent = (Student)getIntent().getSerializableExtra("student");
             Buddy regBuddy = (Buddy) getIntent().getSerializableExtra("buddy");
             final Buddy budlogin = (Buddy) getIntent().getSerializableExtra("budlogin");


//            int isBud1 = Integer.parseInt(CheckBuddy);

//            Log.d("GGGG", String.valueOf(isBud1));

            Log.d("RRRRR",String.valueOf(isbud));



            if (regBuddy != null && regstudent != null && isbud == -1){
                 Ed.putString("BudName",regBuddy.getFirstName() + " " + regBuddy.getLastName());
                 Ed.putString("BudMail",regBuddy.getUsername() );
                 Ed.putString("StudentName",regstudent.getFirstName() + " " + regstudent.getLastName());
                 Ed.putString("StudentMail",regstudent.getUserName());
                 Ed.apply();
                 Ed.commit();
             }




            if (budlogin != null && isbud == 1){
                 String idd = String.valueOf(5000);
                 String name = budlogin.getFirstName() + " " + budlogin.getLastName();
                 String username = budlogin.getUsername();
                 Ed.putString("BudName",budlogin.getFirstName() + " " + budlogin.getLastName());
                 Ed.putString("BudMail",budlogin.getUsername());
                 Ed.apply();
                 Ed.commit();
                 fullName.setText("Name: " + name);
                 email.setText("Email: " + username);
                 id.setText("User ID: " + idd);
                 Call<List<Student>> call = retroAPI.getStudents();
                 call.enqueue(new Callback<List<Student>>() {
                     @Override
                     public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                         students = response.body();
                         if (budlogin.getNumberOfMatches() != 0) {
                             String bName = "";
                             String bMail = "";
                             String bName1 = "";
                             String bMail1 = "";
                             String bName2 = "";
                             String bMail2 = "";

                             for (Student b : students) {
                                 if (b.getUserName().trim().equals(budlogin.getStudent1ID().trim())) {
                                     bName = b.getFirstName() + " " + b.getLastName();
                                     bMail = b.getUserName();
                                 }
                                 if (budlogin.getNumberOfMatches()>1){
                                 if (b.getUserName().trim().equals(budlogin.getStudent2ID().trim())) {
                                     bName1 = b.getFirstName() + " " + b.getLastName();
                                     bMail1 = b.getUserName();
                                 }
                                 }

                                 if (budlogin.getNumberOfMatches()>2){
                                     if (b.getUserName().trim().equals(budlogin.getStudent3ID().trim())) {
                                         bName2 = b.getFirstName() + " " + b.getLastName();
                                         bMail2 = b.getUserName();
                                     }

                                 }



                             }
                             BudName.setText("Student 1  Name: " + bName);
                             BudMail.setText("Student 1  Email " + bMail);

                             if (!bName1.trim().equals("")) {
                                 BudName1.setText("Student 2  Name: " + bName1);
                                 BudMail1.setText("Student 2 Email " + bMail1);
                             }

                             if (!bName2.trim().equals("")) {

                                 BudName2.setText("Student 3  Name: " + bName2);
                                 BudMail2.setText("Student 3 Email " + bMail2);
                             }




                             SharedPreferences.Editor Lo = sp.edit();
                             Lo.putString("StudentName", bName);
                             Lo.putString("StudentMail", bMail);
                             Lo.putString("StudentName1",bName1);
                             Lo.putString("StudentMail1",bMail1);
                             Lo.putString("StudentName2",bName2);
                             Lo.putString("StudentMail2",bMail2);
                             Lo.apply();
                             Lo.commit();

                         }
                     }

                     @Override
                     public void onFailure(Call<List<Student>> call, Throwable t) {

                     }
                 });



             }

             else {
                 if (isbud == 1) {
                     String Studentname = sp.getString("StudentName", "");
                     String Studentmail = sp.getString("StudentMail", "");
                     String Studentname1 = sp.getString("StudentName1", "");
                     String Studentmail1 = sp.getString("StudentMail1", "");
                     String Studentname2 = sp.getString("StudentName2", "");
                     String Studentmail2 = sp.getString("StudentMail2", "");

                     String Buddyname = sp.getString("BudName", "");
                     String Buddymail = sp.getString("BudMail", "");

                     String idd = String.valueOf(5000);

                     fullName.setText("Name: " + Buddyname);
                     email.setText("Email: " + Buddymail);
                     id.setText("User ID: " + idd);

                     BudName.setText("Student 1 Name: " + Studentname);
                     BudMail.setText("Student 1 Email: " + Studentmail);

                     if (!Studentname1.trim().equals("")) {
                         BudName1.setText("Student 2 Name: " + Studentname1);
                         BudMail1.setText("Student 2 Email: " + Studentmail1);
                     }
                     if (!Studentname2.trim().equals("")) {

                         BudName2.setText("Student 3 Name: " + Studentname2);
                         BudMail2.setText("Student 3 Email: " + Studentmail2);
                     }
                 }

             }


//            Log.d("ZZZZ", regBuddy.getFirstName());
//            Log.d("MMMM", regstudent.getFirstName());
             if (student != null && isbud == -1 ) {
                 Log.d("RECIEVEDSTUDENT", student.getFirstName());
                 String idd = String.valueOf(5000);
                 String name = student.getFirstName() + " " + student.getLastName();
                 String username = student.getUserName();
                 Ed.putString("StudentName",student.getFirstName() + " " + student.getLastName());
                 Ed.putString("StudentMail",student.getUserName());
                 Ed.apply();
                 Ed.commit();
                 fullName.setText("Name: " + name);
                 email.setText("Email: " + username);
                 id.setText("User ID: " + idd);

                 Call<List<Buddy>> call = retroAPI.getBuddies();
                 call.enqueue(new Callback<List<Buddy>>() {
                                  @Override
                                  public void onResponse(Call<List<Buddy>> call, Response<List<Buddy>> response) {
                                      buddies = response.body();
                                      if (student.getBuddy() != null) {
                                          String bName = "";
                                          String bMail = "";
                                          Log.d("KKKK",student.getBuddy().trim());
                                          for (Buddy b : buddies) {
                                              Log.d("BBBB",b.getUsername().trim());
                                              Log.d("CHECK",String.valueOf(b.getUsername().trim() == student.getBuddy().trim()));
                                              if (b.getUsername().trim().equals(student.getBuddy().trim())){
                                                  bName = b.getFirstName() + " " + b.getLastName();
                                                  bMail = b.getUsername();
                                              }

                                          }
                                          BudName.setText("Buddy Name: " + bName);
                                          BudMail.setText("Buddy Email: " + bMail);
                                          SharedPreferences.Editor Lo = sp.edit();
                                          Lo.putString("BudName",bName);
                                          Lo.putString("BudMail",bMail);
                                          Lo.apply();
                                          Lo.commit();
                                      }
                                      else {
                                          String buddyname = sp.getString("BudName","");
                                          String buddymail = sp.getString("BudMail","");
                                          String studentname = sp.getString("StudentName","");
                                          String studentmail = sp.getString("StudentMail","");
                                          email.setText("Email: " + studentmail);
                                          id.setText("User ID: " + String.valueOf(5000));
                                          fullName.setText("Name: " + studentname);
                                          BudName.setText("Buddy Name: " + buddyname);
                                          BudMail.setText("Buddy Email " + buddymail);


                                      }

                                  }


                     @Override
                     public void onFailure(Call<List<Buddy>> call, Throwable t) {
                     }
                 });

             }


             else {

                 if (isbud == -1) {
                     String buddyname = sp.getString("BudName", "");
                     String buddymail = sp.getString("BudMail", "");
                     String studentname = sp.getString("StudentName", "");
                     String studentmail = sp.getString("StudentMail", "");

//                     Log.d("RECIEVEDBUDSTUDENT",regBuddy.getFirstName());
                     String idd = String.valueOf(5000);
//                     String name = regstudent.getFirstName() + " " + regstudent.getLastName();
//                     String username = regstudent.getUserName();
                     fullName.setText("Name : " + studentname);
                     email.setText("Email: " + studentmail);
                     id.setText("User ID: " + idd);
//                         String budName = buddyname;
//
//                         String budEmail = buddymail;

                     BudName.setText("Buddy Name: " + buddyname);
                     BudMail.setText("Buddy Email " + buddymail);
                     BudName1.setText("");
                     BudMail1.setText("");
                     BudName2.setText("");
                     BudMail2.setText("");
                 }


             }



        }

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor Me = mPrefs.edit();
                SharedPreferences.Editor Le = sp.edit();
                Le.putString("BudName",null);
                Le.putString("BudMail",null);
                Le.putString("StudentName",null);
                Le.putString("StudentMail",null);
                Le.putString("StudentName1",null);
                Le.putString("StudentMail1",null);
                Le.putString("StudentName2",null);
                Le.putString("StudentMail2",null);
                Me.putInt("ISBUDDY",0);
                Le.apply();
                Le.commit();
                Me.commit();




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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(MyProfile.this, MainPage.class));
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
