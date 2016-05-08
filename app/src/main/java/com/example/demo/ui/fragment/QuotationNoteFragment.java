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

import com.example.demo.R;

import timber.log.Timber;

public class QuotationNoteFragment extends BaseFragment implements View.OnClickListener{

    public interface TextNoteContract {
        void saveTextNote(String quote, String citation);
    }

    private TextNoteContract mActivity;
    private EditText mQuote;
    private EditText mCitation;

    public QuotationNoteFragment() {}

    public static QuotationNoteFragment newInstance() {
        return new QuotationNoteFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quotation_note, container, false);

        mQuote = (EditText) view.findViewById(R.id.quotation_note_text);
        mCitation = (EditText) view.findViewById(R.id.citation_note_text);
        Button saveButton = (Button) view.findViewById(R.id.save_note);
        saveButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        // retrieve title and description and propagate upto hosting activity
        String strTitle = mQuote.getText() != null ? mQuote.getText().toString() : "";
        String strDescription = mCitation.getText() != null ? mCitation.getText().toString() : "";
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