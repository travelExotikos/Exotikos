package com.exotikosteam.exotikos.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.activities.TravelStatusActivity;
import com.exotikosteam.exotikos.models.events.EventInputContext;
import com.exotikosteam.exotikos.models.flightstatus.DateLocalAndUTC;
import com.exotikosteam.exotikos.models.flightstatus.FlightStatus;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.EventProcessor;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.SchedulesApiEndpoint;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.parceler.Parcels;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TripTrackerService extends WakefulIntentService {

    public static final String TAG = TripTrackerService.class.getSimpleName();
    public static final AtomicInteger notificationNumber = new AtomicInteger(0);

    private FlightStatusApiEndpoint flightStatus;
    private SchedulesApiEndpoint flightSchedule;
    private ExotikosApplication app;

    PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
            .appendSeconds().appendSuffix(" seconds")
            .appendMinutes().appendSuffix(" minutes")
            .appendHours().appendSuffix(" hours\n")
            .appendDays().appendSuffix(" days\n")
            .appendWeeks().appendSuffix(" weeks\n")
            .appendMonths().appendSuffix(" months\n")
            .appendYears().appendSuffix(" years\n")
            .printZeroNever()
            .toFormatter();

    public TripTrackerService() {
        super(TAG);
    }

    @Override
    protected void doWakefulWork(Intent intent) {
        app = ((ExotikosApplication) getApplication());
        flightStatus = app.getFlightStatusService();
        flightSchedule = app.getFlightScheduleService();

        Log.i(TAG, "Service is up and running");

        // Do some clever work
        for (TripStatus trip: TripStatus.getAll()) {
            updateTripStatus(trip);
            processFlightEvents(trip);
        }
        Log.i(TAG, "Service is done. Going to sleep");
    }

    private void generatePushNotification(TripStatus trip, String title, String content) {
        Context ctx = getApplicationContext();
        Intent intent = new Intent(ctx, TravelStatusActivity.class);
        intent.putExtra("trip", Parcels.wrap(trip));
        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(ctx);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.checkbox_marked_circle_outline)
                .setTicker("Exotikos")
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationNumber.getAndIncrement(), b.build());
    }

    private void updateTripStatus(TripStatus trip) {
        Context ctx = getApplicationContext();

        for (Flight flight : trip.getFlights()) {
            boolean isClose = flightIsClose(flight);
            int departureYear = new DateTime(flight.getDepartureTimeUTC()).getYear();
            int departureMonth = new DateTime(flight.getDepartureTimeUTC()).getMonthOfYear();
            int departureDay = new DateTime(flight.getDepartureTimeUTC()).getDayOfMonth();
            String flightName = flight.getFlightCarrier() + flight.getFlightNumber();

            if (isClose) {
                flightStatus.getByDepartingDate(
                        flight.getFlightCarrier(),
                        flight.getFlightNumber(),
                        departureYear,
                        departureMonth,
                        departureDay,
                        app.getFligthStatsAppID(),
                        app.getFligthStatsAppKey())
                        .subscribe(flightStatusResponse -> {
                            FlightStatus status = null;
                            // We do not handle multiple results in the same response
                            for (FlightStatus f : flightStatusResponse.getFlightStatuses()) {
                                status = f;
                            }

                            if (status.getAirportResources() != null) {
                                flight.setBaggage(updateStringParam(
                                        trip,
                                        status.getAirportResources().getBaggage(),
                                        flight.getBaggage(),
                                        flightName,
                                        ctx.getString(R.string.baggage_claim_notification)
                                ));

                                flight.setArrivalGate(updateStringParam(
                                        trip,
                                        status.getAirportResources().getArrivalGate(),
                                        flight.getArrivalGate(),
                                        flightName,
                                        ctx.getString(R.string.arrival_gate_notification)
                                ));

                                flight.setDepartureGate(updateStringParam(
                                        trip,
                                        status.getAirportResources().getDepartureGate(),
                                        flight.getDepartureGate(),
                                        flightName,
                                        ctx.getString(R.string.departure_gate_notification)
                                ));

                                flight.setDepartureTerminal(updateStringParam(
                                        trip,
                                        status.getAirportResources().getDepartureTerminal(),
                                        flight.getDepartureTerminal(),
                                        flightName,
                                        ctx.getString(R.string.departure_terminal_notification)
                                ));

                                flight.setArrivalTerminal(updateStringParam(
                                        trip,
                                        status.getAirportResources().getArrivalTerminal(),
                                        flight.getArrivalTerminal(),
                                        flightName,
                                        ctx.getString(R.string.arrival_terminal_notification)
                                ));
                            }

                            flight.setArrivalTimeUTC(updateTimeParam(
                                    trip,
                                    ctx,
                                    status.getArrivalDate(),
                                    flight.getArrivalTimeUTC(),
                                    flightName,
                                    ctx.getString(R.string.arrival_time_notification)
                            ));
                            flight.setArrivalTime(status.getArrivalDate().getDateLocal());

                            flight.setDepartureTimeUTC(updateTimeParam(
                                    trip,
                                    ctx,
                                    status.getArrivalDate(),
                                    flight.getArrivalTimeUTC(),
                                    flightName,
                                    ctx.getString(R.string.departure_time_notification)
                            ));
                            flight.setDepartureTime(status.getDepartureDate().getDateLocal());

                            flight.save();
                        });
            } else {
                flightSchedule.getByDepartingDate(flight.getFlightCarrier(),
                        flight.getFlightNumber(),
                        departureYear,
                        departureMonth,
                        departureDay,
                        app.getFligthStatsAppID(),
                        app.getFligthStatsAppKey())
                        .subscribe(scheduleResponse -> {
                            ScheduledFlight scheduledFlight = null;
                            // We do not handle multiple results in the same response
                            for (ScheduledFlight f : scheduleResponse.getScheduledFlights()) {
                                scheduledFlight = f;
                            }

                            if (scheduledFlight != null) {
                                flight.setDepartureTerminal(scheduledFlight.getDepartureTerminal());
                                flight.setArrivalTerminal(scheduledFlight.getArrivalTerminal());
                                flight.setArrivalTime(scheduledFlight.getArrivalTime());
                                flight.setDepartureTime(scheduledFlight.getDepartureTime());
                                // TODO how to get UTC?
                            }

                            flight.save();
                        });
            }
        }
    }

    private void processFlightEvents(TripStatus trip) {
        DateTime now = new DateTime(DateTimeZone.UTC);
        EventProcessor ep = EventProcessor.newDefaultInstance(this);
        for (Flight f : trip.getFlights()) {
            ep.process(new EventInputContext(trip, f, now, null));
        }
        trip.setUpdatedAt(now.toDate());
        trip.save();
    }

    private boolean flightIsClose(Flight flight) {
        DateTime nowUtc = new DateTime(DateTimeZone.UTC);

        DateTime departureUtc = new DateTime(flight.getDepartureTimeUTC());
        DateTime arrivalUtc = new DateTime(flight.getArrivalTimeUTC());

        Hours betweenDeparture = Hours.hoursBetween(nowUtc, departureUtc);
        Hours betweenArrival = Hours.hoursBetween(nowUtc, arrivalUtc);

        return betweenDeparture.isLessThan(Hours.hours(48)) ||
                betweenArrival.isLessThan(Hours.hours(48));
    }

    private String updateStringParam(TripStatus trip,
                                     String remoteParam,
                                     String localParam,
                                     String notificationTitle,
                                     String notificationText) {
        if (remoteParam != null && !remoteParam.equals(localParam)) {
            generatePushNotification(trip, notificationTitle, String.format(notificationText, remoteParam));
            return remoteParam;
        }

        return localParam;
    }

    private Date updateTimeParam(TripStatus trip,
                                 Context ctx,
                                 DateLocalAndUTC remoteParam,
                                 Date localParam,
                                 String notificationTitle,
                                 String notificationText) {
        DateTime remote = new DateTime(remoteParam.getDateUtc());
        DateTime local = new DateTime(localParam);
        Period difference = new Period(remote, local);

        if (localParam == null || Minutes.minutesBetween(remote, local).isGreaterThan(Minutes.minutes(5))) {
            String differenceTag = local.isBefore(remote) ?
                    ctx.getString(R.string.date_difference_ahead) :
                    ctx.getString(R.string.date_difference_delay);

            generatePushNotification(trip, notificationTitle,
                    String.format(notificationText, differenceTag, periodFormatter.print(difference)));
            return remote.toDate();
        }

        return localParam;
    }

}
