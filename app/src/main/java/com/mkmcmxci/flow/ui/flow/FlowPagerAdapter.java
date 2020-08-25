package com.mkmcmxci.flow.ui.flow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

public class FlowPagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public FlowPagerAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        super(fm);

        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MainFlowFragment();
            case 1:
                return new LastAnsweredFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Posts";
            case 1:
                return "Last Replies";
            default:
                return null;

        }    }
}
