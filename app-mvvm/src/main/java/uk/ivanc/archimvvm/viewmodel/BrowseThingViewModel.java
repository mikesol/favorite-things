package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import braque.RESTShow;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import uk.ivanc.archimvvm.ArchiApplication;
import uk.ivanc.archimvvm.R;

/**
 * View model for the MainActivity
 */
public abstract class BrowseThingViewModel<T extends RESTShow> implements ViewModel {

    private static final String TAG = "TopLevelFragmentVM";

    public ObservableInt infoMessageVisibility;
    public ObservableInt progressVisibility;
    public ObservableInt recyclerViewVisibility;
    public ObservableInt searchButtonVisibility;
    public ObservableField<String> infoMessage;

    private Context context;
    private Subscription subscription;
    private List<T> favoriteThings;
    private DataListener dataListener;

    protected abstract ObservableField<String> getInfoObservableField(Context context);

    public BrowseThingViewModel(Context context, DataListener<T> dataListener) {
        this.context = context;
        this.dataListener = dataListener;
        infoMessageVisibility = new ObservableInt(View.VISIBLE);
        progressVisibility = new ObservableInt(View.INVISIBLE);
        recyclerViewVisibility = new ObservableInt(View.INVISIBLE);
        searchButtonVisibility = new ObservableInt(View.GONE);
        infoMessage = getInfoObservableField(context);
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        subscription = null;
        context = null;
        dataListener = null;
    }

    abstract protected Observable<List<T>> makeObservable(Context context, String input);

    public void loadFavoriteThings(String input) {Log.d("Braque","loading favorite things");
        progressVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.INVISIBLE);
        infoMessageVisibility.set(View.INVISIBLE);
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        ArchiApplication application = ArchiApplication.get(context);
        subscription = makeObservable(context, input)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<Collection<T>>() {
                    @Override
                    public void onCompleted() {
                        if (dataListener != null) dataListener.onFavoriteThingsChanged(favoriteThings);
                        progressVisibility.set(View.INVISIBLE);
                        if (!favoriteThings.isEmpty()) {
                            recyclerViewVisibility.set(View.VISIBLE);
                        } else {
                            infoMessage.set(context.getString(R.string.text_empty_repos));
                            infoMessageVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "Error loading GitHub repos ", error);
                        progressVisibility.set(View.INVISIBLE);
                        if (isHttp404(error)) {
                            infoMessage.set(context.getString(R.string.error_username_not_found));
                        } else {
                            infoMessage.set(context.getString(R.string.error_loading_repos));
                        }
                        infoMessageVisibility.set(View.VISIBLE);
                    }

                    @Override
                    public void onNext(Collection<T> repositories) {
                        Log.i(TAG, "Repos loaded " + repositories);
                        BrowseThingViewModel.this.favoriteThings = new ArrayList<>(repositories);
                    }
                });
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }

    public interface DataListener<T> {
        void onFavoriteThingsChanged(List<T> repositories);
    }
}
