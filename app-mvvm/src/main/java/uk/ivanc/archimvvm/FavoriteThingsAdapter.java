package uk.ivanc.archimvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;
import uk.ivanc.archimvvm.databinding.ItemFavoritethingBinding;
import uk.ivanc.archimvvm.model.Repository;
import uk.ivanc.archimvvm.viewmodel.ItemFavoriteThingViewModel;

public class FavoriteThingsAdapter extends RecyclerView.Adapter<FavoriteThingsAdapter.RepositoryViewHolder> {

    private List<BrowseGithubsShowGithubThing> repositories;

    public FavoriteThingsAdapter() {
        this.repositories = Collections.emptyList();
    }

    public FavoriteThingsAdapter(List<BrowseGithubsShowGithubThing> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<BrowseGithubsShowGithubThing> repositories) {
        this.repositories = repositories;
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
        holder.bindRepository(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        final ItemFavoritethingBinding binding;

        public RepositoryViewHolder(ItemFavoritethingBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindRepository(BrowseGithubsShowGithubThing repository) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemFavoriteThingViewModel(itemView.getContext(), repository));
            } else {
                binding.getViewModel().setRepository(repository);
            }
        }
    }
}
