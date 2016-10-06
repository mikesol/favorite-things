package uk.ivanc.archimvvm.view;

import uk.ivanc.archimvvm.api.braqued.BrowseTVMazesShowTVMazeThing;
import uk.ivanc.archimvvm.viewmodel.BrowseTVMazeViewModel;
import uk.ivanc.archimvvm.viewmodel.BrowseThingViewModel;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class TVMazeFragment extends BrowsableFragment<BrowseTVMazesShowTVMazeThing> {
    @Override
    BrowseThingViewModel<BrowseTVMazesShowTVMazeThing> makeViewModel() {
        return new BrowseTVMazeViewModel(getActivity(), this);
    }
}
