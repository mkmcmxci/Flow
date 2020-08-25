package com.mkmcmxci.flow.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.sharedpreferences.Validation;
import com.mkmcmxci.flow.tasks.UserRegisterTask;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {

    Button mButton;
    TextView mUsername, mMail, mPassword;
    CheckBox mCheckBox;
    View mView;
    final String URL = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/registeruser/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        getViews();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        setHasOptionsMenu(true);

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getActivity().onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void getViews() {
        mButton = mView.findViewById(R.id.fragment_sign_up_signup_button);
        mUsername = mView.findViewById(R.id.fragment_sign_up_username_input);
        mMail = mView.findViewById(R.id.fragment_sign_up_email_input);
        mPassword = mView.findViewById(R.id.fragment_sign_up_password_input);
        mCheckBox = mView.findViewById(R.id.fragment_sign_up_signup_check);
    }


    public void registerUser() {

        if (!mCheckBox.isChecked()) {

            Toast.makeText(getContext(), "Please Check Terms", Toast.LENGTH_SHORT).show();

        } else if (!Validation.isValidPassword(mPassword.getText().toString())) {

            Toast.makeText(getContext(), "Not Valid Password", Toast.LENGTH_SHORT).show();

        } else if (!Validation.isValidEmail(mMail.getText().toString())) {

            Toast.makeText(getContext(), "Not Valid E-mail", Toast.LENGTH_SHORT).show();

        } else {

            UserRegisterTask task = new UserRegisterTask();

            task.execute(URL + mMail.getText().toString() + "/" + mUsername.getText().toString() + "/" +
                    mPassword.getText().toString());

            Navigation.findNavController(mView).navigate(R.id.action_nav_sign_up_to_nav_sign_in);

        }

    }


}
