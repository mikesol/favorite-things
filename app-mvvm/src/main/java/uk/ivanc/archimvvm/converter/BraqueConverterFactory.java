package uk.ivanc.archimvvm.converter;

import android.util.Log;

import com.google.inject.util.Types;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import braque.braqued.StringProvisioner;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;

import static uk.ivanc.archimvvm.converter.BraqueResponseBodyConverter.BraqueProperty;

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
            Log.d("BraqueConverterFactory","converter worked");
            return new BraqueResponseBodyConverter(BrowseGithubsShowGithubThing.class, "id",
                    new BraqueResponseBodyConverter.BraqueProperty[]{
                            new BraqueProperty("stargazers_count", StringProvisioner.propStars()),
                            new BraqueProperty("name", StringProvisioner.propName()),
                            new BraqueProperty("forks_count", StringProvisioner.propForks()),
                            new BraqueProperty("watchers_count", StringProvisioner.propWatchers()),
                            new BraqueProperty("language", StringProvisioner.propLanguage()),
                            new BraqueProperty("owner.name", StringProvisioner.propOwner()),
                            new BraqueProperty("owner.avatar_url", StringProvisioner.propAvatarURL())
                    });
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return null;
    }
}