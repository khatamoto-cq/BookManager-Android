package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;

import javax.inject.Inject;

public class AddBookActivity extends AbstractBookActivity implements RegisterBookContract.View {

    @Inject
    AddBookPresenter addBookPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        addBookPresenter.attachView(this);

        toolbar.setTitle(R.string.title_add_book);
    }

    @Override
    protected void onDestroy() {
        addBookPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_book;
    }

    @Override
    protected AbstractBookActivity getBookActivity() {
        return this;
    }

    @Override
    protected void clickBackButton() {
        addBookPresenter.backScreen();
    }

    @Override
    protected void clickSaveButton() {
        String name = nameEditTest.getText().toString();
        int price = 0;
        if (!TextUtils.isEmpty(priceEditText.getText().toString())) {
            price = Integer.parseInt(priceEditText.getText().toString());
        }
        String purchaseDate = purchaseDateEditText.getText().toString();

        addBookPresenter.save(new Book(name, price, purchaseDate));
    }
}
