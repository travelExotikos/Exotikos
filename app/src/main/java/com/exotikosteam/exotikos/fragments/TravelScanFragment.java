package com.exotikosteam.exotikos.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.TravelScanFragmentBinding;
import com.exotikosteam.exotikos.models.trip.TripStatus;

/**
 * Created by lramaswamy on 11/11/16.
 */

public class TravelScanFragment extends Fragment {

    private OnScanCompletedListener listener;
    private TravelScanFragmentBinding scanFragmentBinding;

    ImageView scanImageClick;

    public interface OnScanCompletedListener {
        public void getTripInstance(TripStatus trip);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnScanCompletedListener) {
            listener = (OnScanCompletedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement TravelScanFragment.OnScanCompletedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        scanFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.travel_scan_fragment, parent, false);
        setupBindings();
        return scanFragmentBinding.getRoot();
    }

    private void setupBindings() {
        scanImageClick = scanFragmentBinding.scanImageClick;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scanImageClick.setOnClickListener(v -> {
            launchCameraView();
        });
    }

    private void launchCameraView() {
        Toast.makeText(getContext(), "Camera View Launched", Toast.LENGTH_SHORT).show();
        //@TODO once the boarding pass is scanned, the data needs to be generated and passed to the activity
        TripStatus trip = TripStatus.newMockInstance();
        scanCompleted(trip);
    }

    public static TravelScanFragment newInstance(String title) {
        TravelScanFragment travelScanFragment = new TravelScanFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        travelScanFragment.setArguments(bundle);
        return travelScanFragment;
    }

    public void scanCompleted(TripStatus trip) {
        listener.getTripInstance(trip);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
