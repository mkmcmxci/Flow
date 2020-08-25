package com.mkmcmxci.flow.ui.myaccount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAccountPagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;
    FragmentManager mFragmentManager;

    public MyAccountPagerAdapter(@NonNull FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
        this.mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new MyAccountQuestionFragment();


            case 1:

                return new MyAccountAnswerFragment();
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

        }
    }
}
