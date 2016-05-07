package com.example.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.example.demo.common.Utils;
import com.example.demo.ui.fragment.VideoNoteFragment;

import java.io.File;

import timber.log.Timber;

public class VideoNoteActivity extends NoteActivity implements
        VideoNoteFragment.NoteFragmentContract{

    private String mVideoPath;
    private String mThumbnailUri;
    private String mMimeType;
    private VideoNoteFragment mFragment;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoNoteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instantiate TextNoteFragment
        mFragment = (VideoNoteFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if ( mFragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, VideoNoteFragment.newInstance())
                    .commit();
        }
    }

    // result returned from VideoListActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.VIDEO_SELECTION && resultCode == RESULT_OK) {
            // extract data from intent
            mVideoPath = data.getStringExtra(Constants.VIDEO_PATH);
            mMimeType = data.getStringExtra(Constants.MIME_TYPE);
            mThumbnailUri = data.getStringExtra(Constants.THUMBNAIL_URI);
            Timber.i("%s: Video path: %s, mimeType: %s, thumbnailUri: %s", Constants.LOG_TAG, mVideoPath, mMimeType, mThumbnailUri);

            // TODO display video thumbnail - which is in the fragment
            Timber.i("Fragment: %s", mFragment);
            if (mFragment != null)
                mFragment.updateImageView(mThumbnailUri);
        }
    }

    // implementation of the fragment contract
    @Override
    public void selectVideo() { // long click on thumbnail
        // launch VideoListActivity using startActivityForResult
        VideoListActivity.launch(this);
    }

    @Override
    public void playVideo() { // click on thumbnail
        // play the video when clicking on the thumbnail
        if(mVideoPath != null) {
            Uri video = Uri.fromFile(new File(mVideoPath));
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(video, mMimeType);
            startActivity(intent);
        } else {
            Utils.showToast(this, "Long click to select video");
        }
    }

    @Override
    public void saveVideoNote() {
        // TODO save note to realm
        Utils.showToast(this, "Save!");
    }




}
