package com.exotikosteam.exotikos.models.trip;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by lramaswamy on 11/9/16.
 */

@Parcel
public class TripStatus {
    List<Flight> flightList;

    //Empty constructor for Parceler
    public TripStatus() {}

    public TripStatus(List<Flight> flightList) {
        this.flightList = flightList;
    }



}
