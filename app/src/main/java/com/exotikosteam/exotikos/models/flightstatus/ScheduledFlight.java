
package com.exotikosteam.exotikos.models.flightstatus;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ScheduledFlight {

    @SerializedName("carrierFsCode")
    @Expose
    private String carrierFsCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("departureAirportFsCode")
    @Expose
    private String departureAirportFsCode;
    @SerializedName("arrivalAirportFsCode")
    @Expose
    private String arrivalAirportFsCode;
    @SerializedName("stops")
    @Expose
    private Integer stops;
    @SerializedName("departureTerminal")
    @Expose
    private String departureTerminal;
    @SerializedName("arrivalTerminal")
    @Expose
    private String arrivalTerminal;
    @SerializedName("departureTime")
    @Expose
    private String departureTime;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("flightEquipmentIataCode")
    @Expose
    private String flightEquipmentIataCode;
    @SerializedName("isCodeshare")
    @Expose
    private Boolean isCodeshare;
    @SerializedName("isWetlease")
    @Expose
    private Boolean isWetlease;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("serviceClasses")
    @Expose
    private List<String> serviceClasses = new ArrayList<String>();
    @SerializedName("trafficRestrictions")
    @Expose
    private List<Object> trafficRestrictions = new ArrayList<Object>();
    @SerializedName("codeshares")
    @Expose
    private List<Codeshare> codeshares = new ArrayList<Codeshare>();
    @SerializedName("referenceCode")
    @Expose
    private String referenceCode;

    /**
     * 
     * @return
     *     The carrierFsCode
     */
    public String getCarrierFsCode() {
        return carrierFsCode;
    }

    /**
     * 
     * @param carrierFsCode
     *     The carrierFsCode
     */
    public void setCarrierFsCode(String carrierFsCode) {
        this.carrierFsCode = carrierFsCode;
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
     *     The departureAirportFsCode
     */
    public String getDepartureAirportFsCode() {
        return departureAirportFsCode;
    }

    /**
     * 
     * @param departureAirportFsCode
     *     The departureAirportFsCode
     */
    public void setDepartureAirportFsCode(String departureAirportFsCode) {
        this.departureAirportFsCode = departureAirportFsCode;
    }

    /**
     * 
     * @return
     *     The arrivalAirportFsCode
     */
    public String getArrivalAirportFsCode() {
        return arrivalAirportFsCode;
    }

    /**
     * 
     * @param arrivalAirportFsCode
     *     The arrivalAirportFsCode
     */
    public void setArrivalAirportFsCode(String arrivalAirportFsCode) {
        this.arrivalAirportFsCode = arrivalAirportFsCode;
    }

    /**
     * 
     * @return
     *     The stops
     */
    public Integer getStops() {
        return stops;
    }

    /**
     * 
     * @param stops
     *     The stops
     */
    public void setStops(Integer stops) {
        this.stops = stops;
    }

    /**
     * 
     * @return
     *     The departureTerminal
     */
    public String getDepartureTerminal() {
        return departureTerminal;
    }

    /**
     * 
     * @param departureTerminal
     *     The departureTerminal
     */
    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    /**
     * 
     * @return
     *     The arrivalTerminal
     */
    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    /**
     * 
     * @param arrivalTerminal
     *     The arrivalTerminal
     */
    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    /**
     * 
     * @return
     *     The departureTime
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * 
     * @param departureTime
     *     The departureTime
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * 
     * @return
     *     The arrivalTime
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 
     * @param arrivalTime
     *     The arrivalTime
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 
     * @return
     *     The flightEquipmentIataCode
     */
    public String getFlightEquipmentIataCode() {
        return flightEquipmentIataCode;
    }

    /**
     * 
     * @param flightEquipmentIataCode
     *     The flightEquipmentIataCode
     */
    public void setFlightEquipmentIataCode(String flightEquipmentIataCode) {
        this.flightEquipmentIataCode = flightEquipmentIataCode;
    }

    /**
     * 
     * @return
     *     The isCodeshare
     */
    public Boolean getIsCodeshare() {
        return isCodeshare;
    }

    /**
     * 
     * @param isCodeshare
     *     The isCodeshare
     */
    public void setIsCodeshare(Boolean isCodeshare) {
        this.isCodeshare = isCodeshare;
    }

    /**
     * 
     * @return
     *     The isWetlease
     */
    public Boolean getIsWetlease() {
        return isWetlease;
    }

    /**
     * 
     * @param isWetlease
     *     The isWetlease
     */
    public void setIsWetlease(Boolean isWetlease) {
        this.isWetlease = isWetlease;
    }

    /**
     * 
     * @return
     *     The serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 
     * @param serviceType
     *     The serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 
     * @return
     *     The serviceClasses
     */
    public List<String> getServiceClasses() {
        return serviceClasses;
    }

    /**
     * 
     * @param serviceClasses
     *     The serviceClasses
     */
    public void setServiceClasses(List<String> serviceClasses) {
        this.serviceClasses = serviceClasses;
    }

    /**
     * 
     * @return
     *     The trafficRestrictions
     */
    public List<Object> getTrafficRestrictions() {
        return trafficRestrictions;
    }

    /**
     * 
     * @param trafficRestrictions
     *     The trafficRestrictions
     */
    public void setTrafficRestrictions(List<Object> trafficRestrictions) {
        this.trafficRestrictions = trafficRestrictions;
    }

    /**
     * 
     * @return
     *     The codeshares
     */
    public List<Codeshare> getCodeshares() {
        return codeshares;
    }

    /**
     * 
     * @param codeshares
     *     The codeshares
     */
    public void setCodeshares(List<Codeshare> codeshares) {
        this.codeshares = codeshares;
    }

    /**
     * 
     * @return
     *     The referenceCode
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * 
     * @param referenceCode
     *     The referenceCode
     */
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

}
