package com.exotikosteam.exotikos;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.crashlytics.android.Crashlytics;
import com.exotikosteam.exotikos.services.TripTrackerAlarmListener;
import com.exotikosteam.exotikos.thirdparty.LenientGsonConverterFactory;
import com.exotikosteam.exotikos.webservices.flightstats.AirlinesApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.AirportsApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.FlightStatusApiEndpoint;
import com.exotikosteam.exotikos.webservices.flightstats.SchedulesApiEndpoint;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import net.danlew.android.joda.JodaTimeAndroid;

import io.fabric.sdk.android.Fabric;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;

public class ExotikosApplication extends Application {

    public static final String TAG = ExotikosApplication.class.getSimpleName();

    public static final String FLIGHTSTATS_API_BASE = "https://api.flightstats.com/";

    private String fligthStatsAppID;
    private String fligthStatsAppKey;
    private Retrofit flightstatsRetrofit;
    private AirlinesApiEndpoint airlinesService;
    private AirportsApiEndpoint airportsService;
    private FlightStatusApiEndpoint flightStatusService;
    private SchedulesApiEndpoint flightScheduleService;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        Fabric.with(this, new Crashlytics());

        FlowManager.init(new FlowConfig.Builder(this).build());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            fligthStatsAppID = bundle.getString("FLIGHTSTATS_APP_ID");
            fligthStatsAppKey = bundle.getString("FLIGHTSTATS_APP_KEY");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        flightstatsRetrofit = new Retrofit.Builder()
                .baseUrl(FLIGHTSTATS_API_BASE)
                .client(client)
                .addConverterFactory(LenientGsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        airlinesService = getFlightstatsRetrofit()
                .create(AirlinesApiEndpoint.class);

        airportsService = getFlightstatsRetrofit()
                .create(AirportsApiEndpoint.class);

        flightStatusService = getFlightstatsRetrofit()
                .create(FlightStatusApiEndpoint.class);

        flightScheduleService = getFlightstatsRetrofit()
                .create(SchedulesApiEndpoint.class);

        JodaTimeAndroid.init(this);


        WakefulIntentService.scheduleAlarms(new TripTrackerAlarmListener(), this, false);
    }

    public String getFligthStatsAppID() {
        return fligthStatsAppID;
    }

    public String getFligthStatsAppKey() {
        return fligthStatsAppKey;
    }

    public Retrofit getFlightstatsRetrofit() {
        return flightstatsRetrofit;
    }

    public AirlinesApiEndpoint getAirlinesService() {
        return airlinesService;
    }

    public AirportsApiEndpoint getAirportsService() {
        return airportsService;
    }

    public FlightStatusApiEndpoint getFlightStatusService() {
        return flightStatusService;
    }

    public SchedulesApiEndpoint getFlightScheduleService() {
        return flightScheduleService;
    }
}
