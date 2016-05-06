package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.ui.activity.VideoListActivity;

public class VideoNoteFragment extends NoteFragment implements View.OnClickListener{

    public interface NoteFragmentContract {
        // TODO - launch VideoListActivity
        //      - save video note to realm
    }

    private TextView mTitle;
    private ImageView mThumbnail;

    public VideoNoteFragment() {}

    public static VideoNoteFragment newInstance() {
        return new VideoNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_note, container, false);

        mTitle = (EditText) view.findViewById(R.id.text_note_title);
        mThumbnail = (ImageView) view.findViewById(R.id.video_note_thumbnail);
        mThumbnail.setOnClickListener(this);
        Button saveButton = (Button) view.findViewById(R.id.save_note);
        saveButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        // TODO fetch video from local store, save note to realm
        switch (view.getId()) {
            case R.id.video_note_thumbnail:
                // use start activityForResult so as to get back the video uri & mimeType
                VideoListActivity.launch(getActivity());
                break;
            case R.id.save_note:

                break;
        }
    }
}
