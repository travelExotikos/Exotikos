package com.exotikosteam.exotikos.models.trip;

import com.exotikosteam.exotikos.models.flightstatus.DateLocalAndUTC;
import com.exotikosteam.exotikos.models.flightstatus.FlightStatus;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;

import org.parceler.Parcel;

/**
 * Created by lramaswamy on 11/9/16.
 */
@Parcel
public class Flight {

    //Get these from Scheduled flight API
    //Flight number of the flight
    private String flightNumber;
    //private Integer stops; Not sure if this is needed...I think this will be handled through Trips

    //Departure Time of the flight
    String departureTime;

    //Arrival time for the flight
    private String arrivalTime;

    //Terminal for the flight departure
    String departureTerminal;

    //Terminal for the flight arrival
    private String arrivalTerminal;

    //Get these from FlightStatus API
    //Date of the departure of the flight
    private DateLocalAndUTC departureDate;

    //Date of the arrival of the flight
    private DateLocalAndUTC arrivalDate;

    private String seatNumber;

    //Empty constructor for Parceler
    public Flight() {}

    public Flight createFlightItem(FlightStatus flightStatus, ScheduledFlight scheduledFlight, String seatNumber) {
        Flight flight = new Flight();
        flight.setArrivalDate(flightStatus.getArrivalDate());
        flight.setDepartureDate(flightStatus.getDepartureDate());
        flight.setFlightNumber(scheduledFlight.getFlightNumber());
        flight.setDepartureTime(scheduledFlight.getDepartureTime());
        flight.setDepartureTerminal(scheduledFlight.getDepartureTerminal());
        flight.setArrivalTime(scheduledFlight.getArrivalTime());
        flight.setArrivalTerminal(scheduledFlight.getArrivalTerminal());
        flight.setSeatNumber(seatNumber);

        return flight;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public DateLocalAndUTC getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(DateLocalAndUTC departureDate) {
        this.departureDate = departureDate;
    }

    public DateLocalAndUTC getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(DateLocalAndUTC arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
