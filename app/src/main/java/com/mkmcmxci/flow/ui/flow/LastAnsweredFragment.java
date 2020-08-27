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
import com.mkmcmxci.flow.listeners.PassToFragmentsListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.tasks.LastAnsweredTask;

import java.util.ArrayList;
import java.util.List;

public class LastAnsweredFragment extends Fragment {

    RecyclerView mRecView;
    LastAnsweredAdapter mAdapter;
    List<Question> mQuestionList;
    LastAnsweredTask mTask;
    FloatingActionButton mFab;
    //static int userID;
    //static String username, password, mail;
    SwipeRefreshLayout mSwipeRefresh;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_last_answered, container, false);
        getViews();
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
        mAdapter = new LastAnsweredAdapter(getContext(), mQuestionList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        mTask = new LastAnsweredTask(getContext(), mAdapter, mQuestionList);
        mTask.execute(Services.getQuestionByAnswer());
    }

    /*
    @Override
    public void onPassToFragments(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.password = password;
        this.mail = mail;
    }
*/
    private void getViews() {
        mRecView = mView.findViewById(R.id.fragment_last_answered_recycler_view);
        mFab = mView.findViewById(R.id.fragment_last_answered_floating_button);
        mSwipeRefresh = mView.findViewById(R.id.fragment_last_answered_swipe_refresh);
    }
}
