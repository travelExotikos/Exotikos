package com.exotikosteam.exotikos.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentBoardingMainBinding;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;

import org.parceler.Parcels;

/**
 * Created by lramaswamy on 11/15/16.
 */

public class BoardingGateFragment extends Fragment {

    FragmentBoardingMainBinding fragmentBoardingMainBinding;
    TripStatus trip;
    TravelPrepFragment.OnButtonsClicks listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBoardingMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_boarding_main, container, false);
        trip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        populateFields();
        return fragmentBoardingMainBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TravelPrepFragment.OnButtonsClicks) {
            listener = (TravelPrepFragment.OnButtonsClicks) context;
        }
    }

    public void populateFields() {
        Flight flight = trip.getFlights().get(trip.getCurrentFlight());
        fragmentBoardingMainBinding.tvBoardingGate.setText(Constants.GATE + flight.getDepartureGate());
        fragmentBoardingMainBinding.tvTerminal.setText(Constants.TERMINAL + flight.getDepartureTerminal());
    }

    public static BoardingGateFragment newInstance(TripStatus trip) {
        BoardingGateFragment fragment = new BoardingGateFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        fragment.setArguments(args);
        return fragment;
    }

}
