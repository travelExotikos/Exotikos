package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.models.trip.Flight;

/**
 * Created by lramaswamy on 11/11/16.
 */

public class TravelScanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.travel_scan_boardingpass_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) view.findViewById(R.id.scanImageClick);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCameraView();
            }
        });
    }

    private void launchCameraView() {
        Toast.makeText(getContext(), "Camera View Launched", Toast.LENGTH_SHORT).show();
    }

    private Flight generateFlightInstance() {
        Flight flight = new Flight();
        return flight;
    }

    public static TravelScanFragment newInstance(String title) {
        TravelScanFragment travelScanFragment = new TravelScanFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        travelScanFragment.setArguments(bundle);
        return travelScanFragment;
    }
}
