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

    private static long getDiffTime(Date date1, Date date2){
        if (date2.getTime() - date1.getTime() < 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(date2);
            c.add(Calendar.DATE, 1);
            date2 = c.getTime();
        }
        long ms = date2.getTime() - date1.getTime();

        return TimeUnit.MINUTES.convert(ms, TimeUnit.MILLISECONDS);
    }

    private static String convertminsToProperString(long timeToBoardMin) {
        int days = 0;
        int hours = 0;
        if(timeToBoardMin > 24)
            days = (int) timeToBoardMin/24/60;
        hours = (int) timeToBoardMin/60%24;
        int min = (int)timeToBoardMin%60;

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

    public static String getTimeDeltaFromCurrent(Date departureTime) {
        if (departureTime == null) {
            return "";
        }
        Date currentTime = new Date();
        long timeToBoardMin = Utils.getDiffTime(currentTime, departureTime);
        return Utils.convertminsToProperString(timeToBoardMin);
    }

    public static String getReadytoPrintBoardingTimeDelta(Date departureTime) {
        Date currentTime = new Date();
        long timeToBoardMin = Utils.getDiffTime(currentTime, departureTime);
        String boardTime = Constants.YOUHAVE + Utils.convertminsToProperString(timeToBoardMin) + Constants.TOBOARD;
        return boardTime;
    }

    public static String getReadytoPrintCheckinTimeDelta(Date departureTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(departureTime);
        c.add(Calendar.DATE, -1);
        Date currentTime = new Date();
        long timeToBoardMin = Utils.getDiffTime(currentTime, c.getTime());
        String boardTime = Constants.YOUHAVE + Utils.convertminsToProperString(timeToBoardMin) + Constants.TOCHECKIN;
        return boardTime;
    }

    public static String getCheckinTimeDelta(Date departureTime) {
        if (departureTime == null) {
            return "";
        }
        Calendar c = Calendar.getInstance();
        c.setTime(departureTime);
        c.add(Calendar.DATE, -1);
        Date currentTime = new Date();
        long timeToBoardMin = Utils.getDiffTime(currentTime, c.getTime());
        return Utils.convertminsToProperString(timeToBoardMin);
    }
}
