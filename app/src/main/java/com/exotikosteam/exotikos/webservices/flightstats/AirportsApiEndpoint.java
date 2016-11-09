package com.exotikosteam.exotikos.webservices.flightstats;

import com.exotikosteam.exotikos.models.AirportsResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AirportsApiEndpoint {

    @GET("/airports/rest/v1/json/all")
    rx.Observable<AirportsResponse> getAll(
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/airports/rest/v1/json/active")
    rx.Observable<AirportsResponse> getActive(
            @Query("appId") String appId,
            @Query("appKey") String appKey);

    @GET("/airports/rest/v1/json/fs/{code}")
    rx.Observable<AirportsResponse> getByFlightStatsCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("code") String code);

    @GET("/airports/rest/v1/json/icao/{icao}")
    rx.Observable<AirportsResponse> getByICAOCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("icao") String icao);

    @GET("/airports/rest/v1/json/iata/{iata}")
    rx.Observable<AirportsResponse> getByIATACode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("iata") String iata);

    @GET("/airports/rest/v1/json/countryCode/{countryCode}")
    rx.Observable<AirportsResponse> getByCountryCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("countryCode") String countryCode);

    @GET("/airports/rest/v1/json/cityCode/{cityCode}")
    rx.Observable<AirportsResponse> getByCityCode(
            @Query("appId") String appId,
            @Query("appKey") String appKey,
            @Path("countryCode") String cityCode);

}
