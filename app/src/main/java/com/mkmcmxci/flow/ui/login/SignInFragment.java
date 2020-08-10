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
import com.mkmcmxci.flow.tasks.UserSignInTask;

public class SignInFragment extends Fragment {

    TextView signInFragmentWithoutSignInTextView;
    TextView signInFragmentForgotPasswordTextView;
    TextView signInFragmentRegisterTextView;
    Button signInFragmentLoginButton;
    EditText signInFragmentEmail;
    EditText signInFragmentPassword;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);

        signInFragmentWithoutSignInTextView = v.findViewById(R.id.fragment_sign_in_without_signing);
        signInFragmentForgotPasswordTextView = v.findViewById(R.id.fragment_sign_in_forgot_password);
        signInFragmentRegisterTextView = v.findViewById(R.id.fragment_sign_in_register);
        signInFragmentLoginButton = v.findViewById(R.id.fragment_sign_in_login_button);
        signInFragmentEmail = v.findViewById(R.id.fragment_sign_in_email_input);
        signInFragmentPassword = v.findViewById(R.id.fragment_sign_in_password_input);

        signInFragmentWithoutSignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        signInFragmentForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_sign_in_to_nav_forgot_password);


            }
        });

        signInFragmentRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_sign_in_to_nav_sign_up);



            }
        });

        signInFragmentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserSignInTask ust = new UserSignInTask(getContext());

                ust.execute("http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/getuserbymailandpassword/" +
                        signInFragmentEmail.getText().toString() + "/" +
                        signInFragmentPassword.getText().toString());






            }
        });


        return v;
    }
}
