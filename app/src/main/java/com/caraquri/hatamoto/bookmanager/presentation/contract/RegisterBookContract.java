package com.caraquri.hatamoto.bookmanager.presentation.contract;

import android.content.Context;
import android.widget.ImageView;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.util.mvp.MvpView;

public interface RegisterBookContract {
    interface View extends MvpView {
        void finish();

        void showDialog(String title, String message);

        Context getContext();

        String getBase64EncordedImage();
    }

    interface Action {
        void backScreen();

        void save(Book book);
    }
}