package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.FlightListAdapter;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.thirdparty.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class TripListActivity extends AppCompatActivity {

    public static final String TAG = TripListActivity.class.getSimpleName();

    private List<TripStatus> trips = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();
    private FlightListAdapter flightListAdapter;

    @BindView(R.id.rvTrips) RecyclerView rvTrips;
    @BindView(R.id.fabNewTrip) FloatingActionButton fabNewTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        ButterKnife.bind(this);

        setupRecyclerView();
        setupListeners();

        trips = TripStatus.getAll();
        flights = new ArrayList<>();

        for (TripStatus ts : trips) {
            flights.addAll(ts.getFlights());
        }
        flightListAdapter.notifyDataSetChanged();
    }

    private void setupListeners() {
        fabNewTrip.setOnClickListener(v -> {
            // Open create trip activity
            Intent i = new Intent(TripListActivity.this, NewTripActivity.class);
            startActivity(i);
        });
    }

    private void setupRecyclerView() {
        flightListAdapter = new FlightListAdapter(flights, this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTrips.setLayoutManager(layout);
        rvTrips.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    }
}
