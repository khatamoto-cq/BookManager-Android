package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.LoginValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;


public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Action {

    @Inject
    Scheduler scheduler;

    public LoginPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }

    @Override
    public void logIn(String email, String password) {
        LoginValidator validator = new LoginValidator(getView());
        List<String> errors = validator.validate(email, password);

        if (!errors.isEmpty()) {
            getView().showError(TextUtils.join("\n", errors));
            return;
        }

        // TODO: APIでログインしてトークンを保存

        getView().moveBookList();
    }
}
