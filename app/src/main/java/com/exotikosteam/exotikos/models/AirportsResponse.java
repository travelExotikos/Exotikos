
package com.exotikosteam.exotikos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AirportsResponse {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("airports")
    @Expose
    private List<Airport> airports = new ArrayList<Airport>();
    @SerializedName("airport")
    @Expose
    private Airport airport;

    /**
     * 
     * @return
     *     The error
     */
    public Error getError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * 
     * @return
     *     The airports
     */
    public List<Airport> getAirports() {
        return airports;
    }

    /**
     * 
     * @param airports
     *     The airports
     */
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    /**
     * 
     * @return
     *     The airport
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * 
     * @param airport
     *     The airport
     */
    public void setAirport(Airport airport) {
        this.airport = airport;
    }

}
