package com.jongla.favoritethings.view;

import com.jongla.favoritethings.api.braqued.BrowseOMDBsShowOMDBThing;
import com.jongla.favoritethings.viewmodel.browse.BrowseOMDBViewModel;
import com.jongla.favoritethings.viewmodel.browse.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class OMDBFragment extends BrowsableFragment<BrowseOMDBsShowOMDBThing> {
    @Override
    BrowseThingViewModel<BrowseOMDBsShowOMDBThing> makeViewModel() {
        return new BrowseOMDBViewModel(getActivity(), getLoaderManager(), this);
    }
}
