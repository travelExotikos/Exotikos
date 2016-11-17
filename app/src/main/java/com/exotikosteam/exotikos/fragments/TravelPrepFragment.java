package com.exotikosteam.exotikos.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.activities.HelpActivity;
import com.exotikosteam.exotikos.databinding.FragmentTravelPrepBinding;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;

import org.parceler.Parcels;

/**
 * Created by lramaswamy on 11/12/16.
 */

public class TravelPrepFragment extends Fragment {

    private static final String TAG = TravelPrepFragment.class.getSimpleName();

    OnButtonsClicks listener;
    FragmentTravelPrepBinding prepFragmentBinding;
    Button btnAirportPage;
    TextView tvFlightNumber;
    TextView tvBoarding;
    TextView tvCheckinDaysLeft;
    TextView tvDepDate;
    TextView tvDepartureCity;
    TripStatus trip;
    TextView tvDestination;
    String departureAirportIATA;
    
    public interface OnButtonsClicks {
        void handleButtonsClicks(String buttonName, String departureAirportIATA);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prepFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_travel_prep, container, false);
        trip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        setupBindings();
        setOnClickListener();
        populateData();
        return prepFragmentBinding.getRoot();
    }

    private void populateData() {
        Flight flight = trip.getFlights().get(trip.getCurrentFlight());
        tvFlightNumber.setText(flight.getFlightNumber());
        tvDepartureCity.setText(flight.getDepartureCity()); // @TODO empty : ada/yeyus
        tvDepDate.setText(flight.getDepartureTime()); // @TODO need to parse this: Lakshmy
        tvDestination.setText(flight.getArrivalCity()); // @TODO empty : ada/yeyus
        departureAirportIATA = flight.getDepartureAirportIATA();
    }

    private void setupBindings() {
        btnAirportPage = prepFragmentBinding.btnAirportPage;
        tvFlightNumber = prepFragmentBinding.tvFlightNumber;
        tvBoarding = prepFragmentBinding.tvBoarding;
        tvCheckinDaysLeft = prepFragmentBinding.tvCheckinDaysLeft;
        tvDepDate = prepFragmentBinding.tvDepDate;
        tvDepartureCity = prepFragmentBinding.tvDepartureCity;
        tvDestination = prepFragmentBinding.tvDestination;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnButtonsClicks) {
            listener = (OnButtonsClicks) context;
        }
    }


    private void setOnClickListener() {
        prepFragmentBinding.btnScan.setOnClickListener(v -> {
            handleLaunchScanPage();
        });

        btnAirportPage.setOnClickListener(v -> {
            handleLaunchAirportMapPage(departureAirportIATA);
        });

        prepFragmentBinding.btnHelp.setOnClickListener( v -> {
            handleHelpAction();
        });

        prepFragmentBinding.btnSecurity.setOnClickListener(v -> {
            launchSecurityCheckin();
        });

        prepFragmentBinding.btnTravelStatus.setOnClickListener(v -> {
            listener.handleButtonsClicks("LaunchCardLayout", null);
        });
    }


    private void launchSecurityCheckin() {
        listener.handleButtonsClicks("LaunchSecurityCheckin", null);
    }

    private void handleLaunchAirportMapPage(String departureAirportIATA) {
        listener.handleButtonsClicks("LaunchAirportPage", departureAirportIATA);
    }

    private void handleLaunchScanPage() {
        listener.handleButtonsClicks("LaunchScan", null);
    }

    private void handleHelpAction() {
        startActivity(new Intent(getContext(), HelpActivity.class));
    }

    public static TravelPrepFragment newInstance(TripStatus trips) {
        TravelPrepFragment frag = new TravelPrepFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trips));
        frag.setArguments(args);
        return frag;
    }


}
