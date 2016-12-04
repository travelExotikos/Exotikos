package com.exotikosteam.exotikos.models.events;

import android.content.Context;
import android.content.Intent;

import com.exotikosteam.exotikos.activities.TravelStatusActivity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.parceler.Parcels;

/**
 * Created by jesusft on 11/20/16.
 */

public class SimpleFlightAlertEvent extends FlightEventProcessor {

    private Period triggerTime;
    private String alertText;

    public SimpleFlightAlertEvent(Context context, Period triggerIntervalBeforeDeparture, String alertText) {
        super(context);
        this.triggerTime = triggerIntervalBeforeDeparture;
        this.alertText = alertText;
    }

    @Override
    public boolean shouldFire(EventInputContext context) {
        DateTime departureUtc = new DateTime(context.getFlight().getDepartureTimeUTC(), DateTimeZone.UTC);

        return departureUtc.minus(triggerTime).isAfter(context.getDeviceTime()) &&
                departureUtc.minus(triggerTime).isAfter(new DateTime(context.getTrip().getUpdatedAt()));
    }

    @Override
    public void onFire(EventInputContext context) {
        Intent intent = new Intent(mContext, TravelStatusActivity.class);
        intent.putExtra("trip", Parcels.wrap(context.getTrip()));
        String flightName = context.getFlight().getFlightCarrier() + context.getFlight().getFlightNumber();
        sendPushNotification(intent, flightName, alertText);
    }
}
