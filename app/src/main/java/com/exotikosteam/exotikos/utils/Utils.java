package com.exotikosteam.exotikos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.exotikosteam.exotikos.models.airport.Airport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ada on 11/12/16.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();
    private static final String FLIGHTSTATS_DATE_FORMAT =  "yyyy-MM-dd'T'hh:mm:ss.S";

    private static final long CHECKIN_TIME_MIN = (long) 2.5 * 60;
    private static final long SEC_CHECKIN_TIME_MIN = (long) 1.5 * 60;
    private static final long BOARDING_TIME_MIN = 30;


    public static Date getUTCDate(String longDate, String timeZone) {
        Log.d(Utils.class.getSimpleName(), longDate + ' ' + timeZone);
        SimpleDateFormat sdfTimeZone = new SimpleDateFormat(FLIGHTSTATS_DATE_FORMAT);
        sdfTimeZone.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date utcDate = null;
        try {
            utcDate = sdfTimeZone.parse(longDate);
        } catch (ParseException e) {
            Log.e(Utils.class.getSimpleName(), String.format("Cannot convert date with timezone %s, %s", longDate, timeZone));
        }
        return utcDate;
    }

    public static long getDiffWithCurrentInMin(Date utcDate) {
        Calendar nowUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar utcCal = new GregorianCalendar();
        utcCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        utcCal.setTime(utcDate);
        long minDiff = TimeUnit.MINUTES.convert(utcCal.getTimeInMillis() - nowUTC.getTimeInMillis(), TimeUnit.MILLISECONDS);
        Log.d(Utils.class.getSimpleName(), String.format("utcCal: %d, utcNow: %d", utcCal.getTimeInMillis(), nowUTC.getTimeInMillis()));
        return minDiff;
    }

    public static Calendar parseJulian3digitsDate(String jDate) throws ParseException {
        if (TextUtils.isEmpty(jDate) || !TextUtils.isDigitsOnly(jDate)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyyddd").parse(prepareYear(cal, Integer.parseInt(jDate)) + jDate));
        return cal;
    }

    private  static int prepareYear(Calendar now, int scanJulian) {
        // the data from boarding card be scanned only at jDate or couple days earlier but for testing/demo we need more
        int acceptableDiff = 300;
        int nowJulian = now.get(Calendar.HOUR_OF_DAY);
        int nowYear = now.get(Calendar.YEAR);
        if (Math.abs(scanJulian - nowYear) < 300) {
            return nowYear;
        }
        if (nowYear < 150) {
            return nowJulian - 1;
        }
        return nowJulian + 1;
    }

    public static Date parseLongFormatDate(String stringDate) {
        if (!TextUtils.isEmpty(stringDate)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(FLIGHTSTATS_DATE_FORMAT);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                return sdf.parse(stringDate);
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return null;
    }

    public static String convertToTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat(FLIGHTSTATS_DATE_FORMAT);
        try {
            Date d = format.parse(date);
            return (DateFormat.format("hh:mma", d)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(FLIGHTSTATS_DATE_FORMAT);
        try {
            Date d = format.parse(date);
            return (DateFormat.format("EEE, MMM dd, yyyy", d)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertMinsToProperString(long timeInMin) {
        if (timeInMin < 0) {
            return "";
        }
        int days = (int) timeInMin / (24 * 60);
        int hours = (int) (timeInMin / 60) % 24;
        int min = (int) timeInMin % 60;

        StringBuffer sbf = new StringBuffer();
        if(days > 0) {
            sbf.append(Integer.toString(days));
            sbf.append("d ");
        }
        if(hours > 0) {
            sbf.append(Integer.toString(hours));
            sbf.append("h ");
        }
        sbf.append(Integer.toString(min));
        sbf.append("min ");
        return sbf.toString();
    }

    public static String getTimeToDate(Date date) {
        if (date == null) {
            return "";
        }
        long timeInMin = Utils.getDiffWithCurrentInMin(date);
        return Utils.convertMinsToProperString(timeInMin);
    }

    public static String getTimeToCheckin(Date departureTime) {
        if (departureTime == null) {
            return "";
        }
        long timeInMin = Utils.getDiffWithCurrentInMin(departureTime);
        return Utils.convertMinsToProperString(timeInMin - CHECKIN_TIME_MIN);
    }

    public static String getTimeToSecCheckin(Date departureTime) {
        if (departureTime == null) {
            return "";
        }
        long timeInMin = Utils.getDiffWithCurrentInMin(departureTime);
        return Utils.convertMinsToProperString(timeInMin - SEC_CHECKIN_TIME_MIN);
    }

    public static String getTimeToBoarding(Date departureTime) {
        if (departureTime == null) {
            return "";
        }
        long timeInMin = Utils.getDiffWithCurrentInMin(departureTime);
        return Utils.convertMinsToProperString(timeInMin - BOARDING_TIME_MIN);
    }


    public static void showAiportLocationPage(Airport departureAirport, Activity activity, Context context) {
        double latitude = departureAirport.getLatitude();
        double longitude = departureAirport.getLongitude();

        String label = departureAirport.getName();
        String uriBegin = "geo:" + latitude + "," + longitude + "(" + label + ")";
        String query1 = departureAirport.getStreet1() + " " + departureAirport.getStreet2() +
                " " + departureAirport.getCity() + " " + departureAirport.getStateCode() + " "
                + departureAirport.getPostalCode() + " " + departureAirport.getCountryName();
        String encodedQuery = Uri.encode(query1);
        String uriString = uriBegin + "?q=" + encodedQuery;
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        intent.setPackage(Constants.GOOGLE_MAP_PACKAGE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
