package com.mkmcmxci.flow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.interfaces.BottomListener;
import com.mkmcmxci.flow.ui.find.FindFragment;
import com.mkmcmxci.flow.ui.flow.AnswerFragment;
import com.mkmcmxci.flow.ui.flow.LastAnsweredFragment;
import com.mkmcmxci.flow.ui.flow.MainFlowFragment;
import com.mkmcmxci.flow.ui.message.MessageFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountAnswerFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountQuestionFragment;
import com.mkmcmxci.flow.ui.myaccount.MyAccountSettingsFragment;
import com.mkmcmxci.flow.ui.showprofile.ShowProfileAnswerFragment;

public class MainActivity extends AppCompatActivity  {

    int userID;
    String name, mail, password;
    static BottomSheetBehavior a, b;

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

        Intent intentFromLogin = getIntent();

        if (intentFromLogin.hasExtra("UserID")) {
            userID = intentFromLogin.getExtras().getInt("UserID");
            name = intentFromLogin.getExtras().getString("Username");
            mail = intentFromLogin.getExtras().getString("Mail");
            password = intentFromLogin.getExtras().getString("Password");

        }

        Intent intentFromPostQuestion = getIntent();

        if (intentFromPostQuestion.hasExtra("UserID")) {
            userID = intentFromPostQuestion.getExtras().getInt("UserID");
            name = intentFromPostQuestion.getExtras().getString("Username");
            mail = intentFromPostQuestion.getExtras().getString("Mail");
            password = intentFromPostQuestion.getExtras().getString("Password");
        }

        Intent intentFromSession = getIntent();

        if (intentFromSession.hasExtra("UserID")) {
            userID = intentFromSession.getExtras().getInt("UserID");
            name = intentFromSession.getExtras().getString("Username");
            mail = intentFromSession.getExtras().getString("Mail");
            password = intentFromSession.getExtras().getString("Password");
        }


        MainFlowFragment mff = new MainFlowFragment();
        LastAnsweredFragment laf = new LastAnsweredFragment();
        AnswerFragment af = new AnswerFragment();
        FindFragment ff = new FindFragment();
        MyAccountFragment maf = new MyAccountFragment();
        MyAccountQuestionFragment maqf = new MyAccountQuestionFragment();
        MyAccountAnswerFragment maaf = new MyAccountAnswerFragment();
        MyAccountSettingsFragment psf = new MyAccountSettingsFragment();
        ChangePasswordActivity cpa = new ChangePasswordActivity();
        ShowProfileAnswerFragment spqf = new ShowProfileAnswerFragment();
        MessageFragment mf = new MessageFragment();
        SendMessageActivity sma = new SendMessageActivity();

        mff.onPassToFrags(userID, name, mail, password);
        laf.onPassToFrags(userID, name, mail, password);
        af.onPassToFrags(userID, name, mail, password);
        ff.onPassToFrags(userID, name, mail, password);
        maf.onPassToFrags(userID, name, mail, password);
        maqf.onPassToFrags(userID, name, mail, password);
        maaf.onPassToFrags(userID, name, mail, password);
        psf.onPassToFrags(userID, name, mail, password);
        cpa.onPassToAct(userID, name, mail, password);
        spqf.onPassToFrags(userID, name, mail, password);
        mf.onPassToFrags(userID, name, mail, password);
        sma.onPassToAct(userID, name, mail, password);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}