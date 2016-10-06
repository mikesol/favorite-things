package com.jongla.favoritethings.viewmodel.browse;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.LoaderManager;

import com.jongla.favoritethings.ArchiApplication;
import com.jongla.favoritethings.R;
import com.jongla.favoritethings.api.braqued.BrowseTVMazesShowTVMazeThing;
import com.jongla.favoritethings.model.TVMazeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import braque.braqued.StringProvisioner;
import rx.Observable;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseTVMazeViewModel extends BrowseThingViewModel<BrowseTVMazesShowTVMazeThing> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.tvmaze_info_message));
    }

    public BrowseTVMazeViewModel(Context context, LoaderManager loaderManager, DataListener<BrowseTVMazesShowTVMazeThing> dataListener) {
        super(context, loaderManager, dataListener);
    }

    @Override
    protected Observable<List<BrowseTVMazesShowTVMazeThing>> makeObservable(Context context, String input) {
        ArchiApplication application = ArchiApplication.get(context);
        TVMazeService tvMazeService = application.getTVMazeService();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", input);
        return tvMazeService.browseTVMazes(queryMap);
    }

    @Override
    protected String pathHead() {
        return StringProvisioner.pathBrowseTVMazes();
    }

    @Override
    protected Class<BrowseTVMazesShowTVMazeThing> klass() {
        return BrowseTVMazesShowTVMazeThing.class;
    }

    @Override
    protected int getLoaderCallbackIndex() {
        return 1;
    }
}
