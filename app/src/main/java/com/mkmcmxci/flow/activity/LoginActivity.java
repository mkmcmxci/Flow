package com.mkmcmxci.flow.activity;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container_login);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }


    @Override
    protected void onStart() {
        super.onStart();

        SessionManagement sm = new SessionManagement(LoginActivity.this);
        /*

        String username = sm.loadUsername();
        String mail = sm.loadMail();
        String password = sm.loadPassword();
        int userID = sm.loadUserID();
*/
        if (sm.loadSession() != -1) {

            Intent i = new Intent(LoginActivity.this, MainActivity.class);

            /*
            i.putExtra("UserID", userID);
            i.putExtra("Username", username);
            i.putExtra("Mail", mail);
            i.putExtra("Password", password);
*/
            startActivity(i);
        }
    }
}
