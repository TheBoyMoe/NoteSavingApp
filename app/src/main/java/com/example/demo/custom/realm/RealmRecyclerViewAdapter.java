package com.example.demo.custom.realm;

import android.support.v7.widget.RecyclerView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;

/**
 * Wrapper class, sets the realm object as the data source of the recycler view adapter
 * @param <T>
 */
public abstract class RealmRecyclerViewAdapter<T extends RealmObject> extends RecyclerView.Adapter{

    private RealmBaseAdapter<T> mRealmBaseAdapter;

    public T getItem(int position) {
        return mRealmBaseAdapter.getItem(position);
    }

    public RealmBaseAdapter<T> getRealmAdapter() {
        return mRealmBaseAdapter;
    }

    public void setRealmAdapter(RealmBaseAdapter<T> realmAdapter) {
        mRealmBaseAdapter = realmAdapter;
    }

}
