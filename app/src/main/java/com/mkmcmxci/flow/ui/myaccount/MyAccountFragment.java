package com.mkmcmxci.flow.ui.myaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.User;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.MyAccountTask;


import java.util.ArrayList;

public class MyAccountFragment extends Fragment {

    TextView mQuestionCount, mAnswerCount, mUsername;
    View mView;
    ArrayList<User> mUserList;
    TabItem mQuestionTab, mAnswerTab;
    String name, mail;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    MyAccountTask mTask;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_account, container, false);
        getViews();
        getTabs();
        init();

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setHasOptionsMenu(true);

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.profile_settings_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_settings_menu_item:

                Navigation.findNavController(mView).navigate(R.id.action_navigation_my_account_to_navigation_my_account_settings);

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void getViews() {

        mAnswerCount = mView.findViewById(R.id.fragment_my_account_answer_count_text_view);
        mUsername = mView.findViewById(R.id.fragment_my_account_username_textview);
        mQuestionCount = mView.findViewById(R.id.fragment_my_account_question_count_text_view);
        mQuestionTab = mView.findViewById(R.id.fragment_my_account_tab_one);
        mAnswerTab = mView.findViewById(R.id.fragment_my_account_tab_two);
        mViewPager = mView.findViewById(R.id.fragment_my_account_viewpager);
        mTabLayout = mView.findViewById(R.id.fragment_my_account_tab_layout);
    }

    private void init() {

        mUserList = new ArrayList<>();
        mTask = new MyAccountTask(getContext(), mUserList, mQuestionCount, mAnswerCount, mUsername, name, mail);
        mTask.execute(Services.getUserByID(SessionManagement.loadUserID()));

    }

    private void getTabs() {
        mTabLayout.setupWithViewPager(mViewPager);
        MyAccountPagerAdapter adapter = new MyAccountPagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
    }


}
