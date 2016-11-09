
package com.exotikosteam.exotikos.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Error {

    @SerializedName("httpStatusCode")
    @Expose
    private Integer httpStatusCode;
    @SerializedName("errorId")
    @Expose
    private String errorId;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    /**
     * 
     * @return
     *     The httpStatusCode
     */
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * 
     * @param httpStatusCode
     *     The httpStatusCode
     */
    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * 
     * @return
     *     The errorId
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * 
     * @param errorId
     *     The errorId
     */
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    /**
     * 
     * @return
     *     The errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage
     *     The errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
