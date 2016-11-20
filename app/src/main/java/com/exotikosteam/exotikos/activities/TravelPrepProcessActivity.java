package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.SmartFragmentStatePagerAdapter;
import com.exotikosteam.exotikos.fragments.BaggageHelpFragment;
import com.exotikosteam.exotikos.fragments.RestrictedItemsFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment;
import com.exotikosteam.exotikos.fragments.WebpageFragment;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;

import org.parceler.Parcels;

import static com.exotikosteam.exotikos.R.id.vpPager;

/**
 * Created by lramaswamy on 11/18/16.
 */

public class TravelPrepProcessActivity extends AppCompatActivity implements OnButtonsClicks {
    ViewPager viewPager;
    TripStatus trip;
    TravelPrepPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_process);
        trip = Parcels.unwrap(getIntent().getParcelableExtra("trip"));
        pagerAdapter = new TravelPrepPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(vpPager);
        viewPager.setAdapter(pagerAdapter);

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
        if(buttonName.equals("LaunchSecurityCheckinVideoHelpPage")) {
            showSecurityCheckinHelpVideoActivity();
        }
    }

    private void showSecurityCheckinHelpVideoActivity() {
        Intent i = new Intent(TravelPrepProcessActivity.this, SecurityVideoActivity.class);
        startActivity(i);
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
