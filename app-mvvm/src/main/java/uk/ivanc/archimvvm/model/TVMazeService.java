package uk.ivanc.archimvvm.model;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import uk.ivanc.archimvvm.api.braqued.BrowseTVMazesShowTVMazeThing;
import uk.ivanc.archimvvm.converter.BraqueConverterFactory;

public interface TVMazeService {

    @GET("search/shows")
    Observable<List<BrowseTVMazesShowTVMazeThing>> browseTVMazes(@QueryMap Map<String, String> options);

    class Factory {
        public static TVMazeService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.tvmaze.com/")
                    .addConverterFactory(BraqueConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(TVMazeService.class);
        }
    }
}
