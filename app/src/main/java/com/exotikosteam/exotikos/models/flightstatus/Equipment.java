
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Equipment {

    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("turboProp")
    @Expose
    private Boolean turboProp;
    @SerializedName("jet")
    @Expose
    private Boolean jet;
    @SerializedName("widebody")
    @Expose
    private Boolean widebody;
    @SerializedName("regional")
    @Expose
    private Boolean regional;

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
     *     The turboProp
     */
    public Boolean getTurboProp() {
        return turboProp;
    }

    /**
     * 
     * @param turboProp
     *     The turboProp
     */
    public void setTurboProp(Boolean turboProp) {
        this.turboProp = turboProp;
    }

    /**
     * 
     * @return
     *     The jet
     */
    public Boolean getJet() {
        return jet;
    }

    /**
     * 
     * @param jet
     *     The jet
     */
    public void setJet(Boolean jet) {
        this.jet = jet;
    }

    /**
     * 
     * @return
     *     The widebody
     */
    public Boolean getWidebody() {
        return widebody;
    }

    /**
     * 
     * @param widebody
     *     The widebody
     */
    public void setWidebody(Boolean widebody) {
        this.widebody = widebody;
    }

    /**
     * 
     * @return
     *     The regional
     */
    public Boolean getRegional() {
        return regional;
    }

    /**
     * 
     * @param regional
     *     The regional
     */
    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

}
