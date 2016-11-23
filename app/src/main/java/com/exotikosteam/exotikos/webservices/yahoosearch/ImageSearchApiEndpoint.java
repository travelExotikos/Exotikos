package com.exotikosteam.exotikos.webservices.yahoosearch;

import com.exotikosteam.exotikos.models.yahoosearch.ImageSearch;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jesusft on 11/22/16.
 */

public interface ImageSearchApiEndpoint {

    ///v1.1/en-US/i/view?o=js&native=1&b=0&p=Baltimore+City
    @GET("/v1.1/en-US/i/view")
    Observable<ImageSearch> getByQuery(
            @Query("o") String output,
            @Query("native") int isNative,
            @Query("b") int b,
            @Query("n") int count,
            @Query("p") String query
    );

}
