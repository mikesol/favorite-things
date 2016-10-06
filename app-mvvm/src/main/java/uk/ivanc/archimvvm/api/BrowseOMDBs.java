package uk.ivanc.archimvvm.api;

import braque.Show;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.Like;
import uk.ivanc.archimvvm.model.favoritething.Name;
import uk.ivanc.archimvvm.model.favoritething.omdb.OMDBThing;
import uk.ivanc.archimvvm.model.favoritething.omdb.Year;

/**
 * Created by mikesolomon on 05/10/16.
 */

@Show(
        baseType = OMDBThing.class,
        argument = Id.class,
        properties = {
                Like.class,
                Name.class,
                Year.class
        })
public interface BrowseOMDBs {
}
