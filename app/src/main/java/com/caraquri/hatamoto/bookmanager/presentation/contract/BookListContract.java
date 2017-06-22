package com.caraquri.hatamoto.bookmanager.presentation.contract;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface BookListContract {
    interface View extends MvpView {
        void showProgress();
        void hideProgress();
        void showError(String title, String message);
        String getErrorMessage(int resource);
        void set(Book book);
    }

    interface Action {
        void load();
    }
}
