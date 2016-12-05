package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;

/**
 * Created by lramaswamy on 12/4/16.
 */

public class BoardingLineInfoHelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boarding_inline, container, false);
    }

    public static BoardingLineInfoHelpFragment newInstance() {
        BoardingLineInfoHelpFragment baggageLineHelpFragment = new BoardingLineInfoHelpFragment();
        Bundle bundle = new Bundle();
        baggageLineHelpFragment.setArguments(bundle);
        return baggageLineHelpFragment;
    }
}
