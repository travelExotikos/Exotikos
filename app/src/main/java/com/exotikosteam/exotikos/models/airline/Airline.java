
package com.exotikosteam.exotikos.models.airline;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.util.List;

import javax.annotation.Generated;

@Parcel(analyze = Airline.class)
@Table(database = ExotikosDatabase.class)
@Generated("org.jsonschema2pojo")
public class Airline extends BaseModel {

    @Column
    @PrimaryKey
    @Unique(onUniqueConflict = ConflictAction.REPLACE)
    @SerializedName("fs")
    @Expose
    protected String fs;

    @Column
    @SerializedName("iata")
    @Expose
    protected String iata;

    @Column
    @SerializedName("icao")
    @Expose
    protected String icao;

    @Column
    @SerializedName("name")
    @Expose
    protected String name;

    @Column
    @SerializedName("phoneNumber")
    @Expose
    protected String phoneNumber;

    @Column
    @SerializedName("active")
    @Expose
    protected Boolean active;

    @Column
    @SerializedName("dateFrom")
    @Expose
    protected String dateFrom;

    @Column
    @SerializedName("dateTo")
    @Expose
    protected String dateTo;

    @Column
    @SerializedName("category")
    @Expose
    protected String category;

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

    public static List<Airline> getAll() {
        return SQLite.select().from(Airline.class).queryList();
    }

}
