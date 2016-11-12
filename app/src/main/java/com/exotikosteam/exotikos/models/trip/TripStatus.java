package com.exotikosteam.exotikos.models.trip;

import com.exotikosteam.exotikos.models.ExotikosDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lramaswamy on 11/9/16.
 */

@Parcel(analyze = {TripStatus.class})
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

    @ForeignKey(saveForeignKeyModel = false)
    Flight currentFlight;

    List<Flight> flights = new ArrayList<>();

    //Empty constructor for Parceler
    public TripStatus() {}

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
        if (flights == null) {
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

    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(Flight currentFlight) {
        this.currentFlight = currentFlight;
    }

    public static TripStatus get(Integer id) {
        return SQLite.select().from(TripStatus.class).where(TripStatus_Table.id.eq(id)).querySingle();
    }

    public static List<TripStatus> getAll() {
        return SQLite.select().from(TripStatus.class).queryList();
    }

    public static void save(TripStatus tripStatus) {
        tripStatus.save();
    }
}
