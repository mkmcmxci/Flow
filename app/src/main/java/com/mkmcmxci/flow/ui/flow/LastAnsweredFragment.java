package com.mkmcmxci.flow.ui.flow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mkmcmxci.bottomnav.R;

public class LastAnsweredFragment extends Fragment {

    public static final String TAG = "LastAnswered";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_last_answered,container,false);


        return v;
    }
}