package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import uk.ivanc.archimvvm.ArchiApplication;
import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;
import uk.ivanc.archimvvm.model.GithubService;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseGithubViewModel extends BrowseThingViewModel<BrowseGithubsShowGithubThing> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.github_info_message));
    }

    public BrowseGithubViewModel(Context context, DataListener<BrowseGithubsShowGithubThing> dataListener) {
        super(context, dataListener);
    }

    @Override
    protected Observable<List<BrowseGithubsShowGithubThing>> makeObservable(Context context, String input) {
        ArchiApplication application = ArchiApplication.get(context);
        GithubService githubService = application.getGithubService();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", input);
        return githubService.browsePublicRepositories(queryMap);
    }
}
