package com.exotikosteam.exotikos.fragments;

import android.content.Intent;
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
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lramaswamy on 11/11/16.
 */

public class TravelStatusFragment extends Fragment {

    TripStatus trips;
    List<Flight> flights;
    private Unbinder unbinder;

    @BindView(R.id.btnTravelStatus) Button btnTravelStatus;
    @BindView(R.id.tvTravelDate1) TextView tvTravelDate1;
    @BindView(R.id.tvTravelDest1) TextView tvTravelDest1;
    @BindView(R.id.tvDepartureTime1) TextView tvDepartureTime1;
    @BindView(R.id.tvStop1) TextView tvStop1;
    @BindView(R.id.tvArrivalTime1) TextView tvArrivalTime1;
    @BindView(R.id.tvDepartureTime2) TextView tvDepartureTime2;
    @BindView(R.id.tvDestination) TextView tvDestination;
    @BindView(R.id.tvArrivalTime2) TextView tvArrivalTime2;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trips = (TripStatus) Parcels.unwrap(getArguments().getParcelable("tripStatus"));
        flights = trips.getFlights();
        //mockData();
    }

    private void mockData() {
        trips = TripStatus.newMockInstance();
        this.flights = trips.getFlights();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.travel_summary_fragment, parent, false);
        unbinder = ButterKnife.bind(this, v);
        setupListeners();
        return  v;
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
        tvStop1.setText(flights.get(1).getArrivalTerminal());
        tvArrivalTime1.setText(flights.get(1).getArrivalTime());
        tvDepartureTime2.setText(flights.get(1).getDepartureTime());
        tvDestination.setText(flights.get(2).getArrivalTerminal());
        tvArrivalTime2.setText(flights.get(2).getArrivalTime());
    }

    public static TravelStatusFragment newInstance(TripStatus trips) {
        TravelStatusFragment frag = new TravelStatusFragment();
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
        unbinder.unbind();
    }
}
