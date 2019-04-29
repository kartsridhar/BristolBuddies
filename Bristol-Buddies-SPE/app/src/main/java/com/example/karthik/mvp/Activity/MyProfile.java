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
                    finish();
                    break;
                case R.id.messaging:
                    break;
                case R.id.myProfile:
                    break;
            }
            return false;
        }
    };

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

        final SharedPreferences sp = getSharedPreferences("Buddy", MODE_PRIVATE);
        SharedPreferences.Editor Ed = sp.edit();

        final SharedPreferences mPrefs = getSharedPreferences("CHECK", 0);
        int isbud = mPrefs.getInt("ISBUDDY", 0);

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

        if (deets != null) {
            String name = deets.getDisplayName();
            String idd = deets.getId();
            String mail = deets.getEmail();
            Uri dp = deets.getPhotoUrl();

            Glide.with(this).load(dp).into(pic);
            fullName.setText("Name: " + name);
            email.setText("Email: " + mail);
            id.setText("User ID: " + idd);
        } else {
            final Student student = (Student) getIntent().getSerializableExtra("serialize_data3");
            final Student regstudent = (Student) getIntent().getSerializableExtra("student");

            Buddy regBuddy = (Buddy) getIntent().getSerializableExtra("buddy");
            Buddy NEWBuddy = (Buddy) getIntent().getSerializableExtra("REGbuddy");

            final Buddy budlogin = (Buddy) getIntent().getSerializableExtra("budlogin");

            if (regBuddy != null && regstudent != null) {
                form.setText("Your Student Account");
                Ed.putString("BudName", regBuddy.getFirstName() + " " + regBuddy.getLastName());
                Ed.putString("BudMail", regBuddy.getUsername());
                Ed.putString("StudentName", regstudent.getFirstName() + " " + regstudent.getLastName());
                Ed.putString("StudentMail", regstudent.getUserName());
                Ed.apply();
                Ed.commit();
                SharedPreferences.Editor Me = mPrefs.edit();
                Me.putInt("ISBUDDY", -1);
                Me.commit();
            }


            if (NEWBuddy != null) {
                Toast.makeText(getApplicationContext(), "Buddy Account saved", Toast.LENGTH_LONG);
                form.setText("Your Buddy Account");
                Ed.putString("BudName", NEWBuddy.getFirstName() + " " + NEWBuddy.getLastName());
                Ed.putString("BudMail", NEWBuddy.getUsername());
                Ed.apply();
                Ed.commit();
                SharedPreferences.Editor Be = mPrefs.edit();
                Be.putInt("ISBUDDY", 1);
                Be.commit();

            }

            int isbud1 = mPrefs.getInt("ISBUDDY", 0);

            if (budlogin != null && isbud == 1) {
                form.setText("Your Buddy Account");
                String idd = String.valueOf(5000);
                String name = budlogin.getFirstName() + " " + budlogin.getLastName();
                String username = budlogin.getUsername();
                Ed.putString("BudName", budlogin.getFirstName() + " " + budlogin.getLastName());
                Ed.putString("BudMail", budlogin.getUsername());
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
                                if (budlogin.getNumberOfMatches() > 1) {
                                    if (b.getUserName().trim().equals(budlogin.getStudent2ID().trim())) {
                                        bName1 = b.getFirstName() + " " + b.getLastName();
                                        bMail1 = b.getUserName();
                                    }
                                }

                                if (budlogin.getNumberOfMatches() > 2) {
                                    if (b.getUserName().trim().equals(budlogin.getStudent3ID().trim())) {
                                        bName2 = b.getFirstName() + " " + b.getLastName();
                                        bMail2 = b.getUserName();
                                    }

                                }
                            }
                            BudName.setText("Student 1  Name: " + bName);
                            BudMail.setText("Student 1  Email: " + bMail);

                            if (!bName1.trim().equals("")) {
                                BudName1.setText("Student 2  Name: " + bName1);
                                BudMail1.setText("Student 2 Email: " + bMail1);
                            }

                            if (!bName2.trim().equals("")) {

                                BudName2.setText("Student 3  Name: " + bName2);
                                BudMail2.setText("Student 3 Email: " + bMail2);
                            }


                            SharedPreferences.Editor Lo = sp.edit();
                            Lo.putString("StudentName", bName);
                            Lo.putString("StudentMail", bMail);
                            Lo.putString("StudentName1", bName1);
                            Lo.putString("StudentMail1", bMail1);
                            Lo.putString("StudentName2", bName2);
                            Lo.putString("StudentMail2", bMail2);
                            Lo.apply();
                            Lo.commit();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {

                    }
                });


            } else {
                if (isbud1 == 1) {
                    form.setText("Your Buddy Account");

                    String Buddyname = sp.getString("BudName", "");
                    final String Buddymail = sp.getString("BudMail", "");
                    String Studentname = sp.getString("StudentName", "");
                    final String Studentmail = sp.getString("StudentMail", "");
                    final String Studentname1 = sp.getString("StudentName1", "");
                    final String Studentmail1 = sp.getString("StudentMail1", "");
                    String Studentname2 = sp.getString("StudentName2", "");
                    final String Studentmail2 = sp.getString("StudentMail2", "");

                    final Call<List<Buddy>> call3 = retroAPI.getBuddies();


                    call3.enqueue(new Callback<List<Buddy>>() {

                        public void onResponse(Call<List<Buddy>> call3, Response<List<Buddy>> response) {
                            buddies = response.body();
                            final Call<List<Student>> call4 = retroAPI.getStudents();
                            call4.enqueue(new Callback<List<Student>>() {
                                @Override
                                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                                    students = response.body();
                                    for (Buddy a : buddies) {
                                        if (a.getUsername().trim().equals(Buddymail.trim())) {
                                            if (!a.getStudent1ID().trim().equals(Studentmail.trim())) {
                                                for (Student w : students) {
                                                    if (w.getUserName().trim().equals(a.getStudent1ID().trim())) {
                                                        String stuname1 = w.getFirstName() + " " + w.getLastName();
                                                        String stumail1 = w.getUserName();
                                                        SharedPreferences.Editor Lw = sp.edit();
                                                        Lw.putString("StudentName", stuname1);
                                                        Lw.putString("StudentMail", stumail1);
                                                        Lw.commit();

                                                    }

                                                }
                                            }
                                            if (!a.getStudent2ID().trim().equals(Studentmail1.trim())) {
                                                for (Student w : students) {
                                                    if (w.getUserName().trim().equals(a.getStudent2ID().trim())) {
                                                        String stuname2 = w.getFirstName() + " " + w.getLastName();
                                                        String stumail2 = w.getUserName();
                                                        SharedPreferences.Editor Lw = sp.edit();
                                                        Lw.putString("StudentName1", stuname2);
                                                        Lw.putString("StudentMail1", stumail2);
                                                        Lw.commit();

                                                    }

                                                }
                                            }
                                            if (!a.getStudent3ID().trim().equals(Studentmail2.trim())) {
                                                for (Student w : students) {
                                                    if (w.getUserName().trim().equals(a.getStudent3ID().trim())) {
                                                        String stuname3 = w.getFirstName() + " " + w.getLastName();
                                                        String stumail3 = w.getUserName();
                                                        SharedPreferences.Editor Lw = sp.edit();
                                                        Lw.putString("StudentName2", stuname3);
                                                        Lw.putString("StudentMail2", stumail3);
                                                        Lw.commit();

                                                    }

                                                }
                                            }
                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Student>> call, Throwable t) {

                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<List<Buddy>> call3, Throwable t) {


                        }
                    });

                    String studentname = sp.getString("StudentName", "");
                    String studentmail = sp.getString("StudentMail", "");
                    String studentname1 = sp.getString("StudentName1", "");
                    String studentmail1 = sp.getString("StudentMail1", "");
                    String studentname2 = sp.getString("StudentName2", "");
                    String studentmail2 = sp.getString("StudentMail2", "");

                    String idd = String.valueOf(5000);

                    fullName.setText("Name: " + Buddyname);
                    email.setText("Email: " + Buddymail);
                    id.setText("User ID: " + idd);

                    BudName.setText("Student 1 Name: " + studentname);
                    BudMail.setText("Student 1 Email: " + studentmail);

                    if (!studentname1.trim().equals("")) {
                        BudName1.setText("Student 2 Name: " + studentname1);
                        BudMail1.setText("Student 2 Email: " + studentmail1);
                    }
                    if (!studentname2.trim().equals("")) {

                        BudName2.setText("Student 3 Name: " + studentname2);
                        BudMail2.setText("Student 3 Email: " + studentmail2);
                    }
                }

            }

            if (student != null && isbud == -1) {
                form.setText("Your Student Account");
                String idd = String.valueOf(5000);
                String name = student.getFirstName() + " " + student.getLastName();
                String username = student.getUserName();
                Ed.putString("StudentName", student.getFirstName() + " " + student.getLastName());
                Ed.putString("StudentMail", student.getUserName());
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
                            for (Buddy b : buddies) {
                                if (b.getUsername().trim().equals(student.getBuddy().trim())) {
                                    bName = b.getFirstName() + " " + b.getLastName();
                                    bMail = b.getUsername();
                                }

                            }
                            BudName.setText("Buddy Name: " + bName);
                            BudMail.setText("Buddy Email: " + bMail);
                            SharedPreferences.Editor Lo = sp.edit();
                            Lo.putString("BudName", bName);
                            Lo.putString("BudMail", bMail);
                            Lo.apply();
                            Lo.commit();
                        } else {
                            form.setText("Your Student Account");

                            String buddyname = sp.getString("BudName", "");
                            String buddymail = sp.getString("BudMail", "");
                            String studentname = sp.getString("StudentName", "");
                            String studentmail = sp.getString("StudentMail", "");
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

            } else {
                if (isbud1 == -1) {
                    form.setText("Your Student Account");
                    String buddyname = sp.getString("BudName", "");
                    final String buddymail = sp.getString("BudMail", "");
                    String studentname = sp.getString("StudentName", "");
                    final String studentmail = sp.getString("StudentMail", "");

                    final Call<List<Student>> call5 = retroAPI.getStudents();

                    call5.enqueue(new Callback<List<Student>>() {

                        public void onResponse(Call<List<Student>> call5, Response<List<Student>> response) {
                            students = response.body();
                            final Call<List<Buddy>> call6 = retroAPI.getBuddies();
                            call6.enqueue(new Callback<List<Buddy>>() {
                                @Override
                                public void onResponse(Call<List<Buddy>> call6, Response<List<Buddy>> response) {
                                    buddies = response.body();
                                    for (Student a : students) {
                                        if (a.getUserName().trim().equals(studentmail.trim())) {
                                            if (!a.getBuddy().trim().equals(buddymail.trim())) {
                                                for (Buddy w : buddies) {
                                                    if (w.getUsername().trim().equals(a.getBuddy().trim())) {
                                                        String budname1 = w.getFirstName() + " " + w.getLastName();
                                                        String budmail1 = w.getUsername();
                                                        SharedPreferences.Editor Lw = sp.edit();
                                                        Lw.putString("BudName", budname1);
                                                        Lw.putString("BudMail", budmail1);
                                                        Lw.commit();

                                                    }

                                                }
                                            }


                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Buddy>> call6, Throwable t) {

                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<List<Student>> call5, Throwable t) {


                        }
                    });

                    String budyname = sp.getString("BudName", "");
                    final String budymail = sp.getString("BudMail", "");
                    String studntname = sp.getString("StudentName", "");
                    final String studntmail = sp.getString("StudentMail", "");

                    String idd = String.valueOf(5000);
                    fullName.setText("Name : " + studntname);
                    email.setText("Email: " + studntmail);
                    id.setText("User ID: " + idd);
                    BudName.setText("Buddy Name: " + budyname);
                    BudMail.setText("Buddy Email " + budymail);
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
                Le.putString("BudName", null);
                Le.putString("BudMail", null);
                Le.putString("StudentName", null);
                Le.putString("StudentMail", null);
                Le.putString("StudentName1", null);
                Le.putString("StudentMail1", null);
                Le.putString("StudentName2", null);
                Le.putString("StudentMail2", null);
                Me.putInt("ISBUDDY", 0);
                Le.apply();
                Le.commit();
                Me.commit();


                signOut();
            }
        });
        getBuddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int isbud = mPrefs.getInt("ISBUDDY", 0);

                if (isbud == -1) {
                    final String studentmail = sp.getString("StudentMail", "");


                    final Call<List<Student>> cal = retroAPI.getStudents();


                    cal.enqueue(new Callback<List<Student>>() {


                        public void onResponse(Call<List<Student>> cal, Response<List<Student>> response) {
                            students = response.body();
                            for (Student s : students) {
                                if (s.getUserName().trim().equals(studentmail)) {

                                    final Student userStu = s;
                                    Intent k = new Intent(getApplicationContext(), showMatch.class);
                                    k.putExtra("User", userStu);
                                    startActivity(k);

                                }


                            }


                        }

                        @Override
                        public void onFailure(Call<List<Student>> call, Throwable t) {

                        }
                    });

                } else if (isbud == 1) {
                    String buddyname = sp.getString("BudName", "");
                    String buddymail = sp.getString("BudName", "");
                    String studentname1 = sp.getString("StudentName1", "");
                    String studentmail1 = sp.getString("StudentMail1", "");
                    String studentname2 = sp.getString("StudentName2", "");
                    String studentmail2 = sp.getString("StudentMail2", "");
                    Intent i = new Intent(getApplicationContext(), showMatch.class);
                    i.putExtra("Buddy", buddymail);
                    startActivity(i);

                }


            }


        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(MyProfile.this, MainPage.class));
        finish();
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
