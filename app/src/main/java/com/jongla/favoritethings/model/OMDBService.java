package com.jongla.favoritethings.model;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import com.jongla.favoritethings.api.braqued.BrowseOMDBsShowOMDBThing;
import com.jongla.favoritethings.converter.BraqueConverterFactory;

public interface OMDBService {

    @GET("/")
    Observable<List<BrowseOMDBsShowOMDBThing>> browseOMDBShows(@QueryMap Map<String, String> options);

    class Factory {
        public static OMDBService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com")
                    .addConverterFactory(BraqueConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(OMDBService.class);
        }
    }
}
