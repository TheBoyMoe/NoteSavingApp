package com.example.demo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.custom.CustomPagerAdapter;

public class TabActivity extends AppCompatActivity {

    private int[] mTabIcons = {
            R.drawable.tab_icon_inspiration,
            R.drawable.tab_icon_goal,
            R.drawable.tab_icon_tasks,
            R.drawable.tab_icon_calendar
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // add the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each section
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        if (viewPager != null) {
            viewPager.setAdapter(pagerAdapter);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            if (tabLayout != null) {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons(tabLayout);
            }
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


    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons(TabLayout tabLayout) {
        // tint icons and add to the tab
        tabLayout.getTabAt(0).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[0]), Color.WHITE));
        tabLayout.getTabAt(1).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[1]), Color.WHITE));
        tabLayout.getTabAt(2).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[2]), Color.WHITE));
        tabLayout.getTabAt(3).setIcon(Utils.tintDrawable(ContextCompat.getDrawable(this, mTabIcons[3]), Color.WHITE));
    }


}
