package com.mkmcmxci.flow.ui.showprofile;

import android.content.Intent;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mkmcmxci.flow.R;

import com.mkmcmxci.flow.activity.SendMessageActivity;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;

public class ShowProfileFragment extends Fragment {

    TabLayout mTabLayout;
    TabItem mQuestionTab, mAnswerTab;
    ViewPager mViewPager;
    View mView;

    TextView mQuestionCount, mAnswerCount, mUsername;
    int userID, userAnswerSize, userQuestionSize;
    String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_show_profile, container, false);
        getArgs();
        getViews();
        getTabs();




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

        mQuestionCount.setText(String.valueOf(userQuestionSize));
        mAnswerCount.setText(String.valueOf(userAnswerSize));
        mUsername.setText(username);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(username);

        setHasOptionsMenu(true);

        return mView;


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        SessionManagement session = new SessionManagement(getContext());
        if(userID != session.loadUserID()){
            inflater.inflate(R.menu.message_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.message_menu_item) {

            Intent intent = new Intent(getContext(), SendMessageActivity.class);
            intent.putExtra("Username",username);
            intent.putExtra("UserID",userID);
            startActivity(intent);


        } else {

        getActivity().onBackPressed();


        }

        return super.onOptionsItemSelected(item);


    }


    public void getViews() {
        mUsername = mView.findViewById(R.id.activity_show_profile_username_textview);
        mQuestionCount = mView.findViewById(R.id.activity_show_profile_question_count_text_view);
        mAnswerCount = mView.findViewById(R.id.activity_show_profile_answer_count_text_view);
        mQuestionTab = mView.findViewById(R.id.activity_show_profile_tab_one);
        mAnswerTab = mView.findViewById(R.id.activity_show_profile_tab_two);
        mViewPager = mView.findViewById(R.id.activity_show_profile_viewpager);
        mTabLayout = mView.findViewById(R.id.activity_show_profile_tab_layout);
    }

    public void getArgs() {
        userID = getArguments().getInt("UserID");
        userAnswerSize = getArguments().getInt("UserAnswerSize");
        userQuestionSize = getArguments().getInt("UserQuestionSize");
        username = getArguments().getString("Username");
    }


    public void getTabs(){
        mTabLayout.setupWithViewPager(mViewPager);
        ShowProfilePagerAdapter adapter = new ShowProfilePagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);




    }
}
