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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Timber.i("onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.i("onViewCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i("onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.i("onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.i("onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.i("onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Timber.i("onDetach()");
    }

}
