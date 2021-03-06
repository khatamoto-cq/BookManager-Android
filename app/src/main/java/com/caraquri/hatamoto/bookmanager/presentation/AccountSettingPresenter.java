package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AccountSettingContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.AccountValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;

public class AccountSettingPresenter extends BasePresenter<AccountSettingContract.View>
        implements AccountSettingContract.Action {

    Scheduler scheduler;

    public AccountSettingPresenter(Scheduler scheduler) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
    }

    @Override
    public void save(String email, String password, String passwordConfirm) {
        List<String> errors = AccountValidator.validate(getView().getContext(), email, password, passwordConfirm);
        if (!errors.isEmpty()) {
            getView().showDialog(getView().getContext().getString(R.string.validation_title), TextUtils.join("\n", errors));
            return;
        }

        // TODO: 登録処理(API)

        if (!getView().isEntried()) {
            getView().entryInAccount();
            getView().moveToBookList();
            return;
        }

        getView().moveToSetting();
    }
}
