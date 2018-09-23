package com.myservices.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class AlarmService extends Service {
    public static boolean isRunning = false;
    public static AlarmService instance = null;
    AlarmManager alarm = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        instance = this;
        alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pintent = PendingIntent
                .getActivity(this, 0 ,new Intent(getApplicationContext(),MainActivity.class), 0);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),30*1000,pintent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
