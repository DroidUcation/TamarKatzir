package com.example.testapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.testapp.MainActivity;
import com.example.testapp.R;

/**
 * Created by user on 15/05/2016.
 */

public class DailyAlarmService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 1000,
                PendingIntent.getBroadcast(this, 0, alarmIntent, 0));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}