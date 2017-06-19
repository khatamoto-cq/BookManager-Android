package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.presentation.contract.SplashContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import io.reactivex.Scheduler;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Action {

    private Scheduler scheduler;

    public SplashPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void moveNextScreen() {
        if (getView().checkEntry()) {
            getView().moveLogin();
        } else {
            getView().moveAccountSetting();
        }
    }
}
