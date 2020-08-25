package com.mkmcmxci.flow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Category;
import com.mkmcmxci.flow.interfaces.PassToActs;
import com.mkmcmxci.flow.tasks.QuestionSendTask;
import com.mkmcmxci.flow.ui.flow.MainFlowFragment;

import java.util.List;

public class PostQuestionActivity extends AppCompatActivity implements PassToActs {

    Spinner postQuestionSpinner;
    EditText postQuestionTitleText;
    EditText postQuestionContentText;
    List<Category> spinnerArray;
    int spinInt;
    static int userID;
    static String username, password, mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);

        postQuestionSpinner = findViewById(R.id.activity_post_question_spinner);
        postQuestionTitleText = findViewById(R.id.activity_post_question_spinner_title_edittext);
        postQuestionContentText = findViewById(R.id.activity_post_question_spinner_content_edittext);

        Category c = new Category();

        spinnerArray = c.getCategoryList();

        final ArrayAdapter<Category> spinnerAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_main_row, spinnerArray);

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_row);
        Spinner sItems = (Spinner) findViewById(R.id.activity_post_question_spinner);
        sItems.setAdapter(spinnerAdapter);

        postQuestionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinInt = spinnerArray.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

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

                QuestionSendTask questionSendTask = new QuestionSendTask();

                questionSendTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/addquestion/" +
                        postQuestionTitleText.getText().toString() + "/" +
                        postQuestionContentText.getText().toString() + "/" +
                        spinInt + "/" +
                        userID);


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("UserID",userID);
                i.putExtra("Username",username);
                i.putExtra("Password",password);
                i.putExtra("Mail",mail);


                startActivity(i);




            default:
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onPassToAct(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.password = password;
        this.mail = mail;


    }
}
