package com.mkmcmxci.flow.ui.login;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.tasks.UserRegisterTask;

import org.w3c.dom.Text;

public class SignUpFragment extends Fragment {

    Button signUpFragmentRegisterButton;
    TextView signUpFragmentUsernameTextView;
    TextView signUpFragmentMailTextView;
    TextView signUpFragmentPasswordTextView;
    CheckBox signUpFragmentCheckBox;
    View signUpFragmentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signUpFragmentView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        signUpFragmentRegisterButton = signUpFragmentView.findViewById(R.id.fragment_sign_up_signup_button);
        signUpFragmentUsernameTextView = signUpFragmentView.findViewById(R.id.fragment_sign_up_username_input);
        signUpFragmentMailTextView = signUpFragmentView.findViewById(R.id.fragment_sign_up_email_input);
        signUpFragmentPasswordTextView = signUpFragmentView.findViewById(R.id.fragment_sign_up_password_input);
        signUpFragmentCheckBox = signUpFragmentView.findViewById(R.id.fragment_sign_up_signup_check);


        signUpFragmentRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                registerUser();

            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Register");

        return signUpFragmentView;
    }

    public void registerUser() {

        if (signUpFragmentCheckBox.isChecked()) {

            UserRegisterTask urt = new UserRegisterTask();

            urt.execute("http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/registeruser/" +
                    signUpFragmentMailTextView.getText().toString() + "/" +
                    signUpFragmentUsernameTextView.getText().toString() + "/" +
                    signUpFragmentPasswordTextView.getText().toString());

            Navigation.findNavController(signUpFragmentView).navigate(R.id.action_nav_sign_up_to_nav_sign_in);

        } else {

            Toast.makeText(getContext(), "Please Check", Toast.LENGTH_SHORT).show();

        }

    }

}
