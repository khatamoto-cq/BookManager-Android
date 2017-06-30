package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface AccountSettingContract {
    interface View extends MvpView {
        void entryInAccount();

        boolean isEntried();

        void showDialog(String title, String message);

        void moveToBookList();

        void moveToSetting();

        void saveAccessTokenAndUserId(String requestToken, int userId);

        Context getContext();
    }

    interface Action {
        void save(String email, String password, String passwordConfirm);
    }
}
