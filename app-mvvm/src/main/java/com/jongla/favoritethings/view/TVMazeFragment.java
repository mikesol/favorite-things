package com.jongla.favoritethings.view;

import com.jongla.favoritethings.api.braqued.BrowseTVMazesShowTVMazeThing;
import com.jongla.favoritethings.viewmodel.browse.BrowseTVMazeViewModel;
import com.jongla.favoritethings.viewmodel.browse.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class TVMazeFragment extends BrowsableFragment<BrowseTVMazesShowTVMazeThing> {
    @Override
    BrowseThingViewModel<BrowseTVMazesShowTVMazeThing> makeViewModel() {
        return new BrowseTVMazeViewModel(getActivity(), getLoaderManager(), this);
    }
}
