package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface LoginContract {
    interface View extends MvpView {
        void showDialog(String title, String message);

        void moveBookList();

        Context getContext();
    }

    interface Action {
        void logIn(String email, String password);
    }
}
