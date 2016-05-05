package com.example.demo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import timber.log.Timber;

// Log fragment life cycles
public class LoggingFragment extends BaseFragment{

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Timber.i("%s onAttach()", LOG_TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("%s onCreate()", LOG_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("%s onCreateView()", LOG_TAG);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.i("%s onViewCreated()", LOG_TAG);
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
    public void onDetach() {
        super.onDetach();
        Timber.i("%s onDetach()", LOG_TAG);
    }

}
