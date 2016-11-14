package com.exotikosteam.exotikos.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.AirlinePickDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTripActivity extends AppCompatActivity {

    @BindView(R.id.btnSelectAirline) Button btnSelectAirline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);

        setupListeners();
    }

    private void setupListeners() {
        btnSelectAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                AirlinePickDialogFragment pickAirlineDialogFragment = AirlinePickDialogFragment.newInstance();
                pickAirlineDialogFragment.show(fm, "fragment_dialog_pick_airline");
            }
        });
    }
}
