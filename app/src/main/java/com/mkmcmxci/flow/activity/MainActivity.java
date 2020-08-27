package com.mkmcmxci.flow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.ui.search.SearchFragment;
import com.mkmcmxci.flow.ui.flow.AnswerFragment;
import com.mkmcmxci.flow.ui.flow.AnswerSendFragment;
import com.mkmcmxci.flow.ui.flow.LastAnsweredFragment;
import com.mkmcmxci.flow.ui.flow.MainFlowFragment;
import com.mkmcmxci.flow.ui.message.MessageFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountAnswerFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountQuestionFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountSettingsFragment;
import com.mkmcmxci.flow.ui.showprofile.ShowProfileAnswerFragment;

public class MainActivity extends AppCompatActivity {

    int userID;
    String username, mail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_flow, R.id.navigation_find, R.id.navigation_message, R.id.navigation_my_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SessionManagement sm = new SessionManagement(MainActivity.this);

        username = sm.loadUsername();
        mail = sm.loadMail();
        password = sm.loadPassword();

        /*
        Intent intentFromLogin = getIntent();

        if (intentFromLogin.hasExtra("UserID")) {
            userID = intentFromLogin.getExtras().getInt("UserID");
            name = intentFromLogin.getExtras().getString("Username");
            mail = intentFromLogin.getExtras().getString("Mail");
            password = intentFromLogin.getExtras().getString("Password");

        }
        */



        Intent intentFromPostQuestion = getIntent();

        if (intentFromPostQuestion.hasExtra("UserID")) {
            userID = intentFromPostQuestion.getExtras().getInt("UserID");
            username = intentFromPostQuestion.getExtras().getString("Username");
            mail = intentFromPostQuestion.getExtras().getString("Mail");
            password = intentFromPostQuestion.getExtras().getString("Password");
        }





        /*
        Intent intentFromSession = getIntent();

        if (intentFromSession.hasExtra("UserID")) {
            userID = intentFromSession.getExtras().getInt("UserID");
            username = intentFromSession.getExtras().getString("Username");
            mail = intentFromSession.getExtras().getString("Mail");
            password = intentFromSession.getExtras().getString("Password");
        }

         */


    }


}