package com.example.demo.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.example.demo.ui.fragment.VideoListFragment;

/*
    Responsible for displaying list of available videos on device,
    allowing user selection
 */
public class VideoListActivity extends NoteActivity implements
        VideoListFragment.VideoListContract{


    private static Intent mVideoIntent;

    // implementation of VideoListContract
    @Override
    public void listItemClick(String path, String mimeType, String title) {
        // return path/mimeType to calling activity (VideoNoteActivity)
        mVideoIntent = new Intent();
        mVideoIntent.putExtra(Constants.VIDEO_PATH, path);
        mVideoIntent.putExtra(Constants.MIME_TYPE, mimeType);
        mVideoIntent.putExtra(Constants.VIDEO_TITLE, title);

        // show video selection confirmation dialog
        DialogFragment dialog = new VideoSelectionDialog();
        dialog.show(getSupportFragmentManager(), "VideoSelectionDialog");

    }


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoListActivity.class);
        activity.startActivityForResult(intent, Constants.VIDEO_SELECTION);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // display the title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

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

    public static class VideoSelectionDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.video_selection_confirmation_title)
                    .setPositiveButton(R.string.video_selection_confirmation_ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().setResult(RESULT_OK, mVideoIntent);
                                    getActivity().finish();
                                }
                            })
                    .setNegativeButton(R.string.video_selection_confirmation_cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // no-op
                                }
                            });

            return builder.create();
        }
    }

}
