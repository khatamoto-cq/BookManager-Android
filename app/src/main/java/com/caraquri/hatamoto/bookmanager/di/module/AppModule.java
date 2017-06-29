package com.caraquri.hatamoto.bookmanager.di.module;

import android.app.Application;
import android.content.Context;

import com.caraquri.hatamoto.bookmanager.data.api.ApiService;
import com.caraquri.hatamoto.bookmanager.data.api.ServiceGenerator;
import com.caraquri.hatamoto.bookmanager.data.repository.AccountRepositoryImpl;
import com.caraquri.hatamoto.bookmanager.data.repository.BookRepositoryImpl;
import com.caraquri.hatamoto.bookmanager.domain.repository.AccountRepository;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;
import com.caraquri.hatamoto.bookmanager.presentation.AccountSettingPresenter;
import com.caraquri.hatamoto.bookmanager.presentation.AddBookPresenter;
import com.caraquri.hatamoto.bookmanager.presentation.BookListPresenter;
import com.caraquri.hatamoto.bookmanager.presentation.EditBookPresenter;
import com.caraquri.hatamoto.bookmanager.presentation.LoginPresenter;
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
    public ApiService provideApiService() {
        return ServiceGenerator.create(ApiService.class);
    }

    @Provides
    AccountRepository provideAccountRepository(ApiService service) {
        return new AccountRepositoryImpl(service);
    }

    @Provides
    BookRepository provideBookRepository(ApiService service) {
        return new BookRepositoryImpl(service);
    }

    @Provides
    SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }

    @Provides
    AccountSettingPresenter provideAccountSettingPresenter(Scheduler scheduler) {
        return new AccountSettingPresenter(scheduler);
    }

    @Provides
    LoginPresenter provideLoginPresenter(Scheduler scheduler, AccountRepository accountRepository) {
        return new LoginPresenter(scheduler, accountRepository);
    }

    @Provides
    AddBookPresenter provideAddBookPresenter(Scheduler scheduler) {
        return new AddBookPresenter(scheduler);
    }

    @Provides
    EditBookPresenter provideEditBookPresenter(Scheduler scheduler) {
        return new EditBookPresenter(scheduler);
    }

    @Provides
    BookListPresenter provideBookListPresenter(Scheduler scheduler, BookRepository bookRepository) {
        return new BookListPresenter(scheduler, bookRepository);
    }
}
