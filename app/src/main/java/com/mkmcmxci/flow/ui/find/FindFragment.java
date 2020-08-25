package com.mkmcmxci.flow.ui.find;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.interfaces.PassToFrags;

public class FindFragment extends Fragment implements PassToFrags {

    SearchView mSearchView;
    FindResultAdapter mAdapter;
    View mView;
    static int userID;
    static String username, password, mail;

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find, container, false);
        getViews();

        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setIconified(false);

            }
        });

        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_find_frame_view, new CategoryFragment()).commit();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.equals("")) {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_find_frame_view, new CategoryFragment()).commit();

                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_find_frame_view, new FindResultFragment(mAdapter, newText)).commit();

                }

                return false;
            }
        });

        return mView;
    }

    public void getViews() {
        mSearchView = mView.findViewById(R.id.fragment_find_search_view);

    }

    @Override
    public void onPassToFrags(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.mail = mail;
        this.password = password;
    }

}