package com.exotikosteam.exotikos.fragments;

import android.content.Context;
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
import com.exotikosteam.exotikos.databinding.FragmentTravelPrepBinding;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.Utils;

import org.parceler.Parcels;

import java.util.Date;

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
        tvDepartureCity.setText(flight.getDepartureCity());
        tvDepDate.setText(Utils.convertToDate(flight.getDepartureTime()));
        tvDestination.setText(flight.getArrivalCity());
        prepFragmentBinding.tvDepartureTime.setText(Utils.convertToTime(flight.getDepartureTime()));

        Date departureTime = Utils.parseFlightstatsDate(flight.getDepartureTime());
        tvCheckinDaysLeft.setText(Utils.getReadytoPrintCheckinTimeDelta(departureTime));
        tvBoarding.setText(Utils.getReadytoPrintBoardingTimeDelta(departureTime));
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
        boolean fromFragment = getArguments().getBoolean("fromFragment");
        if(!fromFragment)
            prepFragmentBinding.btnNext.setVisibility(View.GONE);
        else
            prepFragmentBinding.btnNext.setVisibility(View.VISIBLE);

        prepFragmentBinding.btnNext.setOnClickListener(v -> {
            handleLaunchPrepActivity();
        });

        btnAirportPage.setOnClickListener(v -> {
            handleLaunchAirportMapPage();
        });

    }

    private void handleLaunchPrepActivity() {
        listener.handleButtonsClicks("LaunchTravelPrepActivity");
    }

    private void handleLaunchAirportMapPage() {
        listener.handleButtonsClicks("LaunchAirportPage");
    }

    public static TravelPrepFragment newInstance(TripStatus trips, boolean fromFragment) {
        TravelPrepFragment frag = new TravelPrepFragment();
        Bundle args = new Bundle();
        args.putBoolean("fromFragment", fromFragment);
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trips));
        frag.setArguments(args);
        return frag;
    }


}
