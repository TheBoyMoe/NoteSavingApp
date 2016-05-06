package com.example.demo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;

import com.example.demo.R;

public class NoteActivity extends BaseActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove the appbar's elevation
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
            if (appbar != null)
                appbar.setElevation(0.0f);
        }

    }

}
