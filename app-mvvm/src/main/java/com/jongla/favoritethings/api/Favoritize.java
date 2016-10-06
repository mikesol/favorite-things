package com.jongla.favoritethings.api;

import braque.Update;
import com.jongla.favoritethings.model.favoritething.Id;
import com.jongla.favoritethings.model.favoritething.Like;

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
