package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.RegisterBookContract;

import javax.inject.Inject;

public class EditBookActivity extends AbstractBookActivity implements RegisterBookContract.View {

    public static final String EXTRA_BOOK = "EditBookActivity.EXTRA_BOOK";

    @Inject
    EditBookPresenter editBookPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        editBookPresenter.attachView(this);
        toolbar.setTitle(R.string.title_edit_book);

        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);
        initControls(book);
    }

    @Override
    protected void onDestroy() {
        editBookPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_book;
    }

    @Override
    protected void onBackButtonClick() {
        editBookPresenter.backScreen();
    }

    @Override
    protected void onSaveButtonClick() {
        String name = nameEditTest.getText().toString();
        int price = 0;
        if (!TextUtils.isEmpty(priceEditText.getText().toString())) {
            price = Integer.parseInt(priceEditText.getText().toString());
        }
        String purchaseDate = purchaseDateEditText.getText().toString();

        editBookPresenter.save(new Book(name, price, purchaseDate));
    }

    private void initControls(Book book) {
        if (!TextUtils.isEmpty(book.getImageUrl())) {
            Glide.with(this).load(book.getImageUrl()).into(imageView);
        }
        nameEditTest.setText(book.getName());
        if (book.getPrice() > 0) {
            priceEditText.setText(String.valueOf(book.getPrice()));
        }
        purchaseDateEditText.setText(book.getPurchaseDate());
    }

    public static Intent createIntent(Context context, Book book) {
        Intent intent = new Intent(context, EditBookActivity.class);
        return intent.putExtra(EXTRA_BOOK, book);
    }
}
