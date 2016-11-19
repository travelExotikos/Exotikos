package com.exotikosteam.exotikos.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentDestinationBinding;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;

import org.parceler.Parcels;

/**
 * Created by lramaswamy on 11/17/16.
 */

public class DestinationFragment extends Fragment {

    FragmentDestinationBinding destinationFragment;
    TripStatus trip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        destinationFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_destination, container, false);
        trip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        populateData();
        return destinationFragment.getRoot();
    }

    private void populateData() {
        Flight flight = trip.getFlights().get(trip.getCurrentFlight());
        destinationFragment.tvArrivalTime.setText(flight.getArrivalTime());
        destinationFragment.tvCarousel.setText(flight.getBaggage());
        destinationFragment.tvArrivalGate.setText(flight.getArrivalGate());
    }

    public static DestinationFragment newInstance(TripStatus trip) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        fragment.setArguments(args);
        return fragment;
    }
}
