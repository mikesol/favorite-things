package uk.ivanc.archimvvm.api;

import braque.Show;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.omdb.Actors;
import uk.ivanc.archimvvm.model.favoritething.omdb.Awards;
import uk.ivanc.archimvvm.model.favoritething.omdb.Genre;
import uk.ivanc.archimvvm.model.favoritething.omdb.Language;
import uk.ivanc.archimvvm.model.favoritething.omdb.OMDBThing;
import uk.ivanc.archimvvm.model.favoritething.omdb.Plot;
import uk.ivanc.archimvvm.model.favoritething.omdb.RunningTime;
import uk.ivanc.archimvvm.model.favoritething.omdb.Year;

/**
 * Created by mikesolomon on 05/10/16.
 */

@Show(
        baseType = OMDBThing.class,
        argument = Id.class,
        properties = {
                Genre.class,
                Language.class,
                Actors.class,
                Awards.class,
                Plot.class,
                RunningTime.class,
                Year.class
})
public interface InspectOMDB {
}
