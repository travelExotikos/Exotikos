
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Delays {

    @SerializedName("departureRunwayDelayMinutes")
    @Expose
    private Integer departureRunwayDelayMinutes;

    /**
     * 
     * @return
     *     The departureRunwayDelayMinutes
     */
    public Integer getDepartureRunwayDelayMinutes() {
        return departureRunwayDelayMinutes;
    }

    /**
     * 
     * @param departureRunwayDelayMinutes
     *     The departureRunwayDelayMinutes
     */
    public void setDepartureRunwayDelayMinutes(Integer departureRunwayDelayMinutes) {
        this.departureRunwayDelayMinutes = departureRunwayDelayMinutes;
    }

}
