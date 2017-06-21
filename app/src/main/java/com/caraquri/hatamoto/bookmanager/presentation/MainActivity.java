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

    public final static String EXTRA_LOAD_FRAGMENT = "MainActivity.EXTRA_LOAD_FRAGMENT";
    public final static int BOOK_LIST_FRAGMENT = 0;
    public final static int SETTING_FRAGMENT = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setSupportActionBar(toolbar);
        loadFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.action_book_list:
                    fragment = BookListFragment.newInstance();
                    toolbar.setTitle(R.string.title_book_list);
                    break;
                case R.id.action_setting:
                    fragment = SettingFragment.newInstance();
                    toolbar.setTitle(R.string.title_setting);
            }

            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.commit();
            }

            return true;
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void loadFragment() {
        Fragment loadFragment;

        if (getIntent().getIntExtra(EXTRA_LOAD_FRAGMENT, 0) == BOOK_LIST_FRAGMENT) {
            loadFragment = BookListFragment.newInstance();
            setTitle(getResources().getString(R.string.title_book_list));
        } else {
            loadFragment = SettingFragment.newInstance();
            setTitle(getResources().getString(R.string.title_setting));
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, loadFragment);
        fragmentTransaction.commit();
    }
}
