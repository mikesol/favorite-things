package uk.ivanc.archimvvm.api;

import braque.Update;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.Like;

/**
 * Created by mikesolomon on 05/10/16.
 */

@Update(
        argument = Id.class,
        properties = {
                Like.class
        })
public class Favoritize {
}
