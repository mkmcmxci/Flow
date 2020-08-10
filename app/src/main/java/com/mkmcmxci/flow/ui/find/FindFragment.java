package com.mkmcmxci.flow.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mkmcmxci.flow.R;

public class FindFragment extends Fragment {

    SearchView findFragmentSearchView;

    FindResultAdapter searchAdapter;


    View v;


    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_find, container, false);



        findFragmentSearchView = v.findViewById(R.id.fragment_find_search_view);

        findFragmentSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findFragmentSearchView.setIconified(false);

            }
        });

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_find_frame_view, new CategoryFragment()).commit();






        findFragmentSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.equals("")){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_find_frame_view, new CategoryFragment()).commit();




                }
                else{
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_find_frame_view, new FindResultFragment(searchAdapter, newText)).commit();




                }





                return false;
            }
        });


        return v;
    }
}