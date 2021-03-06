package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface LoginContract {
    interface View extends MvpView {
        void showDialog(String title, String message);

        void moveToBookList();

        Context getContext();
    }

    interface Action {
        void login(String email, String password);
    }
}
