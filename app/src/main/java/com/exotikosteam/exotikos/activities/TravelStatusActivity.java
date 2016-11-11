package com.exotikosteam.exotikos.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.SampleCardFragment;

import rx.Observable;

public class TravelStatusActivity extends AppCompatActivity {

    private SampleCardFragment c1, c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_status);

        c1 = SampleCardFragment.newInstance("Card 1", "Test", true);
        c2 = SampleCardFragment.newInstance("Card 2", "Test", false);
        c3 = SampleCardFragment.newInstance("Card 3", "Test", true);
        c4 = SampleCardFragment.newInstance("Card 4", "Test", true);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flCard1, c1);
        ft.replace(R.id.flCard2, c2);
        ft.replace(R.id.flCard3, c3);
        ft.replace(R.id.flCard4, c4);
        ft.commit();

        setupListeners();
    }

    private void setupListeners() {
        Observable.merge(
                c1.getTitleClickSubject(),
                c2.getTitleClickSubject(),
                c3.getTitleClickSubject(),
                c4.getTitleClickSubject()
        ).subscribe(fragment -> fragment.toggle());
    }
}
