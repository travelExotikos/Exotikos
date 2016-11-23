
package com.exotikosteam.exotikos.models.yahoosearch;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ImageSearch {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("beac")
    @Expose
    private Beac beac;

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The beac
     */
    public Beac getBeac() {
        return beac;
    }

    /**
     * 
     * @param beac
     *     The beac
     */
    public void setBeac(Beac beac) {
        this.beac = beac;
    }

}
