
package com.exotikosteam.exotikos.models.yahoosearch;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Beac {

    @SerializedName("bCurImg")
    @Expose
    private String bCurImg;
    @SerializedName("bNxtImg")
    @Expose
    private String bNxtImg;
    @SerializedName("bPrvImg")
    @Expose
    private String bPrvImg;

    /**
     * 
     * @return
     *     The bCurImg
     */
    public String getBCurImg() {
        return bCurImg;
    }

    /**
     * 
     * @param bCurImg
     *     The bCurImg
     */
    public void setBCurImg(String bCurImg) {
        this.bCurImg = bCurImg;
    }

    /**
     * 
     * @return
     *     The bNxtImg
     */
    public String getBNxtImg() {
        return bNxtImg;
    }

    /**
     * 
     * @param bNxtImg
     *     The bNxtImg
     */
    public void setBNxtImg(String bNxtImg) {
        this.bNxtImg = bNxtImg;
    }

    /**
     * 
     * @return
     *     The bPrvImg
     */
    public String getBPrvImg() {
        return bPrvImg;
    }

    /**
     * 
     * @param bPrvImg
     *     The bPrvImg
     */
    public void setBPrvImg(String bPrvImg) {
        this.bPrvImg = bPrvImg;
    }

}
