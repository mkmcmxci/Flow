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

    TabLayout flowFragmentTabLayout;
    TabItem flowFragmentTabItemOne, flowFragmentTabItemTwo;
    ViewPager flowFragmentViewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_flow, container, false);

        flowFragmentTabLayout = root.findViewById(R.id.fragment_flow_tab_layout);
        flowFragmentTabItemOne = root.findViewById(R.id.fragment_flow_tab_one);
        flowFragmentTabItemTwo = root.findViewById(R.id.fragment_flow_tab_two);
        flowFragmentViewPager = root.findViewById(R.id.fragment_flow_viewpager);

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), flowFragmentTabLayout.getTabCount());

        flowFragmentViewPager.setAdapter(adapter);

        flowFragmentViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        flowFragmentTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                flowFragmentViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }


}