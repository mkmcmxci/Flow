package com.mkmcmxci.flow.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.MainActivity;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.tasks.UserSignInTask;

public class SignInFragment extends Fragment {

    TextView mForgotPassword, mRegister;
    Button mLoginButton;
    EditText mEmail, mPassword;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        getViews();

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_sign_in_to_nav_forgot_password);

            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_sign_in_to_nav_sign_up);

            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserSignInTask ust = new UserSignInTask(getContext());
                ust.execute(Services.getUserByMailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()));


            }
        });

        return mView;
    }

    public void getViews() {
        mForgotPassword = mView.findViewById(R.id.fragment_sign_in_forgot_password);
        mRegister = mView.findViewById(R.id.fragment_sign_in_register);
        mLoginButton = mView.findViewById(R.id.fragment_sign_in_login_button);
        mEmail = mView.findViewById(R.id.fragment_sign_in_email_input);
        mPassword = mView.findViewById(R.id.fragment_sign_in_password_input);
    }
}
