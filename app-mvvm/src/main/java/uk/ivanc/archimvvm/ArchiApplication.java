package uk.ivanc.archimvvm;

import android.app.Application;
import android.content.Context;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import uk.ivanc.archimvvm.model.GithubService;
import uk.ivanc.archimvvm.model.OMDBService;
import uk.ivanc.archimvvm.model.TVMazeService;

public class ArchiApplication extends Application {

    private GithubService githubService;
    private TVMazeService tvMazeService;
    private OMDBService omdbService;
    private Scheduler defaultSubscribeScheduler;

    public static ArchiApplication get(Context context) {
        return (ArchiApplication) context.getApplicationContext();
    }

    public GithubService getGithubService() {
        if (githubService == null) {
            githubService = GithubService.Factory.create();
        }
        return githubService;
    }

    public TVMazeService getTVMazeService() {
        if (tvMazeService == null) {
            tvMazeService = TVMazeService.Factory.create();
        }
        return tvMazeService;
    }

    public OMDBService getOMDBService() {
        if (omdbService == null) {
            omdbService = OMDBService.Factory.create();
        }
        return omdbService;
    }

    //For setting mocks during testing
    public void setGithubService(GithubService githubService) {
        this.githubService = githubService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
