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
import com.example.demo.ui.activity.TextNoteActivity;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class NoteListFragment extends BaseFragment implements View.OnClickListener{


    private int[] mToolbarIcons = {
            R.drawable.action_note,
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
        // RelativeLayout recyclerViewContainer = (RelativeLayout) view.findViewById(R.id.recycler_view_container);
        setupFooterToolbarButtons(view);
        mRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);
        mRecyclerView.addItemDecoration(new CustomItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_spacer)));

//        // create and add the footer toolbar programmatically
//        RelativeLayout footerToolbar = new RelativeLayout(getActivity());
//        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); // position the footer toolbar
//        footerToolbar.setLayoutParams(relativeParams);
//
//        // instantiate and add the toolbar title and buttons
//        setupFooterToolbarTitle(footerToolbar);
//        setupFooterToolbarButtons(footerToolbar);
//
//        // add the footer toolbar to the fragment layout
//        recyclerViewContainer.addView(footerToolbar);

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

//    private void setupFooterToolbarTitle(RelativeLayout relativeLayout) {
//        // instantiate and add text view
//        TextView noteText = new TextView(getActivity());
//        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        noteText.setLayoutParams(textViewParams);
//        noteText.setText(R.string.bottom_toolbar_title);
//
//        relativeLayout.addView(noteText);
//    }

    private void setupFooterToolbarButtons(View view) {
        ImageButton textNoteButton = (ImageButton) view.findViewById(R.id.button_text);
        ImageButton imageNoteButton = (ImageButton) view.findViewById(R.id.button_photo);
        ImageButton audioNoteButton = (ImageButton) view.findViewById(R.id.button_audio);


        // ImageButton textNoteButton = new ImageButton(getActivity());
        // ImageButton imageNoteButton = new ImageButton(getActivity());
        // ImageButton audioNoteButton = new ImageButton(getActivity());

        textNoteButton.setOnClickListener(this);
        imageNoteButton.setOnClickListener(this);
        audioNoteButton.setOnClickListener(this);

        textNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[0]), R.color.colorIcon));
        imageNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[1]), R.color.colorIcon));
        audioNoteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[2]), R.color.colorIcon));

//        // position audio button
//        RelativeLayout.LayoutParams audioButtonParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        audioButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        audioNoteButton.setLayoutParams(audioButtonParams);
//
//        relativeLayout.addView(textNoteButton);
//        relativeLayout.addView(imageNoteButton);
//        relativeLayout.addView(audioNoteButton);
    }

    @Override
    public void onClick(View view) {
        // handle footer toolbar clicks
        switch (view.getId()) {
            case R.id.button_text:
                // launch text note activity
                TextNoteActivity.launch(getActivity());
                break;
            case R.id.button_photo:
                Utils.showToast(getActivity(), "Clicked photo button");
                break;
            case R.id.button_audio:
                Utils.showToast(getActivity(), "Clicked audio button");
                break;
        }
    }


}
