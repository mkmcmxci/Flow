package com.mkmcmxci.flow.ui.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.ChangePasswordActivity;
import com.mkmcmxci.flow.activity.LoginActivity;
import com.mkmcmxci.flow.interfaces.PassToFrags;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.QuestionSendTask;

public class MyAccountSettingsFragment extends Fragment implements PassToFrags {

    EditText mUsername, mEmail;
    TextView mChangePassword, mLogOut;
    View mView;
    static int userID;
    static String name, mail, password;
    final String URL = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/updateuser/";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile_settings, container, false);
        getViews();

        mUsername.setText(name);
        mEmail.setText(mail);

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(i);

            }
        });

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SessionManagement sm = new SessionManagement(getContext());
                sm.removeSession();

                Intent i = new Intent(getContext(), LoginActivity.class);

                startActivity(i);
            }
        });


        setHasOptionsMenu(true);

        return mView;
    }

    public void getViews() {
        mUsername = mView.findViewById(R.id.fragment_my_profile_settings_username_edittext);
        mEmail = mView.findViewById(R.id.fragment_my_profile_settings_mail_edittext);
        mChangePassword = mView.findViewById(R.id.fragment_my_profile_settings_change_password_textview);
        mLogOut = mView.findViewById(R.id.fragment_my_profile_settings_logout_textview);
    }

    @Override
    public void onPassToFrags(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.name = name;
        this.mail = mail;
        this.password = password;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.settings_save_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.answer_save_menu_item) {
            QuestionSendTask task = new QuestionSendTask();
            task.execute(URL + userID + "/" + mEmail.getText().toString() + "/" + mUsername.getText().toString());

            Navigation.findNavController(mView).navigate(R.id.action_navigation_my_account_settings_to_navigation_my_account);


        } else {
            getActivity().onBackPressed();

        }

        return super.onOptionsItemSelected(item);
    }
}
