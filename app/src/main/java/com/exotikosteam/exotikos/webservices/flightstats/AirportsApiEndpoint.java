package com.exotikosteam.exotikos.webservices.flightstats;

import com.exotikosteam.exotikos.models.airport.AirportsResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AirportsApiEndpoint {

    @GET("/flex/airports/rest/v1/json/all")
    rx.Observable<AirportsResponse> getAll(
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/airports/rest/v1/json/active")
    rx.Observable<AirportsResponse> getActive(
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/airports/rest/v1/json/fs/{code}")
    rx.Observable<AirportsResponse> getByFlightStatsCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("code") String code);

    @GET("/flex/airports/rest/v1/json/icao/{icao}")
    rx.Observable<AirportsResponse> getByICAOCode(
            @Path("icao") String icao,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/airports/rest/v1/json/iata/{iata}")
    rx.Observable<AirportsResponse> getByIATACode(
            @Path("iata") String iata,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/airports/rest/v1/json/countryCode/{countryCode}")
    rx.Observable<AirportsResponse> getByCountryCode(
            @Path("countryCode") String countryCode,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/flex/airports/rest/v1/json/cityCode/{cityCode}")
    rx.Observable<AirportsResponse> getByCityCode(
            @Path("cityCode") String cityCode,
            @Query("appId") String appId,
            @Query("appKey") String appKey);

}
