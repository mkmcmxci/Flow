package com.mkmcmxci.flow.ui.flow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.PostQuestionActivity;
import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.MainFlowTask;

import java.util.ArrayList;
import java.util.List;

public class MainFlowFragment extends Fragment {

    RecyclerView mRecView;
    MainFlowAdapter mAdapter;
    List<Question> mQuestionList;
    FloatingActionButton mFab;
    MainFlowTask mTask;
    SwipeRefreshLayout mSwipeRefresh;
    int userID;
    String username, password, mail;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_flow, container, false);
        getViews();
        getSession();


        init();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(intent);

            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                init();

                mSwipeRefresh.setRefreshing(false);
            }
        });

        return mView;
    }

    private void init() {
        mQuestionList = new ArrayList<>();
        mAdapter = new MainFlowAdapter(getContext(), mQuestionList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        mTask = new MainFlowTask(getContext(), mAdapter, mQuestionList);
        mTask.execute(Services.getAllQuestions());
    }

    private void getViews() {
        mRecView = mView.findViewById(R.id.fragment_main_flow_recycler_view);
        mSwipeRefresh = mView.findViewById(R.id.fragment_main_flow_swipe_refresh);
        mFab = mView.findViewById(R.id.fragment_main_flow_floating_button);
    }

    private void getSession() {
        userID = SessionManagement.loadUserID();
        username = SessionManagement.loadUsername();
        password = SessionManagement.loadPassword();
        mail = SessionManagement.loadMail();

    }

}
