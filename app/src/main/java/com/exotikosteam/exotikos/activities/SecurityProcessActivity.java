package com.exotikosteam.exotikos.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.exotikosteam.exotikos.adapters.SmartFragmentStatePagerAdapter;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckingHelpFragment;
import com.exotikosteam.exotikos.models.trip.TripStatus;

/**
 * Created by lramaswamy on 11/18/16.
 */

public class SecurityProcessActivity extends AppCompatActivity {


    //return the order of the fragments in the view pager
    public class SecurityCheckinPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {"Checkin List", "Help", "Video" };
        private TripStatus trip;

        public SecurityCheckinPagerAdapter(FragmentManager fm, TripStatus trip) {
            super(fm);
            this.trip = trip;
        }

        //controls the order and creation of the fragments
        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return SecurityCheckinFragment.newInstance(trip);
            else if (position == 1)
                return SecurityCheckingHelpFragment.newInstance(trip);
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
