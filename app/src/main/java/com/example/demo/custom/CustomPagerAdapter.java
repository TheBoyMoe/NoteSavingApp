package com.example.demo.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.demo.ui.fragment.InspirationFragment;
import com.example.demo.ui.fragment.PlaceHolderFragment;

public class CustomPagerAdapter extends FragmentPagerAdapter{

    private final int INSPIRATION_FRAGMENT = 0;
    private final int GOAL_FRAGMENT = 1;
    private final int TASK_FRAGMENT = 2;
    private final int CALENDAR_FRAGMENT = 3;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // instantiate the appropriate fragment
        switch (position) {
            case INSPIRATION_FRAGMENT:
                return InspirationFragment.newInstance();
            case GOAL_FRAGMENT:
            case TASK_FRAGMENT:
            case CALENDAR_FRAGMENT:
                return PlaceHolderFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null; // show only tab icon
    }

    @Override
    public int getCount() {
        return 4; // show 4 pages
    }


}
