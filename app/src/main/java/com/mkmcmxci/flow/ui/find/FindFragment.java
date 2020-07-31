package com.mkmcmxci.flow.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Category;


import java.util.List;

public class FindFragment extends Fragment {

    SearchView findFragmentSearchView;
    RecyclerView findFragmentRecView;
    FindAdapter findFragmentFindAdapter;
    List<Category> findFragmentCategoryList;
    Category findFragmentCategory;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find, container, false);

        findFragmentSearchView = v.findViewById(R.id.fragment_find_search_view);

        findFragmentSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findFragmentSearchView.setIconified(false);
            }
        });

        findFragmentRecView = v.findViewById(R.id.fragment_find_recycler_view);

        findFragmentCategory =  new Category();

        findFragmentCategoryList = findFragmentCategory.getCategoryList();

        findFragmentFindAdapter = new FindAdapter(getContext(), findFragmentCategoryList);

        findFragmentRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        findFragmentRecView.setAdapter(findFragmentFindAdapter);

        return v;
    }
}