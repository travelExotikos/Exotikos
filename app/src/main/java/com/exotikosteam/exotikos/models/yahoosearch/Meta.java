
package com.exotikosteam.exotikos.models.yahoosearch;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Meta {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("first")
    @Expose
    private Integer first;
    @SerializedName("last")
    @Expose
    private Integer last;
    @SerializedName("nextoffsetaddcount")
    @Expose
    private Integer nextoffsetaddcount;

    /**
     * 
     * @return
     *     The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The first
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * 
     * @param first
     *     The first
     */
    public void setFirst(Integer first) {
        this.first = first;
    }

    /**
     * 
     * @return
     *     The last
     */
    public Integer getLast() {
        return last;
    }

    /**
     * 
     * @param last
     *     The last
     */
    public void setLast(Integer last) {
        this.last = last;
    }

    /**
     * 
     * @return
     *     The nextoffsetaddcount
     */
    public Integer getNextoffsetaddcount() {
        return nextoffsetaddcount;
    }

    /**
     * 
     * @param nextoffsetaddcount
     *     The nextoffsetaddcount
     */
    public void setNextoffsetaddcount(Integer nextoffsetaddcount) {
        this.nextoffsetaddcount = nextoffsetaddcount;
    }

}
