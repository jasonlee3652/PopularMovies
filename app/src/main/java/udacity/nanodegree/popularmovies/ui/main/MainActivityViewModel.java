package udacity.nanodegree.popularmovies.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import udacity.nanodegree.popularmovies.MovieApp;
import udacity.nanodegree.popularmovies.api.TMDbApi;
import udacity.nanodegree.popularmovies.api.models.MoviesResponse;

public class MainActivityViewModel extends ViewModel {

    @Inject TMDbApi api;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<List<MoviesResponse.Movie>> data;

    public MainActivityViewModel() {
        MovieApp.graph().inject(this);
        data = new MutableLiveData<>();
        data.setValue(new ArrayList<>());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    MutableLiveData<List<MoviesResponse.Movie>> getData() {
        return data;
    }

    void addMovies(final List<MoviesResponse.Movie> movies) {
        final List<MoviesResponse.Movie> list = data.getValue();
        list.addAll(movies);
        data.setValue(list);
    }

    void loadImageConfiguration() {

    }

    void clear() {
        data.getValue().clear();
    }

    void setSortOrder(final Integer order) {

    }
}
