package udacity.nanodegree.popularmovies.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import udacity.nanodegree.popularmovies.MovieApp;
import udacity.nanodegree.popularmovies.ui.main.MainActivityViewModel;
import udacity.nanodegree.popularmovies.utils.glide.MyGlideModule;

@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    AppModule.class,
    InjectorModule.class,
    NetworkModule.class
})
public interface AppComponent {

    void inject(MovieApp app);
    void inject(MyGlideModule myGlideModule);
    void inject(MainActivityViewModel mainActivityViewModel);

    final class Initializer {

        private Initializer() { }

        public static AppComponent init(@NonNull final MovieApp app) {
            final AppComponent graph = DaggerAppComponent
                .builder()
                .appModule(new AppModule(app))
                .build();
            graph.inject(app);
            return graph;
        }
    }
}
