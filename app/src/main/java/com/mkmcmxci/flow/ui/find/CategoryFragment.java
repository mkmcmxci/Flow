package com.mkmcmxci.flow.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Category;

import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView categoryFragmentRecView;
    Category categoryFragmentCategory;
    List<Category> categoryFragmentCategoryList;
    FindAdapter findFragmentFindAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);


        categoryFragmentRecView = v.findViewById(R.id.fragment_category_rec_view);

        categoryFragmentCategory = new Category();

        categoryFragmentCategoryList = categoryFragmentCategory.getCategoryList();

        findFragmentFindAdapter = new FindAdapter(getContext(), categoryFragmentCategoryList);

        categoryFragmentRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryFragmentRecView.setAdapter(findFragmentFindAdapter);

        return v;
    }
}
