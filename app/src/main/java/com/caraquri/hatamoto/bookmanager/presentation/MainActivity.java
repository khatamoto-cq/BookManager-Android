package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.presentation.adapter.BookAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    public final static String EXTRA_BOOK_ID = "MainActivity.EXTRA_BOOK_ID";

    private BookAdapter bookAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.book_list)
    RecyclerView booksRecyclerView;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);

        toolbar.setTitle(R.string.title_book_list);
        toolbar.inflateMenu(R.menu.menu_account_setting);
        setSupportActionBar(toolbar);

        bookAdapter = new BookAdapter() {
            @Override
            protected void onItemClicked(@NonNull Book book) {
                super.onItemClicked(book);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_BOOK_ID, book.getId());
                startActivity(intent);
            }
        };

        booksRecyclerView.setAdapter(bookAdapter);
        booksRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }


}
