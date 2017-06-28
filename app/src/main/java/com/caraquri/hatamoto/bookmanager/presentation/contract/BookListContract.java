package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

import java.util.List;

public interface BookListContract {
    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showDialog(String title, String message);

        void set(List<Book> books);

        Context getContext();
    }

    interface Action {
        void load();
    }
}
