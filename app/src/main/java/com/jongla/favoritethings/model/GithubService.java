package com.jongla.favoritethings.model;

import com.jongla.favoritethings.api.braqued.BrowseGithubsShowGithubThing;
import com.jongla.favoritethings.converter.BraqueConverterFactory;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface GithubService {

    @GET("search/repositories")
    Observable<List<BrowseGithubsShowGithubThing>> browsePublicRepositories(@QueryMap Map<String, String> options);

    class Factory {
        public static GithubService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(BraqueConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(GithubService.class);
        }
    }
}
