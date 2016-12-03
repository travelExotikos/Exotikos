package com.exotikosteam.exotikos.utils;

import org.joda.time.Period;

/**
 * Created by ada on 11/12/16.
 */

public class Constants {

    public static final String PDF417_KEY = "2YPPHKVV-SSO7YBVP-BY5GGPXK-XHGUJ7UM-RDZPXH3V-72GIR4X3-T5275DEI-6L54556L";
    public static final Integer REQUEST_CODE_SCAN = 1;
    public static final Integer REQUEST_CODE_CONTACT = 2;
    public static final String PARAM_TRIP = "paramTrip";

    public static final String GOOGLE_MAP_PACKAGE = "com.google.android.apps.maps";
    public static final String GOOGLE_TRANSLATE_PACKAGE = "com.google.android.apps.translate";

    public static final String TERMINAL = "Terminal ";
    public static final String GATE = "Gate  ";
    public static final String YOUHAVE = "You have ";
    public static final String TOBOARD = " to Board";
    public static final String TOCHECKIN = " to Checkin";
    
    public static final String GO_TO_CHECK_IN_HINTS = "gotoCheckInHints";
    public static final String GO_TO_SECURITY_VIDEO_HINT = "gotoSecurityVideoHint";
    public static final String GO_TO_IN_PLANE_HINTS = "gotoInPlaneHints";

    public static final String GO_TO_AIRPORT_PAGE = "gotoAirportPage";
    public static final String GO_TO_SECURITY_CHECKING = "gotoSecurityCheckinActivity";
    public static final String GO_TO_PREP_PAGE = "gotoTravelPrepActivity";
    public static final String CHECKIN_LIST = "Checkin List";
    public static final String TSA_WEBPAGE = "https://www.tsa.gov/travel/security-screening";
    public static final String TSA_RESTRICTED = "https://www.tsa.gov/travel/security-screening/prohibited-items";

    public static final String WEBPAGE_URL = "webpage_url";
    public static final String TRIP_DETAILS = "Trip Details";
    public static final String BAGGAGE_HELP = "Baggage Help";
    public static final String TSA_WEBLBL = "TSA Webpage";
    public static final String RESTRICTED = "Restricted Items";
    public static final String RESTRICTED_PAGE = "Restricted Webpage";

    public static final long SERVICE_INTERVAL = 5 * 60000L;
    public static final Period NOTIFY_CHECKIN_AVAILABLE = Period.hours(24);
    public static final Period NOTIFY_LEAVE_FOR_AIRPORT = Period.hours(5);
    public static final Period NOTIFY_HEAD_TO_SECURITY = new Period(2, 30, 0, 0); // 2h 30min
    public static final Period NOTIFY_HEAD_TO_DEPARTURE_GATE = Period.hours(1);

}
