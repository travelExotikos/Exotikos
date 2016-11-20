package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;

/**
 * Created by lramaswamy on 11/19/16.
 */

public class BaggageHelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baggage_help, container, false);
    }

    public static BaggageHelpFragment newInstance() {
        BaggageHelpFragment baggageHelpFragment = new BaggageHelpFragment();
        Bundle bundle = new Bundle();
        //bundle.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        baggageHelpFragment.setArguments(bundle);
        return baggageHelpFragment;
    }
}
