package com.example.demo.custom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.example.demo.common.Utils;
import com.example.demo.model.Note;

import java.io.File;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;


public class CustomRealmViewAdapter extends RealmBasedRecyclerViewAdapter<Note, CustomRealmViewAdapter.CustomRealmViewHolder>{

    private RealmResults<Note> mResults;
    private Context mContext;

    public CustomRealmViewAdapter(
            Context context,
            RealmResults<Note> realmResults,
            boolean automaticUpdate,
            boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);

        mContext = context;
        mResults = realmResults;
    }

    @Override
    public CustomRealmViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        // define which layout to use depending on the viewType, by default it's 0
        View view = null;
        switch (viewType) {
            case Constants.TEXT_TYPE:
                view = inflater.inflate(R.layout.text_note_item, viewGroup, false);
                break;
            case Constants.MEDIA_TYPE:
                view = inflater.inflate(R.layout.media_note_item, viewGroup, false);
                break;
        }

        return new CustomRealmViewHolder(view, viewType);
    }

    @Override
    public void onBindRealmViewHolder(CustomRealmViewHolder holder, int position) {
        holder.bindViewHolder(mResults.get(position), position);
    }

    @Override
    public int getItemRealmViewType(int position) {
        if (mResults.get(position).getViewType() == Constants.TEXT_TYPE)
            return Constants.TEXT_TYPE;
        else if (mResults.get(position).getViewType() == Constants.MEDIA_TYPE)
            return Constants.MEDIA_TYPE;

        return -1;
    }


    public class CustomRealmViewHolder extends RealmViewHolder implements View.OnClickListener {

        int position;
        int viewType;
        TextView textField1;
        TextView textField2;
        ImageView thumbnail;
        String filePath;
        String mimeType;

        public CustomRealmViewHolder(View itemView, int viewType) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.viewType = viewType;
            textField1 = (TextView) itemView.findViewById(R.id.text_field_one);
            switch (this.viewType) {
                case Constants.TEXT_TYPE:
                    textField2 = (TextView) itemView.findViewById(R.id.text_field_two);
                    break;
                case Constants.MEDIA_TYPE:
                    thumbnail = (ImageView) itemView.findViewById(R.id.note_thumbnail);
                    break;
            }
        }

        public void bindViewHolder(Note note, int position) {
            this.position = position;
            if (mResults.get(position).getViewType() == Constants.TEXT_TYPE) {
                textField1.setText(note.getTextField1());
                textField2.setText(note.getTextField2());
            } else {
                if (mResults.get(position).getViewType() == Constants.MEDIA_TYPE) {
                    //textField1.setText(note.getTextField1());
                    filePath = note.getFilePath();
                    thumbnail.setImageBitmap(Utils.generateBitmap(filePath));
                    mimeType = note.getMimeType();
                }
            }
        }

        @Override
        public void onClick(View v) {
            switch (viewType) {
                case Constants.TEXT_TYPE:
                    // TODO launch Activity/Fragment showing Detail view of note
                    Utils.showToast(mContext, "Clicked on quote: " + position);
                    break;
                case Constants.MEDIA_TYPE:
                    // launch default media player
                    // TODO check if there is a media player installed
                    if(filePath != null && mimeType != null) {
                        Uri video = Uri.fromFile(new File(filePath));
                        Intent videoIntent = new Intent(Intent.ACTION_VIEW);
                        videoIntent.setDataAndType(video, mimeType);
                        mContext.startActivity(videoIntent);
                    }
                    break;
            }
        }

    }


}
