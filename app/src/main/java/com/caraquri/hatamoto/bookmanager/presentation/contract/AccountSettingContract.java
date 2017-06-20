package com.caraquri.hatamoto.bookmanager.presentation.contract;

import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

import java.util.List;

public interface AccountSettingContract {
    interface View extends MvpView {
        void setEntryFlg();
        boolean isEntried();
        void showError(String message);
        void moveBookList();
        void backSetting();
    }

    interface Action {
        void save(String email, String password, String passwordConfirm);
        List<String> validate(String email, String password, String passwordConfirm);
    }
}
