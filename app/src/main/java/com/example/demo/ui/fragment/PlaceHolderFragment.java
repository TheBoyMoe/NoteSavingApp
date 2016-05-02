package com.example.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;

public class PlaceHolderFragment extends BaseFragment{

    /** The fragment argument representing the section number for this fragment. */
    private static final String VIEW_PAGER_POSITION = "view_pager_position";

    public PlaceHolderFragment() { }

    /** Returns a new instance of this fragment for the given section number.  */
    public static PlaceHolderFragment newInstance(int position) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt(VIEW_PAGER_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_holder, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_number);
        textView.setText(String.valueOf(getArguments().getInt(VIEW_PAGER_POSITION) + 1));

        return view;
    }






}
