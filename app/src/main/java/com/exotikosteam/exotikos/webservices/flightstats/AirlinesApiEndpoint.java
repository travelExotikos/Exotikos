package com.exotikosteam.exotikos.webservices.flightstats;

import com.exotikosteam.exotikos.models.airline.AirlinesResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AirlinesApiEndpoint {

    @GET("/airlines/rest/v1/json/all")
    rx.Observable<AirlinesResponse> getAll(
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/airlines/rest/v1/json/active")
    rx.Observable<AirlinesResponse> getActive(
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/airlines/rest/v1/json/fs/{code}")
    rx.Observable<AirlinesResponse> getByFlightStatsCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("code") String code);

    @GET("/airlines/rest/v1/json/icao/{icao}")
    rx.Observable<AirlinesResponse> getByICAOCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("icao") String icao);

    @GET("/airlines/rest/v1/json/iata/{iata}")
    rx.Observable<AirlinesResponse> getByIATACode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("iata") String iata);

}
