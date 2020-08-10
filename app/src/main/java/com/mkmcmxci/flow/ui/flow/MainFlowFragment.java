package com.mkmcmxci.flow.ui.flow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.mkmcmxci.flow.ListContainer.MainFlowListContainer;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.PostQuestionActivity;
import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.tasks.MainFlowTask;

import java.util.ArrayList;
import java.util.List;

public class MainFlowFragment extends Fragment {

    RecyclerView mainFlowRecView;
    MainFlowAdapter mainFlowAdapter;
    ArrayList<Question> mainFlowQuestionList;
    FloatingActionButton mainFlowFloatingActionButton;
    MainFlowTask mainFlowTask;
    SwipeRefreshLayout mainFlowSwipeRefresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_flow, container, false);

        mainFlowRecView = v.findViewById(R.id.fragment_main_flow_recycler_view);

        mainFlowSwipeRefresh =  v.findViewById(R.id.fragment_main_flow_swipe_refresh);
        
        mainFlowFloatingActionButton = v.findViewById(R.id.fragment_main_flow_floating_button);

        mainFlowQuestionList = new ArrayList<>();

        mainFlowAdapter = new MainFlowAdapter(getContext(), mainFlowQuestionList);

        mainFlowRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mainFlowRecView.setAdapter(mainFlowAdapter);

        mainFlowTask = new MainFlowTask(getContext(), mainFlowAdapter, mainFlowQuestionList);

        mainFlowTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getallquestions");

        mainFlowFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(i);

            }
        });

        mainFlowSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mainFlowTask = new MainFlowTask(getContext(), mainFlowAdapter, mainFlowQuestionList);

                mainFlowTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getallquestions");

                mainFlowSwipeRefresh.setRefreshing(false);
            }
        });

        return v;
    }


}
