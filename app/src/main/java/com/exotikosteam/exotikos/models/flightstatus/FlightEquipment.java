
package com.exotikosteam.exotikos.models.flightstatus;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FlightEquipment {

    @SerializedName("scheduledEquipmentIataCode")
    @Expose
    private String scheduledEquipmentIataCode;
    @SerializedName("actualEquipmentIataCode")
    @Expose
    private String actualEquipmentIataCode;
    @SerializedName("tailNumber")
    @Expose
    private String tailNumber;

    /**
     * 
     * @return
     *     The scheduledEquipmentIataCode
     */
    public String getScheduledEquipmentIataCode() {
        return scheduledEquipmentIataCode;
    }

    /**
     * 
     * @param scheduledEquipmentIataCode
     *     The scheduledEquipmentIataCode
     */
    public void setScheduledEquipmentIataCode(String scheduledEquipmentIataCode) {
        this.scheduledEquipmentIataCode = scheduledEquipmentIataCode;
    }

    /**
     * 
     * @return
     *     The actualEquipmentIataCode
     */
    public String getActualEquipmentIataCode() {
        return actualEquipmentIataCode;
    }

    /**
     * 
     * @param actualEquipmentIataCode
     *     The actualEquipmentIataCode
     */
    public void setActualEquipmentIataCode(String actualEquipmentIataCode) {
        this.actualEquipmentIataCode = actualEquipmentIataCode;
    }

    /**
     * 
     * @return
     *     The tailNumber
     */
    public String getTailNumber() {
        return tailNumber;
    }

    /**
     * 
     * @param tailNumber
     *     The tailNumber
     */
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

}
