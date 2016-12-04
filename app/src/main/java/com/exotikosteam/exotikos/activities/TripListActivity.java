package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.FlightListAdapter;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.thirdparty.SimpleDividerItemDecoration;
import com.exotikosteam.exotikos.utils.Constants;

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
            startActivityForResult(i, NewTripActivity.REQUEST_FLIGHT_SELECTION);
        });

        flightListAdapter.getItemClickSubject().retry().subscribe(flight -> {
            Intent i = new Intent(TripListActivity.this, TravelStatusActivity.class);
            TripStatus trip = getTripForFlight(flight);
            i.putExtra(Constants.PARAM_TRIP, Parcels.wrap(trip));
            startActivity(i);
        });

        flightListAdapter.getDeleteSubject().retry().subscribe(flight -> {
            int index = flights.indexOf(flight);
            flights.remove(flight);
            flightListAdapter.notifyItemRemoved(index);
            TripStatus trip = getTripForFlight(flight);
            TripStatus.deleteTrip(trip);
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
