package com.example.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class LoggingActivity extends AppCompatActivity{

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("%s onCreate()", LOG_TAG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i("%s onStart()", LOG_TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.i("%s onResume()", LOG_TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.i("%s onPause()", LOG_TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.i("%s onStop()", LOG_TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("%s onDestroy()", LOG_TAG);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Timber.i("%s onBackPressed()", LOG_TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.i("%s onRestart()", LOG_TAG);
    }


}
