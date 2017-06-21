package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;


public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Action {

    private Scheduler scheduler;

    public LoginPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }

    @Override
    public void logIn(String email, String password) {
        List<String> errors = validate(email, password);
        if (!errors.isEmpty()) {
            getView().showError(TextUtils.join("\n", errors));
            return;
        }

        // TODO: APIでログインしてトークンを保存

        getView().moveBookList();
    }

    private List<String> validate(String email, String password) {
        List errors = new ArrayList<String>();

        if (email.isEmpty()) {
            errors.add(getView().getErrorMessage(R.string.validation_email_require));
        } else {
            String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
            if (!email.matches(regex)) {
                errors.add(getView().getErrorMessage(R.string.validation_email_invalid));
            }
        }

        if (password.isEmpty()) {
            errors.add(getView().getErrorMessage(R.string.validation_password_require));
        }

        return errors;
    }
}
