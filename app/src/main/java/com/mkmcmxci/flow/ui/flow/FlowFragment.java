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

    TabLayout tabLayout;
    TabItem tabItem1;
    TabItem tabItem2;
    ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_flow, container, false);

        tabLayout = root.findViewById(R.id.fragment_flow_tab);
        tabItem1 = root.findViewById(R.id.tab_one);
        tabItem2 = root.findViewById(R.id.tab_two);
        viewPager = root.findViewById(R.id.viewpager);

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 return true;
             }
         });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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