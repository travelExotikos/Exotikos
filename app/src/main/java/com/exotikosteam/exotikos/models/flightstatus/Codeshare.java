
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Codeshare {

    @SerializedName("fsCode")
    @Expose
    private String fsCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("relationship")
    @Expose
    private String relationship;

    /**
     * 
     * @return
     *     The fsCode
     */
    public String getFsCode() {
        return fsCode;
    }

    /**
     * 
     * @param fsCode
     *     The fsCode
     */
    public void setFsCode(String fsCode) {
        this.fsCode = fsCode;
    }

    /**
     * 
     * @return
     *     The flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * 
     * @param flightNumber
     *     The flightNumber
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * 
     * @return
     *     The relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 
     * @param relationship
     *     The relationship
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

}
