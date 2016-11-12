package com.exotikosteam.exotikos.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.TravelScanFragment;
import com.exotikosteam.exotikos.fragments.TravelStatusFragment;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.webservices.flightstats.AirlinesApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.AirportsApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.SchedulesApiEndpoint;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TravelScanFragment.OnScanCompletedListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private TripStatus trip;
    private boolean tripExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripExists = false;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String appId = ((ExotikosApplication)getApplication()).getFligthStatsAppID();
        String appKey = ((ExotikosApplication)getApplication()).getFligthStatsAppKey();

        AirlinesApiEndpoint airlinesService = ((ExotikosApplication)getApplication()).getFlightstatsRetrofit()
                .create(AirlinesApiEndpoint.class);

        AirportsApiEndpoint airportsService = ((ExotikosApplication)getApplication()).getFlightstatsRetrofit()
                .create(AirportsApiEndpoint.class);

        FlightStatusApiEndpoint flightStatusService = ((ExotikosApplication)getApplication()).getFlightstatsRetrofit()
                .create(FlightStatusApiEndpoint.class);

        SchedulesApiEndpoint flightScheduleService = ((ExotikosApplication)getApplication()).getFlightstatsRetrofit()
                .create(SchedulesApiEndpoint.class);

        // Get the list of all airlines
//        airlinesService.getAll(appId, appKey)
//                .flatMapIterable(airlinesResponse -> airlinesResponse.getAirlines())
//                .subscribe(
//                        airline -> Log.i(TAG, airline.getName()),
//                        throwable -> Log.e(TAG, "Error getting airlines", throwable),
//                        () -> Log.i(TAG, "Done with airlines")
//                );

        // Get single airline using ICAO code
        airlinesService.getByICAOCode("AAL", appId, appKey)
                .map(airlinesResponse -> airlinesResponse.getAirline())
                .subscribe(
                        airline -> Log.i(TAG, airline.getName()),
                        throwable -> Log.e(TAG, "Error getting airline", throwable),
                        () -> Log.i(TAG, "Done with airline by ICAO")
                );

        // Get single airline using IATA code
        airlinesService.getByIATACode("AA", appId, appKey)
                .map(airlinesResponse -> airlinesResponse.getAirline())
                .subscribe(
                        airline -> Log.i(TAG, airline.getName()),
                        throwable -> Log.e(TAG, "Error getting airline", throwable),
                        () -> Log.i(TAG, "Done with airline by IATA")
                );

        // Get airports by city
        airportsService.getByCityCode("SFO", appId, appKey)
                .flatMapIterable(airportsResponse -> airportsResponse.getAirports())
                .subscribe(
                        airport -> Log.i(TAG, airport.getName()),
                        throwable -> Log.e(TAG, "Error getting airport", throwable),
                        () -> Log.i(TAG, "Done with airport by city")
                );

        // Get airport by ICAO code
        airportsService.getByICAOCode("KSFO", appId, appKey)
                .flatMapIterable(airportsResponse -> airportsResponse.getAirports())
                .subscribe(
                        airport -> Log.i(TAG, airport.getName()),
                        throwable -> Log.e(TAG, "Error getting airport", throwable),
                        () -> Log.i(TAG, "Done with airport by city")
                );

        // Get flight status for British Airways BA287 2016-11-11
        flightStatusService.getByDepartingDate("BA", "287", 2016, 11, 11, appId, appKey)
                .subscribe(
                        statusResponse -> {
                            Log.i(TAG, statusResponse.getAppendix().getAirlines().toString());
                            Log.i(TAG, statusResponse.getAppendix().getAirports().toString());
                            Log.i(TAG, statusResponse.getAppendix().getEquipments().toString());
                            Log.i(TAG, statusResponse.getFlightStatuses().toString());
                        },
                        throwable -> Log.e(TAG, "Error getting status", throwable),
                        () -> Log.i(TAG, "Done with status")
                );

        // Get flight schedule for British Airways BA287 2016-12-10
        flightScheduleService.getByDepartingDate("BA", "287", 2016, 11, 11, appId, appKey)
                .subscribe(
                        statusResponse -> {
                            Log.i(TAG, statusResponse.getAppendix().getAirlines().toString());
                            Log.i(TAG, statusResponse.getAppendix().getAirports().toString());
                            Log.i(TAG, statusResponse.getAppendix().getEquipments().toString());
                            Log.i(TAG, statusResponse.getScheduledFlights().toString());
                        },
                        throwable -> Log.e(TAG, "Error getting schedule", throwable),
                        () -> Log.i(TAG, "Done with schedule")
                );


        setupStarterFragment();

    }

    private void setupStarterFragment() {
        //Depending on whether the user has scanned the boarding pass or not,
        // we show the travel summary or the scanner fragment here
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(tripExists != false) //replace with some logic to see if the use has created a trip before
            showTravelStatusFragment(ft);
        else
            showTravelScanFragment(ft);
    }

    private void showTravelStatusFragment(FragmentTransaction ft) {
        ft.replace(R.id.frgPlaceholder, TravelStatusFragment.newInstance(trip));
        ft.commit();

    }

    private void showTravelScanFragment(FragmentTransaction ft) {
        ft.replace(R.id.frgPlaceholder, TravelScanFragment.newInstance("Travel Status"));
        ft.commit();

    }

    @Override
    public void getTripInstance(TripStatus trip) {
        this.trip = trip;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        showTravelStatusFragment(ft);
    }
}
