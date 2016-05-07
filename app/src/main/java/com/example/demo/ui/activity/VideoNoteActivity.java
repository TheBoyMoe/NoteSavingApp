package com.example.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.ui.fragment.VideoNoteFragment;

public class VideoNoteActivity extends NoteActivity implements
        VideoNoteFragment.NoteFragmentContract{

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoNoteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instantiate TextNoteFragment
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, VideoNoteFragment.newInstance())
                    .commit();
        }

    }

    // implementation of the fragment contract
    @Override
    public void videoSelection() {
        VideoListActivity.launch(this);
        Utils.showToast(this, "Video");
    }

    @Override
    public void saveVideoNote() {
        Utils.showToast(this, "Save!");
    }
}
