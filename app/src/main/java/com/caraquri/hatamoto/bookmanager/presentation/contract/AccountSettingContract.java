package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface AccountSettingContract {
    interface View extends MvpView {
        void setEntryFlg();

        boolean isEntried();

        void showError(String message);

        void moveBookList();

        void moveSetting();

        Context getContext();
    }

    interface Action {
        void save(String email, String password, String passwordConfirm);
    }
}
