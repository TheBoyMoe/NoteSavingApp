package com.example.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.demo.R;
import com.example.demo.common.Constants;
import com.example.demo.common.Utils;
import com.example.demo.model.Note;
import com.example.demo.ui.fragment.QuotationNoteFragment;

import io.realm.Realm;
import timber.log.Timber;

public class QuotationNoteActivity extends NoteActivity implements
        QuotationNoteFragment.TextNoteContract{

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, QuotationNoteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instantiate QuotationNoteFragment
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, QuotationNoteFragment.newInstance())
                    .commit();
        }

    }


    @Override
    public void saveTextNote(final String quote, final String citation) {
        // save note to realm
        final Note textNote = new Note();
        textNote.setId(Utils.generateCustomId());
        textNote.setViewType(Constants.TEXT_TYPE);
        textNote.setTextField1(quote);
        textNote.setTextField2(citation);

        mTransaction = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(textNote);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Timber.i("%s: Success! title: %s, description: %s", Constants.LOG_TAG, quote, citation);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Timber.e("Error writing object to realm, %s", error.getMessage());
            }
        });

        finish();
    }

}
