package com.mkmcmxci.flow.ui.find;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Category;

import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView mRecView;
    Category mCategory;
    List<Category> mCategoryList;
    FindAdapter mAdapter;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        getViews();
        init();
        return mView;
    }

    public void getViews(){
        mRecView = mView.findViewById(R.id.fragment_category_rec_view);
    }

    public void init(){
        mCategory = new Category();
        mCategoryList = mCategory.getCategoryList();
        mAdapter = new FindAdapter(getContext(), mCategoryList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
    }




}
