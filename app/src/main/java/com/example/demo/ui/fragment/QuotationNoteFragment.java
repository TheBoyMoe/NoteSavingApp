package com.example.demo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.demo.R;
import com.example.demo.common.Utils;

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

        // add the toolbar
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if (actionBar != null) {
                //actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                toolbar.setNavigationIcon(Utils.tintDrawable(ContextCompat
                        .getDrawable(getActivity(), R.drawable.action_back), R.color.colorIcon));
                toolbar.setNavigationOnClickListener(this);
                toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryText));
            }
        }

        mQuote = (EditText) view.findViewById(R.id.quotation_note_text);
        mCitation = (EditText) view.findViewById(R.id.citation_note_text);
        //Button saveButton = (Button) view.findViewById(R.id.save_note);
        //saveButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        saveAndQuit();
    }

    private void saveAndQuit() {
        // retrieve title and description and propagate upto hosting activity
        // check quote string is not empty before saving
        String quote = mQuote.getText() != null ? mQuote.getText().toString() : "";
        String citation = mCitation.getText() != null ? mCitation.getText().toString() : "";
        if (!quote.isEmpty())
            mActivity.saveTextNote(quote, citation);

        getActivity().finish();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                //saveAndQuit();
//                Timber.i("%s Clicked on home button", Constants.LOG_TAG);
//                Utils.showToast(getActivity(), "Save and quit");
////                getActivity().onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
