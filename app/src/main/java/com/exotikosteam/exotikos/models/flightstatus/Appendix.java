
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Appendix {

    @SerializedName("airlines")
    @Expose
    private List<Airline> airlines = new ArrayList<Airline>();
    @SerializedName("airports")
    @Expose
    private List<Airport> airports = new ArrayList<Airport>();
    @SerializedName("equipments")
    @Expose
    private List<Equipment> equipments = new ArrayList<Equipment>();

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
     *     The equipments
     */
    public List<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * 
     * @param equipments
     *     The equipments
     */
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

}
