package com.exotikosteam.exotikos.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
}
