package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.BoardingGateFragment;
import com.exotikosteam.exotikos.fragments.CardViewFragment;
import com.exotikosteam.exotikos.fragments.CheckInFragment;
import com.exotikosteam.exotikos.fragments.DestinationFragment;
import com.exotikosteam.exotikos.fragments.FragmentTravelScan;
import com.exotikosteam.exotikos.fragments.InPlaneFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckingHelpFragment;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.airport.Airport;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.Utils;
import com.exotikosteam.exotikos.webservices.flightstats.AirportsApiEndpoint;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;

public class TravelStatusActivity extends ExotikosBaseActivity implements FragmentTravelScan.OnScanCompletedListener,
        OnButtonsClicks {

    public static final String TAG = TravelStatusActivity.class.getSimpleName();

    private List<CardViewFragment> cardViewFragmentList;
    TripStatus trip;
    Flight flight;
    String appId;
    String appKey;
    Airport departureAirport;
    AirportsApiEndpoint airportsService;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_status);

        prepareDrawableMenu();

        cardViewFragmentList = new ArrayList<>(5);
        trip = Parcels.unwrap(getIntent().getParcelableExtra("trip"));

        appId = ((ExotikosApplication)getApplication()).getFligthStatsAppID();
        appKey = ((ExotikosApplication)getApplication()).getFligthStatsAppKey();
        flight = trip.getFlights().get(trip.getCurrentFlight());
        getAirport(flight.getDepartureAirportIATA());

        Date departureTime = Utils.parseFlightstatsDate(flight.getDepartureTime());

        createTravelPrepCard();
        createCheckinCard(flight.getDepartureTimeUTC());
        createSecurityCheckinCard(flight.getDepartureTimeUTC());
        createBoardingGateCard(flight.getDepartureTimeUTC());
        createInPlaneCard(flight.getArrivalTimeUTC());
        createDestinationCard(flight);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flCard1, cardViewFragmentList.get(0));
        ft.replace(R.id.flCard2, cardViewFragmentList.get(1));
        ft.replace(R.id.flCard3, cardViewFragmentList.get(2));
        ft.replace(R.id.flCard4, cardViewFragmentList.get(3));
        ft.replace(R.id.flCard5, cardViewFragmentList.get(4));
        ft.replace(R.id.flCard6, cardViewFragmentList.get(5));
        ft.commit();

        setupListeners();
    }

    private void createDestinationCard(Flight flight) {
        cardViewFragmentList.add(5, CardViewFragment.newInstance(Constants.DESTINATION,
                Utils.getTimeDeltaFromCurrent(
                        Utils.parseFlightstatsDate(flight.getArrivalTime())), isStepActive(5)));
        DestinationFragment destinationFragment = DestinationFragment.newInstance(trip);
        cardViewFragmentList.get(5).setFragment(destinationFragment);
    }

    private void createInPlaneCard(Date arrivalTime) {
        cardViewFragmentList.add(4, CardViewFragment.newInstance("In Plane", Utils.getTimeDeltaFromCurrent(arrivalTime), isStepActive(4)));
        InPlaneFragment fragment = InPlaneFragment.newInstance(trip);
        cardViewFragmentList.get(4).setFragment(fragment);
    }

    private void createBoardingGateCard(Date departureTime) {
        cardViewFragmentList.add(3, CardViewFragment.newInstance(Constants.BOARDING, Utils.getTimeDeltaFromCurrent(departureTime), isStepActive(3)));
        BoardingGateFragment boardingGateFragment = BoardingGateFragment.newInstance(trip);
        cardViewFragmentList.get(3).setFragment(boardingGateFragment);
    }

    private void createSecurityCheckinCard(Date departureTime) {
        cardViewFragmentList.add(2, CardViewFragment.newInstance(Constants.SECURITY_CHECKING, Utils.getTimeDeltaFromCurrent(departureTime), isStepActive(2)));
        SecurityCheckinFragment securityCheckinFragment = SecurityCheckinFragment.newInstance(true);
        cardViewFragmentList.get(2).setFragment(securityCheckinFragment);
    }

    private void createCheckinCard(Date departureTime) {
        cardViewFragmentList.add(1, CardViewFragment.newInstance(Constants.CHECKIN, Utils.getCheckinTimeDelta(departureTime), isStepActive(1)));
        CheckInFragment checkinFragment = CheckInFragment.newInstance(trip);
        cardViewFragmentList.get(1).setFragment(checkinFragment);
    }

    private void createTravelPrepCard() {
        cardViewFragmentList.add(0, CardViewFragment.newInstance(Constants.TRAVEL_PREP, "", isStepActive(0)));
        TravelPrepFragment prepFragment = TravelPrepFragment.newInstance(trip, true);
        cardViewFragmentList.get(0).setFragment(prepFragment);
    }

    private boolean isStepActive(int step) {
        return step == trip.getFlightStep().ordinal();
    }

    private void setupListeners() {
        Observable.merge(
                cardViewFragmentList.get(0).getTitleClickSubject(),
                cardViewFragmentList.get(1).getTitleClickSubject(),
                cardViewFragmentList.get(2).getTitleClickSubject(),
                cardViewFragmentList.get(3).getTitleClickSubject(),
                cardViewFragmentList.get(4).getTitleClickSubject(),
                cardViewFragmentList.get(5).getTitleClickSubject()
        ).subscribe(fragment -> ((CardViewFragment)fragment).toggle());

    }

    @Override
    public void getTripInstance(TripStatus trip) {
        this.trip = trip;
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
        if (Constants.GO_TO_AIRPORT_PAGE.equals(buttonName)) {
            showAiportLocationPage();
        }
        if (Constants.GO_TO_SCAN_PAGE.equals(buttonName)) {
            showTravelScanFragment();
        }
        if (Constants.GO_TO_CHECK_IN_HINTS.equals(buttonName)) {
            showCheckInHintsActivity();
        }
        if (Constants.GO_TO_IN_PLANE_HINTS.equals(buttonName)) {
            showInPlaneHintsActivity();
        }
        if (Constants.GO_TO_SECURITY_CHECKING.equals(buttonName)) {
            showSecurityCheckinActivity();
        }
        if(Constants.GO_TO_PREP_PAGE.equals(buttonName)) {
            showTravelPrepActivity();
        }
    }

    private void showSecurityCheckinActivity() {
        Intent i = new Intent(this, SecurityProcessActivity.class);
        startActivity(i);
    }

    private void showCheckInHintsActivity() {
        Intent i = new Intent(this, CheckInHintsActivity.class);
        startActivity(i);
    }

    private void showInPlaneHintsActivity() {
        Intent i = new Intent(this, InPlaneHintsActivity.class);
        startActivity(i);
    }

    private void showTravelPrepActivity() {
        Intent i = new Intent(this, TravelPrepProcessActivity.class);
        i.putExtra("trip", Parcels.wrap(trip));
        startActivity(i);
    }

    private void showSecurityCheckinHelpFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flCard3, SecurityCheckingHelpFragment.newInstance());
        ft.commit();
    };

    private void getAirport(String departureAirportIATA) {
        airportsService = ((ExotikosApplication) getApplication()).getAirportsService();
        airportsService.getByIATACode(departureAirportIATA, appId, appKey)
                .flatMapIterable(airportsResponse -> airportsResponse.getAirports())
                .subscribe(
                        airport -> setDepartureAirport(airport),
                        throwable -> Log.e(TAG, "Error getting airline", throwable),
                        () -> Log.i(TAG, "Done with airline by IATA")
                );
    }

    private void setDepartureAirport(Airport airport) {
        departureAirport = airport;
    }

    private void showAiportLocationPage() {
        double latitude = departureAirport.getLatitude();
        double longitude = departureAirport.getLongitude();

        String label = departureAirport.getName();
        String uriBegin = "geo:" + latitude + "," + longitude + "(" + label + ")";
        String query1 = departureAirport.getStreet1() + " " + departureAirport.getStreet2() +
                " " + departureAirport.getCity() + " " + departureAirport.getStateCode() + " "
                + departureAirport.getPostalCode() + " " + departureAirport.getCountryName();
        String encodedQuery = Uri.encode(query1);
        String uriString = uriBegin + "?q=" + encodedQuery;
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        intent.setPackage(Constants.GOOGLE_MAP_PACKAGE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void showTravelScanFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgPlaceholder, FragmentTravelScan.newInstance(this.trip));
        ft.commit();
    }
}
