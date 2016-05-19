package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.custom.CustomItemDecoration;
import com.example.demo.custom.realm.NotesAdapter;
import com.example.demo.custom.realm.RealmNoteAdapter;
import com.example.demo.model.Note;
import com.example.demo.ui.activity.QuotationNoteActivity;
import com.example.demo.ui.activity.VideoNoteActivity;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmNoteListFragment extends BaseFragment implements View.OnClickListener{

    private int[] mToolbarIcons = {
            R.drawable.action_note,
            R.drawable.action_video,
            R.drawable.action_photo,
            R.drawable.action_audio
    };
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
        setupFooterToolbarButtons(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new CustomItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_spacer)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new NotesAdapter(getActivity());
        recyclerView.setAdapter(mAdapter); // sets empty adapter
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
