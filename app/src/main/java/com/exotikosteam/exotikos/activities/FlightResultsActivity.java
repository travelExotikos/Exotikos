package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.FlightResultsAdapter;
import com.exotikosteam.exotikos.models.airline.Airline;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;
import com.exotikosteam.exotikos.models.trip.TripStatus;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FlightResultsActivity extends AppCompatActivity {

    @BindView(R.id.lvFlights) ListView lvFlights;

    private List<ScheduledFlight> flights;
    private FlightResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_results);
        ButterKnife.bind(this);

        flights = new ArrayList<>();

        // Read arguments
        Airline airline = Parcels.unwrap(getIntent().getParcelableExtra("airline"));
        int year = getIntent().getIntExtra("year", -1);
        int month = getIntent().getIntExtra("month", -1);
        int day = getIntent().getIntExtra("day", -1);
        String flightNumber = getIntent().getStringExtra("flightNumber");

        ExotikosApplication app = ((ExotikosApplication)getApplicationContext());
        app.getFlightScheduleService().getByDepartingDate(airline.getFs(),
                flightNumber,
                year,
                month,
                day,
                app.getFligthStatsAppID(),
                app.getFligthStatsAppKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flightScheduleResponse -> {
                    flights.addAll(flightScheduleResponse.getScheduledFlights());
                    adapter.notifyDataSetChanged();
                });

        setupListView();
        setupListeners();
    }

    private void setupListView() {
        adapter = new FlightResultsAdapter(this, flights);
        lvFlights.setAdapter(adapter);
    }

    private void setupListeners() {
        lvFlights.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent result = new Intent();

                ScheduledFlight selectedFlight = flights.get(i);

                // TODO is only handling one flight per schedule
                ArrayList<ScheduledFlight> flights= new ArrayList<>();
                flights.add(selectedFlight);

                TripStatus trip = TripStatus.fromScheduledFlights(flights);
                result.putExtra("trip", Parcels.wrap(trip));
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
