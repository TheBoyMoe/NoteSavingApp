package com.example.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.R;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class NoteActivity extends AppCompatActivity{

    protected Realm mRealm;
    protected RealmAsyncTask mTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_fragment_container);
        // remove the appbar's elevation
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
//            if (appbar != null)
//                appbar.setElevation(0.0f);
//        }

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mTransaction != null && !mTransaction.isCancelled())
            mTransaction.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }


}
