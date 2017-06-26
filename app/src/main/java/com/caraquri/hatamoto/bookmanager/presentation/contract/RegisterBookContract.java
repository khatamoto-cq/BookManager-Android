package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface RegisterBookContract {
    interface View extends MvpView {
        void finish();

        void showDialog(String title, String message);

        Context getContext();
    }

    interface Action {
        void backScreen();

        void save(Book book);
    }
}