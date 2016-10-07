package com.jongla.favoritethings.converter;

import com.google.inject.util.Types;
import com.jongla.favoritethings.api.braqued.BrowseGithubsShowGithubThing;
import com.jongla.favoritethings.api.braqued.BrowseOMDBsShowOMDBThing;
import com.jongla.favoritethings.api.braqued.BrowseTVMazesShowTVMazeThing;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import braque.braqued.StringProvisioner;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.jongla.favoritethings.converter.BraqueResponseBodyConverter.BraqueProperty;

/**
 * Created by mikesolomon on 05/10/16.
 */

public final class BraqueConverterFactory extends Converter.Factory {

    public static BraqueConverterFactory create() {
        return new BraqueConverterFactory();
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (type.toString().equals(Types.newParameterizedType(List.class, BrowseGithubsShowGithubThing.class).toString())) {
            return new BraqueResponseBodyConverter(BrowseGithubsShowGithubThing.class,
                    StringProvisioner.pathBrowseGithubs(),
                    new BraqueResponseBodyConverter.BraqueProperty[]{
                            new BraqueProperty("id", StringProvisioner.propId()),
                            new BraqueProperty("stargazers_count", StringProvisioner.propStars()),
                            new BraqueProperty("name", StringProvisioner.propName()),
                            new BraqueProperty("forks_count", StringProvisioner.propForks()),
                            new BraqueProperty("watchers_count", StringProvisioner.propWatchers()),
                            new BraqueProperty("description", StringProvisioner.propDescription())

                    }, "items");
        } else if (type.toString().equals(Types.newParameterizedType(List.class, BrowseTVMazesShowTVMazeThing.class).toString())) {
            return new BraqueResponseBodyConverter(BrowseTVMazesShowTVMazeThing.class,
                    StringProvisioner.pathBrowseTVMazes(),
                    new BraqueResponseBodyConverter.BraqueProperty[]{
                            new BraqueProperty("show.id", StringProvisioner.propId()),
                            new BraqueProperty("show.type", StringProvisioner.propShowType()),
                            new BraqueProperty("show.name", StringProvisioner.propName()),
                            new BraqueProperty("show.summary", StringProvisioner.propDescription())
                    });
        } else if (type.toString().equals(Types.newParameterizedType(List.class, BrowseOMDBsShowOMDBThing.class).toString())) {
            return new BraqueResponseBodyConverter(BrowseOMDBsShowOMDBThing.class,
                    StringProvisioner.pathBrowseOMDBs(),
                    new BraqueResponseBodyConverter.BraqueProperty[]{
                            new BraqueProperty("imdbID", StringProvisioner.propId()),
                            new BraqueProperty("Title", StringProvisioner.propName()),
                            new BraqueProperty("Year", StringProvisioner.propYear()),
                    }, "Search");
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return null;
    }
}