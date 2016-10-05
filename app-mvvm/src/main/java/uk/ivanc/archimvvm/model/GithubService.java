package uk.ivanc.archimvvm.model;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import uk.ivanc.archimvvm.api.braqued.BrowseGithubsShowGithubThing;
import uk.ivanc.archimvvm.converter.BraqueConverterFactory;

public interface GithubService {

    @GET("search/repositories")
    Observable<List<BrowseGithubsShowGithubThing>> browsePublicRepositories(@QueryMap Map<String, String> options);

    @GET
    Observable<User> userFromUrl(@Url String userUrl);


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
