package com.exotikosteam.exotikos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ada on 11/12/16.
 */

public class Utils {

    public static Calendar parseJulian3digitsDate(String jDate) throws ParseException {
        //TODO do not have a better idea
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyyddd").parse(cal.get(Calendar.YEAR) + jDate));
        return cal;
    }
}
