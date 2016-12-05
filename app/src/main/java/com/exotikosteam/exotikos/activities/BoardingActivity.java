package com.exotikosteam.exotikos.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.SmartFragmentStatePagerAdapter;
import com.exotikosteam.exotikos.fragments.BoardingGateHelpFragment;
import com.exotikosteam.exotikos.fragments.BoardingLineInfoHelpFragment;

import static com.exotikosteam.exotikos.R.id.vpPager;

/**
 * Created by lramaswamy on 12/4/16.
 */

public class BoardingActivity extends AppCompatActivity {
    ViewPager mViewPager;
    BoardingActivity.BoardingPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_process);

        mPagerAdapter = new BoardingActivity.BoardingPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(vpPager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomInTransformer());
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(mViewPager);
    }


    public class BoardingPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {
                getString(R.string.gate),
                getString(R.string.boardingInfoHd)};

        public BoardingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //controls the order and creation of the fragments
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BoardingGateHelpFragment.newInstance();
                case 1:
                    return BoardingLineInfoHelpFragment.newInstance();
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
