package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.EditBookContract;

import javax.inject.Inject;

public class EditBookActivity extends AbstractBookActivity implements EditBookContract.View  {

    @Inject
    EditBookPresenter editBookPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        editBookPresenter.attachView(this);
        toolbar.setTitle(R.string.title_edit_book);

        Book book = getIntent().getParcelableExtra(BookListFragment.EXTRA_BOOK);
        initControls(book);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_book;
    }

    @Override
    protected AbstractBookActivity getBookActivity() {
        return this;
    }

    @Override
    protected void tapBackButton() {
        Toast.makeText(this, "戻るボタンが押されたよ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void tapSaveButton() {
        Toast.makeText(this, "保存ボタンが押されたよ", Toast.LENGTH_LONG).show();
    }

    private void initControls(Book book) {
        if (!book.getImageUrl().isEmpty()) {
            Glide.with(this).load(book.getImageUrl()).into(imageView);
        }
        nameEditTest.setText(book.getName());
        if (book.getPrice() > 0) {
            priceEditText.setText(String.valueOf(book.getPrice()));
        }
        purchaseDateEditText.setText(book.getPurchaseDate());
    }
}
