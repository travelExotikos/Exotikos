package com.exotikosteam.exotikos.models.trip;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.exotikosteam.exotikos.models.flightstatus.Appendix;
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
import java.util.Date;
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

    @Column(name = "flight_step")
    FlightStep flightStep;

    @Column
    Integer currentFlight;

    @Column(name= "updated_at")
    Date updatedAt;

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
                    .orderBy(Flight_Table.departure_time_UTC, true)
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

    public Flight getCurrentFlightInstance() {
        return flights.get(currentFlight);
    }

    public void setCurrentFlight(Integer currentFlight) {
        this.currentFlight = currentFlight;
    }

    public Integer getFlightStepOrdinal() {
        return flightStepOrdinal;
    }

    public void setFlightStepOrdinal(Integer flightStepOrdinal) {
        this.flightStepOrdinal = flightStepOrdinal;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        tripStatus.setUpdatedAt(new Date());
        tripStatus.save();
    }

    public static TripStatus saveOrUpdateTrip(TripStatus trip, ScheduledFlight flight, Appendix appendix) {
        if (trip == null || trip.getId() == null) {
            return createTrip(Arrays.asList(flight), appendix);
        }
        return updateTrip(trip, new Flight(flight, appendix));
    }


    public static TripStatus saveOrUpdateTrip(TripStatus trip, FlightStatus flight, String seatNo, Appendix appendix) {
        Flight f = new Flight(flight, seatNo, appendix);
        if (trip == null || trip.getId() == null) {
            return createNewTrip(Arrays.asList(f));
        }
        return updateTrip(trip, f);
    }

    public static TripStatus createTrip(List<ScheduledFlight> scheduleFlights, Appendix appendix) {
        List<Flight> flights = new ArrayList<>();
        for (ScheduledFlight s: scheduleFlights) {
            flights.add(new Flight(s, appendix));
        }
        return createNewTrip(flights);
    }

    public static void deleteTrip(TripStatus trip) {
        trip.delete();
    }

    public static TripStatus deleteFlight(Integer flightId) {
        Flight flight = Flight.get(flightId);
        if (flight == null) {
            return null;
        }
        Integer tripId = flight.getTripId();
        TripStatus trip = TripStatus.get(tripId);
        Integer currentFlight = trip.getCurrentFlight();
        Integer flightsCount = trip.getFlights().size();
        if (flightsCount < 2) {
            trip.delete();
            return null;
        } else if (currentFlight == flightsCount - 1) {
            trip.setCurrentFlight(--currentFlight);
            trip.save();
        }
        flight.delete();
        return TripStatus.get(tripId);
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
            flight.setTripId(trip.getId());
            flight.save();
            trip.getFlights().add(flight);
        }
        trip.setUpdatedAt(new Date());
        trip.save();
        return trip;
    }

    private static TripStatus createNewTrip(List<Flight> flights) {
        TripStatus trip = new TripStatus();
        trip.setFlightStep(FlightStep.PREPARATION);
        trip.setCurrentFlight(0);
        trip.setUpdatedAt(new Date());
        trip.save();
        int i = 0;
        for (Flight f: flights) {
            f.setTripId(trip.getId());
            f.save();
        }
        return TripStatus.get(trip.getId());
    }
}
