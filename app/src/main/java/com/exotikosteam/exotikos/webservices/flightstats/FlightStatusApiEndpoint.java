package com.exotikosteam.exotikos.webservices.flightstats;

import com.exotikosteam.exotikos.models.flightstatus.FlightStatusResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlightStatusApiEndpoint {

    @GET("/flex/flightstatus/rest/v2/json/flight/status/{flightId}")
    rx.Observable<FlightStatusResponse> getByFlightID(
            @Path("flightId") String flightId,
            @Query("appId") String appId,
            @Query("appKey") String appKey);


    @GET("/flex/flightstatus/rest/v2/json/flight/status/{carrier}/{flightNumber}/arr/{year}/{month}/{day}")
    rx.Observable<FlightStatusResponse> getByArrivingDate(
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/flightstatus/rest/v2/json/flight/status/{carrier}/{flightNumber}/dep/{year}/{month}/{day}")
    rx.Observable<FlightStatusResponse> getByDepartingDate(
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/flightstatus/rest/v2/json/flight/status/{carrier}/{flightNumber}/dep/{year}/{month}/{day}")
    rx.Observable<FlightStatusResponse> getByDepartingDateAndAirportIATA(
            @Path("carrier") String carrier,
            @Path("flightNumber") String flightNumber,
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day,
            @Query("airport") String airportIATA,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

}
