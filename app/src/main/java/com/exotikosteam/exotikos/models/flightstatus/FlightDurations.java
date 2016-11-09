
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class FlightDurations {

    @SerializedName("scheduledBlockMinutes")
    @Expose
    private Integer scheduledBlockMinutes;
    @SerializedName("scheduledAirMinutes")
    @Expose
    private Integer scheduledAirMinutes;
    @SerializedName("scheduledTaxiOutMinutes")
    @Expose
    private Integer scheduledTaxiOutMinutes;
    @SerializedName("taxiOutMinutes")
    @Expose
    private Integer taxiOutMinutes;
    @SerializedName("scheduledTaxiInMinutes")
    @Expose
    private Integer scheduledTaxiInMinutes;

    /**
     * 
     * @return
     *     The scheduledBlockMinutes
     */
    public Integer getScheduledBlockMinutes() {
        return scheduledBlockMinutes;
    }

    /**
     * 
     * @param scheduledBlockMinutes
     *     The scheduledBlockMinutes
     */
    public void setScheduledBlockMinutes(Integer scheduledBlockMinutes) {
        this.scheduledBlockMinutes = scheduledBlockMinutes;
    }

    /**
     * 
     * @return
     *     The scheduledAirMinutes
     */
    public Integer getScheduledAirMinutes() {
        return scheduledAirMinutes;
    }

    /**
     * 
     * @param scheduledAirMinutes
     *     The scheduledAirMinutes
     */
    public void setScheduledAirMinutes(Integer scheduledAirMinutes) {
        this.scheduledAirMinutes = scheduledAirMinutes;
    }

    /**
     * 
     * @return
     *     The scheduledTaxiOutMinutes
     */
    public Integer getScheduledTaxiOutMinutes() {
        return scheduledTaxiOutMinutes;
    }

    /**
     * 
     * @param scheduledTaxiOutMinutes
     *     The scheduledTaxiOutMinutes
     */
    public void setScheduledTaxiOutMinutes(Integer scheduledTaxiOutMinutes) {
        this.scheduledTaxiOutMinutes = scheduledTaxiOutMinutes;
    }

    /**
     * 
     * @return
     *     The taxiOutMinutes
     */
    public Integer getTaxiOutMinutes() {
        return taxiOutMinutes;
    }

    /**
     * 
     * @param taxiOutMinutes
     *     The taxiOutMinutes
     */
    public void setTaxiOutMinutes(Integer taxiOutMinutes) {
        this.taxiOutMinutes = taxiOutMinutes;
    }

    /**
     * 
     * @return
     *     The scheduledTaxiInMinutes
     */
    public Integer getScheduledTaxiInMinutes() {
        return scheduledTaxiInMinutes;
    }

    /**
     * 
     * @param scheduledTaxiInMinutes
     *     The scheduledTaxiInMinutes
     */
    public void setScheduledTaxiInMinutes(Integer scheduledTaxiInMinutes) {
        this.scheduledTaxiInMinutes = scheduledTaxiInMinutes;
    }

}
