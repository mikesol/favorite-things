package com.jongla.favoritethings;

import android.content.ContentValues;
import android.os.Bundle;

import com.jongla.favoritethings.database.FavoriteThingContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class Utils {
    public static String toBraquePath(String... strings) {
        StringBuilder builder = new StringBuilder();
        if (strings == null) {
            return null;
        }
        int i = 0;
        for (String string : strings) {
            if (i > 0) {
                builder.append("/");
            }
            builder.append(string);
            i++;
        }
        return builder.toString();
    }

    public static Map<String, Object> bundleToSerialized(Bundle bundle) {
        Map<String, Object> out = new HashMap<>();
        for (String key : bundle.keySet()) {
            out.put(key, bundle.get(key));
        }
        return out;
    }

    public static Bundle serializedToBundle(Map<String, Object> serialized) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : serialized.entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                bundle.putBoolean(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE, (Boolean) entry.getValue());
            } else if (entry.getValue() instanceof Integer) {
                bundle.putInt(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE, (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                bundle.putDouble(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE, (Double) entry.getValue());
            }else if (entry.getValue() instanceof Float) {
                bundle.putFloat(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE, (Float) entry.getValue());
            }else if (entry.getValue() instanceof Long) {
                bundle.putLong(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE, (Long) entry.getValue());
            }else if (entry.getValue() instanceof String) {
                bundle.putString(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE, (String) entry.getValue());
            } else {
                throw new IllegalArgumentException("unserializiable value");
            }
        }
        return bundle;
    }
    
    public static ContentValues[] serializedToManyContentValues(Map<String, Object> serialized) {
        List<ContentValues> contentValuesList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : serialized.entrySet()) {
            ContentValues contentValues = new ContentValues();
            final String valueColumn = FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE;
            if (entry.getValue() instanceof Boolean) {
                contentValues.put(valueColumn, (Boolean) entry.getValue());
            } else if (entry.getValue() instanceof Integer) {
                contentValues.put(valueColumn, (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                contentValues.put(valueColumn, (Double) entry.getValue());
            }else if (entry.getValue() instanceof Float) {
                contentValues.put(valueColumn, (Float) entry.getValue());
            }else if (entry.getValue() instanceof Long) {
                contentValues.put(valueColumn, (Long) entry.getValue());
            }else if (entry.getValue() instanceof String) {
                contentValues.put(valueColumn, (String) entry.getValue());
            } else {
                throw new IllegalArgumentException("unserializiable value");
            }
            contentValues.put(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_PATH, entry.getKey());
            contentValues.put(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_UID, entry.getKey().split("/")[1]);
            contentValues.put(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_ORIGIN, entry.getKey().split("/")[0]);
            contentValuesList.add(contentValues);
        }
        ContentValues[] contentValuesArray = new ContentValues[contentValuesList.size()];
        contentValuesList.toArray(contentValuesArray);
        return contentValuesArray;
    }
}
