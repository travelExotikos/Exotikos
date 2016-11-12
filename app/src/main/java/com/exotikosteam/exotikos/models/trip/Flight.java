package com.exotikosteam.exotikos.models.trip;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.exotikosteam.exotikos.models.flightstatus.FlightStatus;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by lramaswamy on 11/9/16.
 */
@Parcel(analyze = {Flight.class})
@Table(database = ExotikosDatabase.class)
public class Flight extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    Integer id;

    @Column
    Integer order;

    @Column(name = "trip_id")
    Integer tripId;

    @Column(name = "flight_number")
    String flightNumber;
    //Integer stops; Not sure if this is needed...I think this will be handled through Trips

    //TODO LocalDataTime? - need converter
    @Column(name = "departure_time")
    String departureTime;

    //TODO LocalDataTime> - need convertor
    @Column(name = "arrival_time")
    String arrivalTime;

    //Terminal for the flight departure
    @Column(name = "departure_terminal")
    String departureTerminal;

    @Column(name = "arrival_terminal")
    String arrivalTerminal;

    //Get these from FlightStatus API
    @Column(name = "departure_date")
    String departureDate;

    @Column(name = "arrival_date")
    String arrivalDate;

    @Column(name = "seat_number")
    String seatNumber;

    //Empty constructor for Parceler
    public Flight() {}

    public static Flight newInstance(FlightStatus flightStatus, ScheduledFlight scheduledFlight, String seatNumber) {
        Flight flight = new Flight();
        flight.setArrivalDate(flightStatus.getArrivalDate().getDateUtc());
        flight.setDepartureDate(flightStatus.getDepartureDate().getDateUtc());
        flight.setFlightNumber(scheduledFlight.getFlightNumber());
        flight.setDepartureTime(scheduledFlight.getDepartureTime());
        flight.setDepartureTerminal(scheduledFlight.getDepartureTerminal());
        flight.setArrivalTime(scheduledFlight.getArrivalTime());
        flight.setArrivalTerminal(scheduledFlight.getArrivalTerminal());
        flight.setSeatNumber(seatNumber);

        return flight;
    }

    public static Flight newInstance(String arrivalDate, String departureDate, String flightNumber, String departureTime,
    String departureTerminal, String arrivalTime, String arrivalTerminal, String seatNumber)
    {
        Flight flight = new Flight();
        flight.setArrivalDate(arrivalDate);
        flight.setDepartureDate(departureDate);
        flight.setFlightNumber(flightNumber);
        flight.setDepartureTime(departureTime);
        flight.setDepartureTerminal(departureTerminal);
        flight.setArrivalTime(arrivalTime);
        flight.setArrivalTerminal(arrivalTerminal);
        flight.setSeatNumber(seatNumber);

        return flight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public static Flight get(Integer id) {
        return SQLite.select().from(Flight.class).where(Flight_Table.id.eq(id)).querySingle();
    }

    public static List<Flight> getAll() {
        return SQLite.select().from(Flight.class).queryList();
    }

    public static void save(Flight flight) {
        flight.save();
    }
}
