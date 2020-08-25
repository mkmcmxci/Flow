package com.mkmcmxci.flow.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mkmcmxci.flow.R;

public class ForgotPasswordFragment extends Fragment {

    Button mSendButton;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mSendButton = mView.findViewById(R.id.fragment_forgot_password_sent_button);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_forgot_password_to_nav_sent_password);
            }
        });

        setHasOptionsMenu(true);

        return mView;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            getActivity().onBackPressed();

        return super.onOptionsItemSelected(item);

    }

}
