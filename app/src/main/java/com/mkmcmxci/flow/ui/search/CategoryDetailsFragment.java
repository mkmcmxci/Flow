package com.mkmcmxci.flow.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.PostQuestionActivity;
import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.tasks.CategoryDetailsTask;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsFragment extends Fragment {

    RecyclerView mRecView;
    CategoryDetailsAdapter mAdapter;
    List<Question> mQuestionList;
    CategoryDetailsTask mTask;
    String catID, catName;
    FloatingActionButton mFab;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category_details, container, false);

        catID = getArguments().getString("catID");
        catName = getArguments().getString("catName");

        getViews();
        init();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(i);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(catName);


        setHasOptionsMenu(true);

        return mView;

    }

    public void getViews() {
        mRecView = mView.findViewById(R.id.fragment_category_details_recycler_view);
        mFab = mView.findViewById(R.id.fragment_category_details_floating_button);
    }

    public void init() {
        mQuestionList = new ArrayList<>();
        mAdapter = new CategoryDetailsAdapter(getContext(), mQuestionList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        mTask = new CategoryDetailsTask(getContext(), mAdapter, mQuestionList);
        mTask.execute(Services.questionByCategory(Integer.parseInt(catID)));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getActivity().onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}
