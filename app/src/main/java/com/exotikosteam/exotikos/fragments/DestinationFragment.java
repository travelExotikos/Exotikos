package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;

import org.parceler.Parcels;

/**
 * Created by lramaswamy on 11/17/16.
 */

public class DestinationFragment extends Fragment {

    DestinationFragment destinationFragment;
    TripStatus trip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_destination, container, false);
    }

    public static DestinationFragment newInstance(TripStatus trip) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        fragment.setArguments(args);
        return fragment;
    }
}
