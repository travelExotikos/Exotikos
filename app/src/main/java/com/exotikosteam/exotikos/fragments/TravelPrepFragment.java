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
import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    
    public interface OnButtonsClicks {
        void handleButtonsClicks(String buttonName, LatLng latLng);
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
        tvDepDate.setText(flight.getDepartureDate()); // @TODO need to parse this: Lakshmy
        tvDestination.setText(flight.getArrivalCity()); // @TODO empty : ada/yeyus
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setOnClickListener() {
        prepFragmentBinding.btnLaunchSecurityFragment.setOnClickListener(v -> {
            handleLaunchSecurityPage();
        });

        btnAirportPage.setOnClickListener(v -> {
            handleLaunchAirportMapPage();
        });

        prepFragmentBinding.btnHelp.setOnClickListener( v -> {
            handleHelpAction();
        });
    }

    private void handleLaunchAirportMapPage() {
        listener.handleButtonsClicks("LaunchAirportPage", new LatLng(37.6213129,-122.3811494));
    }
    private void handleLaunchSecurityPage() {
        listener.handleButtonsClicks("LaunchSecurityFragment", null);
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

    public String getDate(String date) { // TODO: make the date not UTC - Lakshmy
        DateFormat parsedDate =
                new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");

//        if (date == null) {
//            return formatDateTime (new Date());
//        }

        // format in (almost) ISO8601 format
        String dateStr = parsedDate.format(date);

        // remap the timezone from 0000 to 00:00 (starts at char 22)
        return dateStr.substring (0, 22)
                + ":" + dateStr.substring (22);
    }

}
