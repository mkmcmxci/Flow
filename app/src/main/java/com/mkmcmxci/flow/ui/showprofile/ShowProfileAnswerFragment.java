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
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.listeners.PassToFragmentsListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.ShowProfileAnswerTask;

import java.util.ArrayList;

public class ShowProfileAnswerFragment extends Fragment {

    RecyclerView mAnswerRecView;
    ShowProfileAnswerFragmentAdapter mAnswerAdapter;
    ArrayList<Answer> mAnswerList;
    ShowProfileAnswerTask mAnswerTask;
    SwipeRefreshLayout mSwipeRefresh;
    View mAnswerView;
    int userID;
    SessionManagement session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAnswerView = inflater.inflate(R.layout.fragment_my_account_answer, container, false);
        getViews();
        getSession();
        init();

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                init();
                mSwipeRefresh.setRefreshing(false);
            }
        });

        return mAnswerView;

    }

    /*
    @Override
    public void onPassToFragments(int userID, String name, String mail, String password) {
        this.userID = userID;

    }

     */

    public void getViews() {

        mAnswerRecView = mAnswerView.findViewById(R.id.fragment_my_account_answer_recycler_view);
        mSwipeRefresh = mAnswerView.findViewById(R.id.fragment_my_account_answer_swipe_refresh);

    }

    public void init() {

        mAnswerList = new ArrayList<>();
        mAnswerAdapter = new ShowProfileAnswerFragmentAdapter(getContext(), mAnswerList);
        mAnswerRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAnswerRecView.setAdapter(mAnswerAdapter);
        mAnswerTask = new ShowProfileAnswerTask(getContext(), mAnswerAdapter, mAnswerList);
        mAnswerTask.execute(Services.answerByUser(userID));

    }

    private void getSession() {
        session = new SessionManagement(getContext());
        userID = session.loadUserID();


    }
}
