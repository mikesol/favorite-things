package com.jongla.favoritethings.api;

import com.jongla.favoritethings.model.favoritething.Id;
import com.jongla.favoritethings.model.favoritething.Like;
import com.jongla.favoritethings.model.favoritething.Name;
import com.jongla.favoritethings.model.favoritething.github.Description;
import com.jongla.favoritethings.model.favoritething.github.Forks;
import com.jongla.favoritethings.model.favoritething.github.GithubThing;
import com.jongla.favoritethings.model.favoritething.github.Stars;
import com.jongla.favoritethings.model.favoritething.github.Watchers;

import braque.Show;

/**
 * Created by mikesolomon on 05/10/16.
 */
@Show(
        baseType = GithubThing.class,
        argument = Id.class,
        properties = {
                Forks.class,
                Like.class,
                Description.class,
                Name.class,
                Stars.class,
                Watchers.class
})
public interface BrowseGithubs {
}
