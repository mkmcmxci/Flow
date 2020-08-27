package com.mkmcmxci.flow.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Message;
import com.mkmcmxci.flow.listeners.PassToActivitiesListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.PostDataTask;
import com.mkmcmxci.flow.tasks.SendMessageTask;

import java.util.ArrayList;

public class SendMessageActivity extends AppCompatActivity {

    EditText mMessageContent;
    ImageView mSendButton;
    RecyclerView mMessageRecView;
    SendMessageAdapter mMessageAdapter;
    ArrayList<Message> mMessageList;
    SendMessageTask mMessageTask;
    String username;
    int receiverID;
    //static int userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_send_message);

        username = getIntent().getExtras().getString("Username");
        receiverID = getIntent().getExtras().getInt("UserID");

        getViews();
        init();

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(username);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDataTask task = new PostDataTask();
                task.execute(Services.addConversation(SessionManagement.loadUserID(), receiverID, mMessageContent.getText().toString()));

                init();
                mMessageContent.setText("");

                closeKeyboard();
            }
        });

    }

    private void init() {

        mMessageList = new ArrayList<>();
        mMessageAdapter = new SendMessageAdapter(this, mMessageList, SessionManagement.loadUserID());
        LinearLayoutManager lManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lManager.setStackFromEnd(true);
        mMessageRecView.setLayoutManager(lManager);
        mMessageRecView.setAdapter(mMessageAdapter);
        mMessageTask = new SendMessageTask(this, mMessageList, mMessageAdapter);
        mMessageTask.execute(Services.getMessages(SessionManagement.loadUserID(), receiverID));


    }

    public void getViews() {
        mSendButton = findViewById(R.id.fragment_send_message_button);
        mMessageContent = findViewById(R.id.fragment_send_message_edit_text);
        mMessageRecView = findViewById(R.id.fragment_send_message_recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public void onPassToActivities(int userID, String name, String mail, String password) {
        this.userID = userID;
    }

     */

    private void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
