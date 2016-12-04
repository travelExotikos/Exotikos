package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.SmartFragmentStatePagerAdapter;
import com.exotikosteam.exotikos.fragments.BaggageHelpFragment;
import com.exotikosteam.exotikos.fragments.RestrictedItemsFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment;
import com.exotikosteam.exotikos.fragments.WebpageFragment;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.airport.Airport;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.Utils;
import com.exotikosteam.exotikos.webservices.flightstats.AirportsApiEndpoint;

import org.parceler.Parcels;

import static com.exotikosteam.exotikos.R.id.vpPager;
import static com.exotikosteam.exotikos.utils.Constants.GO_TO_SECURITY_VIDEO_HINT;

/**
 * Created by lramaswamy on 11/18/16.
 */

public class TravelPrepProcessActivity extends AppCompatActivity implements OnButtonsClicks {

    public static final String TAG = TravelPrepProcessActivity.class.getSimpleName();

    ViewPager viewPager;
    TripStatus trip;
    TravelPrepPagerAdapter pagerAdapter;
    Airport departureAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_process);
        trip = Parcels.unwrap(getIntent().getParcelableExtra("trip"));
        pagerAdapter = new TravelPrepPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(vpPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new ZoomInTransformer());
        Flight flight = trip.getFlights().get(trip.getCurrentFlight());
        getAirport(flight.getDepartureAirportIATA());

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
        if(buttonName.equals(GO_TO_SECURITY_VIDEO_HINT)) {
            showSecurityCheckinHelpVideoActivity();
        }
        if(buttonName.equals(Constants.GO_TO_AIRPORT_PAGE)) {
            Utils.showAiportLocationPage(departureAirport, this, getApplicationContext());
        }
    }

    private void showSecurityCheckinHelpVideoActivity() {
        Intent i = new Intent(TravelPrepProcessActivity.this, SecurityVideoActivity.class);

        startActivity(i);
    }

    private void getAirport(String departureAirportIATA) {
        AirportsApiEndpoint airportsService = ((ExotikosApplication) getApplication()).getAirportsService();
        airportsService.getByIATACode(departureAirportIATA, ((ExotikosApplication)getApplication()).getFligthStatsAppID(),
                ((ExotikosApplication)getApplication()).getFligthStatsAppKey())
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

    //return the order of the fragments in the view pager
    public class TravelPrepPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {Constants.TRIP_DETAILS, Constants.CHECKIN_LIST,
                Constants.RESTRICTED, Constants.BAGGAGE_HELP, Constants.TSA_WEBLBL, Constants.RESTRICTED_PAGE };

        public TravelPrepPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //controls the order and creation of the fragments
        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return TravelPrepFragment.newInstance(trip, false);
            else if (position == 1)
                return SecurityCheckinFragment.newInstance(false);
            else if (position == 2)
                return RestrictedItemsFragment.newInstance();
            else if (position == 3)
                return BaggageHelpFragment.newInstance();
            else if (position == 4)
                return WebpageFragment.newInstance(Constants.TSA_WEBPAGE);
            else if (position == 5)
                return WebpageFragment.newInstance(Constants.TSA_RESTRICTED);
            else
                return null;
        }

        //returns the Title of the tab
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}
