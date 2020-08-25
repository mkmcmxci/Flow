package com.mkmcmxci.flow.ui.showprofile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ShowProfilePagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public ShowProfilePagerAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        super(fm);

        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ShowProfileQuestionFragment();

            case 1:

                return new ShowProfileAnswerFragment();
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

                return "Replies";
            default:
                return null;

        }    }
}

