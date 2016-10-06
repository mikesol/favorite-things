package uk.ivanc.archimvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import braque.RESTShow;
import braque.braqued.Transformer;
import uk.ivanc.archimvvm.api.braqued.$FavoritizeUpdateFavoriteThing;
import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;
import uk.ivanc.archimvvm.api.braqued.BrowseOMDBsShowOMDBThing;
import uk.ivanc.archimvvm.api.braqued.BrowseTVMazesShowTVMazeThing;
import uk.ivanc.archimvvm.databinding.ItemFavoritethingBinding;
import uk.ivanc.archimvvm.model.favoritething.braqued.IdGet;
import uk.ivanc.archimvvm.model.favoritething.braqued.LikeGet;
import uk.ivanc.archimvvm.viewmodel.ItemFavoriteThingViewModel;

public class FavoriteThingsAdapter<S extends RESTShow> extends
        RecyclerView.Adapter<FavoriteThingsAdapter.RepositoryViewHolder> {

    public static class UpdateHolder {
        public $FavoritizeUpdateFavoriteThing u;
        UpdateHolder($FavoritizeUpdateFavoriteThing update) {
            this.u = update;
        }
    }

    private List<S> things;
    private List<UpdateHolder> updates;

    private void makeUpdates() {
        if (things == null) {
            updates = null;
        }
        updates = new ArrayList<>();
        for (int i = 0; i < things.size(); i++) {
            RESTShow thing = things.get(i);
            $FavoritizeUpdateFavoriteThing update = null;

            if (thing instanceof BrowseGithubsShowGithubThing) {
                update = Transformer.makeFavoritizeUpdateGithubThing(((IdGet) thing).getId());
            } else if (thing instanceof BrowseTVMazesShowTVMazeThing) {
                update = Transformer.makeFavoritizeUpdateTVMazeThing(((IdGet) thing).getId());
            } else if (thing instanceof BrowseOMDBsShowOMDBThing) {
                update = Transformer.makeFavoritizeUpdateOMDBThing(((IdGet) thing).getId());
            }
            update.addLike(thing instanceof LikeGet && ((LikeGet)thing).getLike());
            updates.add(new UpdateHolder(update));
        }
    }

    public FavoriteThingsAdapter() {
        this.things = Collections.emptyList();
        makeUpdates();
    }

    public FavoriteThingsAdapter(List<S> things) {
        this.things = things;
        makeUpdates();
    }

    public void setThings(List<S> things) {
        this.things = things;
        makeUpdates();
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFavoritethingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_favoritething,
                parent,
                false);
        return new RepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.bindFavoriteThing(things.get(position), updates.get(position));
    }

    @Override
    public int getItemCount() {
        return things.size();
    }

    public static class RepositoryViewHolder<S extends RESTShow> extends RecyclerView.ViewHolder {
        final ItemFavoritethingBinding binding;

        public RepositoryViewHolder(ItemFavoritethingBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindFavoriteThing(S favoriteThing, UpdateHolder update) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemFavoriteThingViewModel(itemView.getContext(),
                        itemView.getResources(), favoriteThing, update));
            } else {
                binding.getViewModel().setFavoriteThing(favoriteThing, update);
            }
        }
    }
}
