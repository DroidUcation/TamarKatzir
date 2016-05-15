package com.example.testapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by user on 11/05/2016.
 */
public class AppIntentService extends IntentService {
//    public static final String PARAM_IN_MSG = "imsg";
//    public static final String PARAM_OUT_MSG = "omsg";
//public DBService() {
//    super("DBService");
//}
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//        fillTheDB();
//    }
    public AppIntentService() {
        super("AppIntentService");
        //Toast.makeText(this , "intent service constractor", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Toast.makeText(this , "intent service onHandleIntent", Toast.LENGTH_LONG).show();
        initData();
        //       String msg = intent.getStringExtra(PARAM_IN_MSG);
        //       SystemClock.sleep(30000); // 30 seconds
//        String resultTxt = msg + " "
//                + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
    }

//    @Override
//    public void onCreate()
//    {
//        super.onCreate();
//        //Toast.makeText(this , "intent service onCreate", Toast.LENGTH_LONG).show();
//    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        super.onStartCommand(intent, startId, startId);
//        Toast.makeText(this , "intent service onStartCommand", Toast.LENGTH_LONG).show();
//        initData();
//        showNotificationData();
//        return START_STICKY;
//    }



    private void initData()
    {
        ContentValues values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 6);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data6));
        Uri uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
        //getContentResolver().update(AppProvider.CONTENT_URI, values, "DataID=1", null);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 7);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data7));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
        //getContentResolver().update(AppProvider.CONTENT_URI, values, AppConst.COL_DATA_ID + "=2", null);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 8);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data8));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
        //getContentResolver().update(AppProvider.CONTENT_URI, values, AppConst.COL_DATA_ID + "=3", null);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 9);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data9));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
        //getContentResolver().update(AppProvider.CONTENT_URI, values, AppConst.COL_DATA_ID + "=4", null);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 10);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data10));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
        //getContentResolver().update(AppProvider.CONTENT_URI, values, AppConst.COL_DATA_ID + "=5", null);
    }

//    private void showNotificationData() {
//        Notification n = new NotificationCompat.Builder(this)
//                .setContentTitle("עובדות חדשות על רקפות")
//                .setContentText("לחץ על הכפתורים להצגתם") //show new data in notification or get fidback to parent page and get new data
//                .setSmallIcon(R.mipmap.ic_rakefet)
//                .build();
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(1, n);
//    }
}
