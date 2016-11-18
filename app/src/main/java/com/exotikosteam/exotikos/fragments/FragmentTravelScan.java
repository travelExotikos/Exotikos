package com.exotikosteam.exotikos.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentTravelScanBinding;
import com.exotikosteam.exotikos.models.BoardingPassScan;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.PDF417Utils;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.microblink.activity.Pdf417ScanActivity;
import com.microblink.recognizers.BaseRecognitionResult;
import com.microblink.recognizers.RecognitionResults;
import com.microblink.recognizers.blinkbarcode.pdf417.Pdf417ScanResult;
import com.microblink.util.Log;

import org.parceler.Parcels;

import java.text.ParseException;

/**
 * Created by lramaswamy on 11/11/16.
 */

public class FragmentTravelScan extends Fragment {

    private OnScanCompletedListener listener;
    private FragmentTravelScanBinding scanFragmentBinding;
    private static String TAG = "ScanAction";
    private TripStatus trip;

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
                    + " must implement FragmentTravelScan.OnScanCompletedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        scanFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_travel_scan, parent, false);
        trip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        return scanFragmentBinding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scanFragmentBinding.scanImageClick.setOnClickListener(v -> {
            PDF417Utils.launchCameraView(getActivity());
        });
    }

    public static FragmentTravelScan newInstance(TripStatus trip) {
        FragmentTravelScan fragmentTravelScan = new FragmentTravelScan();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        fragmentTravelScan.setArguments(bundle);
        return fragmentTravelScan;
    }

    public void scanCompleted(TripStatus trip) {
        listener.getTripInstance(trip);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_SCAN) {
            if (resultCode != Pdf417ScanActivity.RESULT_OK) {
                android.util.Log.e(TAG, "Scan error " + resultCode);
                return;
            }
            saveTrip(data);
        }
    }



    private void saveTrip(Intent data) {
        FlightStatusApiEndpoint flightStatusService = ((ExotikosApplication) getActivity().getApplication()).getFlightstatsRetrofit().create(FlightStatusApiEndpoint.class);
        String appId = ((ExotikosApplication) getActivity().getApplication()).getFligthStatsAppID();
        String appKey = ((ExotikosApplication) getActivity().getApplication()).getFligthStatsAppKey();

        android.util.Log.d(TAG, "Scan captured");
        RecognitionResults results = data.getParcelableExtra(Pdf417ScanActivity.EXTRAS_RECOGNITION_RESULTS);
        BaseRecognitionResult[] resultArray = results.getRecognitionResults();

        for (BaseRecognitionResult res : resultArray) {
            if (res instanceof Pdf417ScanResult) { // check if scan result is result of Pdf417 recognizer
                try {
                    BoardingPassScan scanData = PDF417Utils.parseIntentData(data);
                    String flightQuery = getQueryParamsInfo(scanData);
                    android.util.Log.d(TAG, flightQuery);
                    flightStatusService.getByDepartingDateAndAirportIATA(scanData.getAirlineIATA(), scanData.getFlightNo(), scanData.getDepartureYear(), scanData.getDepartuteMonth(), scanData.getDepartureDay(), scanData.getDepartureAirportIATA(), appId, appKey)
                            .subscribe(
                                    statusResponse -> {
                                        if (statusResponse == null || statusResponse.getFlightStatuses() == null || statusResponse.getFlightStatuses().size() < 1) {
                                            Log.e(TAG, "Cannot find flight. " + flightQuery);
                                        } else {
                                            this.trip = TripStatus.saveOrUpdateTrip(this.trip, statusResponse.getFlightStatuses().get(0), scanData.getSeatNo(), statusResponse.getAppendix());
                                            scanCompleted(this.trip);
                                        }
                                    },
                                    throwable -> android.util.Log.e(TAG, "Error getting status", throwable),
                                    () -> android.util.Log.i(TAG, "Done with status")
                            );
                    android.util.Log.d(TAG, ((Pdf417ScanResult) res).getStringData());
                } catch (ParseException e) {
                    Log.e(TAG, "Cannot parse barcode. " + e.getMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Cannot save trip. " + e.getMessage());
                }
            }
        }

    }

    private String getQueryParamsInfo(BoardingPassScan scanData) {
        return String.format("query params: airlineIATA:%s, flightNo:%s, departureYear:%s, departuteMonth:%s, departureDay:%s, departureAirportIATA:%s",
                scanData.getAirlineIATA(),
                scanData.getFlightNo(),
                scanData.getDepartureYear(),
                scanData.getDepartuteMonth(),
                scanData.getDepartureDay(),
                scanData.getDepartureAirportIATA());
    }
}
