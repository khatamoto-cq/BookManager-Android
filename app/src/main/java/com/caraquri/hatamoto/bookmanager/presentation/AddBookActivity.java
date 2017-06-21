package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AddBookContract;

import javax.inject.Inject;

public class AddBookActivity extends BaseActivity implements AddBookContract.View {

    @Inject
    AddBookPresenter addBookPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        addBookPresenter.attachView(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_book;
    }
}
