package com.jongla.favoritethings.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jongla.favoritethings.database.FavoriteThingContract;
import com.jongla.favoritethings.database.FavoriteThingDatabaseHelper;

/**
 * Created by mikesolomon on 06/10/16.
 */

public class FavoriteThingProvider extends ContentProvider {

    private FavoriteThingDatabaseHelper mOpenHelper;

    static final public String AUTHORITY = "com.jongla.favoritethings.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    @Override
    public boolean onCreate() {
        mOpenHelper = new FavoriteThingDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = mOpenHelper.getReadableDatabase()
                .query(FavoriteThingContract.FavoriteThingEntry.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    private long genericInsert(ContentValues values) {
        mOpenHelper.getWritableDatabase().delete(FavoriteThingContract.FavoriteThingEntry.TABLE_NAME,
                FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_PATH+" IS ?",
                new String[]{values.getAsString(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_PATH)});
        return mOpenHelper.getWritableDatabase().insertOrThrow(FavoriteThingContract.FavoriteThingEntry.TABLE_NAME,
                null, values);
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        int res = 0;
        for (ContentValues contentValues : values) {
            long id = genericInsert(contentValues);
            res += (id >= 0) ? 1 : 0;
        }
        if (res > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return res;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = genericInsert(values);
        if (id >= 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int nDeleted = mOpenHelper.getWritableDatabase()
                .delete(FavoriteThingContract.FavoriteThingEntry.TABLE_NAME,
                        selection, selectionArgs);
        if (nDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return nDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int nUpdated = mOpenHelper.getWritableDatabase()
                .update(FavoriteThingContract.FavoriteThingEntry.TABLE_NAME,
                        values, selection, selectionArgs);
        if (nUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return nUpdated;
    }
}
