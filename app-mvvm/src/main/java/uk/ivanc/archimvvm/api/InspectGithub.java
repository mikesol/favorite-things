package uk.ivanc.archimvvm.api;

import braque.Show;
import braque.Update;
import uk.ivanc.archimvvm.model.favoritething.ILikeIt;
import uk.ivanc.archimvvm.model.favoritething.Id;
import uk.ivanc.archimvvm.model.favoritething.Name;
import uk.ivanc.archimvvm.model.favoritething.github.AvatarURL;
import uk.ivanc.archimvvm.model.favoritething.github.Forks;
import uk.ivanc.archimvvm.model.favoritething.github.GithubThing;
import uk.ivanc.archimvvm.model.favoritething.github.Language;
import uk.ivanc.archimvvm.model.favoritething.github.Owner;
import uk.ivanc.archimvvm.model.favoritething.github.Stars;
import uk.ivanc.archimvvm.model.favoritething.github.Watchers;

/**
 * Created by mikesolomon on 05/10/16.
 */
@Show(
        baseType = GithubThing.class,
        argument = Id.class,
        properties = {
        AvatarURL.class,
        Forks.class,
        Language.class,
        Name.class,
        Owner.class,
        Stars.class,
        Watchers.class
})

@Update(argument = Id.class, properties = {
        ILikeIt.class
})
public interface InspectGithub {
}
