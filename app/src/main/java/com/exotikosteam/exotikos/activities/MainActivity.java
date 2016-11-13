package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment.OnButtonsClicks;
import com.exotikosteam.exotikos.fragments.TravelScanFragment;
import com.exotikosteam.exotikos.fragments.TravelSummaryFragment;
import com.exotikosteam.exotikos.models.trip.FlightStep;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.webservices.flightstats.AirlinesApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.AirportsApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.SchedulesApiEndpoint;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TravelScanFragment.OnScanCompletedListener,
                                                                OnButtonsClicks,
                                                                GoogleApiClient.ConnectionCallbacks,
                                                                GoogleApiClient.OnConnectionFailedListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private TripStatus trip;
    private  FlightStep fStep = FlightStep.PREPARATION;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        setupStarterFragment();
        setupGoogleClient();

    }

    private void setupGoogleClient() {
        // Create the location client to start receiving updates
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }

    private void setupStarterFragment() {
        //TODO the temporary code. Remove after implement PREPARATION step
        this.trip = new TripStatus();
        this.trip.setFlightStep(FlightStep.PREPARATION);
        this.trip.save();
        Log.d(TAG, String.format("trip saved %s, %d", this.trip.getFlightStep(), this.trip.getId()));
        //TODO end of temp code
        //Depending on whether the user has scanned the boarding pass or not,
        // we show the travel summary or the scanner fragment here
        if (fStep == FlightStep.PREPARATION) {
            //TODO trip should be created in this step will create temporary trip for test
            showTravelPreparationFragment();
        } if (fStep == FlightStep.CHECKIN_IN_DONE) {//replace with some logic to see if the use has created a trip before
            showTravelStatusFragment();
        } if (fStep == FlightStep.CHECK_IN) {
            showTravelScanFragment();
        }
    }

    private void showTravelPreparationFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, TravelPrepFragment.newInstance());
        ft.commit();
    }

    private void showTravelStatusFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, TravelSummaryFragment.newInstance(trip));
        ft.commit();

    }

    private void showTravelScanFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, TravelScanFragment.newInstance(this.trip));
        ft.commit();

    }

    @Override
    public void getTripInstance(TripStatus trip) {
        this.trip = trip;
        showTravelStatusFragment();
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
        if(buttonName.equals("LaunchScanPage")) {
            showTravelScanFragment();
        }
        if(buttonName.equals("LaunchAirportPage")) {
            showAiportLocationPage(latLng);
        }
    }

    private void showAiportLocationPage(LatLng latLng) {
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
