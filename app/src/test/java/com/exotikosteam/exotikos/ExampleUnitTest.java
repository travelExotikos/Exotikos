package com.exotikosteam.exotikos;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
/*
    @Test
    public void test_save_read_trip() throws Exception {

        TripStatus ts = new TripStatus();
        ts.setFlightStep(FlightStep.PREPARATION);
        ts.setPassengerName("ada");
        TripStatus.save(ts);

        List<TripStatus> trips = TripStatus.getAll();
        assertEquals(1, trips.size());
        TripStatus trip = trips.get(0);
        assertEquals(0, trip.getFlights().size());

        Flight flight = new Flight();
        flight.setArrivalDate("2016-11-11");
        flight.setOrder(0);
        flight.setTripId(trip.getId());
        flight.save();

        trip = TripStatus.get(trip.getId());
        assertNotNull(trip);
        assertEquals(1, trip.getFlights().size());

    }
    */
}