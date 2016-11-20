package com.exotikosteam.exotikos.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentCheckInBinding;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.models.BoardingPassScan;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.PDF417Utils;
import com.microblink.activity.Pdf417ScanActivity;

import org.parceler.Parcels;

public class CheckInFragment extends Fragment {

    private OnButtonsClicks mListener;
    private FragmentCheckInBinding mBinding;
    private TripStatus mTrip;
    private Flight mFlight;

    public CheckInFragment() {
        // Required empty public constructor
    }

    public static CheckInFragment newInstance(TripStatus trip) {
        CheckInFragment frag = new CheckInFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_in, container, false);
        mTrip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        mFlight = mTrip.getFlights().get(mTrip.getCurrentFlight());
        setOnClickListener();
        populateData();
        return mBinding.getRoot();
    }

    private void setOnClickListener() {
        mBinding.btnFindCheckIn.setOnClickListener(v -> {
            String text = getResources().getString(R.string.check_in_station_question_english);
            showInfoDialog(String.format(text, mFlight.getFlightCarrierName()));
        });
        mBinding.btnFindWeight.setOnClickListener( v -> {
            showInfoDialog(getResources().getString(R.string.check_in_weight_question_english));
        });
        mBinding.btnCheckInScan.setOnClickListener( v -> {
            PDF417Utils.launchCameraView(getActivity());
        });
        mBinding.btnNext.setOnClickListener(v -> {
            handleLauncCheckInHintsActivity();
        });
    }

    private void populateData() {
        String text = getResources().getString(R.string.check_in_station);
        mBinding.tvFindCheckIn.setText(Html.fromHtml(String.format(text, mFlight.getFlightCarrierName()), Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_SCAN) {
            if (resultCode != Pdf417ScanActivity.RESULT_OK) {
                parsingError();
                return;
            }
            try {
                BoardingPassScan bps = PDF417Utils.parseIntentData(data);
                mFlight.setSeatNumber(bps.getSeatNo());
                mFlight.save();
            } catch (Exception e) {
                parsingError();
                return;
            }
        }
    }

    private void showInfoDialog(String text) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        InfoFragment alertDialog = InfoFragment.newInstance("Can you help me?", text);
        alertDialog.show(fm, "fragment_alert");
    }

    private void parsingError() {
        Snackbar.make(mBinding.getRoot(), R.string.scan_parsing_error, Snackbar.LENGTH_LONG)
                .show();
    }

    private void handleLauncCheckInHintsActivity() {
        mListener.handleButtonsClicks(Constants.GO_TO_CHECK_IN_HINTS);
    }

}
