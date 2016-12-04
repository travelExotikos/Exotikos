package com.exotikosteam.exotikos.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentInPlaneBinding;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.Utils;

import org.parceler.Parcels;

public class InPlaneFragment extends Fragment {

    private OnButtonsClicks mListener;
    private FragmentInPlaneBinding mBinding;
    private TripStatus mTrip;
    private Flight mFlight;


    public InPlaneFragment() {
        // Required empty public constructor
    }

    public static InPlaneFragment newInstance(TripStatus trip) {
        InPlaneFragment frag = new InPlaneFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_in_plane, container, false);
        mTrip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        mFlight = mTrip.getFlights().get(mTrip.getCurrentFlight());
        setOnClickListener();
        populateData();
        return mBinding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonsClicks) {
            mListener = (OnButtonsClicks) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnButtonsClicks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setOnClickListener() {
        mBinding.btnNext.setOnClickListener(v -> {
            handleLauncInPlaneHintsActivity();
        });
    }

    private void populateData() {
        String text = getResources().getString(R.string.in_plane_welcome);
        mBinding.tvInPlane.setText(Html.fromHtml(String.format(text,
                mFlight.getArrivalCity(),
                Utils.getTimeToDate(mFlight.getArrivalTimeUTC()),
                Utils.convertToTime(mFlight.getArrivalTime()))));
    }

    private void handleLauncInPlaneHintsActivity() {
        mListener.handleButtonsClicks(Constants.GO_TO_IN_PLANE_HINTS);
    }

}
