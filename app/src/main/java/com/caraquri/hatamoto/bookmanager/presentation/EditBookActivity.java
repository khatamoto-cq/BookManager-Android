package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.contract.EditBookContract;

import javax.inject.Inject;

import butterknife.BindView;

public class EditBookActivity extends BaseActivity implements EditBookContract.View  {

    @Inject
    EditBookPresenter editBookPresenter;

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.nameEditText)
    EditText nameEditTest;
    @BindView(R.id.priceEditText)
    EditText priceEditText;
    @BindView(R.id.purchaseDateEditText)
    EditText purchaseDateEditText;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        editBookPresenter.attachView(this);

        Book book = getIntent().getParcelableExtra(BookListFragment.EXTRA_BOOK);
        initControls(book);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_book;
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
