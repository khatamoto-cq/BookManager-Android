package com.caraquri.hatamoto.bookmanager.di.module;

import android.app.Application;
import android.content.Context;

import com.caraquri.hatamoto.bookmanager.presentation.SplashPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    public Scheduler provideScheduler() {
        return Schedulers.io();
    }

    @Provides
    SplashPresenter provideSplashPresenter(Scheduler scheduler) {
        return new SplashPresenter(scheduler);
    }
}
