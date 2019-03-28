package com.example.karthik.mvp.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.ApplozicClient;
import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.applozic.mobicomkit.api.account.user.User;
import com.applozic.mobicomkit.listners.AlLoginHandler;
import com.applozic.mobicomkit.uiwidgets.conversation.ConversationUIService;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;
import com.example.karthik.mvp.R;

public class Messaging extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

      Applozic.init(getApplicationContext(), "bristol1531aa8e1d36eade3217f60d8c8b6cbc");

          User user = new User();
        //  final Student student = (Student)getIntent().getSerializableExtra("messagingData");
        //     user.setUserId(student.getUserName());
        //     user.setDisplayName(student.getFirstName() + " " + student.getLastName());
        user.setUserId("ks17226");
        user.setDisplayName("KARTHIK SRIDHAR");
        user.setAuthenticationTypeId(User.AuthenticationType.APPLOZIC.getValue());

        Applozic.connectUser(getApplicationContext(), user, new AlLoginHandler() {

            @Override
            public void onSuccess(RegistrationResponse registrationResponse, Context context) {
                ApplozicClient.getInstance(context).setContextBasedChat(true);
            }

             @Override
             public void onFailure(RegistrationResponse registrationResponse, Exception exception) {
                 // If any failure in registration the callback  will come here
             }
         });

           if (Applozic.isConnected(getApplicationContext())) {
               Toast.makeText(getApplicationContext(), "User logged in", Toast.LENGTH_LONG).show();
         }

           Intent i = new Intent(getApplicationContext(), ConversationActivity.class);
        //              i.putExtra(ConversationUIService.USER_ID, student.getUserName());
        //              i.putExtra(ConversationUIService.DISPLAY_NAME, student.getFirstName());
         i.putExtra(ConversationUIService.USER_ID, "ks17226");
         i.putExtra(ConversationUIService.DISPLAY_NAME, "KARTHIK");
         i.putExtra(ConversationUIService.TAKE_ORDER, true);
        startActivity(i);


    }
}
