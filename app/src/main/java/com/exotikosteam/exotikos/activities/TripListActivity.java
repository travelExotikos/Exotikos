package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.FlightListAdapter;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.thirdparty.ItemClickSupport;
import com.exotikosteam.exotikos.thirdparty.SimpleDividerItemDecoration;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class TripListActivity extends AppCompatActivity {

    public static final String TAG = TripListActivity.class.getSimpleName();

    private List<TripStatus> trips;
    private List<Flight> flights;
    private FlightListAdapter flightListAdapter;

    @BindView(R.id.rvTrips) RecyclerView rvTrips;
    @BindView(R.id.fabNewTrip) FloatingActionButton fabNewTrip;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        ButterKnife.bind(this);

        setupRecyclerView();
        setupListeners();

        setSupportActionBar(toolbar);

        fetchTrips();
    }

    private void setupListeners() {
        fabNewTrip.setOnClickListener(v -> {
            // Open create trip activity
            Intent i = new Intent(TripListActivity.this, NewTripActivity.class);
            startActivity(i);
        });

        ItemClickSupport.addTo(rvTrips).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent i = new Intent(TripListActivity.this, MainActivity.class);
                TripStatus trip = getTripForFlight(flights.get(position));
                i.putExtra("trip", Parcels.wrap(trip));
                startActivity(i);
            }
        });
    }

    private TripStatus getTripForFlight(Flight flight) {
        for (TripStatus t: trips) {
            if (t.getFlights().contains(flight)) {
                return t;
            }
        }

        return null;
    }

    private void setupRecyclerView() {
        trips = new ArrayList<>();
        flights = new ArrayList<>();
        flightListAdapter = new FlightListAdapter(this, flights);
        rvTrips.setAdapter(flightListAdapter);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTrips.setLayoutManager(layout);
        rvTrips.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    }

    private void fetchTrips() {
        flights.clear();
        trips = TripStatus.getAll();

        for (TripStatus ts : trips) {
            flights.addAll(ts.getFlights());
        }
        flightListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fetchTrips();
    }
}
