package com.caraquri.hatamoto.bookmanager.presentation.contract;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface LoginContract {
    interface View extends MvpView {
        void showError(String message);
        void moveBookList();
        String getResourceString(int resource);
    }

    interface Action {
        void logIn(String email, String password);
    }
}
