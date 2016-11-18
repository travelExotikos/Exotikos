package com.exotikosteam.exotikos.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.BoardingGateFragment;
import com.exotikosteam.exotikos.fragments.CardViewFragment;
import com.exotikosteam.exotikos.fragments.DestinationFragment;
import com.exotikosteam.exotikos.fragments.SecurityCheckinFragment;
import com.exotikosteam.exotikos.fragments.TravelPrepFragment;
import com.exotikosteam.exotikos.models.trip.TripStatus;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class TravelStatusActivity extends AppCompatActivity {

    private List<CardViewFragment> cardViewFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_status);
        cardViewFragmentList = new ArrayList<>(5);
        TripStatus trip = Parcels.unwrap(getIntent().getParcelableExtra("trip"));

        cardViewFragmentList.add(0, CardViewFragment.newInstance("Travel Preparation", "Test", true));
        TravelPrepFragment prepFragment = TravelPrepFragment.newInstance(trip);
        cardViewFragmentList.get(0).setFragment(prepFragment);

        cardViewFragmentList.add(1, CardViewFragment.newInstance("Checkin", "Test", true));
        //Change this to Checking Fragment once ada is done
        SecurityCheckinFragment checkinFragment = SecurityCheckinFragment.newInstance(trip);
        cardViewFragmentList.get(1).setFragment(checkinFragment);

        cardViewFragmentList.add(2, CardViewFragment.newInstance("Security Checkin", "Test", true));
        SecurityCheckinFragment securityCheckinFragment = SecurityCheckinFragment.newInstance(trip);
        cardViewFragmentList.get(2).setFragment(securityCheckinFragment);

        cardViewFragmentList.add(3, CardViewFragment.newInstance("Boarding", "Test", true));
        BoardingGateFragment boardingGateFragment = BoardingGateFragment.newInstance(trip);
        cardViewFragmentList.get(3).setFragment(boardingGateFragment);

        cardViewFragmentList.add(4, CardViewFragment.newInstance("Destination", "Test", true));
        DestinationFragment destinationFragment = DestinationFragment.newInstance(trip);
        cardViewFragmentList.get(4).setFragment(destinationFragment);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.add(R.id.llContainer, fragment1, "fragment_one");
        ft.add(R.id.flCard1, cardViewFragmentList.get(0), "fragment_one");

        ft.add(R.id.flCard1, cardViewFragmentList.get(1), "fragment_two");

        ft.add(R.id.flCard1, cardViewFragmentList.get(2), "fragment_three");

        ft.add(R.id.flCard1, cardViewFragmentList.get(3), "fragment_four");
        ft.commit();
//
//        ft.replace(R.id.flCard5, cardViewFragmentList.get(4));
//        ft.commit();

        setupListeners();
    }

    private void setupListeners() {
        Observable.merge(
                cardViewFragmentList.get(0).getTitleClickSubject(),
                cardViewFragmentList.get(1).getTitleClickSubject(),
                cardViewFragmentList.get(2).getTitleClickSubject(),
                cardViewFragmentList.get(3).getTitleClickSubject(),
                cardViewFragmentList.get(4).getTitleClickSubject()
        ).subscribe(fragment -> ((CardViewFragment)fragment).toggle());
    }
}