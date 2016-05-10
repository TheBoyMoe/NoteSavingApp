package com.example.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.example.demo.common.Utils;
import com.example.demo.model.Note;
import com.example.demo.ui.fragment.VideoNoteFragment;

import java.io.File;

import io.realm.Realm;
import timber.log.Timber;

public class VideoNoteActivity extends NoteActivity implements
        VideoNoteFragment.NoteFragmentContract{

    private String mVideoPath;
    private String mMimeType;
    private String mTitle;
    private VideoNoteFragment mFragment;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoNoteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instantiate QuotationNoteFragment
        mFragment = (VideoNoteFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if ( mFragment == null) {
            mFragment = VideoNoteFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mFragment)
                    .commit();
        }

        if (savedInstanceState != null) {
            mVideoPath = savedInstanceState.getString(Constants.VIDEO_PATH);
            mMimeType = savedInstanceState.getString(Constants.MIME_TYPE);
            mTitle = savedInstanceState.getString(Constants.VIDEO_TITLE);
        }
    }

    // result returned from VideoListActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.VIDEO_SELECTION && resultCode == RESULT_OK) {
            // extract data from intent
            mVideoPath = data.getStringExtra(Constants.VIDEO_PATH);
            mMimeType = data.getStringExtra(Constants.MIME_TYPE);
            String title = data.getStringExtra(Constants.VIDEO_TITLE);

            // display video thumbnail - which is in the fragment
            if (mFragment != null) {
                mFragment.updateFragmentUI(mVideoPath, title);
            }
        }
    }

    // implementation of the fragment contract
    @Override
    public void selectVideo() { // long click on thumbnail
        // check if there are any videos on the device
        CursorLoader loader = new CursorLoader(
                this,
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null,
                MediaStore.Video.Media.TITLE);
        Cursor cursor = loader.loadInBackground();
        if (cursor.getCount() > 0)
            // launch VideoListActivity using startActivityForResult
            VideoListActivity.launch(this);
        else
            Utils.showToast(this, "No videos found on device");
    }

    @Override
    public void playVideo() { // click on thumbnail
        // play the video when clicking on the thumbnail
        if(mVideoPath != null) {
            Uri video = Uri.fromFile(new File(mVideoPath));
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(video, mMimeType);
            startActivity(intent);
        }
    }

    @Override
    public void saveVideoNote(String title) {
        // update title, in-case it's been amended
        mTitle = title;
        Timber.i("%s path: %s, mimeType: %s, title: %s",
                Constants.LOG_TAG, mVideoPath, mMimeType, mTitle);
        if (mVideoPath != null) {
            // save note to realm
            final Note videoNote = new Note();
            videoNote.setId(Utils.generateCustomId());
            videoNote.setViewType(Constants.MEDIA_TYPE);
            videoNote.setTextField1(mTitle);
            videoNote.setFilePath(mVideoPath);
            videoNote.setMimeType(mMimeType);

            mTransaction = mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(videoNote);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Timber.i("%s: Saved title: %s, path: %s to realm", Constants.LOG_TAG, mTitle, mVideoPath);
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Timber.e("Error writing object to realm, %s", error.getMessage());
                }
            });
        }
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.VIDEO_PATH, mVideoPath);
        outState.putString(Constants.MIME_TYPE, mMimeType);
        outState.putString(Constants.VIDEO_TITLE, mTitle);
    }


}
