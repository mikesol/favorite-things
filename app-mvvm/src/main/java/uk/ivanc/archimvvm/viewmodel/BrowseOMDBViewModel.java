package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import uk.ivanc.archimvvm.ArchiApplication;
import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.api.braqued.BrowseOMDBsShowOMDBThing;
import uk.ivanc.archimvvm.model.OMDBService;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseOMDBViewModel extends BrowseThingViewModel<BrowseOMDBsShowOMDBThing> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.omdb_info_message));
    }

    public BrowseOMDBViewModel(Context context, DataListener<BrowseOMDBsShowOMDBThing> dataListener) {
        super(context, dataListener);
    }

    @Override
    protected Observable<List<BrowseOMDBsShowOMDBThing>> makeObservable(Context context, String input) {
        ArchiApplication application = ArchiApplication.get(context);
        OMDBService omdbService = application.getOMDBService();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("s", input);
        return omdbService.browseOMDBShows(queryMap);
    }
}
