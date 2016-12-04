package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.SmartFragmentStatePagerAdapter;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckingHelpFragment;
import com.exotikosteam.exotikos.utils.Constants;

import static com.exotikosteam.exotikos.R.id.vpPager;

/**
 * Created by lramaswamy on 11/18/16.
 */

public class SecurityProcessActivity extends AppCompatActivity implements OnButtonsClicks {
    ViewPager viewPager;

    SecurityCheckinPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_process);

        pagerAdapter = new SecurityCheckinPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(vpPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new ZoomInTransformer());
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
        if(Constants.GO_TO_SECURITY_VIDEO_HINT.equals(buttonName)) {
            showSecurityCheckinHelpVideoActivity();
        }
    }

    private void showSecurityCheckinHelpVideoActivity() {
        Intent i = new Intent(SecurityProcessActivity.this, SecurityVideoActivity.class);
        startActivity(i);
    }

    //return the order of the fragments in the view pager
    public class SecurityCheckinPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {getString(R.string.sec_check_in_hint_tab_description), getString(R.string.sec_check_in_hint_tab_help)};

        public SecurityCheckinPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //controls the order and creation of the fragments
        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return SecurityCheckinFragment.newInstance(false);
            else if (position == 1)
                return SecurityCheckingHelpFragment.newInstance();
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
