package com.example.testapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by בת שבע on 18/04/2016.
 */
public class sqlHelper extends SQLiteOpenHelper{

    public sqlHelper(Context context) {
        super(context, "DBFlower.DB", null,1);
    }

    public sqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Flowers (DataID int," +
                " DataText varchar(255))";

        db.execSQL(sql);
        //createInitailData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Flowers");
        onCreate(db);
    }

//    public long insertFlower(SQLiteDatabase db, String DataText)
//    {
//        ContentValues values = new ContentValues();
//        values.put("DataText", DataText);
//        long rowID = db.insert("Flowers", null, values);
//        return rowID;
//    }

    public long insertFlower(SQLiteDatabase db, ContentValues values)
    {
        long rowID = db.insert("Flowers", null, values);
        return rowID;
    }
}
