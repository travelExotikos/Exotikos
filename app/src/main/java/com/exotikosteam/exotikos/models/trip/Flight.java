package com.exotikosteam.exotikos.models.trip;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.exotikosteam.exotikos.models.flightstatus.AirportResources;
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

    @Column(name = "flight_id")
    Integer flightId;

    @Column(name = "seat_number")
    String seatNumber;

    @Column(name = "departure_gate")
    String departureGate;

    @Column(name = "departure_date")
    String departureDate;

    @Column(name = "departure_terminal")
    String departureTerminal;

    @Column(name = "departure_time")
    String departureTime;

    @Column(name = "departure_airport_IATA")
    String departureAirportIATA;

    @Column(name = "departure_city")
    String departureCity;

    @Column(name = "arrival_date")
    String arrivalDate;

    @Column(name = "arrival_terminal")
    String arrivalTerminal;

    @Column(name = "arrival_time")
    String arrivalTime;

    @Column(name = "arrival_airport_IATA")
    String arrivalAirportIATA;

    @Column(name = "arrival_city")
    String arrivalCity;


    //Empty constructor for Parceler
    public Flight() {
    }

    public static Flight newInstance(Integer tripId, FlightStatus flightStatus, String seatNumber) {
        Flight flight = new Flight();
        flight.setTripId(tripId);
        flight.setFlightNumber(flightStatus.getFlightNumber());
        flight.setDepartureTime(flightStatus.getDepartureDate().getDateUtc().substring(11, 16));
        flight.setDepartureDate(flightStatus.getDepartureDate().getDateUtc().substring(0, 10));
        flight.setDepartureAirportIATA(flightStatus.getDepartureAirportFsCode());
        flight.setDepartureCity("TODO");
        flight.setArrivalTime(flightStatus.getArrivalDate().getDateUtc().substring(11, 16));
        flight.setArrivalDate(flightStatus.getArrivalDate().getDateUtc().substring(0, 10));
        flight.setArrivalAirportIATA(flightStatus.getArrivalAirportFsCode());
        flight.setArrivalCity("TODO");
        flight.setSeatNumber(seatNumber);
        flight.setFlightId(flightStatus.getFlightId());

        AirportResources ar = flightStatus.getAirportResources();
        if (ar != null) {
            flight.setDepartureGate(ar.getDepartureGate());
            flight.setDepartureTerminal(ar.getDepartureTerminal());
            flight.setArrivalTerminal(ar.getArrivalTerminal());
        }

        return flight;
    }

    public static Flight newInstance(String arrivalDate, String departureDate, String flightNumber, String departureTime,
                                     String departureTerminal, String arrivalTime, String arrivalTerminal, String seatNumber) {
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

    public static Flight fromScheduledFlight(ScheduledFlight sch) {
        Flight flight = new Flight();
        // TODO - We have date and time in here? why not a date object?
        flight.setArrivalDate(sch.getArrivalTime());
        flight.setDepartureDate(sch.getDepartureTime());
        flight.setFlightNumber(sch.getFlightNumber());
        flight.setArrivalTime(sch.getArrivalTime());
        flight.setDepartureTime(sch.getDepartureTime());
        flight.setArrivalAirportIATA(sch.getArrivalAirportFsCode());
        flight.setDepartureAirportIATA(sch.getDepartureAirportFsCode());
        flight.setDepartureTerminal(sch.getDepartureTerminal());
        flight.setArrivalTerminal(sch.getArrivalTerminal());

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

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(String departureGate) {
        this.departureGate = departureGate;
    }

    public String getDepartureAirportIATA() {
        return departureAirportIATA;
    }

    public void setDepartureAirportIATA(String departureAirportIATA) {
        this.departureAirportIATA = departureAirportIATA;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalAirportIATA() {
        return arrivalAirportIATA;
    }

    public void setArrivalAirportIATA(String arrivalAirportIATA) {
        this.arrivalAirportIATA = arrivalAirportIATA;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }


    //=================== DB operations ========================

    public static Flight get(Integer id) {
        return SQLite.select().from(Flight.class).where(Flight_Table.id.eq(id)).querySingle();
    }

    public static List<Flight> getAll() {
        return SQLite.select().from(Flight.class).queryList();
    }

    public static TripStatus createNewFlight(Flight flight) {
        TripStatus trip = null;
        if (flight.getTripId() == null) {
            trip = new TripStatus();
            trip.setFlightStep(FlightStep.PREPARATION);
            trip.setCurrentFlight(0);
            trip.save();
            flight.setTripId(trip.getId());
            flight.setOrder(0);
        } else {
            trip = TripStatus.get(flight.getTripId());
            flight.setOrder(trip.getFlights().size());
        }
        flight.save();
        trip.getFlights().add(flight);
        return trip;
    }
}
