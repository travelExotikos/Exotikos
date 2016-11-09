package com.exotikosteam.exotikos.webservices.flightstats;

import com.exotikosteam.exotikos.models.flightstatus.FlightScheduleResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SchedulesApiEndpoint {

    @GET("/schedules/rest/v1/json/flight/{carrier}/{flightNumber}/departing/{year}/{month}/{day}")
    rx.Observable<FlightScheduleResponse> getByDepartingDate(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

    @GET("/schedules/rest/v1/json/flight/{carrier}/{flightNumber}/arriving/{year}/{month}/{day}")
    rx.Observable<FlightScheduleResponse> getByArrivingDate(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);
}
