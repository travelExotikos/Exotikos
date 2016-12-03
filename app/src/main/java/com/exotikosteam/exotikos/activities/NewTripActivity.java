package com.exotikosteam.exotikos.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.fragments.AirlinePickDialogFragment;
import com.exotikosteam.exotikos.models.BoardingPassScan;
import com.exotikosteam.exotikos.models.airline.Airline;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.PDF417Utils;

import org.parceler.Parcels;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTripActivity extends AppCompatActivity {

    public static final int REQUEST_FLIGHT_SELECTION = 20;

    private Airline mSelectedAirline;

    @BindView(R.id.btnSelectAirline) Button btnSelectAirline;
    @BindView(R.id.btnSelectFlights) Button btnSelectFlights;
    @BindView(R.id.etFlightNumber) EditText etFlightNumber;
    @BindView(R.id.dpDepartureDate) DatePicker dpDepartureDate;
    @BindView(R.id.btnScan) Button btnScan;

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_new_trip, null);
        setContentView(rootView);
        ButterKnife.bind(this);

        setupListeners();

        // only dates starting tomorrow
        dpDepartureDate.setMinDate(System.currentTimeMillis() - 1000);
    }

    private void setupListeners() {
        btnSelectAirline.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            AirlinePickDialogFragment pickAirlineDialogFragment = AirlinePickDialogFragment.newInstance();
            pickAirlineDialogFragment.getSelectSubject()
                    .subscribe(airline -> {
                        mSelectedAirline = airline;
                        btnSelectAirline.setText(airline.getName());
                        Glide.with(NewTripActivity.this)
                                .load(airline.getIconUrl())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                        btnSelectAirline.setCompoundDrawablesWithIntrinsicBounds(
                                                new BitmapDrawable(getResources(), bitmap), null, null, null);
                                    }
                                });
                        pickAirlineDialogFragment.dismiss();
                    });
            pickAirlineDialogFragment.show(fm, "fragment_dialog_pick_airline");
        });

        btnSelectFlights.setOnClickListener(view -> {
            showResultsForFlightData(
                    mSelectedAirline.getIata(),
                    etFlightNumber.getText().toString(),
                    dpDepartureDate.getYear(),
                    dpDepartureDate.getMonth() + 1,
                    dpDepartureDate.getDayOfMonth()
            );
        });

        btnScan.setOnClickListener(v -> {
            PDF417Utils.launchCameraView(NewTripActivity.this);
        });

    }

    private void parsingError() {
        Snackbar.make(rootView, R.string.scan_parsing_error, Snackbar.LENGTH_LONG)
                .show();
    }

    private void showResultsForFlightData(String airline, String flightNumber, int year, int month, int day) {
        Intent i = new Intent(NewTripActivity.this, FlightResultsActivity.class);
        i.putExtra("airline", airline);
        i.putExtra("year", year);
        i.putExtra("month", month);
        i.putExtra("day", day);
        i.putExtra("flightNumber", flightNumber);
        startActivityForResult(i, REQUEST_FLIGHT_SELECTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FLIGHT_SELECTION && resultCode == RESULT_OK) {
            TripStatus trip = Parcels.unwrap(data.getParcelableExtra("trip"));
            TripStatus.persist(trip);
            setResult(resultCode);
            finish();
        } else if (requestCode == Constants.REQUEST_CODE_SCAN) {
            if (resultCode == RESULT_OK) {
                try {
                    BoardingPassScan bps = PDF417Utils.parseIntentData(data);
                    showResultsForFlightData(
                            bps.getAirlineIATA(),
                            bps.getFlightNo(),
                            bps.getDepartureYear(),
                            bps.getDepartuteMonth(),
                            bps.getDepartureDay()
                    );
                } catch (ParseException e) {
                    parsingError();
                }
            } else {
                parsingError();
            }
        }
    }
}
