package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.demo.R;
import com.example.demo.common.Utils;

public class NoteFragment extends BaseFragment{


    public NoteFragment() {}

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if (actionBar != null) {
                // hide title by default
                actionBar.setDisplayShowTitleEnabled(false);
                // set navigation icon and color
                toolbar.setNavigationIcon(Utils.tintDrawable(ContextCompat
                        .getDrawable(getActivity(), R.drawable.action_back), R.color.colorIcon));
                // set title text color
                toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            }
        }
    }


}
