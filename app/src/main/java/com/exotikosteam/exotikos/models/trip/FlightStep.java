package com.exotikosteam.exotikos.models.trip;

/**
 * Created by ada on 11/11/16.
 */

public enum FlightStep {
    //PREPARATION, I dont think we will have this state of trip...we are starting with scanning the boarding card which we get after checkin
    CHECK_IN,
    SECURITY_CHECK_IN,
    BOARDING,
    PLANE,
    DONE;


}
