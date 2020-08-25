package com.mkmcmxci.flow.ui.flow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mkmcmxci.flow.R;

public class FlowFragment extends Fragment {

    TabLayout mTabLayout;
    TabItem mTabItemOne, mTabItemTwo;
    ViewPager mViewPager;
    View mView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_flow, container, false);
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

        return mView;
    }

    public void getViews() {
        mTabLayout = mView.findViewById(R.id.fragment_flow_tab_layout);
        mTabItemOne = mView.findViewById(R.id.fragment_flow_tab_one);
        mTabItemTwo = mView.findViewById(R.id.fragment_flow_tab_two);
        mViewPager = mView.findViewById(R.id.fragment_flow_viewpager);
    }

    public void getTabs(){
        mTabLayout.setupWithViewPager(mViewPager);
        FlowPagerAdapter adapter = new FlowPagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
    }


}