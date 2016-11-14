
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;


@Generated("org.jsonschema2pojo")
public class FlightScheduleResponse {

    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("scheduledFlights")
    @Expose
    private List<ScheduledFlight> scheduledFlights = new ArrayList<ScheduledFlight>();
    @SerializedName("appendix")
    @Expose
    private Appendix appendix;

    /**
     * 
     * @return
     *     The request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * 
     * @param request
     *     The request
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * 
     * @return
     *     The scheduledFlights
     */
    public List<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }

    /**
     * 
     * @param scheduledFlights
     *     The scheduledFlights
     */
    public void setScheduledFlights(List<ScheduledFlight> scheduledFlights) {
        this.scheduledFlights = scheduledFlights;
    }

    /**
     * 
     * @return
     *     The appendix
     */
    public Appendix getAppendix() {
        return appendix;
    }

    /**
     * 
     * @param appendix
     *     The appendix
     */
    public void setAppendix(Appendix appendix) {
        this.appendix = appendix;
    }

}
