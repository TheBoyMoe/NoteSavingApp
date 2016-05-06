package com.example.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.demo.R;
import com.example.demo.ui.fragment.VideoListFragment;

import java.io.File;

/*
    Responsible for displaying list of available videos on device,
    allowing user selection
 */
public class VideoListActivity extends NoteActivity implements
        VideoListFragment.VideoListContract{

    // TODO implementation of VideoListContract
    @Override
    public void listItemClick(String path, String mimeType) {
        // return uri/mimeType to calling activity

        // play the video for now
        Uri video = Uri.fromFile(new File(path));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(video, mimeType);
        startActivity(intent);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
            Requires use of fragment and FragmentManager from support lib
            since onAttach(Context) is not called on devices api < 23
         */
        // instantiate VideoGridView Fragment
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, VideoListFragment.newInstance())
                    .commit();
        }

    }



}
