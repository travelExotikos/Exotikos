
package com.exotikosteam.exotikos.models.flightstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class OperationalTimes {

    @SerializedName("publishedDeparture")
    @Expose
    private DateLocalAndUTC publishedDeparture;
    @SerializedName("publishedArrival")
    @Expose
    private DateLocalAndUTC publishedArrival;
    @SerializedName("scheduledGateDeparture")
    @Expose
    private DateLocalAndUTC scheduledGateDeparture;
    @SerializedName("estimatedGateDeparture")
    @Expose
    private DateLocalAndUTC estimatedGateDeparture;
    @SerializedName("actualGateDeparture")
    @Expose
    private DateLocalAndUTC actualGateDeparture;
    @SerializedName("flightPlanPlannedDeparture")
    @Expose
    private DateLocalAndUTC flightPlanPlannedDeparture;
    @SerializedName("estimatedRunwayDeparture")
    @Expose
    private DateLocalAndUTC estimatedRunwayDeparture;
    @SerializedName("actualRunwayDeparture")
    @Expose
    private DateLocalAndUTC actualRunwayDeparture;
    @SerializedName("scheduledGateArrival")
    @Expose
    private DateLocalAndUTC scheduledGateArrival;
    @SerializedName("estimatedGateArrival")
    @Expose
    private DateLocalAndUTC estimatedGateArrival;
    @SerializedName("flightPlanPlannedArrival")
    @Expose
    private DateLocalAndUTC flightPlanPlannedArrival;

    public DateLocalAndUTC getPublishedDeparture() {
        return publishedDeparture;
    }

    public void setPublishedDeparture(DateLocalAndUTC publishedDeparture) {
        this.publishedDeparture = publishedDeparture;
    }

    public DateLocalAndUTC getPublishedArrival() {
        return publishedArrival;
    }

    public void setPublishedArrival(DateLocalAndUTC publishedArrival) {
        this.publishedArrival = publishedArrival;
    }

    public DateLocalAndUTC getScheduledGateDeparture() {
        return scheduledGateDeparture;
    }

    public void setScheduledGateDeparture(DateLocalAndUTC scheduledGateDeparture) {
        this.scheduledGateDeparture = scheduledGateDeparture;
    }

    public DateLocalAndUTC getEstimatedGateDeparture() {
        return estimatedGateDeparture;
    }

    public void setEstimatedGateDeparture(DateLocalAndUTC estimatedGateDeparture) {
        this.estimatedGateDeparture = estimatedGateDeparture;
    }

    public DateLocalAndUTC getActualGateDeparture() {
        return actualGateDeparture;
    }

    public void setActualGateDeparture(DateLocalAndUTC actualGateDeparture) {
        this.actualGateDeparture = actualGateDeparture;
    }

    public DateLocalAndUTC getFlightPlanPlannedDeparture() {
        return flightPlanPlannedDeparture;
    }

    public void setFlightPlanPlannedDeparture(DateLocalAndUTC flightPlanPlannedDeparture) {
        this.flightPlanPlannedDeparture = flightPlanPlannedDeparture;
    }

    public DateLocalAndUTC getEstimatedRunwayDeparture() {
        return estimatedRunwayDeparture;
    }

    public void setEstimatedRunwayDeparture(DateLocalAndUTC estimatedRunwayDeparture) {
        this.estimatedRunwayDeparture = estimatedRunwayDeparture;
    }

    public DateLocalAndUTC getActualRunwayDeparture() {
        return actualRunwayDeparture;
    }

    public void setActualRunwayDeparture(DateLocalAndUTC actualRunwayDeparture) {
        this.actualRunwayDeparture = actualRunwayDeparture;
    }

    public DateLocalAndUTC getScheduledGateArrival() {
        return scheduledGateArrival;
    }

    public void setScheduledGateArrival(DateLocalAndUTC scheduledGateArrival) {
        this.scheduledGateArrival = scheduledGateArrival;
    }

    public DateLocalAndUTC getEstimatedGateArrival() {
        return estimatedGateArrival;
    }

    public void setEstimatedGateArrival(DateLocalAndUTC estimatedGateArrival) {
        this.estimatedGateArrival = estimatedGateArrival;
    }

    public DateLocalAndUTC getFlightPlanPlannedArrival() {
        return flightPlanPlannedArrival;
    }

    public void setFlightPlanPlannedArrival(DateLocalAndUTC flightPlanPlannedArrival) {
        this.flightPlanPlannedArrival = flightPlanPlannedArrival;
    }
}
