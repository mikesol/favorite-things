package com.jongla.favoritethings;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jongla.favoritethings.databinding.ItemFavoritethingBinding;
import com.jongla.favoritethings.viewmodel.ItemFavoriteThingViewModel;

import java.util.Collections;
import java.util.List;

import braque.RESTEndpoint;

public class FavoriteThingsAdapter<S extends RESTEndpoint> extends
        RecyclerView.Adapter<FavoriteThingsAdapter.RepositoryViewHolder> {

    private List<S> things;

    public FavoriteThingsAdapter() {
        this.things = Collections.emptyList();
    }

    public void setThings(List<S> things) {
        this.things = things;
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
        holder.bindFavoriteThing(things.get(position));
    }

    @Override
    public int getItemCount() {
        return things.size();
    }

    public static class RepositoryViewHolder<S extends RESTEndpoint> extends RecyclerView.ViewHolder {
        final ItemFavoritethingBinding binding;

        public RepositoryViewHolder(ItemFavoritethingBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindFavoriteThing(S favoriteThing) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemFavoriteThingViewModel(itemView.getContext(), favoriteThing));
            } else {
                binding.getViewModel().setFavoriteThing(favoriteThing);
            }
        }
    }
}
