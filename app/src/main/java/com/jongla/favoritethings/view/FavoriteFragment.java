package com.jongla.favoritethings.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import braque.RESTEndpoint;

import com.jongla.favoritethings.R;
import com.jongla.favoritethings.api.braqued.BrowseGithubsShowGithubThing;
import com.jongla.favoritethings.viewmodel.browse.BrowseFavoritesViewModel;
import com.jongla.favoritethings.viewmodel.browse.BrowseGithubViewModel;
import com.jongla.favoritethings.viewmodel.browse.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class FavoriteFragment extends BrowsableFragment<RESTEndpoint> {
    @Override
    BrowseThingViewModel<RESTEndpoint> makeViewModel() {
        return new BrowseFavoritesViewModel(getActivity(), getLoaderManager(), this);
    }
    //loadFavoriteThings
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getModel().loadFavoriteThings(null);
        return view;
    }
}
