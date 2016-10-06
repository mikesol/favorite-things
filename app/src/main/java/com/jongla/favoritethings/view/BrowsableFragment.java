package com.jongla.favoritethings.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jongla.favoritethings.FavoriteThingsAdapter;
import com.jongla.favoritethings.R;
import com.jongla.favoritethings.databinding.TopLevelFragmentBinding;
import com.jongla.favoritethings.viewmodel.browse.BrowseThingViewModel;

import java.util.List;

import braque.RESTEndpoint;
import braque.RESTShow;

public abstract class BrowsableFragment<T extends RESTEndpoint>
        extends Fragment implements BrowseThingViewModel.DataListener<T> {

    private TopLevelFragmentBinding binding;
    private BrowseThingViewModel<T> browseThingViewModel;

    abstract BrowseThingViewModel<T> makeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                              Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_level_fragment, container, false);
        browseThingViewModel = makeViewModel();
        binding.setViewModel(browseThingViewModel);
        setupRecyclerView(binding.reposRecyclerView);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        browseThingViewModel.destroy();
    }

    @Override
    public void onFavoriteThingsChanged(List<T> things) {
        FavoriteThingsAdapter adapter =
                (FavoriteThingsAdapter) binding.reposRecyclerView.getAdapter();
        adapter.setThings(things);
        adapter.notifyDataSetChanged();
        ((MainActivity)getActivity()).hideSoftKeyboard();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        FavoriteThingsAdapter adapter = new FavoriteThingsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public BrowseThingViewModel getModel() {
        return browseThingViewModel;
    }

}
