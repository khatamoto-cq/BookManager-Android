package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AccountSettingContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;


public class AccountSettingPresenter extends BasePresenter<AccountSettingContract.View>
        implements AccountSettingContract.Action {

    @Inject
    Scheduler scheduler;

    public AccountSettingPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }

    @Override
    public void save(String email, String password, String passwordConfirm) {
        List<String> errors = validate(email, password, passwordConfirm);
        if (!errors.isEmpty()) {
            getView().showError(TextUtils.join("\n", errors));
            return;
        }

        // TODO: 登録処理(API)

        if (!getView().isEntried()) {
            getView().setEntryFlg();
            getView().moveBookList();
            return;
        }

        getView().backSetting();
    }

    private List<String> validate(String email, String password, String passwordConfirm) {
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

        if (passwordConfirm.isEmpty()) {
            errors.add(getView().getErrorMessage(R.string.validation_password_confirm_require));
        }

        if (!password.isEmpty() && !passwordConfirm.isEmpty() && !password.equals(passwordConfirm)) {
            errors.add(getView().getErrorMessage(R.string.validation_password_compare));
        }

        return errors;
    }
}
