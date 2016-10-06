package uk.ivanc.archimvvm.api;

import braque.Show;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.Like;
import uk.ivanc.archimvvm.model.favoritething.Name;
import uk.ivanc.archimvvm.model.favoritething.github.Description;
import uk.ivanc.archimvvm.model.favoritething.github.Forks;
import uk.ivanc.archimvvm.model.favoritething.github.GithubThing;
import uk.ivanc.archimvvm.model.favoritething.github.Stars;
import uk.ivanc.archimvvm.model.favoritething.github.Watchers;

/**
 * Created by mikesolomon on 05/10/16.
 */
@Show(
        baseType = GithubThing.class,
        argument = Id.class,
        properties = {
                Like.class,
                Forks.class,
                Name.class,
                Description.class,
                Stars.class,
                Watchers.class
})
public interface BrowseGithubs {
}
