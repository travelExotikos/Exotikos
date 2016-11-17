package com.exotikosteam.exotikos.models.trip;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.exotikosteam.exotikos.models.airline.Airline;
import com.exotikosteam.exotikos.models.airport.Airport;
import com.exotikosteam.exotikos.models.flightstatus.FlightScheduleResponse;
import com.exotikosteam.exotikos.models.flightstatus.FlightStatus;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lramaswamy on 11/9/16.
 */

@Parcel(analyze = {TripStatus.class})
@ModelContainer
@Table(database = ExotikosDatabase.class)
public class TripStatus extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    Integer id;

    @Column(name = "passenger_name")
    String passengerName;

    @Column
    Integer flightStepOrdinal;

    FlightStep flightStep;

    @Column
    Integer currentFlight;

    List<Flight> flights = new ArrayList<>();

    //Empty constructor for Parceler
    public TripStatus() {
        super();
    }

    public TripStatus(List<Flight> flights) {
        this.flights = flights;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setFlightStep(FlightStep flightStep) {
        this.flightStepOrdinal = flightStep.ordinal();
        this.flightStep = flightStep;
    }

    @OneToMany(methods = OneToMany.Method.ALL, variableName = "flights")
    public List<Flight> getFlights() {
        if (flights == null || flights.size() < 1) {
            flights = SQLite.select()
                    .from(Flight.class)
                    .where(Flight_Table.trip_id.eq(id))
                    .orderBy(Flight_Table.order, true)
                    .queryList();
        }
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public FlightStep getFlightStep() {
        return flightStep;
    }

    public Integer getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(Integer currentFlight) {
        this.currentFlight = currentFlight;
    }

    public static TripStatus get(Integer id) {
        return SQLite.select().from(TripStatus.class).where(TripStatus_Table.id.eq(id)).querySingle();
    }

    public static List<TripStatus> getAll() {
        return SQLite.select().from(TripStatus.class).queryList();
    }

    public static void persist(TripStatus tripStatus) {
        for (Flight f: tripStatus.getFlights()) {
            f.save();
        }
        tripStatus.save();
    }

    public static TripStatus saveOrUpdateTrip(TripStatus trip, ScheduledFlight flight) {
        if (trip == null || trip.getId() == null) {
            return createTrip(Arrays.asList(flight));
        }
        return updateTrip(trip, new Flight(flight));
    }


    public static TripStatus saveOrUpdateTrip(TripStatus trip, FlightStatus flight, String seatNo) {
        if (trip == null || trip.getId() == null) {
            return createNewTrip(Arrays.asList(new Flight(flight, seatNo)));
        }
        return updateTrip(trip, new Flight(flight, seatNo));
    }

    public static TripStatus createTrip(List<ScheduledFlight> scheduleFlights) {
        //only for API 24 List<Flight> flights = scheduleFlights.stream().map(f -> new Flight(f)).collect(Collectors.toList());
        List<Flight> flights = new ArrayList<>();
        for (ScheduledFlight s: scheduleFlights) {
            flights.add(new Flight(s));
        }
        return createNewTrip(flights);
    }

    public static TripStatus createTrip(List<ScheduledFlight> scheduleFlights, FlightScheduleResponse response) {
        //only for API 24 List<Flight> flights = scheduleFlights.stream().map(f -> new Flight(f)).collect(Collectors.toList());
        List<Airport> airports = response.getAppendix().getAirports();
        List<Airline> airlines = response.getAppendix().getAirlines();

        List<Flight> flights = new ArrayList<>();
        for (ScheduledFlight s: scheduleFlights) {
            Flight f = new Flight(s);
            // Find arrival airport payload
            for (Airport a: airports) {
                if (a.getFs().equals(s.getArrivalAirportFsCode())) {
                    f.setArrivalCity(a.getCity());
                }
            }
            // Find departure airport payload
            for (Airport a: airports) {
                if (a.getFs().equals(s.getDepartureAirportFsCode())) {
                    f.setDepartureCity(a.getCity());
                }
            }

            // Find carrier name
            for (Airline al: airlines) {
                if (al.getFs().equals(s.getCarrierFsCode())) {
                    f.setFlightCarrierName(al.getName());
                }
            }

            flights.add(f);
        }
        return createNewTrip(flights);
    }


    private static TripStatus updateTrip(TripStatus trip, Flight flight) {
        boolean isNewFlight = true;
        for (int i = 0; i < trip.getFlights().size(); i++) {
            Flight f = trip.getFlights().get(i);
            if (f.getFlightNumber() == flight.getFlightNumber() && f.getDepartureAirportIATA() == flight.getDepartureAirportIATA()) {
                Flight.mergeFlights(f, flight);
                f.save();
                isNewFlight = false;
                break;
            }
        }
        if (isNewFlight) {
            flight.setOrder(trip.getFlights().size());
            flight.setTripId(trip.getId());
            flight.save();
            trip.getFlights().add(flight);
        }
        trip.save();
        return trip;
    }

    private static TripStatus createNewTrip(List<Flight> flights) {
        TripStatus trip = new TripStatus();
        trip.setFlightStep(FlightStep.PREPARATION);
        trip.setCurrentFlight(0);
        trip.save();
        int i = 0;
        for (Flight f: flights) {
            f.setTripId(trip.getId());
            f.setOrder(++i);
            f.save();
        }
        return TripStatus.get(trip.getId());
    }
}
