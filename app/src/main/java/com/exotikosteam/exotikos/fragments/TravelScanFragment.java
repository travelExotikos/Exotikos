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
import com.exotikosteam.exotikos.databinding.TravelScanFragmentBinding;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;
import com.exotikosteam.exotikos.utils.Utils;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.microblink.activity.Pdf417ScanActivity;
import com.microblink.recognizers.BaseRecognitionResult;
import com.microblink.recognizers.RecognitionResults;
import com.microblink.recognizers.blinkbarcode.pdf417.Pdf417RecognizerSettings;
import com.microblink.recognizers.blinkbarcode.pdf417.Pdf417ScanResult;
import com.microblink.recognizers.settings.RecognitionSettings;
import com.microblink.recognizers.settings.RecognizerSettings;
import com.microblink.util.Log;

import org.parceler.Parcels;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by lramaswamy on 11/11/16.
 */

public class TravelScanFragment extends Fragment {

    private OnScanCompletedListener listener;
    private TravelScanFragmentBinding scanFragmentBinding;
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
                    + " must implement TravelScanFragment.OnScanCompletedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        scanFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.travel_scan_fragment, parent, false);
        trip = Parcels.unwrap(getArguments().getParcelable(Constants.PARAM_TRIP));
        return scanFragmentBinding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scanFragmentBinding.scanImageClick.setOnClickListener(v -> {
            launchCameraView(v);
        });
    }

    //TODO remove comments, but first read about options
    private void launchCameraView(View view) {
        // Intent for ScanActivity
        Intent intent = new Intent(view.getContext(), Pdf417ScanActivity.class);

        // If you want sound to be played after the scanning process ends,
        // put here the resource ID of your sound file. (optional)
       //intent.putExtra(Pdf417ScanActivity.EXTRAS_BEEP_RESOURCE, );

        // In order for scanning to work, you must enter a valid licence key. Without licence key,
        // scanning will not work. Licence key is bound the the package name of your app, so when
        // obtaining your licence key from Microblink make sure you give us the correct package name
        // of your app. You can obtain your licence key at http://microblink.com/login or contact us
        // at http://help.microblink.com.
        // Licence key also defines which recognizers are enabled and which are not. Since the licence
        // key validation is performed on image processing thread in native code, all enabled recognizers
        // that are disallowed by licence key will be turned off without any error and information
        // about turning them off will be logged to ADB logcat.
        intent.putExtra(Pdf417ScanActivity.EXTRAS_LICENSE_KEY, Constants.PDF417_KEY);
        // If you want to open front facing camera, uncomment the following line.
        // Note that front facing cameras do not have autofocus support, so it will not
        // be possible to scan denser and smaller codes.
//        intent.putExtra(Pdf417ScanActivity.EXTRAS_CAMERA_TYPE, (Parcelable) CameraType.CAMERA_FRONTFACE);

        // You need to define array of recognizer settings. There are 4 types of recognizers available
        // in PDF417.mobi SDK.
        // Don't enable recognizers and barcode types which you don't actually use because this will
        // significantly decrease the scanning speed.

        // Pdf417RecognizerSettings define the settings for scanning plain PDF417 barcodes.
        Pdf417RecognizerSettings pdf417RecognizerSettings = new Pdf417RecognizerSettings();
        // Set this to true to scan barcodes which don't have quiet zone (white area) around it
        // Use only if necessary because it drastically slows down the recognition process
        pdf417RecognizerSettings.setNullQuietZoneAllowed(true);
        // Set this to true to scan even barcode not compliant with standards
        // For example, malformed PDF417 barcodes which were incorrectly encoded
        // Use only if necessary because it slows down the recognition process
//        pdf417RecognizerSettings.setUncertainScanning(true);

        // BarDecoderRecognizerSettings define settings for scanning 1D barcodes with algorithms
        // implemented by Microblink team.
    //    BarDecoderRecognizerSettings oneDimensionalRecognizerSettings = new BarDecoderRecognizerSettings();
        // set this to true to enable scanning of Code 39 1D barcodes
    //    oneDimensionalRecognizerSettings.setScanCode39(true);
        // set this to true to enable scanning of Code 128 1D barcodes
    //    oneDimensionalRecognizerSettings.setScanCode128(true);
        // set this to true to use heavier algorithms for scanning 1D barcodes
        // those algorithms are slower, but can scan lower resolution barcodes
//        oneDimensionalRecognizerSettings.setTryHarder(true);

        // ZXingRecognizerSettings define settings for scanning barcodes with ZXing library
        // We use modified version of ZXing library to support scanning of barcodes for which
        // we still haven't implemented our own algorithms.
    //    ZXingRecognizerSettings zXingRecognizerSettings = new ZXingRecognizerSettings();
        // set this to true to enable scanning of QR codes
    //    zXingRecognizerSettings.setScanQRCode(true);
    //    zXingRecognizerSettings.setScanITFCode(true);

        // finally, when you have defined settings for each recognizer you want to use,
        // you should put them into array held by global settings object

        RecognitionSettings recognitionSettings = new RecognitionSettings();
        // add settings objects to recognizer settings array
        // Pdf417Recognizer, BarDecoderRecognizer and ZXingRecognizer
        //  will be used in the recognition process
        recognitionSettings.setRecognizerSettingsArray(new RecognizerSettings[]{pdf417RecognizerSettings});

        // additionally, there are generic settings that are used by all recognizers or the
        // whole recognition process

        // by default, this option is true, which means that it is possible to obtain multiple
        // recognition results from the same image.
        // if you want to obtain one result from the first successful recognizer
        // (when first barcode is found, no matter which type) set this option to false
//        recognitionSettings.setAllowMultipleScanResultsOnSingleImage(false);

        // finally send that settings object over intent to scan activity
        // use Pdf417ScanActivity.EXTRAS_RECOGNITION_SETTINGS to set recognizer settings
        intent.putExtra(Pdf417ScanActivity.EXTRAS_RECOGNITION_SETTINGS, recognitionSettings);

        // if you do not want the dialog to be shown when scanning completes, add following extra
        // to intent
//        intent.putExtra(Pdf417ScanActivity.EXTRAS_SHOW_DIALOG_AFTER_SCAN, false);

        // if you want to enable pinch to zoom gesture, add following extra to intent
        intent.putExtra(Pdf417ScanActivity.EXTRAS_ALLOW_PINCH_TO_ZOOM, true);

        // if you want Pdf417ScanActivity to display rectangle where camera is focusing,
        // add following extra to intent
        intent.putExtra(Pdf417ScanActivity.EXTRAS_SHOW_FOCUS_RECTANGLE, true);

        // if you want to use camera fit aspect mode to letterbox the camera preview inside
        // available activity space instead of cropping camera frame (default), add following
        // extra to intent.
        // Camera Fit mode does not look as nice as Camera Fill mode on all devices, especially on
        // devices that have very different aspect ratios of screens and cameras. However, it allows
        // all camera frame pixels to be processed - this is useful when reading very large barcodes.
//        intent.putExtra(Pdf417ScanActivity.EXTRAS_CAMERA_ASPECT_MODE, (Parcelable) CameraAspectMode.ASPECT_FIT);

        intent.putExtra(Pdf417ScanActivity.EXTRAS_SHOW_DIALOG_AFTER_SCAN, false);
        // Start Activity
        startActivityForResult(intent, Constants.REQUEST_CODE_SCAN);
    }

    public static TravelScanFragment newInstance(TripStatus trip) {
        TravelScanFragment travelScanFragment = new TravelScanFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
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
                Pdf417ScanResult result = (Pdf417ScanResult) res;
                try {
                    ScanData scanData = new ScanData(result);
                    android.util.Log.d(TAG, String.format("query params: %s, %s, %s, %s, %s, %s",
                            scanData.airlineIATA,
                            scanData.flightNo,
                            scanData.departureYear,
                            scanData.departuteMonth,
                            scanData.departureDay,
                            scanData.departureAirportIATA));
                    flightStatusService.getByDepartingDateAndAirportIATA(scanData.airlineIATA, scanData.flightNo, scanData.departureYear, scanData.departuteMonth, scanData.departureDay, scanData.departureAirportIATA, appId, appKey)
                            .subscribe(
                                    statusResponse -> {
                                        //TODO probably the save should be done on summary page
                                        Flight flight = Flight.newInstance(this.trip.getId(), statusResponse.getFlightStatuses().get(0), scanData.seatNo);
                                        flight.save();
                                        trip.getFlights().add(flight);
                                        scanCompleted(trip);
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

    private class ScanData {
        String name;
        String bookingReference;
        String departureAirportIATA;
        String arrivingAirportIATA;
        String airlineIATA;
        String flightNo;
        Integer departureDay;
        Integer departuteMonth;
        //TODO year There is no info about year in the scan
        Integer departureYear;
        String cabin;
        String seatNo;

        public ScanData(Pdf417ScanResult result) throws ParseException {
            String barcodeData = result.getStringData();
            boolean uncertainData = result.isUncertain();
            String[] barcodeDataArray = barcodeData.replaceAll("\\s+"," ").split(" ");
            if (uncertainData && barcodeDataArray.length != 8 && barcodeDataArray[2].length() != 8) {
                Log.e(TAG, "There scan is not correct");
            }
            this.name = barcodeDataArray[0];
            this.bookingReference = barcodeDataArray[1];
            this.departureAirportIATA = barcodeDataArray[2].substring(0,3);
            this.arrivingAirportIATA = barcodeDataArray[2].substring(3,6);
            this.airlineIATA = barcodeDataArray[2].substring(6,8);
            this.flightNo = Integer.valueOf(barcodeDataArray[3]).toString();
            Calendar cal = Utils.parseJulian3digitsDate(barcodeDataArray[4].substring(0,3));
            this.departureDay = cal.get(Calendar.DAY_OF_MONTH);
            this.departuteMonth = cal.get(Calendar.MONTH) + 1;
            this.departureYear = cal.get(Calendar.YEAR);
            this.cabin = barcodeDataArray[4].substring(3,4);
            this.seatNo = barcodeDataArray[4].substring(4,barcodeDataArray[4].length());
        }
    }
}
