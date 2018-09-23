package com.myservices.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class NotificationService extends Service {
    private NotificationManager nManager;
    private int NOTIFICATION = 1;
    public static boolean isRunning = false;
    public static NotificationService instance = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;
        isRunning =true;
        nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,
                new Intent(this,MainActivity.class),0);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("Service Running")
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("My App")
                    .setContentText("Service Running fast")
                    .setContentIntent(contentIntent)
                    .setOngoing(true)
                    .build();
            startForeground(NOTIFICATION,notification);
            return START_STICKY;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        instance = null;
        nManager.cancel(NOTIFICATION);
    }
    public void doSomething(){
        Toast.makeText(getApplicationContext(),"DO some stuff",Toast.LENGTH_SHORT).show();
    }
}
