package com.caraquri.hatamoto.bookmanager.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.caraquri.hatamoto.bookmanager.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    public final static String EXTRA_BOOK_ID = "MainActivity.EXTRA_BOOK_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        toolbar.setTitle(R.string.title_book_list);
        setSupportActionBar(toolbar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, BookListFragment.newInstance());
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.action_book_list:
                    fragment = BookListFragment.newInstance();
                    break;
                case R.id.action_setting:
                    fragment = SettingFragment.newInstance();
            }

            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.commit();
            }

            return true;
        });

//        bookAdapter = new BookAdapter() {
//            @Override
//            protected void onItemClicked(@NonNull Book book) {
//                super.onItemClicked(book);
//                Intent intent = new Intent();
//                intent.putExtra(EXTRA_BOOK_ID, book.getId());
//                startActivity(intent);
//            }
//        };
//
//        booksRecyclerView.setAdapter(bookAdapter);
//        booksRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

}
