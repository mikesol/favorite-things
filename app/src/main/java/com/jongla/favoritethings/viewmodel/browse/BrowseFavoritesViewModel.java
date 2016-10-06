package com.jongla.favoritethings.viewmodel.browse;

import android.content.Context;
import android.database.Cursor;
import android.databinding.ObservableField;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.jongla.favoritethings.R;
import com.jongla.favoritethings.Utils;
import com.jongla.favoritethings.api.braqued.BrowseGithubsShowGithubThing;
import com.jongla.favoritethings.api.braqued.BrowseOMDBsShowOMDBThing;
import com.jongla.favoritethings.api.braqued.BrowseTVMazesShowTVMazeThing;
import com.jongla.favoritethings.database.FavoriteThingContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import braque.RESTEndpoint;
import braque.braqued.Deserializer;
import braque.braqued.StringProvisioner;
import rx.Observable;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseFavoritesViewModel extends BrowseThingViewModel<RESTEndpoint> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.github_info_message));
    }

    public BrowseFavoritesViewModel(Context context, LoaderManager loaderManager,
                                    DataListener<RESTEndpoint> dataListener) {
        super(context, loaderManager, dataListener);
    }

    @Override
    protected Observable<List<RESTEndpoint>> makeObservable(Context context, String input) {
        List<RESTEndpoint> justThis = new ArrayList<>();
        return Observable.just(justThis);
    }

    @Override
    protected String pathHead() {
        return null;
    }

    @Override
    protected Class<RESTEndpoint> klass() {
        return null;
    }

    @Override
    protected int getLoaderCallbackIndex() {
        return 0;
    }

    @Override
    protected int getEmptyResource() {
        return R.string.text_fill_up;
    }


    @Override
    protected List<RESTEndpoint> mergeData(List<RESTEndpoint> originalData, Cursor cursor) {
        Map<String, Object> githubs = new HashMap<>();
        Map<String, Object> movies = new HashMap<>();
        Map<String, Object> tvShows = new HashMap<>();
        int indexPath = cursor.getColumnIndex(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_PATH);
        int indexValue = cursor.getColumnIndex(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_VALUE);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String path = cursor.getString(indexPath);
            String base = path.split("/")[0];
            Map<String, Object> toUse = base.equals(StringProvisioner.pathBrowseGithubs())
                    ? githubs
                    : base.equals(StringProvisioner.pathBrowseTVMazes())
                    ? tvShows
                    : base.equals(StringProvisioner.pathBrowseOMDBs())
                    ? movies
                    : null;
            toUse.put(path, Utils.stringToObjectFromDb(path, cursor.getString(indexValue)));
        }
        List<RESTEndpoint> newData = new ArrayList<>();
        newData.addAll(Deserializer._deserialize(githubs, BrowseGithubsShowGithubThing.class));
        newData.addAll(Deserializer._deserialize(tvShows, BrowseTVMazesShowTVMazeThing.class));
        newData.addAll(Deserializer._deserialize(movies, BrowseOMDBsShowOMDBThing.class));
        return newData;
    }
}
