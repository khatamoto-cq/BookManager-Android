package com.caraquri.hatamoto.bookmanager.di.component;

import com.caraquri.hatamoto.bookmanager.di.module.AppModule;
import com.caraquri.hatamoto.bookmanager.presentation.AccountSettingActivity;
import com.caraquri.hatamoto.bookmanager.presentation.AddBookActivity;
import com.caraquri.hatamoto.bookmanager.presentation.BookListFragment;
import com.caraquri.hatamoto.bookmanager.presentation.EditBookActivity;
import com.caraquri.hatamoto.bookmanager.presentation.LoginActivity;
import com.caraquri.hatamoto.bookmanager.presentation.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class})
public interface AppComponent {
    void inject(SplashActivity activity);

    void inject(AccountSettingActivity activity);

    void inject(LoginActivity activity);

    void inject(AddBookActivity activity);

    void inject(EditBookActivity activity);

    void inject(BookListFragment fragment);
}
