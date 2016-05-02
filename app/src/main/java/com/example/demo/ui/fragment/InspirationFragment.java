package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.demo.R;
import com.example.demo.common.Utils;
import com.example.demo.ui.activity.TextNoteActivity;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;

public class InspirationFragment extends BaseFragment implements View.OnClickListener{

    private Realm mRealm;
    private RealmRecyclerView mRecyclerView;
    private int[] mToolbarIcons = {
            R.drawable.action_note,
            R.drawable.action_photo,
            R.drawable.action_audio
    };

    public InspirationFragment() {}

    public static InspirationFragment newInstance() {
        InspirationFragment fragment = new InspirationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspiration, container, false);
//        TextView textView = (TextView) view.findViewById(R.id.dummy_text);
//        textView.setText(getString(R.string.dummy_text));
        setupToolbarButtons(view);

        return view;
    }

    private void setupToolbarButtons(View view) {
        ImageButton noteButton = (ImageButton) view.findViewById(R.id.button_text);
        ImageButton PhotoButton = (ImageButton) view.findViewById(R.id.button_photo);
        ImageButton audioButton = (ImageButton) view.findViewById(R.id.button_audio);

        noteButton.setOnClickListener(this);
        PhotoButton.setOnClickListener(this);
        audioButton.setOnClickListener(this);

        noteButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[0]), R.color.colorIcon));
        PhotoButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[1]), R.color.colorIcon));
        audioButton.setImageDrawable(Utils.tintDrawable(ContextCompat.getDrawable(getActivity(), mToolbarIcons[2]), R.color.colorIcon));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_text:
                // launch text note activity
                TextNoteActivity.launch(getActivity());
                break;
            case R.id.button_photo:
                Utils.showToast(getActivity(), "Clicked photo button");
                break;
            case R.id.button_audio:
                Utils.showToast(getActivity(), "Clicked audio button");
                break;
        }
    }


}
