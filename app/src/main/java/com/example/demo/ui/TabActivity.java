package com.example.demo.ui;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.common.Utils;

public class TabActivity extends AppCompatActivity {

    private int[] mTabIcons = {
            R.drawable.tab_icon_inspiration,
            R.drawable.tab_icon_goal,
            R.drawable.tab_icon_tasks,
            R.drawable.tab_icon_calendar
    };


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /** The {@link ViewPager} that will host the section contents.  */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (mViewPager != null) {
            mViewPager.setAdapter(mSectionsPagerAdapter);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            if (tabLayout != null) {
                tabLayout.setupWithViewPager(mViewPager);
                setupTabIcons(tabLayout);
            }
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings
                || super.onOptionsItemSelected(item);
    }

    /** A placeholder fragment containing a simple view.  */
    public static class PlaceholderFragment extends Fragment {

        /** The fragment argument representing the section number for this fragment. */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() { }

        /** Returns a new instance of this fragment for the given section number.  */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            textView.setText(getString(R.string.dummy_text));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // show only the tab icon
//            switch (position) {
//                case 0:
//                    return "Inspiration";
//                case 1:
//                    return "Goals";
//                case 2:
//                    return "Tasks";
//                case 3:
//                    return "Calendar";
//            }
            return null;
        }
    }


    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons(TabLayout tabLayout) {
        // tint icons and add to the tab
        tabLayout.getTabAt(0).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[0]), Color.WHITE));
        tabLayout.getTabAt(1).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[1]), Color.WHITE));
        tabLayout.getTabAt(2).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[2]), Color.WHITE));
        tabLayout.getTabAt(3).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[3]), Color.WHITE));
    }

}
