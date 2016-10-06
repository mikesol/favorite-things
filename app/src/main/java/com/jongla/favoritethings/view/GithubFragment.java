package com.jongla.favoritethings.view;

import com.jongla.favoritethings.api.braqued.BrowseGithubsShowGithubThing;
import com.jongla.favoritethings.viewmodel.browse.BrowseGithubViewModel;
import com.jongla.favoritethings.viewmodel.browse.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class GithubFragment extends BrowsableFragment<BrowseGithubsShowGithubThing> {
    @Override
    BrowseThingViewModel<BrowseGithubsShowGithubThing> makeViewModel() {
        return new BrowseGithubViewModel(getActivity(), getLoaderManager(), this);
    }
}
