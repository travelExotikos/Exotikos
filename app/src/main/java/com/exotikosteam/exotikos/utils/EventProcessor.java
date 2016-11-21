package com.exotikosteam.exotikos.utils;

import android.content.Context;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.models.events.EventInputContext;
import com.exotikosteam.exotikos.models.events.FlightEventProcessor;
import com.exotikosteam.exotikos.models.events.HeadToGateEvent;
import com.exotikosteam.exotikos.models.events.LeaveForAirportEvent;
import com.exotikosteam.exotikos.models.events.SimpleFlightAlertEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesusft on 11/20/16.
 */

public class EventProcessor {

    private List<FlightEventProcessor> processorList;

    public EventProcessor(List<FlightEventProcessor> processorList) {
        this.processorList = processorList;
    }

    public void process(EventInputContext context) {
        for (FlightEventProcessor e : processorList) {
            if (e.shouldFire(context)) {
                e.onFire(context);
            }
        }
    }

    public static EventProcessor newDefaultInstance(Context ctx) {
        ArrayList<FlightEventProcessor> list = new ArrayList<>();
        list.add(new HeadToGateEvent(ctx, Constants.NOTIFY_HEAD_TO_DEPARTURE_GATE));
        list.add(new LeaveForAirportEvent(ctx, Constants.NOTIFY_LEAVE_FOR_AIRPORT));
        list.add(new SimpleFlightAlertEvent(ctx, Constants.NOTIFY_CHECKIN_AVAILABLE, ctx.getString(R.string.flight_checkin_notification)));
        list.add(new SimpleFlightAlertEvent(ctx, Constants.NOTIFY_HEAD_TO_SECURITY, ctx.getString(R.string.head_to_security_notification)));

        return new EventProcessor(list);
    }

}
