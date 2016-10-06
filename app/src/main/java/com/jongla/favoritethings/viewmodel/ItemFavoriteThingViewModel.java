package com.jongla.favoritethings.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.jongla.favoritethings.R;
import com.jongla.favoritethings.Utils;
import com.jongla.favoritethings.database.FavoriteThingContract;
import com.jongla.favoritethings.model.favoritething.braqued.IdGet;
import com.jongla.favoritethings.model.favoritething.braqued.LikeGet;
import com.jongla.favoritethings.model.favoritething.braqued.NameGet;
import com.jongla.favoritethings.model.favoritething.github.braqued.DescriptionGet;
import com.jongla.favoritethings.model.favoritething.github.braqued.ForksGet;
import com.jongla.favoritethings.model.favoritething.github.braqued.StarsGet;
import com.jongla.favoritethings.model.favoritething.github.braqued.WatchersGet;
import com.jongla.favoritethings.model.favoritething.omdb.braqued.YearGet;
import com.jongla.favoritethings.model.favoritething.tvmaze.braqued.ShowTypeGet;
import com.jongla.favoritethings.provider.FavoriteThingProvider;

import java.util.ArrayList;
import java.util.Map;

import braque.RESTEndpoint;
import braque.braqued.Serializer;
import braque.braqued.StringProvisioner;

/**
 * View model for each item in the repositories RecyclerView
 */
public class ItemFavoriteThingViewModel<T extends RESTEndpoint> extends BaseObservable implements ViewModel {

    private T favoriteThing;
    private Context context;

    public ItemFavoriteThingViewModel(Context context, T favoriteThing) {
        this.favoriteThing = favoriteThing;
        this.context = context;
    }

    public boolean getLike() {
        return favoriteThing instanceof LikeGet && ((LikeGet)favoriteThing).getLike();
    }


    public void onFavoriteClick(View view) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                if (getLike()) {
                    context.getContentResolver()
                            .delete(FavoriteThingProvider.CONTENT_URI,
                                    FavoriteThingContract.FavoriteThingEntry.COLUMN_NAME_UID+" IS ?",
                                    new String[]{((IdGet)favoriteThing).getId()});
                } else {
                    Map<String, Object> serialized = Serializer._serialize(favoriteThing);
                    String likePath = new ArrayList<>(serialized.keySet()).get(0).split("/")[0]
                            + "/" + ((IdGet) favoriteThing).getId()
                            + "/" + StringProvisioner.propLike().toLowerCase();
                    serialized.put(likePath, true);
                    context.getContentResolver()
                            .bulkInsert(FavoriteThingProvider.CONTENT_URI,
                                    Utils.serializedToManyContentValues(serialized));
                }
                return null;
            }
        }.execute();
    }

    public String getName() {
        return favoriteThing instanceof NameGet ? ((NameGet) favoriteThing).getName() : null;
    }

    public String getDescription() {
        return favoriteThing instanceof DescriptionGet ? ((DescriptionGet) favoriteThing).getDescription() : null;
    }

    public boolean getStarsVisible() {
        return favoriteThing instanceof StarsGet;
    }

    public boolean getWatchersVisible() {
        return favoriteThing instanceof WatchersGet;
    }

    public boolean getForksVisible() {
        return favoriteThing instanceof ForksGet;
    }

    public boolean getShowtypeVisible() {
        return favoriteThing instanceof ShowTypeGet;
    }

    public boolean getYearVisible() {
        return favoriteThing instanceof YearGet;
    }

    public String getStars() {
        return context.getString(R.string.text_stars,
                favoriteThing instanceof StarsGet ? ((StarsGet) favoriteThing).getStars() : 0);
    }

    public String getWatchers() {
        return context.getString(R.string.text_watchers,
                favoriteThing instanceof WatchersGet ? ((WatchersGet) favoriteThing).getWatchers() : 0);
    }

    public String getForks() {
        return context.getString(R.string.text_forks,
                favoriteThing instanceof ForksGet ? ((ForksGet) favoriteThing).getForks() : 0);
    }

    public String getShowtype() {
        return context.getString(R.string.text_showtype,
                favoriteThing instanceof ShowTypeGet ? ((ShowTypeGet) favoriteThing).getShowType() : "");
    }

    public String getYear() {
        return context.getString(R.string.text_year,
                favoriteThing instanceof YearGet ? ((YearGet) favoriteThing).getYear() : "");
    }

    // Allows recycling ItemRepoViewModels within the recyclerview adapter
    public void setFavoriteThing(T favoriteThing) {
        this.favoriteThing = favoriteThing;
        notifyChange();
    }

    @Override
    public void destroy() {
        //In this case destroy doesn't need to do anything because there is not async calls
    }

}
