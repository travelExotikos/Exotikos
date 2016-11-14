
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class DateLocalAndUTC {

    @SerializedName("dateLocal")
    @Expose
    String dateLocal;
    @SerializedName("dateUtc")
    @Expose
    String dateUtc;

    /**
     * 
     * @return
     *     The dateLocal
     */
    public String getDateLocal() {
        return dateLocal;
    }

    /**
     * 
     * @param dateLocal
     *     The dateLocal
     */
    public void setDateLocal(String dateLocal) {
        this.dateLocal = dateLocal;
    }

    /**
     * 
     * @return
     *     The dateUtc
     */
    public String getDateUtc() {
        return dateUtc;
    }

    /**
     * 
     * @param dateUtc
     *     The dateUtc
     */
    public void setDateUtc(String dateUtc) {
        this.dateUtc = dateUtc;
    }

}
