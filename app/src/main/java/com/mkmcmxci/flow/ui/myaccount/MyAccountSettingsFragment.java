package com.mkmcmxci.flow.ui.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.mkmcmxci.flow.listeners.PassToFragmentsListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.PostDataTask;

public class MyAccountSettingsFragment extends Fragment {

    EditText mUsername, mEmail;
    TextView mChangePassword, mLogOut;
    View mView;
    //static int userID;
    //static String name, mail, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile_settings, container, false);
        getViews();

        mUsername.setText(SessionManagement.loadUsername());
        mEmail.setText(SessionManagement.loadMail());

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SessionManagement session = new SessionManagement(getContext());
                session.removeSession();

                Intent intent = new Intent(getContext(), LoginActivity.class);

                startActivity(intent);
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

    /*
    @Override
    public void onPassToFragments(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.name = name;
        this.mail = mail;
        this.password = password;

    }


     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.settings_save_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.answer_save_menu_item) {
            PostDataTask task = new PostDataTask();
            task.execute(Services.updateUser(SessionManagement.loadUserID(), mEmail.getText().toString(), mUsername.getText().toString()));
            SessionManagement.saveMail(mEmail.getText().toString());
            SessionManagement.saveUsername(mUsername.getText().toString());

            Navigation.findNavController(mView).navigate(R.id.action_navigation_my_account_settings_to_navigation_my_account);


        } else {
            getActivity().onBackPressed();

        }

        return super.onOptionsItemSelected(item);
    }
}
