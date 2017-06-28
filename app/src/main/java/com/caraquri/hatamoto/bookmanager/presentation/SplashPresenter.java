package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.presentation.contract.SplashContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Action {

    @Override
    public void moveToNextScreen() {
        if (getView().isEntried()) {
            getView().moveToLogin();
        } else {
            getView().moveToAccountSetting();
        }
    }
}
