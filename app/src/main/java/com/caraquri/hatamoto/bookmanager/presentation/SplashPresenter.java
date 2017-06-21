package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.presentation.contract.SplashContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Action {

    @Override
    public void moveNextScreen() {
        if (getView().isEntried()) {
            getView().moveLogin();
        } else {
            getView().moveAccountSetting();
        }
    }
}
