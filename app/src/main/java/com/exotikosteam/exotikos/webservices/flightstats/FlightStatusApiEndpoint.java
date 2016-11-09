package com.exotikosteam.exotikos.webservices.flightstats;

import com.exotikosteam.exotikos.models.flightstatus.FlightStatusResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlightStatusApiEndpoint {

    @GET("/flightstatus/rest/v2/json/flight/status/{flightId}")
    rx.Observable<FlightStatusResponse> getByFlightID(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("flightId") String flightId);


    @GET("/flightstatus/rest/v2/json/flight/status/{carrier}/{flightNumber}/arr/{year}/{month}/{day}")
    rx.Observable<FlightStatusResponse> getByArrivingDate(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

    @GET("/flightstatus/rest/v2/json/flight/status/{carrier}/{flightNumber}/dep/{year}/{month}/{day}")
    rx.Observable<FlightStatusResponse> getByDepartingDate(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

}
