
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Airport {

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
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("cityCode")
    @Expose
    private String cityCode;
    @SerializedName("stateCode")
    @Expose
    private String stateCode;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("regionName")
    @Expose
    private String regionName;
    @SerializedName("timeZoneRegionName")
    @Expose
    private String timeZoneRegionName;
    @SerializedName("localTime")
    @Expose
    private String localTime;
    @SerializedName("utcOffsetHours")
    @Expose
    private Integer utcOffsetHours;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("elevationFeet")
    @Expose
    private Integer elevationFeet;
    @SerializedName("classification")
    @Expose
    private Integer classification;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("delayIndexUrl")
    @Expose
    private String delayIndexUrl;
    @SerializedName("weatherUrl")
    @Expose
    private String weatherUrl;
    @SerializedName("faa")
    @Expose
    private String faa;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("weatherZone")
    @Expose
    private String weatherZone;

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
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 
     * @param cityCode
     *     The cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 
     * @return
     *     The stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * 
     * @param stateCode
     *     The stateCode
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * 
     * @return
     *     The countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 
     * @param countryCode
     *     The countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 
     * @return
     *     The countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 
     * @param countryName
     *     The countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 
     * @return
     *     The regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * 
     * @param regionName
     *     The regionName
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * 
     * @return
     *     The timeZoneRegionName
     */
    public String getTimeZoneRegionName() {
        return timeZoneRegionName;
    }

    /**
     * 
     * @param timeZoneRegionName
     *     The timeZoneRegionName
     */
    public void setTimeZoneRegionName(String timeZoneRegionName) {
        this.timeZoneRegionName = timeZoneRegionName;
    }

    /**
     * 
     * @return
     *     The localTime
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * 
     * @param localTime
     *     The localTime
     */
    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    /**
     * 
     * @return
     *     The utcOffsetHours
     */
    public Integer getUtcOffsetHours() {
        return utcOffsetHours;
    }

    /**
     * 
     * @param utcOffsetHours
     *     The utcOffsetHours
     */
    public void setUtcOffsetHours(Integer utcOffsetHours) {
        this.utcOffsetHours = utcOffsetHours;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The elevationFeet
     */
    public Integer getElevationFeet() {
        return elevationFeet;
    }

    /**
     * 
     * @param elevationFeet
     *     The elevationFeet
     */
    public void setElevationFeet(Integer elevationFeet) {
        this.elevationFeet = elevationFeet;
    }

    /**
     * 
     * @return
     *     The classification
     */
    public Integer getClassification() {
        return classification;
    }

    /**
     * 
     * @param classification
     *     The classification
     */
    public void setClassification(Integer classification) {
        this.classification = classification;
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
     *     The delayIndexUrl
     */
    public String getDelayIndexUrl() {
        return delayIndexUrl;
    }

    /**
     * 
     * @param delayIndexUrl
     *     The delayIndexUrl
     */
    public void setDelayIndexUrl(String delayIndexUrl) {
        this.delayIndexUrl = delayIndexUrl;
    }

    /**
     * 
     * @return
     *     The weatherUrl
     */
    public String getWeatherUrl() {
        return weatherUrl;
    }

    /**
     * 
     * @param weatherUrl
     *     The weatherUrl
     */
    public void setWeatherUrl(String weatherUrl) {
        this.weatherUrl = weatherUrl;
    }

    /**
     * 
     * @return
     *     The faa
     */
    public String getFaa() {
        return faa;
    }

    /**
     * 
     * @param faa
     *     The faa
     */
    public void setFaa(String faa) {
        this.faa = faa;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * @return
     *     The weatherZone
     */
    public String getWeatherZone() {
        return weatherZone;
    }

    /**
     * 
     * @param weatherZone
     *     The weatherZone
     */
    public void setWeatherZone(String weatherZone) {
        this.weatherZone = weatherZone;
    }

}
