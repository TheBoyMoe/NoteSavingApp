package com.example.demo.custom.realm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.example.demo.common.Utils;
import com.example.demo.model.Note;

import io.realm.Realm;

public class NotesAdapter extends RealmRecyclerViewAdapter<Note>{

    final Context mContext;
    private Realm mRealm;
    private LayoutInflater mInflater;
    private String mFilePath;
    private String mMimeType;
    //private Note note;
    //private int mPosition;

    public NotesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // define the layout depending on the viewType
        View view = null;
        switch (viewType) {
            case Constants.TEXT_TYPE:
                view = inflater.inflate(R.layout.text_note_item, parent, false);
                break;
            case Constants.MEDIA_TYPE:
                view = inflater.inflate(R.layout.media_note_item, parent, false);
                break;
        }
        return new NoteViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //mPosition = position;
        Note note = getItem(position);
        NoteViewHolder viewHolder = (NoteViewHolder) holder;
        if(note.getViewType() == Constants.TEXT_TYPE) {
            viewHolder.textField1.setText(note.getTextField1());
            viewHolder.textField2.setText(note.getTextField2());
        } else if(note.getViewType() == Constants.MEDIA_TYPE){
            mFilePath = note.getFilePath();
            viewHolder.thumbnail.setImageBitmap(Utils.generateBitmap(mFilePath));
            mMimeType = note.getMimeType();
        }
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Note note = getItem(position);
        if (note.getViewType() == Constants.TEXT_TYPE)
            return Constants.TEXT_TYPE;
        else if (note.getViewType() == Constants.MEDIA_TYPE)
            return Constants.MEDIA_TYPE;

        return -1;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public int position;
        public int viewType;
        public TextView textField1;
        public TextView textField2;
        public ImageView thumbnail;
        public String filepath;
        public String mimeType;


        public NoteViewHolder(View itemView, int viewType) {
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


        @Override
        public void onClick(View v) {
            // TODO
            Toast.makeText(mContext, "Position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }

}
