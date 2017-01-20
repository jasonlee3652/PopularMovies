package udacity.nanodegree.popularmovies;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import timber.log.Timber;
import udacity.nanodegree.popularmovies.di.AppComponent;
import udacity.nanodegree.popularmovies.utils.DebugTree;

import static udacity.nanodegree.popularmovies.utils.DeveloperHelper.enableStetho;


public class MovieApp extends Application implements HasActivityInjector, HasServiceInjector {

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject DispatchingAndroidInjector<Service>  dispatchingServiceInjector;

    private static AppComponent               graph;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        graph = AppComponent.Initializer.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree("movies"));
            enableStetho(this);
        }
        AndroidThreeTen.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }

    public static AppComponent graph() {
        return graph;
    }
}
