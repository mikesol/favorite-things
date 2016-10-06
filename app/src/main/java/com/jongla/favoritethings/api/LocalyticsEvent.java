package com.jongla.favoritethings.api;

import com.jongla.favoritethings.model.favoritething.DeviceModel;
import com.jongla.favoritethings.model.favoritething.Id;
import com.jongla.favoritethings.model.favoritething.Like;
import com.jongla.favoritethings.model.favoritething.Name;
import com.jongla.favoritethings.model.favoritething.Timestamp;
import com.jongla.favoritethings.model.favoritething.github.Description;
import com.jongla.favoritethings.model.favoritething.github.Forks;
import com.jongla.favoritethings.model.favoritething.github.Stars;
import com.jongla.favoritethings.model.favoritething.github.Watchers;

import braque.Create;
/**
 * Created by mikesolomon on 06/10/16.
 */

@Create(
        argument = Id.class,
        properties = {
                Timestamp.class,
                DeviceModel.class,
                Like.class,
                Name.class
        }
)
public interface LocalyticsEvent {
}
