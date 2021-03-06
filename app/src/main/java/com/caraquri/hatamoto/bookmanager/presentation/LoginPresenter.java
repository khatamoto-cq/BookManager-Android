package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.LoginValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Action {

    Scheduler scheduler;

    public LoginPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }

    @Override
    public void login(String email, String password) {
        List<String> errors = LoginValidator.validate(getView().getContext(), email, password);

        if (!errors.isEmpty()) {
            getView().showDialog(getView().getContext().getString(R.string.validation_title), TextUtils.join("\n", errors));
            return;
        }

        // TODO: APIでログインしてトークンを保存

        getView().moveToBookList();
    }
}
