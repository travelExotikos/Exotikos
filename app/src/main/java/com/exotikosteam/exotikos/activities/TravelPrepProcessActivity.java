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
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.TSAWebpageFragment;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.trip.TripStatus;

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
        private String tabTitles[] = {"Trip Details", "Checkin List", "Baggage Help", "TSA Webpage" };

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
                return BaggageHelpFragment.newInstance();
            else if (position == 3)
                return TSAWebpageFragment.newInstance();
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
