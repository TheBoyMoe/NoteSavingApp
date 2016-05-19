package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;
import com.example.demo.custom.CustomItemDecoration;
import com.example.demo.custom.realm.NotesAdapter;
import com.example.demo.custom.realm.RealmNoteAdapter;
import com.example.demo.model.Note;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmNoteListFragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private Realm mRealm;

    public RealmNoteListFragment() {}

    public static RealmNoteListFragment newInstance() {
        RealmNoteListFragment fragment = new RealmNoteListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new CustomItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_spacer)));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new NotesAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter); // sets empty adapter
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // RealmNoteAdapter requires - context, realmResults, boolean (do you want auto update)
        RealmResults<Note> results = mRealm.where(Note.class).findAllSorted("mId", Sort.DESCENDING);
        RealmNoteAdapter realmAdapter = new RealmNoteAdapter(getActivity(), results, true);
        mAdapter.setRealmAdapter(realmAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        // TODO handle onItemClick


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
