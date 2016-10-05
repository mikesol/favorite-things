package uk.ivanc.archimvvm.api;

import braque.Show;
import braque.Update;
import uk.ivanc.archimvvm.model.favoritething.Description;
import uk.ivanc.archimvvm.model.favoritething.ILikeIt;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.Name;
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
        Forks.class,
        Name.class,
        Description.class,
        Stars.class,
        Watchers.class
})

@Update(argument = Id.class, properties = {
        ILikeIt.class
})
public interface BrowseGithubs {
}
