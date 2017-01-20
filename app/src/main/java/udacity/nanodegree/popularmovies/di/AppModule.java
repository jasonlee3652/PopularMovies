package udacity.nanodegree.popularmovies.di;

import android.arch.persistence.room.Room;

import dagger.Module;
import dagger.Provides;
import udacity.nanodegree.popularmovies.MovieApp;
import udacity.nanodegree.popularmovies.room.AppDatabase;

@Module
class AppModule {

    private MovieApp app;

    AppModule(final MovieApp app) {
        this.app = app;
    }

    @Provides
    MovieApp provideMovieApp() {
        return app;
    }

    @Provides
    AppDatabase provideDb() {
        return Room.databaseBuilder(app, AppDatabase.class, "movie").build();
    }
}
