package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.demo.R;

import timber.log.Timber;

public class TextNoteFragment extends BaseFragment{

    public TextNoteFragment() {}

    public static TextNoteFragment newInstance() {
        return new TextNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_note, container, false);
        EditText title = (EditText) view.findViewById(R.id.text_note_title);
        EditText description = (EditText) view.findViewById(R.id.text_note_description);

        String titleString = title.getText().toString();
        String descriptionString = description.getText().toString();
        Timber.i("%s, %s", titleString, descriptionString);

        return view;
    }
}
