package com.example.demo.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.model.Note;

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
        View view = inflater.inflate(R.layout.text_note_item, viewGroup, false);
        return new CustomRealmViewHolder(view);
    }

    @Override
    public void onBindRealmViewHolder(CustomRealmViewHolder holder, int position) {
        Note note = mResults.get(position);
        holder.bindViewHolder(note, position);
    }

    @Override
    public int getItemRealmViewType(int position) {
        return super.getItemRealmViewType(position);
    }

    public class CustomRealmViewHolder extends RealmViewHolder implements View.OnClickListener{

        int position;
        TextView title;
        TextView description;
        ImageView icon;

        public CustomRealmViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.text_field_one);
            description = (TextView) itemView.findViewById(R.id.text_field_two);
        }

        public void bindViewHolder(Note note, int position) {
            this.position = position;
            title.setText(note.getTextField1());
            description.setText(note.getTextField2());
            // TODO add icon
        }

        @Override
        public void onClick(View v) {
            // TODO handle list item click
            Utils.showToast(mContext, "Clicked on " + position);
        }

    }


}
