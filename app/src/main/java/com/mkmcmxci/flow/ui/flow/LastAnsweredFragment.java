package com.mkmcmxci.flow.ui.flow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.PostQuestionActivity;
import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.interfaces.PassToActs;
import com.mkmcmxci.flow.interfaces.PassToFrags;
import com.mkmcmxci.flow.tasks.LastAnsweredTask;
import com.mkmcmxci.flow.tasks.MainFlowTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LastAnsweredFragment extends Fragment implements PassToFrags {

    RecyclerView lastAnsweredRecView;
    LastAnsweredAdapter lastAnsweredAdapter;
    List<Question> lastAnsweredQuestionList;
    LastAnsweredTask lastAnsweredTask;
    FloatingActionButton lastAnsweredFloatingActionButton;
    static int userID;
    static String username, password, mail;
    SwipeRefreshLayout lastAnsweredSwipeRefresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_last_answered, container, false);

        lastAnsweredRecView = v.findViewById(R.id.fragment_last_answered_recycler_view);

        lastAnsweredFloatingActionButton = v.findViewById(R.id.fragment_last_answered_floating_button);

        lastAnsweredSwipeRefresh =  v.findViewById(R.id.fragment_last_answered_swipe_refresh);

        lastAnsweredQuestionList = new ArrayList<>();

        lastAnsweredAdapter = new LastAnsweredAdapter(getContext(), lastAnsweredQuestionList);

        lastAnsweredRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        lastAnsweredRecView.setAdapter(lastAnsweredAdapter);

        lastAnsweredTask = new LastAnsweredTask(getContext(), lastAnsweredAdapter, lastAnsweredQuestionList);

        lastAnsweredTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getquestionsbyanswer/");

        lastAnsweredFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(i);
            }
        });

        lastAnsweredSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                lastAnsweredQuestionList = new ArrayList<>();

                lastAnsweredAdapter = new LastAnsweredAdapter(getContext(), lastAnsweredQuestionList);

                lastAnsweredRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

                lastAnsweredRecView.setAdapter(lastAnsweredAdapter);

                lastAnsweredTask = new LastAnsweredTask(getContext(), lastAnsweredAdapter, lastAnsweredQuestionList);

                lastAnsweredTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getquestionsbyanswer/");

                lastAnsweredSwipeRefresh.setRefreshing(false);
            }
        });

        return v;
    }


    @Override
    public void onPassToFrags(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.password = password;
        this.mail = mail;
    }
}
