package com.jongla.favoritethings.viewmodel.browse;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.LoaderManager;

import com.jongla.favoritethings.ArchiApplication;
import com.jongla.favoritethings.R;
import com.jongla.favoritethings.api.braqued.BrowseGithubsShowGithubThing;
import com.jongla.favoritethings.model.GithubService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import braque.braqued.StringProvisioner;
import rx.Observable;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseGithubViewModel extends BrowseThingViewModel<BrowseGithubsShowGithubThing> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.github_info_message));
    }

    public BrowseGithubViewModel(Context context, LoaderManager loaderManager,
                                 DataListener<BrowseGithubsShowGithubThing> dataListener) {
        super(context, loaderManager, dataListener);
    }

    @Override
    protected Observable<List<BrowseGithubsShowGithubThing>> makeObservable(Context context, String input) {
        ArchiApplication application = ArchiApplication.get(context);
        GithubService githubService = application.getGithubService();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", input);
        return githubService.browsePublicRepositories(queryMap);
    }

    @Override
    protected int getEmptyResource() {
        return R.string.text_empty;
    }

    @Override
    protected String pathHead() {
        return StringProvisioner.pathBrowseGithubs();
    }

    @Override
    protected Class<BrowseGithubsShowGithubThing> klass() {
        return BrowseGithubsShowGithubThing.class;
    }

    @Override
    protected int getLoaderCallbackIndex() {
        return 1;
    }
}
