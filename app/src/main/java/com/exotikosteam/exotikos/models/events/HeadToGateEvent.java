package com.exotikosteam.exotikos.models.events;

import android.content.Context;
import android.content.Intent;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.activities.TravelStatusActivity;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.parceler.Parcels;

/**
 * Created by jesusft on 11/20/16.
 */

public class HeadToGateEvent extends FlightEventProcessor {

    private Period triggerTime;

    public HeadToGateEvent(Context mContext, Period triggerIntervalBeforeDeparture) {
        super(mContext);
        this.triggerTime = triggerIntervalBeforeDeparture;
    }

    @Override
    public boolean shouldFire(EventInputContext context) {
        DateTime departureUtc = new DateTime(context.getFlight().getDepartureTimeUTC());

        return departureUtc.minus(triggerTime).isAfter(context.getDeviceTime()) &&
                departureUtc.minus(triggerTime).isAfter(new DateTime(context.getTrip().getUpdatedAt()));
    }

    @Override
    public void onFire(EventInputContext context) {
        Intent intent = new Intent(mContext, TravelStatusActivity.class);
        intent.putExtra("trip", Parcels.wrap(context.getTrip()));
        String flightName = context.getFlight().getFlightCarrier() + context.getFlight().getFlightNumber();
        sendPushNotification(intent, flightName,
                String.format(mContext.getString(R.string.head_to_gate_notification), context.getFlight().getDepartureGate()));
    }
}
