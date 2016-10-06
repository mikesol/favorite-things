package uk.ivanc.archimvvm.api;

import braque.Show;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.Name;
import uk.ivanc.archimvvm.model.favoritething.tvmaze.Description;
import uk.ivanc.archimvvm.model.favoritething.tvmaze.ShowType;
import uk.ivanc.archimvvm.model.favoritething.tvmaze.TVMazeThing;

/**
 * Created by mikesolomon on 05/10/16.
 */
@Show(
        baseType = TVMazeThing.class,
        argument = Id.class,
        properties = {
                ShowType.class,
                Name.class,
                Description.class
})
public interface InspectTVMaze {
}
