package com.mkmcmxci.flow.ui.myaccount;

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

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.listeners.PassToFragmentsListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.MyAccountAnswerTask;

import java.util.ArrayList;

public class MyAccountAnswerFragment extends Fragment {

    RecyclerView mRecView;
    MyAccountAnswerAdapter mAdapter;
    ArrayList<Answer> mAnswerList;
    MyAccountAnswerTask mTask;
    SwipeRefreshLayout mSwipeRefresh;
    View mView;
    //static int userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_account_answer, container, false);
        getViews();
        init();

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                init();
                mSwipeRefresh.setRefreshing(false);
            }
        });

    return mView;

    }
/*
    @Override
    public void onPassToFragments(int userID, String name, String mail, String password) {
        this.userID = userID;

    }

 */

    public void getViews() {
        mRecView = mView.findViewById(R.id.fragment_my_account_answer_recycler_view);
        mSwipeRefresh =  mView.findViewById(R.id.fragment_my_account_answer_swipe_refresh);
    }

    public void init(){
        mAnswerList = new ArrayList<>();
        mAdapter = new MyAccountAnswerAdapter(getContext(), mAnswerList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        mTask = new MyAccountAnswerTask(getContext(), mAdapter, mAnswerList);
        mTask.execute(Services.answerByUser(SessionManagement.loadUserID()));
    }
}
