package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AddBookContract;

import javax.inject.Inject;

public class AddBookActivity extends AbstractBookActivity implements AddBookContract.View {

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
    protected int getLayoutRes() {
        return R.layout.activity_add_book;
    }

    @Override
    protected AbstractBookActivity getBookActivity() {
        return this;
    }

    @Override
    protected void tapBackButton() {
        addBookPresenter.backScreen();
    }

    @Override
    protected void tapSaveButton() {
        String name = nameEditTest.getText().toString();
        int price = 0;
        if (!priceEditText.getText().toString().isEmpty()) {
            price = Integer.parseInt(priceEditText.getText().toString());
        }
        String purchaseDate = purchaseDateEditText.getText().toString();

        addBookPresenter.save(new Book(name, price, purchaseDate));
    }
}
