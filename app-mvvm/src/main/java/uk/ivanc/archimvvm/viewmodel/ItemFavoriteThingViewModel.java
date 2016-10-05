package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;
import uk.ivanc.archimvvm.model.favoritething.braqued.DescriptionGet;
import uk.ivanc.archimvvm.model.favoritething.braqued.NameGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.ForksGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.StarsGet;
import uk.ivanc.archimvvm.model.favoritething.github.braqued.WatchersGet;

/**
 * View model for each item in the repositories RecyclerView
 */
public class ItemFavoriteThingViewModel extends BaseObservable implements ViewModel {

    private BrowseGithubsShowGithubThing repository;
    private Context context;

    public ItemFavoriteThingViewModel(Context context, BrowseGithubsShowGithubThing repository) {
        this.repository = repository;
        this.context = context;
    }

    public String getName() {
        return repository instanceof NameGet ? ((NameGet) repository).getName() : null;
    }

    public String getDescription() {
        return repository instanceof DescriptionGet ? ((DescriptionGet) repository).getDescription() : null;
    }

    public String getStars() {
        return context.getString(R.string.text_stars,
                repository instanceof StarsGet ? ((StarsGet) repository).getStars() : 0);
    }

    public String getWatchers() {
        return context.getString(R.string.text_watchers,
                repository instanceof WatchersGet ? ((WatchersGet) repository).getWatchers() : 0);
    }

    public String getForks() {
        return context.getString(R.string.text_forks,
                repository instanceof ForksGet ? ((ForksGet) repository).getForks() : 0);
    }

    public void onItemClick(View view) {
        //context.startActivity(RepositoryActivity.newIntent(context, repository));
        Log.d("FavoriteThings", "clicked");
    }

    // Allows recycling ItemRepoViewModels within the recyclerview adapter
    public void setRepository(BrowseGithubsShowGithubThing repository) {
        this.repository = repository;
        notifyChange();
    }

    @Override
    public void destroy() {
        //In this case destroy doesn't need to do anything because there is not async calls
    }

}
