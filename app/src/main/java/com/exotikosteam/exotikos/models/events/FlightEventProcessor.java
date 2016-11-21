package com.exotikosteam.exotikos.models.events;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.exotikosteam.exotikos.R;

import static com.exotikosteam.exotikos.services.TripTrackerService.notificationNumber;

/**
 * Created by jesusft on 11/20/16.
 */

public abstract class FlightEventProcessor {

    protected Context mContext;

    public FlightEventProcessor(Context mContext) {
        this.mContext = mContext;
    }

    protected void sendPushNotification(Intent intent, String title, String content) {
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(mContext);

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


        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationNumber.getAndIncrement(), b.build());
    }

    public abstract boolean shouldFire(EventInputContext context);

    public abstract void onFire(EventInputContext context);

}
