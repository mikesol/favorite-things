package com.jongla.favoritethings.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mikesolomon on 06/10/16.
 */

public class FavoriteThingDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "favoritethings.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String NOT_NULL = " NOT NULL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavoriteThingContract.FavoriteThingEntry.TABLE_NAME + " (" +
                    FavoriteThingContract.FavoriteThingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_PATH + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_ORIGIN + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_UID + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE + TEXT_TYPE + NOT_NULL + " )";

    public FavoriteThingDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nothing for now
    }
}
