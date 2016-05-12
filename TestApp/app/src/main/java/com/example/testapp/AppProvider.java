package com.example.testapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by kl669 on 05/05/2016.
 */


public class AppProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.testapp.AppProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cte";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cte", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "cte/*", uriCode);
    }


    sqlHelper sql_helper;

    // Defines the database name
    private static final String DBNAME = "DBFlower.DB";

    // Holds the database object
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        sql_helper = new sqlHelper(getContext());
        return true;
    }

//    @Override
//    public boolean onCreate() {
//        Context context = getContext();
//        DatabaseHelper dbHelper = new DatabaseHelper(context);
//        db = dbHelper.getWritableDatabase();
//        if (db != null) {
//            return true;
//        }
//        return false;
//    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.delete(AppConst.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/cte";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
    // Implements the provider's insert method

//    public Cursor insert(Uri uri){//}, ContentValues values) {
//        // Insert code here to determine which table to open, handle error-checking, and so forth
//
//        ...
//
//        /*
//         * Gets a writeable database. This will trigger its creation if it doesn't already exist.
//         *
//         */
//        db = sql_helper.getWritableDatabase();
//    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
         db = sql_helper.getWritableDatabase();
        long rowID = sql_helper.insertFlower(db, values);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor retCursor;

        switch (uriMatcher.match(uri)) {
            case uriCode:
                retCursor = sql_helper.getReadableDatabase().query(
                                    AppConst.TABLE_NAME,
                                    projection,
                                    selection,
                                    selectionArgs,
                                    null,
                                    null,
                                    sortOrder
                                    );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return  retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.update(AppConst.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}
