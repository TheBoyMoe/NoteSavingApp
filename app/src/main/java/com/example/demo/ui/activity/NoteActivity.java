package com.example.demo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;

import com.example.demo.R;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class NoteActivity extends BaseActivity{

    protected Realm mRealm;
    protected RealmAsyncTask mTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove the appbar's elevation
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
            if (appbar != null)
                appbar.setElevation(0.0f);
        }

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mTransaction != null && !mTransaction.isCancelled())
            mTransaction.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }


}
