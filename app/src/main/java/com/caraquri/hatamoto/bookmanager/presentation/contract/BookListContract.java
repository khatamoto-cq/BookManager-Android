package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface BookListContract {
    interface View extends MvpView {
        void showProgress();
        void hideProgress();
        void showError(String title, String message);
        void set(Book book);
        Context getContext();
    }

    interface Action {
        void load();
    }
}
