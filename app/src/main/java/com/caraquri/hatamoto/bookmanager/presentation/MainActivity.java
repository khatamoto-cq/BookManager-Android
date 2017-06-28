package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.caraquri.hatamoto.bookmanager.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private final static String EXTRA_LOAD_FRAGMENT = "MainActivity.EXTRA_LOAD_FRAGMENT";
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
        toolbar.inflateMenu(R.menu.menu_save);
        loadFragment(getIntent().getIntExtra(EXTRA_LOAD_FRAGMENT, 0));

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_add);

            switch (item.getItemId()) {
                case R.id.action_book_list:
                    fragment = BookListFragment.newInstance();
                    toolbar.setTitle(R.string.title_book_list);
                    menuItem.setVisible(true);
                    break;
                case R.id.action_setting:
                    fragment = SettingFragment.newInstance();
                    toolbar.setTitle(R.string.title_setting);
                    menuItem.setVisible(false);
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

    private void loadFragment(int currentFragment) {
        Fragment loadFragment;
        if (currentFragment == BOOK_LIST_FRAGMENT) {
            loadFragment = BookListFragment.newInstance();
            setTitle(getString(R.string.title_book_list));
        } else {
            loadFragment = SettingFragment.newInstance();
            setTitle(getString(R.string.title_setting));
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, loadFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            startActivity(AddBookActivity.class, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent createIntent(Context context, int moveToScreen) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent.putExtra(EXTRA_LOAD_FRAGMENT, moveToScreen);
    }
}
