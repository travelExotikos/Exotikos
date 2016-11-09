
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Schedule {

    @SerializedName("flightType")
    @Expose
    private String flightType;
    @SerializedName("serviceClasses")
    @Expose
    private String serviceClasses;
    @SerializedName("restrictions")
    @Expose
    private String restrictions;

    /**
     * 
     * @return
     *     The flightType
     */
    public String getFlightType() {
        return flightType;
    }

    /**
     * 
     * @param flightType
     *     The flightType
     */
    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    /**
     * 
     * @return
     *     The serviceClasses
     */
    public String getServiceClasses() {
        return serviceClasses;
    }

    /**
     * 
     * @param serviceClasses
     *     The serviceClasses
     */
    public void setServiceClasses(String serviceClasses) {
        this.serviceClasses = serviceClasses;
    }

    /**
     * 
     * @return
     *     The restrictions
     */
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * 
     * @param restrictions
     *     The restrictions
     */
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

}
