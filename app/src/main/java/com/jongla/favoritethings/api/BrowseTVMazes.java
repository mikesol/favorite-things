package com.jongla.favoritethings.api;

import com.jongla.favoritethings.model.favoritething.Id;
import com.jongla.favoritethings.model.favoritething.Like;
import com.jongla.favoritethings.model.favoritething.Name;
import com.jongla.favoritethings.model.favoritething.tvmaze.Description;
import com.jongla.favoritethings.model.favoritething.tvmaze.ShowType;
import com.jongla.favoritethings.model.favoritething.tvmaze.TVMazeThing;

import braque.Show;

/**
 * Created by mikesolomon on 05/10/16.
 */
@Show(
        baseType = TVMazeThing.class,
        argument = Id.class,
        properties = {
                Like.class,
                ShowType.class,
                Name.class,
                Description.class
        }
)
public interface BrowseTVMazes {
}
