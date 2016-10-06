package com.jongla.favoritethings.api;

import com.jongla.favoritethings.model.favoritething.DeviceId;
import com.jongla.favoritethings.model.favoritething.Id;
import com.jongla.favoritethings.model.favoritething.Like;
import com.jongla.favoritethings.model.favoritething.Timestamp;

import braque.Create;
/**
 * Created by mikesolomon on 06/10/16.
 */

@Create(
        argument = Id.class,
        properties = {
                Timestamp.class,
                DeviceId.class,
                Like.class
        }
)
public interface LocalyticsEvent {
}
