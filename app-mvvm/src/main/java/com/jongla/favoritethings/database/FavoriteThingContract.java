package com.jongla.favoritethings.database;

import android.provider.BaseColumns;

/**
 * Created by mikesolomon on 06/10/16.
 */

public class FavoriteThingContract {
    private FavoriteThingContract() {}

    public static class FavoriteThingEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_NAME_PATH = "path";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_UID = "uid";
        public static final String COLUMN_NAME_ORIGIN = "origin";
    }
}

