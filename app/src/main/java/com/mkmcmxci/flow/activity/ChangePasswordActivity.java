package com.mkmcmxci.flow.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.interfaces.PassToActs;
import com.mkmcmxci.flow.sharedpreferences.Validation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChangePasswordActivity extends AppCompatActivity implements PassToActs {

    EditText mCurrent, mNew, mConfirm;
    static int userID;

    static String name, mail, password;
    private static final String mURL = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/updateuserPassword/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void getViews() {

        mCurrent = findViewById(R.id.activity_change_password_current_password);
        mNew = findViewById(R.id.activity_change_password_new_password);
        mConfirm = findViewById(R.id.activity_change_password_confirm_new_password);

    }

    @Override
    public void onPassToAct(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.name = name;
        this.mail = mail;
        this.password = password;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.settings_save_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            case R.id.answer_save_menu_item:

                if (item.getItemId() != android.R.id.home && !mCurrent.getText().toString().equals(password)) {

                    Toast.makeText(ChangePasswordActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();

                } else if (!mNew.getText().toString().equals("") && !Validation.isValidPassword(mNew.getText().toString())) {

                    Toast.makeText(ChangePasswordActivity.this, "Enter Valid Password", Toast.LENGTH_SHORT).show();

                } else if (!mNew.getText().toString().equals(mConfirm.getText().toString())) {

                    Toast.makeText(ChangePasswordActivity.this, "Re-Enter New Password", Toast.LENGTH_SHORT).show();

                } else {

                    ChangePasswordTask changePasswordTask = new ChangePasswordTask();
                    changePasswordTask.execute(mURL + userID + "/" + mNew.getText().toString());
                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                }

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public class ChangePasswordTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {

            String urlString = strings[0];

            StringBuilder buffer = new StringBuilder();

            try {
                URL url = new URL(urlString);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = "";

                while ((line = bf.readLine()) != null) {

                    buffer.append(line);

                }

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return buffer.toString();

        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

}
