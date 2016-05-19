package com.example.demo.custom.realm;

import android.content.Context;

import com.example.demo.model.Note;

import io.realm.RealmResults;


public class RealmNoteAdapter extends RealmModelAdapter<Note>{

    public RealmNoteAdapter(Context context, RealmResults<Note> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
