package com.exotikosteam.exotikos.models.events;

import android.location.Location;

import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;

import org.joda.time.DateTime;

/**
 * Created by jesusft on 11/20/16.
 */

public class EventInputContext {

    private TripStatus trip;
    private Flight flight;
    private DateTime deviceTime;
    private Location deviceLocation;

    public EventInputContext(TripStatus trip, Flight flight, DateTime deviceTime, Location deviceLocation) {
        this.trip = trip;
        this.flight = flight;
        this.deviceTime = deviceTime;
        this.deviceLocation = deviceLocation;
    }

    public TripStatus getTrip() {
        return trip;
    }

    public Flight getFlight() {
        return flight;
    }

    public DateTime getDeviceTime() {
        return deviceTime;
    }

    public Location getDeviceLocation() {
        return deviceLocation;
    }
}
