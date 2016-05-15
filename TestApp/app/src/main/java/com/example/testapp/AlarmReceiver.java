package com.example.testapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.AppIntentService;

/**
 * Created by user on 11/05/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // start intent service
        context.startService(new Intent(context, AppIntentService.class));
        showNotificationData(context);

        //n send notification
    }
//    private void showNotificationData(Context context) {
//        Notification n = new NotificationCompat.Builder(context)
//                .setContentTitle("עובדות חדשות על רקפות")
//                .setContentText("לחץ על הכפתורים להצגתם") //show new data in notification or get fidback to parent page and get new data
//                .setSmallIcon(R.mipmap.ic_rakefet)
//                .build();
//
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(1, n);
//    }

    private void showNotificationData(Context context) {
        Intent iMain = new Intent(context, MainActivity.class);
        //iMain.putExtra("flagNewData", "1");
        iMain.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);// | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                //iMain, PendingIntent.FLAG_UPDATE_CURRENT);
                new Intent(context, MainActivity.class).putExtra("flagNewData", "1") , PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_rakefet)
                        .setContentTitle("עובדות חדשות על רקפות")
                        .setContentText("לחץ על הכפתורים להצגתם");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

   /* public void DisplayNotification(Context context) {
        super.onStart(intent, startId);
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainMenuActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.mipmap.coffee_logo)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.coffe_and_the_environment, "Call", pendingIntent)
                .addAction(R.drawable.coffee_in_the_global_economy, "More", pendingIntent)
                .addAction(R.drawable.coffee_production_and_labor, "And more", pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, noti);*/




}