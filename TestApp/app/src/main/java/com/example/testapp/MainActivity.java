package com.example.testapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();

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
                    String strData= "";
                    switch (v.getId()) {
                            case R.id.button1:
                                strData = getData("1");
                                break;

                            case R.id.button2:
                                strData = getData("2");
                                break;

                        case R.id.button3:
                            strData = getData("3");
                            break;

                        case R.id.button4:
                            strData = getData("4");
                            break;

                        case R.id.button5:
                            strData = getData("5");
                            break;
                        }
                    Toast.makeText(MainActivity.this, strData, Toast.LENGTH_LONG).show();
                }});
        }
    }

    private String getData(String strId)
    {
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
    private void initData()
    {
        ContentValues values = new ContentValues();
        values.put(AppConst.COL_DATA_ID, 1);
        values.put(AppConst.COL_DATA_TEXT, getString(R.string.data1));
        Uri uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);

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

//    public void onClick1(View view) {
//        //save all in onCreate and take in all bunon et hamarim
//        Toast.makeText(getBaseContext(), "שלום", Toast.LENGTH_LONG).show();
//        ContentValues values = new ContentValues();
//        values.put("DataText", "אני פרח מוגן");
//        Uri uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
//        Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG).show();
//    }
//
//    public void onClick2(View view)
//    {
//        Toast.makeText(MainActivity.this, "אני גדל מתחת לסלע", Toast.LENGTH_LONG).show();
//    }

    //    public void onClick3(View view)
//    {
//        Toast.makeText(MainActivity.this, "אני כפוף אך בולט", Toast.LENGTH_LONG).show();
//    }
//
//    public void onClick4(View view)
//    {
//        Toast.makeText(MainActivity.this, "אני גדל רק בקור", Toast.LENGTH_LONG).show();
//    }
//
//    public void onClick5(View view)
//    {
//        Toast.makeText(MainActivity.this, "אני נובל מהר", Toast.LENGTH_LONG).show();
//    }
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
}




//        final Button button1 = (Button) findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
////                int vName = v.getId();
////                //Toast.makeText(MainActivity.this, vName, Toast.LENGTH_LONG).show();
////                Toast.makeText(MainActivity.this, "אני פרח מוגן", Toast.LENGTH_LONG).show();
//
//                ContentValues values = new ContentValues();
//                values.put("DataText", "אני פרח מוגן");
//                Uri uri = getContentResolver().insert(AppProvider.CONTENT_URI, values);
//                Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG).show();
//
//
//            }
//
//        });


//        final Button button2 = (Button) findViewById(R.id.button1);
//        button2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                Toast.makeText(MainActivity.this, "אני פרח מוגן", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        final Button button3 = (Button) findViewById(R.id.button1);
//        button3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                Toast.makeText(MainActivity.this, "אני גדל מתחת לסלע", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        final Button button4 = (Button) findViewById(R.id.button1);
//        button4.setOnClickListener(new View.OnClickListener() {
//            public void onClick4(View v) {
//                // Perform action on click
//                Toast.makeText(MainActivity.this, "אני גדל רק בקור", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        final Button button5 = (Button) findViewById(R.id.button1);
//        button5.setOnClickListener(new View.OnClickListener() {
//            public void onClick5(View v) {
//                // Perform action on click
//                Toast.makeText(MainActivity.this, "אני נובל מהר", Toast.LENGTH_LONG).show();
//            }
//        });