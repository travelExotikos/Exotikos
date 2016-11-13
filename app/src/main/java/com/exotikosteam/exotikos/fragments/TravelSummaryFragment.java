package com.exotikosteam.exotikos.fragments;

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
import com.exotikosteam.exotikos.activities.TravelStatusActivity;
import com.exotikosteam.exotikos.databinding.TravelSummaryFragmentBinding;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by lramaswamy on 11/11/16.
 */

public class TravelSummaryFragment extends Fragment {

    TripStatus trips;
    List<Flight> flights;
    private TravelSummaryFragmentBinding summaryFragmentBinding;
    Button btnTravelStatus;
    TextView tvTravelDate1;
    TextView tvTravelDest1;
    TextView tvDepartureTime1;
    TextView tvStop1;
    TextView tvArrivalTime1;
    TextView tvDepartureTime2;
    TextView tvDestination;
    TextView tvArrivalTime2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trips = (TripStatus) Parcels.unwrap(getArguments().getParcelable("tripStatus"));
        flights = trips.getFlights();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        summaryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_travel_summary, parent, false);
        setupBindings();
        setupListeners();
        return  summaryFragmentBinding.getRoot();
    }

    private void setupBindings() {
        tvTravelDate1 = summaryFragmentBinding.tvTravelDate1;
        btnTravelStatus = summaryFragmentBinding.btnTravelStatus;
        tvTravelDest1 = summaryFragmentBinding.tvTravelDest1;
        tvDepartureTime1 = summaryFragmentBinding.tvDepartureTime1;
        tvStop1 = summaryFragmentBinding.tvStop1;
        tvArrivalTime1 = summaryFragmentBinding.tvArrivalTime1;
        tvDepartureTime2 = summaryFragmentBinding.tvDepartureTime2;
        tvDestination = summaryFragmentBinding.tvDestination;
        tvArrivalTime2 = summaryFragmentBinding.tvArrivalTime2;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateViewObjects(view);
    }

    private void populateViewObjects(View view) {

        //@TODO need a solid logic for this...for now I am putting in mock data in static views...
        //Assumption: Flight 0 will be the first top flight...any further flights will
        // be additional stops and the last entry will be the destination
        // For now, I am assuming 1 departure, 1 stop and 1 destination
        tvTravelDate1.setText(flights.get(0).getDepartureDate());
        tvTravelDest1.setText(flights.get(0).getDepartureTerminal()); //This should be departure city, not terminal
        tvDepartureTime1.setText(flights.get(0).getDepartureTime());

    }

    public static TravelSummaryFragment newInstance(TripStatus trips) {
        TravelSummaryFragment frag = new TravelSummaryFragment();
        Bundle args = new Bundle();
        args.putParcelable("tripStatus", Parcels.wrap(trips));
        frag.setArguments(args);
        return frag;
    }

    private void setupListeners() {
        btnTravelStatus.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), TravelStatusActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }
}
