package com.caraquri.hatamoto.bookmanager.presentation;

import com.caraquri.hatamoto.bookmanager.presentation.contract.EditBookContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import io.reactivex.Scheduler;

public class EditBookPresenter extends BasePresenter<EditBookContract.View> implements EditBookContract.Action {
    private Scheduler scheduler;

    public EditBookPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }
}