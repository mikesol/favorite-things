package uk.ivanc.archimvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import braque.RESTShow;
import uk.ivanc.archimvvm.FavoriteThingsAdapter;
import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.databinding.TopLevelFragmentBinding;
import uk.ivanc.archimvvm.viewmodel.BrowseThingViewModel;

public abstract class BrowsableFragment<T extends RESTShow> extends Fragment implements BrowseThingViewModel.DataListener<T> {

    private TopLevelFragmentBinding binding;
    private BrowseThingViewModel<T> browseThingViewModel;

    abstract BrowseThingViewModel<T> makeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                              Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_level_fragment, container, false); // TODO: change layout file!!!
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
    public void onFavoriteThingsChanged(List<T> repositories) {
        FavoriteThingsAdapter adapter =
                (FavoriteThingsAdapter) binding.reposRecyclerView.getAdapter();
        adapter.setThings(repositories);
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
