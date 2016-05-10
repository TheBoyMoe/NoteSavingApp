package com.example.demo.ui.fragment;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class VideoListFragment extends Fragment implements
        AdapterView.OnItemClickListener,
        SimpleCursorAdapter.ViewBinder,
        LoaderManager.LoaderCallbacks<Cursor> {

    private VideoListContract mActivity;
    private GridView mGridView;
    private SimpleCursorAdapter mAdapter;


    public interface VideoListContract {
        // pass a reference to the video selected
        void listItemClick(String videoPath, String mimeType, String title);
    }

    public VideoListFragment() {}

    public static VideoListFragment newInstance() {
        return new VideoListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_view, container, false);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] from = {MediaStore.Video.Media._ID};
        int[] to = {R.id.grid_view_item};
        mAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.grid_item,
                null,
                from,
                to,
                0);
        mAdapter.setViewBinder(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        // fragment needs to inherit from fragment, and not
        // the support ver for this method to work
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null,
                MediaStore.Video.Media.TITLE);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        Timber.i("%s cursor size: %d", Constants.LOG_TAG, cursor.getCount());
        if (columnIndex == cursor.getColumnIndex(MediaStore.Video.Media._ID)) {
            Uri thumbnailUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    cursor.getInt(columnIndex));

            Picasso.with(getActivity())
                    .load(thumbnailUri.toString())
                    .fit().centerCrop()
                    .placeholder(R.drawable.action_video)
                    .into((ImageView) view);

            return true;
        }
        return false;
    }

     @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
         int uriColumn = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
         int mimeType = cursor.getColumnIndex(MediaStore.Video.Media.MIME_TYPE);
         int title = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
         mActivity.listItemClick(cursor.getString(uriColumn), cursor.getString(mimeType), cursor.getString(title));
     }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            mActivity = (VideoListContract) activity;
        } catch (ClassCastException e) {
            Timber.e("%s does not implement contract interface, error: %s", activity.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }



}
