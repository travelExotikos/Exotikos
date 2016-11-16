package com.exotikosteam.exotikos.models.trip;

import android.text.TextUtils;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.exotikosteam.exotikos.models.flightstatus.AirportResources;
import com.exotikosteam.exotikos.models.flightstatus.FlightStatus;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;
import com.exotikosteam.exotikos.utils.Utils;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.util.Date;
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

    @Column(name = "trip_id", excludeFromToModelMethod = false)
    Integer tripId;

    @Column(name = "flight_number")
    String flightNumber;

    @Column(name = "flight_carrier")
    String flightCarrier;

    @Column(name = "flight_id")
    Integer flightId;

    @Column(name = "seat_number")
    String seatNumber;

    @Column(name = "departure_gate")
    String departureGate;

    @Column(name = "departure_time")
    String departureTime;

    @Column(name = "departure_terminal")
    String departureTerminal;

    @Column(name = "departure_time_UTC")
    Date departureTimeUTC;

    @Column(name = "departure_airport_IATA")
    String departureAirportIATA;

    @Column(name = "departure_city")
    String departureCity;

    @Column(name = "arrival_time")
    String arrivalTime;

    @Column(name = "arrival_terminal")
    String arrivalTerminal;

    @Column(name = "arrival_time_UTC")
    Date arrivalTimeUTC;

    @Column(name = "arrival_airport_IATA")
    String arrivalAirportIATA;

    @Column(name = "arrival_city")
    String arrivalCity;


    //Empty constructor for Parceler
    public Flight() {
    }

    public Flight(ScheduledFlight sch) {
        super();
        this.setArrivalAirportIATA(sch.getArrivalAirportFsCode());
        //TODO set city
        this.setArrivalCity("TODO");
        this.setArrivalTerminal(sch.getArrivalTerminal());
        this.setArrivalTime(sch.getArrivalTime());
        this.setDepartureAirportIATA(sch.getDepartureAirportFsCode());
        //TODO set city
        this.setDepartureCity("TODO");
        this.setDepartureTerminal(sch.getDepartureTerminal());
        this.setDepartureTime(sch.getDepartureTime());
        this.setFlightCarrier(sch.getCarrierFsCode());
        this.setFlightNumber(sch.getFlightNumber());
    }

    public Flight(FlightStatus flightStatus, String seatNo) {
        super();
        this.setArrivalAirportIATA(flightStatus.getArrivalAirportFsCode());
        //TODO set city
        this.setArrivalCity("TODO");
        this.setArrivalTime(flightStatus.getArrivalDate().getDateLocal());
        this.setDepartureAirportIATA(flightStatus.getDepartureAirportFsCode());
        //TODO set city
        this.setDepartureCity("TODO");
        this.setDepartureTime(flightStatus.getDepartureDate().getDateLocal());

        this.setArrivalTimeUTC(Utils.parseFlightstatsDate(flightStatus.getArrivalDate().getDateUtc()));
        this.setDepartureTimeUTC(Utils.parseFlightstatsDate(flightStatus.getArrivalDate().getDateUtc()));

        this.setFlightId(flightStatus.getFlightId());
        this.setFlightCarrier(flightStatus.getCarrierFsCode());
        this.setFlightNumber(flightStatus.getFlightNumber());

        AirportResources ar = flightStatus.getAirportResources();
        if (ar != null) {
            this.setDepartureGate(ar.getDepartureGate());
            this.setDepartureTerminal(ar.getDepartureTerminal());
            this.setArrivalTerminal(ar.getArrivalTerminal());
        }

        if (!TextUtils.isEmpty(seatNo)) {
            this.setSeatNumber(seatNo);
        }
    }

    //TODO remove - use only for example data
    public static Flight newInstance(String arrivalDate, String departureDate, String flightNumber, String departureTime,
                                     String departureTerminal, String arrivalTime, String arrivalTerminal, String seatNumber) {
        Flight flight = new Flight();
        //flight.setArrivalDate(arrivalDate);
        //flight.setDepartureDate(departureDate);
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

    public String getFlightCarrier() {
        return flightCarrier;
    }

    public void setFlightCarrier(String flightCarrier) {
        this.flightCarrier = flightCarrier;
    }

    public String getAirlineIconUrl() {
        return String.format("http://www.gstatic.com/flights/airline_logos/70px/%s.png", flightCarrier);
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureTimeUTC() {
        return departureTimeUTC;
    }

    public void setDepartureTimeUTC(Date departureTimeUTC) {
        this.departureTimeUTC = departureTimeUTC;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTimeUTC() {
        return arrivalTimeUTC;
    }

    public void setArrivalTimeUTC(Date arrivalTimeUTC) {
        this.arrivalTimeUTC = arrivalTimeUTC;
    }

    //=================== DB operations ========================

    public static Flight get(Integer id) {
        return SQLite.select().from(Flight.class).where(Flight_Table.id.eq(id)).querySingle();
    }

    public static List<Flight> getAll() {
        return SQLite.select().from(Flight.class).queryList();
    }

    public static void mergeFlights(Flight to, Flight from) {
        to.setArrivalTimeUTC((from.getArrivalTimeUTC() != null ? from.getArrivalTimeUTC() : to.getDepartureTimeUTC()));
        to.setArrivalTime((!TextUtils.isEmpty(from.getArrivalTime()) ? from.getArrivalTime() : to.getArrivalTime()));
        to.setDepartureTimeUTC((from.getDepartureTimeUTC() != null ? from.getDepartureTimeUTC() : to.getDepartureTimeUTC()));
        to.setDepartureTime((!TextUtils.isEmpty(from.getDepartureTime()) ? from.getDepartureTime() : to.getDepartureTime()));
        to.setArrivalTerminal((!TextUtils.isEmpty(from.getArrivalTerminal()) ? from.getArrivalTerminal() : to.getArrivalTerminal()));
        to.setDepartureTerminal((!TextUtils.isEmpty(from.getDepartureTerminal()) ? from.getDepartureTerminal() : to.getDepartureTerminal()));
        to.setArrivalAirportIATA((!TextUtils.isEmpty(from.getArrivalAirportIATA()) ? from.getArrivalAirportIATA() : to.getArrivalAirportIATA()));
        to.setDepartureAirportIATA((!TextUtils.isEmpty(from.getDepartureAirportIATA()) ? from.getDepartureAirportIATA() : to.getDepartureAirportIATA()));
        to.setArrivalCity((!TextUtils.isEmpty(from.getArrivalCity()) ? from.getArrivalCity() : to.getArrivalCity()));
        to.setDepartureCity((!TextUtils.isEmpty(from.getDepartureCity()) ? from.getDepartureCity() : to.getDepartureCity()));
        to.setDepartureGate((!TextUtils.isEmpty(from.getDepartureGate()) ? from.getDepartureGate() : to.getDepartureGate()));
        to.setFlightCarrier((!TextUtils.isEmpty(from.getFlightCarrier()) ? from.getFlightCarrier() : to.getFlightCarrier()));
        to.setFlightId((from.getFlightId() != null ? from.getFlightId() : to.getFlightId()));
        to.setSeatNumber((!TextUtils.isEmpty(from.getSeatNumber()) ? from.getSeatNumber() : to.getSeatNumber()));
    }
}
