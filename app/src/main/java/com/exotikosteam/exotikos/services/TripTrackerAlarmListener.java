package com.exotikosteam.exotikos.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.exotikosteam.exotikos.utils.Constants;

public class TripTrackerAlarmListener implements WakefulIntentService.AlarmListener {

    private long alarmInterval = Constants.SERVICE_INTERVAL;

    @Override
    public void scheduleAlarms(AlarmManager alarmManager, PendingIntent pendingIntent, Context context) {
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime()+60000,
                alarmInterval,
                pendingIntent);
    }

    @Override
    public void sendWakefulWork(Context context) {
        WakefulIntentService.sendWakefulWork(context, TripTrackerService.class);
    }

    @Override
    public long getMaxAge() {
        return(alarmInterval * 2);
    }

}
