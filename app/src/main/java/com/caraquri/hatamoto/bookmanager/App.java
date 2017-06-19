package com.caraquri.hatamoto.bookmanager;

import android.app.Application;
import android.content.Context;

import com.caraquri.hatamoto.bookmanager.di.component.AppComponent;
import com.caraquri.hatamoto.bookmanager.di.component.DaggerAppComponent;
import com.caraquri.hatamoto.bookmanager.di.module.AppModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class App extends Application {

    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        return app.mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeTimber();
        initializeStetho();
        initializeLeakCanary();
        initializeDagger();
    }

    private void initializeDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO: Clashlytics
        }
    }

    private void initializeStetho() {
        BuildConfig.STETHO.init(this);
    }

    private void initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
