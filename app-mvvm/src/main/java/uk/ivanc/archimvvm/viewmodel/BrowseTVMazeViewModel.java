package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import uk.ivanc.archimvvm.ArchiApplication;
import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.api.braqued.BrowseTVMazesShowTVMazeThing;
import uk.ivanc.archimvvm.model.TVMazeService;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class BrowseTVMazeViewModel extends BrowseThingViewModel<BrowseTVMazesShowTVMazeThing> {
    @Override
    protected ObservableField<String> getInfoObservableField(Context context) {
        return new ObservableField<>(context.getString(R.string.tvmaze_info_message));
    }

    public BrowseTVMazeViewModel(Context context, DataListener<BrowseTVMazesShowTVMazeThing> dataListener) {
        super(context, dataListener);
    }

    @Override
    protected Observable<List<BrowseTVMazesShowTVMazeThing>> makeObservable(Context context, String input) {
        ArchiApplication application = ArchiApplication.get(context);
        TVMazeService tvMazeService = application.getTVMazeService();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", input);
        return tvMazeService.browseTVMazes(queryMap);
    }
}
