package com.exotikosteam.exotikos.models;

import com.exotikosteam.exotikos.utils.Utils;
import com.microblink.recognizers.blinkbarcode.pdf417.Pdf417ScanResult;
import com.microblink.util.Log;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by jesusft on 11/15/16.
 */

public class BoardingPassScan {

    public static final String TAG = BoardingPassScan.class.getSimpleName();

    protected String name;
    protected String bookingReference;
    protected String departureAirportIATA;
    protected String arrivingAirportIATA;
    protected String airlineIATA;
    protected String flightNo;
    protected Integer departureDay;
    protected Integer departuteMonth;
    //TODO year There is no info about year in the scan
    protected Integer departureYear;
    protected String cabin;
    protected String seatNo;

    public BoardingPassScan() {}

    public String getName() {
        return name;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getDepartureAirportIATA() {
        return departureAirportIATA;
    }

    public String getArrivingAirportIATA() {
        return arrivingAirportIATA;
    }

    public String getAirlineIATA() {
        return airlineIATA;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public Integer getDepartureDay() {
        return departureDay;
    }

    public Integer getDepartuteMonth() {
        return departuteMonth;
    }

    public Integer getDepartureYear() {
        return departureYear;
    }

    public String getCabin() {
        return cabin;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public static BoardingPassScan fromScanResults(Pdf417ScanResult result) throws ParseException {
        String barcodeData = result.getStringData();
        boolean uncertainData = result.isUncertain();
        String[] barcodeDataArray = barcodeData.replaceAll("\\s+"," ").split(" ");
        if (uncertainData && barcodeDataArray.length != 8 && barcodeDataArray[2].length() != 8) {
            Log.e(TAG, "The scan is not correct");
        }
        BoardingPassScan bp = new BoardingPassScan();
        bp.name = barcodeDataArray[0];
        bp.bookingReference = barcodeDataArray[1];
        bp.departureAirportIATA = barcodeDataArray[2].substring(0,3);
        bp.arrivingAirportIATA = barcodeDataArray[2].substring(3,6);
        bp.airlineIATA = barcodeDataArray[2].substring(6,8);
        bp.flightNo = Integer.valueOf(barcodeDataArray[3]).toString();
        Calendar cal = Utils.parseJulian3digitsDate(barcodeDataArray[4].substring(0,3));
        bp.departureDay = cal.get(Calendar.DAY_OF_MONTH);
        bp.departuteMonth = cal.get(Calendar.MONTH) + 1;
        // TODO check year if julian date is before today year+1 if not year
        bp.departureYear = cal.get(Calendar.YEAR);
        bp.cabin = barcodeDataArray[4].substring(3,4);
        bp.seatNo = barcodeDataArray[4].substring(4,barcodeDataArray[4].length());

        return bp;
    }

}
