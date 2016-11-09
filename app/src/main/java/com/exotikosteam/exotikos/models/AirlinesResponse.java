
package com.exotikosteam.exotikos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AirlinesResponse {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("airlines")
    @Expose
    private List<Airline> airlines = new ArrayList<Airline>();
    @SerializedName("airline")
    @Expose
    private Airline airline;

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
     *     The airlines
     */
    public List<Airline> getAirlines() {
        return airlines;
    }

    /**
     * 
     * @param airlines
     *     The airlines
     */
    public void setAirlines(List<Airline> airlines) {
        this.airlines = airlines;
    }

    /**
     * 
     * @return
     *     The airline
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * 
     * @param airline
     *     The airline
     */
    public void setAirline(Airline airline) {
        this.airline = airline;
    }

}
