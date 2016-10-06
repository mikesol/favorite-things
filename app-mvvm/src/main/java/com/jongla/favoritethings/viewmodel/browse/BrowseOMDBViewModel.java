package com.jongla.favoritethings.viewmodel.browse;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.LoaderManager;

import com.jongla.favoritethings.ArchiApplication;
import com.jongla.favoritethings.R;
import com.jongla.favoritethings.api.braqued.BrowseOMDBsShowOMDBThing;
import com.jongla.favoritethings.model.OMDBService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import braque.braqued.StringProvisioner;
import rx.Observable;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseOMDBViewModel extends BrowseThingViewModel<BrowseOMDBsShowOMDBThing> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.omdb_info_message));
    }

    public BrowseOMDBViewModel(Context context, LoaderManager loaderManager,
                               DataListener<BrowseOMDBsShowOMDBThing> dataListener) {
        super(context, loaderManager, dataListener);
    }

    @Override
    protected Observable<List<BrowseOMDBsShowOMDBThing>> makeObservable(Context context, String input) {
        ArchiApplication application = ArchiApplication.get(context);
        OMDBService omdbService = application.getOMDBService();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("s", input);
        return omdbService.browseOMDBShows(queryMap);
    }

    @Override
    protected String pathHead() {
        return StringProvisioner.pathBrowseOMDBs();
    }

    @Override
    protected Class<BrowseOMDBsShowOMDBThing> klass() {
        return BrowseOMDBsShowOMDBThing.class;
    }

    @Override
    protected int getLoaderCallbackIndex() {
        return 2;
    }
}
