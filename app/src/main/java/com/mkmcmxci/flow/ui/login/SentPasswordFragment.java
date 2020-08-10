package com.mkmcmxci.flow.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;

public class SentPasswordFragment extends Fragment {

    Button sendPasswordFragmentSendButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sent_password, container, false);

        sendPasswordFragmentSendButton = v.findViewById(R.id.fragment_sent_password_back_login_button);

        sendPasswordFragmentSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Navigation.findNavController(v).navigate(R.id.action_nav_sent_password_to_nav_sign_in);
            }
        });



        return v;
    }
}
