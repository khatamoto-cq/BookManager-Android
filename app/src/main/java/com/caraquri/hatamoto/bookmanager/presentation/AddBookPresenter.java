package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.presentation.contract.AddBookContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import io.reactivex.Scheduler;

public class AddBookPresenter extends BasePresenter<AddBookContract.View> implements AddBookContract.Action {

    private Scheduler scheduler;

    public AddBookPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }
}
