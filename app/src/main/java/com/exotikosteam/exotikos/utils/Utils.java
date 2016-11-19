package com.exotikosteam.exotikos.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ada on 11/12/16.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();
    private static final String FLIGHTSTATS_DATE_FORMAT =  "yyyy-MM-dd'T'hh:mm:ss.S";

    public static Calendar parseJulian3digitsDate(String jDate) throws ParseException {
        //TODO do not have a better idea
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyyddd").parse(cal.get(Calendar.YEAR) + jDate));
        return cal;
    }

    public static Date parseFlightstatsDate(String stringDate) {
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        try {
            Date d = format.parse(date);
            return (DateFormat.format("hh:mma", d)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        try {
            Date d = format.parse(date);
            return (DateFormat.format("EEE, MMM yyyy", d)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getDiffTime(Date date1, Date date2){
        if (date2.getTime() - date1.getTime() < 0) {// if for example date1 = 22:00, date2 = 01:55.
            Calendar c = Calendar.getInstance();
            c.setTime(date2);
            c.add(Calendar.DATE, 1);
            date2 = c.getTime();
        } //else for example date1 = 01:55, date2 = 03:55.
        long ms = date2.getTime() - date1.getTime();

        //235 minutes ~ 4 hours for (22:00 -- 01:55).
        //120 minutes ~ 2 hours for (01:55 -- 03:55).
        return TimeUnit.MINUTES.convert(ms, TimeUnit.MILLISECONDS);
    }


}
