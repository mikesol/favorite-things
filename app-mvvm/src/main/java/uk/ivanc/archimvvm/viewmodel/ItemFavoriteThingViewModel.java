package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.util.Log;
import android.view.View;

import braque.RESTShow;
import uk.ivanc.archimvvm.FavoriteThingsAdapter;
import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.model.favoritething.braqued.LikeGet;
import uk.ivanc.archimvvm.model.favoritething.braqued.NameGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.DescriptionGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.ForksGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.StarsGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.WatchersGet;
import uk.ivanc.archimvvm.model.favoritething.omdb.braqued.YearGet;
import uk.ivanc.archimvvm.model.favoritething.tvmaze.braqued.ShowTypeGet;

/**
 * View model for each item in the repositories RecyclerView
 */
public class ItemFavoriteThingViewModel<T extends RESTShow> extends BaseObservable implements ViewModel {

    private T favoriteThing;
    private FavoriteThingsAdapter.UpdateHolder update;
    private Context context;
    public ObservableBoolean favorite;

    public ItemFavoriteThingViewModel(Context context, Resources resources,
                                      T favoriteThing,
                                      FavoriteThingsAdapter.UpdateHolder update) {
        this.favoriteThing = favoriteThing;
        this.context = context;
        this.update = update;
        this.favorite = new ObservableBoolean();
        updateFavorite();
    }

    private void updateFavorite() {
        this.favorite.set(update.u.commit() instanceof LikeGet && ((LikeGet)update.u.commit()).getLike());
    }

    public void onFavoriteClick(View view) {
        if (favorite.get()) {
            favorite.set(false);
            update.u = update.u.removeLike();
        } else {
            favorite.set(true);
            update.u = update.u.addLike(true);
        }
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


    public void onItemClick(View view) {
        //context.startActivity(InspectActivity.newIntent(context, favoriteThing));
        Log.d("FavoriteThings", "clicked");
    }

    // Allows recycling ItemRepoViewModels within the recyclerview adapter
    public void setFavoriteThing(T favoriteThing,FavoriteThingsAdapter.UpdateHolder update) {
        this.favoriteThing = favoriteThing;
        this.update = update;
        updateFavorite();
        notifyChange();
    }

    @Override
    public void destroy() {
        //In this case destroy doesn't need to do anything because there is not async calls
    }

}
