package com.mkmcmxci.flow.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.listeners.PassToActivitiesListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.sharedpreferences.Validation;
import com.mkmcmxci.flow.tasks.PostDataTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText mCurrent, mNew, mConfirm;
    int userID;
    String username, mail, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getViews();
        getSession();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void getSession() {
        userID = SessionManagement.loadUserID();
        username = SessionManagement.loadUsername();
        mail = SessionManagement.loadMail();
        password = SessionManagement.loadPassword();
    }

    public void getViews() {
        mCurrent = findViewById(R.id.activity_change_password_current_password);
        mNew = findViewById(R.id.activity_change_password_new_password);
        mConfirm = findViewById(R.id.activity_change_password_confirm_new_password);
    }

    /*
    @Override
    public void onPassToActivities(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.mail = mail;
        this.password = password;
    }

     */

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

                    PostDataTask changePasswordTask = new PostDataTask();
                    changePasswordTask.execute(Services.updateUserPassword(userID, mNew.getText().toString()));
                    SessionManagement.savePassword(mNew.getText().toString());

                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                }

            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
