
package com.exotikosteam.exotikos.models.airline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import javax.annotation.Generated;

@Parcel
@Generated("org.jsonschema2pojo")
public class Airline {

    @SerializedName("fs")
    @Expose
    private String fs;
    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("icao")
    @Expose
    private String icao;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("dateFrom")
    @Expose
    private String dateFrom;
    @SerializedName("dateTo")
    @Expose
    private String dateTo;
    @SerializedName("category")
    @Expose
    private String category;

    /**
     * 
     * @return
     *     The fs
     */
    public String getFs() {
        return fs;
    }

    /**
     * 
     * @param fs
     *     The fs
     */
    public void setFs(String fs) {
        this.fs = fs;
    }

    /**
     * 
     * @return
     *     The iata
     */
    public String getIata() {
        return iata;
    }

    /**
     * 
     * @param iata
     *     The iata
     */
    public void setIata(String iata) {
        this.iata = iata;
    }

    /**
     * 
     * @return
     *     The icao
     */
    public String getIcao() {
        return icao;
    }

    /**
     * 
     * @param icao
     *     The icao
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return
     *     The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * 
     * @param active
     *     The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * 
     * @return
     *     The dateFrom
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * 
     * @param dateFrom
     *     The dateFrom
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * 
     * @return
     *     The dateTo
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * 
     * @param dateTo
     *     The dateTo
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public String getIconUrl() {
        return String.format("http://www.gstatic.com/flights/airline_logos/70px/%s.png", iata);
    }

}
