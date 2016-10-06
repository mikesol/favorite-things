package uk.ivanc.archimvvm.view;

import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;
import uk.ivanc.archimvvm.viewmodel.BrowseGithubViewModel;
import uk.ivanc.archimvvm.viewmodel.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class GithubFragment extends BrowsableFragment<BrowseGithubsShowGithubThing> {
    @Override
    BrowseThingViewModel<BrowseGithubsShowGithubThing> makeViewModel() {
        return new BrowseGithubViewModel(getActivity(), this);
    }
}
