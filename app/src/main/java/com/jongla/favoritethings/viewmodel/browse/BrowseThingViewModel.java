package com.jongla.favoritethings.viewmodel.browse;

import android.content.Context;
import android.database.Cursor;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

import com.jongla.favoritethings.ArchiApplication;
import com.jongla.favoritethings.R;
import com.jongla.favoritethings.database.FavoriteThingContract;
import com.jongla.favoritethings.model.favoritething.braqued.IdGet;
import com.jongla.favoritethings.provider.FavoriteThingProvider;
import com.jongla.favoritethings.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import braque.RESTEndpoint;
import braque.RESTShow;
import braque.braqued.Deserializer;
import braque.braqued.Serializer;
import braque.braqued.StringProvisioner;
import retrofit2.adapter.rxjava.HttpException;
import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * View model for the MainActivity
 */
public abstract class BrowseThingViewModel<T extends RESTEndpoint & RESTShow> implements ViewModel {

    private static final String TAG = "TopLevelFragmentVM";

    public ObservableInt infoMessageVisibility;
    public ObservableInt progressVisibility;
    public ObservableInt recyclerViewVisibility;
    public ObservableInt searchButtonVisibility;
    public ObservableField<String> infoMessage;

    private Context context;
    private LoaderManager loaderManager;
    private Subscription subscription;
    private List<T> favoriteThings;
    private DataListener dataListener;

    protected abstract ObservableField<String> getInfoObservableField(Context context);

    public BrowseThingViewModel(Context context, LoaderManager loaderManager, DataListener<T> dataListener) {
        this.context = context;
        this.loaderManager = loaderManager;
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

    /**
     * Braque serializer and deserializer in action to easily convert
     * between object implementations, integratable with RxJava and Retrofit.
     * @param originalData
     * @param cursor
     * @return
     */
    private List<T> mergeData(List<T> originalData, Cursor cursor) {
        if (originalData.size() == 0) {
            return originalData;
        }
        Set<String> likes = new HashSet<>();
        int index = cursor.getColumnIndex(FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_UID);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            likes.add(cursor.getString(index));
        }
        List<T> newData = new ArrayList<>();
        for (RESTEndpoint element : originalData) {
            Map<String, Object> serialized = Serializer._serialize(element);
            if (likes.contains(((IdGet)element).getId())) {
                serialized.put(pathHead()
                        +"/"+((IdGet)element).getId()
                        +"/"
                        + StringProvisioner.propLike().toLowerCase(), true);
            }
            newData.addAll(Deserializer._deserialize(serialized, klass()));
        }
        return newData;
    }
    
    abstract protected Observable<List<T>> makeObservable(final Context context, String input);
    abstract protected String pathHead();
    abstract protected Class<T> klass();
    abstract protected int getLoaderCallbackIndex();

    public void loadFavoriteThings(String input) {
        progressVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.INVISIBLE);
        infoMessageVisibility.set(View.INVISIBLE);
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        ArchiApplication application = ArchiApplication.get(context);
        subscription = makeObservable(context, input)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .concatMap(new Func1<List<T>, Observable<List<T>>>() {
                    @Override
                    public Observable<List<T>> call(final List<T> ts) {
                        return Observable.fromEmitter(new Action1<Emitter<List<T>>>() {
                            @Override
                            public void call(final Emitter<List<T>> listEmitter) {
                                LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks =
                                        new LoaderManager.LoaderCallbacks<Cursor>() {
                                            @Override
                                            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                                                CursorLoader loader = new CursorLoader(context,
                                                        FavoriteThingProvider.CONTENT_URI,
                                                        new String[]{
                                                                FavoriteThingContract.FavoriteThingEntry._ID,
                                                                FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_UID
                                                        }, null, null, null);
                                                return loader;
                                            }

                                            @Override
                                            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                                                listEmitter.onNext(mergeData(ts, data));
                                            }

                                            @Override
                                            public void onLoaderReset(Loader<Cursor> loader) {

                                            }
                                        };
                                loaderManager.initLoader(getLoaderCallbackIndex(), null, loaderCallbacks);
                            }
                        }, Emitter.BackpressureMode.BUFFER);
                    }
                }).subscribe(new Subscriber<Collection<T>>() {
                    @Override
                    public void onCompleted() {
                        throw new IllegalStateException("should never complete");
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
                    public void onNext(Collection<T> items) {
                        BrowseThingViewModel.this.favoriteThings = new ArrayList<>(items);
                        if (dataListener != null) dataListener.onFavoriteThingsChanged(favoriteThings);
                        progressVisibility.set(View.INVISIBLE);
                        if (!favoriteThings.isEmpty()) {
                            recyclerViewVisibility.set(View.VISIBLE);
                        } else {
                            infoMessage.set(context.getString(R.string.text_empty_repos));
                            infoMessageVisibility.set(View.VISIBLE);
                        }
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
