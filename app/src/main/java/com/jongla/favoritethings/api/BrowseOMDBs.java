package com.jongla.favoritethings.api;

import com.jongla.favoritethings.model.favoritething.Id;
import com.jongla.favoritethings.model.favoritething.Like;
import com.jongla.favoritethings.model.favoritething.Name;
import com.jongla.favoritethings.model.favoritething.omdb.OMDBThing;
import com.jongla.favoritethings.model.favoritething.omdb.Year;

import braque.Show;

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
        }
)
public interface BrowseOMDBs {
}
