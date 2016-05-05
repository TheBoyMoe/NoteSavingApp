package com.example.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.ui.fragment.TextNoteFragment;

public class TextNoteActivity extends AppCompatActivity{

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TextNoteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_note);

        // add the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (toolbar != null)
                toolbar.setNavigationIcon(Utils.tintDrawable(ContextCompat
                        .getDrawable(this, R.drawable.action_back), R.color.colorIcon));
        }

        // remove the appbar's elevation
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
            if (appbar != null)
                appbar.setElevation(0.0f);
        }

        // instantiate TextNoteFragment
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, TextNoteFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
