
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class FlightStatusResponse {

    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("appendix")
    @Expose
    private Appendix appendix;
    @SerializedName("flightStatuses")
    @Expose
    private List<FlightStatus> flightStatuses = new ArrayList<FlightStatus>();

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

    /**
     * 
     * @return
     *     The flightStatuses
     */
    public List<FlightStatus> getFlightStatuses() {
        return flightStatuses;
    }

    /**
     * 
     * @param flightStatuses
     *     The flightStatuses
     */
    public void setFlightStatuses(List<FlightStatus> flightStatuses) {
        this.flightStatuses = flightStatuses;
    }

}
