package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.custom.CustomItemDecoration;
import com.example.demo.custom.CustomRealmViewAdapter;
import com.example.demo.model.Note;
import com.example.demo.ui.activity.QuotationNoteActivity;
import com.example.demo.ui.activity.VideoNoteActivity;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class NoteListFragment extends BaseFragment implements View.OnClickListener{

    private int[] mToolbarIcons = {
            R.drawable.action_note,
            R.drawable.action_video,
            R.drawable.action_photo,
            R.drawable.action_audio
    };
    private Realm mRealm;
    private CustomRealmViewAdapter mAdapter;
    private RealmResults<Note> mResults;
    private RealmChangeListener mCallback = new RealmChangeListener() {
        @Override
        public void onChange() {
            // update ui
            mAdapter.updateRealmResults(mResults);
        }
    };
    private RealmRecyclerView mRecyclerView;

    public NoteListFragment() {}

    public static NoteListFragment newInstance() {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        setupFooterToolbarButtons(view);
        mRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);
        mRecyclerView.addItemDecoration(new CustomItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_spacer)));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // set the query and a callback whenever the query has completed and every time the realm is updated
        mResults = mRealm.where(Note.class).findAllAsync();
        mResults.addChangeListener(mCallback);

        // instantiate and bind adapter
        mAdapter = new CustomRealmViewAdapter(
                getActivity(),
                mResults,
                true, // refresh adapter on realm update
                false
        );
        if (isAdded())
            mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        // remove the callback listener
        mResults.removeChangeListener(mCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    private void setupFooterToolbarButtons(View view) {
        ImageButton textNoteButton = (ImageButton) view.findViewById(R.id.button_text);
        ImageButton videoNoteButton = (ImageButton) view.findViewById(R.id.button_video);
        ImageButton imageNoteButton = (ImageButton) view.findViewById(R.id.button_photo);
        ImageButton audioNoteButton = (ImageButton) view.findViewById(R.id.button_audio);

        textNoteButton.setOnClickListener(this);
        videoNoteButton.setOnClickListener(this);
        imageNoteButton.setOnClickListener(this);
        audioNoteButton.setOnClickListener(this);

        textNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[0]), R.color.colorIcon));
        videoNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[1]), R.color.colorIcon));
        imageNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[2]), R.color.colorIcon));
        audioNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[3]), R.color.colorIcon));
    }

    @Override
    public void onClick(View view) {
        // handle footer toolbar clicks
        switch (view.getId()) {
            case R.id.button_text:
                // launch text note activity
                QuotationNoteActivity.launch(getActivity());
                break;
            case R.id.button_video:
                // launch video note activity
                VideoNoteActivity.launch(getActivity());
                break;
            case R.id.button_photo:
                Utils.showToast(getActivity(), "Feature to be added");
                break;
            case R.id.button_audio:
                Utils.showToast(getActivity(), "Feature to be added");
                break;
        }
    }

}
