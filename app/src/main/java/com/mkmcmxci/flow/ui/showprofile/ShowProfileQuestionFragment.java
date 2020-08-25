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
import com.mkmcmxci.flow.interfaces.ShowProfileListener;
import com.mkmcmxci.flow.tasks.ShowProfileQuestionTask;

import java.util.ArrayList;

public class ShowProfileQuestionFragment extends Fragment implements ShowProfileListener {

    RecyclerView showProfileQuestionRecView;
    ShowProfileQuestionFragmentAdapter showProfileQuestionAdapter;
    ArrayList<Question> showProfileQuestionQuestionList;
    ShowProfileQuestionTask showProfileQuestionTask;
    SwipeRefreshLayout showProfileQuestionSwipeRefresh;
    View showProfileQuestionView;
    static int userID;
    static String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showProfileQuestionView = inflater.inflate(R.layout.fragment_my_account_question, container, false);
        getViews();

        showProfileQuestionQuestionList = new ArrayList<>();

        showProfileQuestionAdapter = new ShowProfileQuestionFragmentAdapter(getContext(), showProfileQuestionQuestionList);

        showProfileQuestionRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showProfileQuestionRecView.setAdapter(showProfileQuestionAdapter);

        showProfileQuestionTask = new ShowProfileQuestionTask(getContext(), showProfileQuestionAdapter, showProfileQuestionQuestionList);

        showProfileQuestionTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/questionsbyuser/" + userID );


        showProfileQuestionSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                showProfileQuestionQuestionList = new ArrayList<>();

                showProfileQuestionTask = new ShowProfileQuestionTask(getContext(), showProfileQuestionAdapter, showProfileQuestionQuestionList);

                showProfileQuestionTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/questionsbyuser/" + userID );

                showProfileQuestionSwipeRefresh.setRefreshing(false);
            }
        });

        return showProfileQuestionView;
    }

    public void getViews() {

        showProfileQuestionRecView = showProfileQuestionView.findViewById(R.id.fragment_my_account_question_recycler_view);
        showProfileQuestionSwipeRefresh =  showProfileQuestionView.findViewById(R.id.fragment_my_account_question_swipe_refresh);

    }




    @Override
    public void showProfileListener(int userID, String name) {
        this.userID = userID;
        this.username = name;

    }
}
