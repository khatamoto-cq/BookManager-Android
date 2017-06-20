package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.presentation.contract.AccountSettingContract;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;


public class AccountSettingPresenter extends BasePresenter<AccountSettingContract.View>
        implements AccountSettingContract.Action {

    private Scheduler scheduler;

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

        // TODO: 登録処理

        if (!getView().isEntried()) {
            getView().setEntryFlg();
            getView().moveBookList();
            return;
        }

        getView().backSetting();
    }

    @Override
    public List<String> validate(String email, String password, String passwordConfirm) {
        List errors = new ArrayList<String>();

        if (email.isEmpty()) {
            errors.add("メールアドレスが入力されていません");
        } else {
            String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
            if (!email.matches(regex)) {
                errors.add("メールアドレスを正しい形式で入力してください");
            }
        }

        if (password.isEmpty()) {
            errors.add("パスワードが入力されていません");
        }

        if (passwordConfirm.isEmpty()) {
            errors.add("パスワード(確認用)が入力されていません");
        }

        if (password != passwordConfirm) {
            errors.add("パスワードとパスワード(確認用)が一致しません");
        }

        return errors;
    }
}
