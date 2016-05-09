package com.example.demo.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.model.MediaNote;
import com.example.demo.model.TextNote;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;


public class CustomRealmViewAdapter extends RealmBasedRecyclerViewAdapter<RealmObject, CustomRealmViewAdapter.CustomRealmViewHolder>{

    private static final int MEDIA_TYPE = 1;
    private static final int TEXT_TYPE = 2;
    private RealmResults<RealmObject> mResults;
    private Context mContext;


    public CustomRealmViewAdapter(
            Context context,
            RealmResults<RealmObject> realmResults,
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
            case TEXT_TYPE:
                view = inflater.inflate(R.layout.text_note_item, viewGroup, false);
                break;
            case MEDIA_TYPE:
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
        if (mResults.get(position) instanceof TextNote)
            return TEXT_TYPE;
        else if (mResults.get(position) instanceof MediaNote)
            return MEDIA_TYPE;

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
                case TEXT_TYPE:
                    textField2 = (TextView) itemView.findViewById(R.id.text_field_two);
                    break;
                case MEDIA_TYPE:
                    thumbnail = (ImageView) itemView.findViewById(R.id.note_thumbnail);
                    break;
            }
        }

        public void bindViewHolder(RealmObject note, int position) {
            this.position = position;
            if (note instanceof TextNote) {
                TextNote textItem = (TextNote) note;
                textField1.setText(textItem.getTextField1());
                textField2.setText(textItem.getTextField2());
            } else {
                if (note instanceof MediaNote) {
                    MediaNote mediaItem = (MediaNote) note;
                    textField1.setText(mediaItem.getTextField1());
                    filePath = mediaItem.getFilePath();
                    thumbnail.setImageBitmap(Utils.generateBitmap(filePath));
                    mimeType = mediaItem.getMimeType();
                }
            }
        }

        @Override
        public void onClick(View v) {
            switch (viewType) {
                case TEXT_TYPE:
                    Utils.showToast(mContext, "Clicked on quote: " + position);
                    break;
                case MEDIA_TYPE:
                    Utils.showToast(mContext, "Clicked on thumbnail: " + position);
                    break;
            }
        }

    }


//    public class TextNoteRealmViewHolder extends RealmViewHolder implements View.OnClickListener{
//
//        int position;
//        TextView quote;
//        TextView citation;
//
//
//        public TextNoteRealmViewHolder(View itemView) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            quote = (TextView) itemView.findViewById(R.id.text_field_one);
//            citation = (TextView) itemView.findViewById(R.id.text_field_two);
//        }
//
//        public void bindViewHolder(TextNote note, int position) {
//            this.position = position;
//            quote.setText(note.getTextField1());
//            citation.setText(note.getTextField2());
//        }
//
//        @Override
//        public void onClick(View v) {
//            Utils.showToast(mContext, "Clicked on quote: " + position);
//        }
//
//    }

//    public class MediaNoteRealmViewHolder extends RealmViewHolder implements View.OnClickListener {
//
//        int position;
//        TextView title;
//        ImageView thumbnail;
//
//        public MediaNoteRealmViewHolder(View itemView) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            title = (TextView) itemView.findViewById(R.id.text_field_one);
//            thumbnail = (ImageView) itemView.findViewById(R.id.note_thumbnail);
//        }
//
//        public void bindViewHolder(MediaNote note, int position) {
//            this.position = position;
//            title.setText(note.getTextField1());
//            thumbnail.setImageBitmap(Utils.generateBitmap(note.getFilePath()));
//        }
//
//        @Override
//        public void onClick(View view) {
//            Utils.showToast(mContext, "Clicked on thumbnail: ");
//        }
//
//    }


}
