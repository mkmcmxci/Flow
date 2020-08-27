package com.mkmcmxci.flow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Category;
import com.mkmcmxci.flow.listeners.PassToActivitiesListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.PostDataTask;

import java.util.List;

public class PostQuestionActivity extends AppCompatActivity {

    Spinner mSpinner, mItemSpinner;
    EditText mTitle,mContent;
    List<Category> spinnerArray;
    Category mCategory;
    int spinValue,userID;
    SessionManagement session;
    String username, password, mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        getViews();
        session = new SessionManagement(this);
        userID = session.loadUserID();
        username = session.loadUsername();
        mail = session.loadMail();
        password = session.loadPassword();

        fillSpinner();
        showKeyboard();


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinValue = spinnerArray.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        mTitle.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(140) });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    private void getViews() {
        mSpinner = findViewById(R.id.activity_post_question_spinner);
        mTitle = findViewById(R.id.activity_post_question_spinner_title_edittext);
        mContent = findViewById(R.id.fragment_answer_send_reply);
    }

    private void fillSpinner() {
        mCategory = new Category();
        spinnerArray = mCategory.getCategoryList();
        final ArrayAdapter<Category> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_main_row, spinnerArray);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_row);
        mItemSpinner = findViewById(R.id.activity_post_question_spinner);
        mItemSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_add_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.question_add_menu_item:

                PostDataTask task = new PostDataTask();
                task.execute(Services.addQuestion(mTitle.getText().toString(), mContent.getText().toString(), spinValue, userID));

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("UserID", userID);
                intent.putExtra("Username", username);
                intent.putExtra("Password", password);
                intent.putExtra("Mail", mail);
                startActivity(intent);

            default:
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return super.onOptionsItemSelected(item);

        }

    }

    private void showKeyboard() {
        mTitle.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeKeyboard();

    }

    private void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
