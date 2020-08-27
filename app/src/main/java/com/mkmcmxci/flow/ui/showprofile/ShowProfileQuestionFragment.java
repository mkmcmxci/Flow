package com.mkmcmxci.flow.ui.showprofile;

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
import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.listeners.ShowProfileListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.ShowProfileQuestionTask;

import java.util.ArrayList;

public class ShowProfileQuestionFragment extends Fragment {

    RecyclerView mRecView;
    ShowProfileQuestionFragmentAdapter mAdapter;
    ArrayList<Question> mQuestionList;
    ShowProfileQuestionTask mQuestionTask;
    SwipeRefreshLayout mSwipeRefresh;
    View mView;
    //static int userID;
   // static String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_account_question, container, false);
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

    public void getViews() {

        mRecView = mView.findViewById(R.id.fragment_my_account_question_recycler_view);
        mSwipeRefresh = mView.findViewById(R.id.fragment_my_account_question_swipe_refresh);

    }

    private void init() {
        mQuestionList = new ArrayList<>();
        mAdapter = new ShowProfileQuestionFragmentAdapter(getContext(), mQuestionList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        mQuestionTask = new ShowProfileQuestionTask(getContext(), mAdapter, mQuestionList);
        mQuestionTask.execute(Services.questionByUser(SessionManagement.loadUserID()));
    }

    /*
    @Override
    public void onShowProfile(int userID, String name) {
        this.userID = userID;
        this.username = name;
    }

     */
}
