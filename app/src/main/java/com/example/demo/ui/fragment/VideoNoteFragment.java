package com.example.demo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.demo.R;
import com.example.demo.common.Constants;

import java.io.File;

import timber.log.Timber;

public class VideoNoteFragment extends BaseFragment implements
        View.OnClickListener, View.OnLongClickListener, TextWatcher{


    public interface NoteFragmentContract {
        void selectVideo();
        void playVideo();
        void saveVideoNote(String title);
    }

    private NoteFragmentContract mActivity;
    private EditText mTitle;
    private ImageView mThumbnail;
    private String mVideoPath;
    private String mVideoTitle;

    public VideoNoteFragment() {}

    public static VideoNoteFragment newInstance() {
        return new VideoNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_note, container, false);

        mTitle = (EditText) view.findViewById(R.id.video_note_title);
        mTitle.addTextChangedListener(this);
        mThumbnail = (ImageView) view.findViewById(R.id.video_note_thumbnail);
        mThumbnail.setOnClickListener(this);
        mThumbnail.setOnLongClickListener(this);
        Button saveButton = (Button) view.findViewById(R.id.save_note);
        saveButton.setOnClickListener(this);

        if (savedInstanceState != null) {
            mVideoTitle = savedInstanceState.getString(Constants.VIDEO_TITLE);
            if (mVideoTitle != null) {
                mTitle.setText(mVideoTitle);
            }
            // retrieve saved video path
            mVideoPath = savedInstanceState.getString(Constants.VIDEO_PATH);
            if (mVideoPath != null)
                generateThumbnail();
        }

        return view;
    }

    // add a listener to EditTextView
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no-op
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // update title text when amended by user
        mVideoTitle = s.toString();
    }

    @Override
    public void afterTextChanged(Editable s) {
        // no-op
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_note_thumbnail:
                mActivity.playVideo();
                break;
            case R.id.save_note:
                // save note to realm with the updated title
                mActivity.saveVideoNote(mVideoTitle);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        // allow the user to select/change the video selection
        // use start activityForResult so as to get back the video/thumbnail path/uri & mimeType
        if (v.getId() == R.id.video_note_thumbnail) {
            mActivity.selectVideo();
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            mActivity = (NoteFragmentContract) activity;
        } catch (ClassCastException e) {
            Timber.e("%s does not implement contract interface, error: %s", activity.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.VIDEO_PATH, mVideoPath);
        outState.putString(Constants.VIDEO_TITLE, mVideoTitle);
    }

    public void updateFragmentUI(String videoPath, String title) {
        // update EditText and ImageView elements
        mVideoPath = videoPath;
        mVideoTitle = title;
        mTitle.setText(mVideoTitle);
        generateThumbnail();
    }

    private void generateThumbnail() {
        Bitmap bitMap = ThumbnailUtils.createVideoThumbnail(new File(mVideoPath).getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND);
        mThumbnail.setImageBitmap(bitMap);
    }


}
