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
import com.exotikosteam.exotikos.fragments.CheckInHintDescriptionFragment;
import com.exotikosteam.exotikos.fragments.RestrictedItemsFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckingHelpFragment;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.utils.Constants;

import static com.exotikosteam.exotikos.R.id.vpPager;

public class CheckInHintsActivity extends AppCompatActivity implements OnButtonsClicks {

    ViewPager mViewPager;
    CheckInHintsActivity.CheckInHintsPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_hints);

        mPagerAdapter = new CheckInHintsActivity.CheckInHintsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(vpPager);
        mViewPager.setAdapter(mPagerAdapter);

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(mViewPager);
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
        if (Constants.GO_TO_SECURITY_VIDEO_HINT.equals(buttonName)) {
            Intent i = new Intent(CheckInHintsActivity.this, SecurityVideoActivity.class);
            startActivity(i);
        }
    }

    public class CheckInHintsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {
                getString(R.string.check_in_hint_tab_description),
                getString(R.string.check_in_hint_tab_next_step),
                getString(R.string.sec_check_in_hint_tab_baggage),
                getString(R.string.sec_check_in_hint_tab_restricted)};

        public CheckInHintsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //controls the order and creation of the fragments
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CheckInHintDescriptionFragment.newInstance();
                case 1:
                    return SecurityCheckingHelpFragment.newInstance();
                case 2:
                    return BaggageHelpFragment.newInstance();
                case 3:
                    return RestrictedItemsFragment.newInstance();
                default:
                    return null;
            }
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
