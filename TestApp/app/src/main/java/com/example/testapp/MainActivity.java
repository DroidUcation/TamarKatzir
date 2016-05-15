package com.example.testapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    boolean dataInserted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initData();
        //SetAlarm();
        //showNotificationData();
        getApplicationContext().startService(new Intent(getApplicationContext(), DailyAlarmService.class));

//        Bundle extras = getIntent().getExtras();
//        //Toast.makeText(MainActivity.this, "a", Toast.LENGTH_LONG).show();
//        if(extras != null){
//            //Log.i( "dd","Extra:" + extras.getString("item_id") );
//            Toast.makeText(MainActivity.this, "flagNewData: " + extras.getString("flagNewData"), Toast.LENGTH_LONG).show();
//            //String strFlagNewData = extras.getString("flagNewData");
//            //Toast.makeText(MainActivity.this, "flagNewData: " + strFlagNewData, Toast.LENGTH_LONG).show();
////            if (strFlagNewData == "1")
////                sTypeData = 5;
//        }
        //boolean dataInserted = false;
         //initialize facts table
        if(!dataInserted){
            initData();
            dataInserted = true; // insert data only once.TODO: need to save in Bundle savedInstanceState
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        ViewGroup group = (ViewGroup)findViewById(R.id.gridLayout);
        View v;
        for(int i = 0; i < group.getChildCount(); i++) {
            v = group.getChildAt(i);
            if(v instanceof Button) v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    short sTypeData = 0;
                    Bundle extras = getIntent().getExtras();
//                    //Toast.makeText(MainActivity.this, "a", Toast.LENGTH_LONG).show();
                    if(extras != null){
//                        //Log.i( "dd","Extra:" + extras.getString("item_id") );
//                        Toast.makeText(MainActivity.this, "flagNewData: " + extras.getString("flagNewData"), Toast.LENGTH_LONG).show();
                        String strFlagNewData = extras.getString("flagNewData");
                        //Toast.makeText(MainActivity.this, "flagNewData: " + strFlagNewData, Toast.LENGTH_LONG).show();
                        if (strFlagNewData.equals("1"))
                            sTypeData = 5;
}
                    String strData= "";
                    String strId;
                    switch (v.getId()) {
                        case R.id.button1:
                            //strId = (String) (1 + sTypeData);
                            //Toast.makeText(MainActivity.this, "strid: " + strId, Toast.LENGTH_LONG).show();
                                strData = getData(1 + sTypeData);
                                break;

                        case R.id.button2:
                                strData = getData(2 + sTypeData);
                                break;

                        case R.id.button3:
                            strData = getData(3 + sTypeData);
                            break;

                        case R.id.button4:
                            strData = getData(4 + sTypeData);
                            break;

                        case R.id.button5:
                            strData = getData(5 + sTypeData);
                            break;
                        }
                    Toast.makeText(MainActivity.this, strData, Toast.LENGTH_LONG).show();
                }});
        }
    }

    private String getData(int intId)
    {
        String strId = String.valueOf(intId);
        //Toast.makeText(MainActivity.this, "strid: " + strId, Toast.LENGTH_LONG).show();
        String[] col = {AppConst.COL_DATA_TEXT};
        String strData= "";
        Cursor cursor = getContentResolver()
                .query(AppProvider.CONTENT_URI,
                        col,
                        AppConst.COL_DATA_ID + "=?",
                        new String[]{strId},
                        null);

        if (cursor.moveToFirst()) {
            strData = cursor.getString(cursor.getColumnIndexOrThrow(AppConst.COL_DATA_TEXT));
        }
        return strData;
    }
//    private void startService()
//    {
//        Intent appIntent = new Intent(this, AppIntentService.class);
//        //appIntent.putExtra(AppIntentService.PARAM_IN_MSG, strInputMsg);
//        startService(appIntent);
//    }

    public void SetAlarm()//Context context)
    {
        Intent intent = new Intent(this, AppIntentService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pintent);
        Toast.makeText(MainActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();

        //girsa 2
//        Intent myIntent = new Intent(this, AppIntentService.class);
//        PendingIntent pi = PendingIntent.getService(this, 0, myIntent, 0);
//        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 1);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
//        Toast.makeText(MainActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();

        //girsa 1
//        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent(context, Alarm.class);
//        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
//        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 10, pi); // Millisec * Second * Minute
//
//        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), result , pendingIntent);
    }

//    private void showNotificationData() {
//        Notification n = new NotificationCompat.Builder(this)
//                .setContentTitle("עובדות חדשות על רקפות")
//                .setContentText("לחץ על הכפתורים להצגתם")
//                .setSmallIcon(R.mipmap.ic_rakefet)
//                .build();
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(1, n);
//    }

    private void initData()
    {
        ContentValues values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 1);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data1));
        Uri uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
        //getContentResolver().update(AppProvider.CONTENT_URI, values, "DataID=1", null);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 2);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data2));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 3);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data3));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 4);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data4));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);

        values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 5);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data5));
        uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    AppIntentService mService;
//    boolean mBound;
//    private ServiceConnection mConnection = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName className,
//                                       IBinder service) {
//            AppIntentService binder = (AppIntentService) service;
//            mService = binder.getSystemService();
//            mBound = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName arg0) {
//            mBound = false;
//        }
//    };
}




