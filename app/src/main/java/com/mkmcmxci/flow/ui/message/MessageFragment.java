package com.mkmcmxci.flow.ui.message;

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

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Conversation;
import com.mkmcmxci.flow.entities.Message;
import com.mkmcmxci.flow.interfaces.PassToFrags;
import com.mkmcmxci.flow.tasks.MainFlowTask;
import com.mkmcmxci.flow.tasks.MessageTask;
import com.mkmcmxci.flow.ui.flow.MainFlowAdapter;

import java.util.ArrayList;

public class MessageFragment extends Fragment implements PassToFrags {

    RecyclerView mRecyclerView;
    MessageAdapter mAdapter;
    ArrayList<Conversation> mMessageList;
    MessageTask mTask;
    SwipeRefreshLayout mSwipeRefresh;
    static int userID;
    static String username, password, mail;
    View mView;
    final String URL = "http://10.0.2.2:8080/BulletinBoard/rest/messagewebservices/getconversation/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_message, container, false);
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

        mRecyclerView = mView.findViewById(R.id.fragment_message_recyclerView);
        mSwipeRefresh =  mView.findViewById(R.id.fragment_message_swipe_refresh);

    }

    public void init(){

        mMessageList = new ArrayList<>();
        mAdapter = new MessageAdapter(getContext(), mMessageList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mTask = new MessageTask(getContext(), mAdapter, mMessageList);
        mTask.execute(URL + userID);



    }

    @Override
    public void onPassToFrags(int userID, String name, String mail, String password) {

        this.userID = userID;
        this.username = name;
        this.password = password;
        this.mail = mail;


    }
}
