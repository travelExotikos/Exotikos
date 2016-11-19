package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.FragmentTravelScan;
import com.exotikosteam.exotikos.fragments.FragmentTravelSummary;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.FlightStep;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.webservices.flightstats.AirlinesApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.AirportsApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.SchedulesApiEndpoint;

import org.parceler.Parcels;

import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends ExotikosBaseActivity implements FragmentTravelScan.OnScanCompletedListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private TripStatus trip;
    private  FlightStep fStep = FlightStep.PREPARATION;
    AirlinesApiEndpoint airlinesService;
    AirportsApiEndpoint airportsService;
    FlightStatusApiEndpoint flightStatusService;
    SchedulesApiEndpoint flightScheduleService;
    String appId;
    String appKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trip = Parcels.unwrap(getIntent().getParcelableExtra(Constants.PARAM_TRIP));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        prepareDrawableMenu();

        appId = ((ExotikosApplication)getApplication()).getFligthStatsAppID();
        appKey = ((ExotikosApplication)getApplication()).getFligthStatsAppKey();

        airlinesService = ((ExotikosApplication) getApplication()).getAirlinesService();
        airportsService = ((ExotikosApplication) getApplication()).getAirportsService();
        flightStatusService = ((ExotikosApplication) getApplication()).getFlightStatusService();
        flightScheduleService = ((ExotikosApplication) getApplication()).getFlightScheduleService();

        // Get single airline using ICAO code
        airlinesService.getByICAOCode("AAL", appId, appKey)
                .concatMapIterable(airlinesResponse -> airlinesResponse.getAirlines())
                .subscribe(
                        airline -> Log.i(TAG, airline.getName()),
                        throwable -> Log.e(TAG, "Error getting airline", throwable),
                        () -> Log.i(TAG, "Done with airline by ICAO")
                );

        // Get single airline using IATA code
        airlinesService.getByIATACode("AA", appId, appKey)
                .concatMapIterable(airlinesResponse -> airlinesResponse.getAirlines())
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

        List<TripStatus> trips = TripStatus.getAll();
        for (TripStatus t: trips) {
            Log.i(TAG, String.format("tripId = %d,  #fligts = %d", t.getId(), t.getFlights().size()));
        }
        List<Flight> flights = Flight.getAll();
        for (Flight f: flights) {
            Log.i(TAG, String.format("flightId = %d,  tripId = %d", f.getId(), f.getTripId()));
        }
        //setupStarterFragment();
        showCardActivity();
    }

    private void setupStarterFragment() {
        //TODO the step statuses are for card view
        if (fStep == FlightStep.PREPARATION) {
            showCardActivity();
        } if (fStep == FlightStep.CHECKIN_IN_DONE) {
            showSecurityCheckinFragment();
        } if (fStep == FlightStep.CHECK_IN) {
            showTravelScanFragment();
        }
    }

    private void showTravelStatusFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, FragmentTravelSummary.newInstance(trip));
        ft.commit();

    }

    private void showTravelScanFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, FragmentTravelScan.newInstance(this.trip));
        ft.commit();
    }

    private void showSecurityCheckinFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, SecurityCheckinFragment.newInstance(false));
        ft.commit();
    }

    @Override
    public void getTripInstance(TripStatus trip) {
        this.trip = trip;
        showTravelStatusFragment();
    }

    private void showCardActivity() {
        Intent i = new Intent(MainActivity.this, TravelStatusActivity.class);
        i.putExtra("trip", Parcels.wrap(this.trip));
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
