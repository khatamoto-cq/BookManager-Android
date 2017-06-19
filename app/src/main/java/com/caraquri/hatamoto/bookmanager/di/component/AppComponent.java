package com.caraquri.hatamoto.bookmanager.di.component;

import com.caraquri.hatamoto.bookmanager.di.module.AppModule;
import com.caraquri.hatamoto.bookmanager.presentation.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class})
public interface AppComponent {
    void inject(SplashActivity activity);
}
