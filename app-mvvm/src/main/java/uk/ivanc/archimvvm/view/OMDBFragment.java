package uk.ivanc.archimvvm.view;

import uk.ivanc.archimvvm.api.braqued.BrowseOMDBsShowOMDBThing;
import uk.ivanc.archimvvm.viewmodel.BrowseOMDBViewModel;
import uk.ivanc.archimvvm.viewmodel.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class OMDBFragment extends BrowsableFragment<BrowseOMDBsShowOMDBThing> {
    @Override
    BrowseThingViewModel<BrowseOMDBsShowOMDBThing> makeViewModel() {
        return new BrowseOMDBViewModel(getActivity(), this);
    }
}
