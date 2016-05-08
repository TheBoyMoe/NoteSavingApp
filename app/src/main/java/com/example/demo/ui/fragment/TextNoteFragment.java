package com.example.demo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo.R;

import timber.log.Timber;

public class TextNoteFragment extends BaseFragment implements View.OnClickListener{

    public interface TextNoteContract {
        void saveTextNote(String title, String description);
    }

    private TextNoteContract mActivity;
    private TextView mTitle;
    private TextView mDescription;

    public TextNoteFragment() {}

    public static TextNoteFragment newInstance() {
        return new TextNoteFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_note, container, false);

        mTitle = (EditText) view.findViewById(R.id.text_note_title);
        mDescription = (EditText) view.findViewById(R.id.text_note_description);
        Button saveButton = (Button) view.findViewById(R.id.save_note);
        saveButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        // retrieve title and description and propagate upto hosting activity
        String strTitle = mTitle.getText() != null ? mTitle.getText().toString() : "";
        String strDescription = mDescription.getText() != null ? mDescription.getText().toString() : "";
        mActivity.saveTextNote(strTitle, strDescription);

        getActivity().finish();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            mActivity = (TextNoteContract) activity;
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
