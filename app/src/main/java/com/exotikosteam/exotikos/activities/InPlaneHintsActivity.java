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
import com.exotikosteam.exotikos.fragments.InPlaneExercisesFragment;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;

import static com.exotikosteam.exotikos.R.id.vpPager;

public class InPlaneHintsActivity extends AppCompatActivity implements OnButtonsClicks {

    ViewPager mViewPager;
    InPlaneHintsPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_plane_hints);

        mPagerAdapter = new InPlaneHintsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(vpPager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomInTransformer());

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(mViewPager);
    }

    @Override
    public void handleButtonsClicks(String buttonName) {
    }

    public class InPlaneHintsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {
                getString(R.string.in_plane_hint_tab_exercises1),
                getString(R.string.in_plane_hint_tab_exercises2),
                getString(R.string.in_plane_hint_tab_exercises3),
                getString(R.string.in_plane_hint_tab_exercises4),
                getString(R.string.in_plane_hint_tab_exercises5),
                getString(R.string.in_plane_hint_tab_exercises6),
                getString(R.string.in_plane_hint_tab_exercises7)};

        public InPlaneHintsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //controls the order and creation of the fragments
        @Override
        public Fragment getItem(int position) {
            if (position > -1 && position < 7) {
                return InPlaneExercisesFragment.newInstance(position);
            }
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
