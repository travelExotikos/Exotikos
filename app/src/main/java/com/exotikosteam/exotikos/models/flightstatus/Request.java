
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Request {

    @SerializedName("airline")
    @Expose
    private RequestAirline airline;
    @SerializedName("flight")
    @Expose
    private RequestFlight flight;
    @SerializedName("date")
    @Expose
    private RequestDate date;
    @SerializedName("utc")
    @Expose
    private RequestUtc utc;
    @SerializedName("airport")
    @Expose
    private RequestAirport airport;
    @SerializedName("codeType")
    @Expose
    private RequestCodeType codeType;
    @SerializedName("extendedOptions")
    @Expose
    private RequestExtendedOptions extendedOptions;
    @SerializedName("url")
    @Expose
    private String url;

    public RequestAirline getAirline() {
        return airline;
    }

    public void setAirline(RequestAirline airline) {
        this.airline = airline;
    }

    public RequestFlight getFlight() {
        return flight;
    }

    public void setFlight(RequestFlight flight) {
        this.flight = flight;
    }

    public RequestDate getDate() {
        return date;
    }

    public void setDate(RequestDate date) {
        this.date = date;
    }

    public RequestUtc getUtc() {
        return utc;
    }

    public void setUtc(RequestUtc utc) {
        this.utc = utc;
    }

    public RequestAirport getAirport() {
        return airport;
    }

    public void setAirport(RequestAirport airport) {
        this.airport = airport;
    }

    public RequestCodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(RequestCodeType codeType) {
        this.codeType = codeType;
    }

    public RequestExtendedOptions getExtendedOptions() {
        return extendedOptions;
    }

    public void setExtendedOptions(RequestExtendedOptions extendedOptions) {
        this.extendedOptions = extendedOptions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
