package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcels;

/**
 * Created by lramaswamy on 11/12/16.
 */

public class FragmentTravelAirport extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LatLng latLng;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupGoogleClient();
        latLng = Parcels.unwrap(getArguments().getParcelable("latlong"));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void setupGoogleClient() {
        // Create the location client to start receiving updates
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
    }

    public static FragmentTravelAirport newInstance(String title, LatLng latLng) {
        FragmentTravelAirport travelAirportFragment = new FragmentTravelAirport();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putParcelable("latlong", Parcels.wrap(latLng));
        travelAirportFragment.setArguments(bundle);
        return travelAirportFragment;
    }
}
