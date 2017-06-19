package com.caraquri.hatamoto.bookmanager.presentation.contract;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface SplashContract {
    interface View extends MvpView {
        boolean checkEntry();
        void moveAccountSetting();
        void moveLogin();
    }

    interface Action {
        void moveNextScreen();
    }
}
