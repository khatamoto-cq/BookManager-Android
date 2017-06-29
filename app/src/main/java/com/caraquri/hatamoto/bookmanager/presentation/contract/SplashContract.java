package com.caraquri.hatamoto.bookmanager.presentation.contract;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface SplashContract {
    interface View extends MvpView {
        boolean isEntried();

        void moveToAccountSetting();

        void moveToLogin();
    }

    interface Action {
        void moveToNextScreen();
    }
}
