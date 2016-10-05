package uk.ivanc.archimvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import uk.ivanc.archimvvm.model.Repository;
import uk.ivanc.archimvvm.viewmodel.ItemFavoriteThingViewModel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ItemFavoriteThingViewModelTest {

    ArchiApplication application;

    @Before
    public void setUp() {
        application = (ArchiApplication) RuntimeEnvironment.application;
    }

    @Test
    public void shouldGetName() {
        Repository repository = new Repository();
        repository.name = "ivan";
        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(application, repository);
        assertEquals(repository.name, itemFavoriteThingViewModel.getName());
    }

    @Test
    public void shouldGetDescription() {
        Repository repository = new Repository();
        repository.description = "This is the description";
        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(application, repository);
        assertEquals(repository.description, itemFavoriteThingViewModel.getDescription());
    }

    @Test
    public void shouldGetStars() {
        Repository repository = new Repository();
        repository.stars = 10;
        String expectedString = application.getString(R.string.text_stars, repository.stars);
        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(application, repository);
        assertEquals(expectedString, itemFavoriteThingViewModel.getStars());
    }

    @Test
    public void shouldGetForks() {
        Repository repository = new Repository();
        repository.forks = 5;
        String expectedString = application.getString(R.string.text_forks, repository.forks);

        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(application, repository);
        assertEquals(expectedString, itemFavoriteThingViewModel.getForks());
    }

    @Test
    public void shouldGetWatchers() {
        Repository repository = new Repository();
        repository.watchers = 7;
        String expectedString = application.getString(R.string.text_watchers, repository.watchers);

        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(application, repository);
        assertEquals(expectedString, itemFavoriteThingViewModel.getWatchers());
    }

    @Test
    public void shouldStartActivityOnItemClick() {
        Repository repository = new Repository();
        Context mockContext = mock(Context.class);
        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(mockContext, repository);
        itemFavoriteThingViewModel.onItemClick(new View(application));
        verify(mockContext).startActivity(any(Intent.class));
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetRepository() {
        Repository repository = new Repository();
        ItemFavoriteThingViewModel itemFavoriteThingViewModel = new ItemFavoriteThingViewModel(application, repository);
        Observable.OnPropertyChangedCallback mockCallback =
                mock(Observable.OnPropertyChangedCallback.class);
        itemFavoriteThingViewModel.addOnPropertyChangedCallback(mockCallback);

        itemFavoriteThingViewModel.setRepository(repository);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
