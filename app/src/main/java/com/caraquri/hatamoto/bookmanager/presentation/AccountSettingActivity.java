package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AccountSettingContract;

import javax.inject.Inject;

import butterknife.BindView;

public class AccountSettingActivity extends BaseActivity implements AccountSettingContract.View {

    private SharedPreferences sharedPreferences;

    @Inject
    AccountSettingPresenter accountSettingPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.password_confirm_edit_text)
    EditText passwordConfirmEditText;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        accountSettingPresenter.attachView(this);

        sharedPreferences = getSharedPreferences(
                getString(R.string.shared_prefference), Context.MODE_PRIVATE);

        toolbar.setTitle(R.string.title_account_setting);
        toolbar.inflateMenu(R.menu.menu_save);
        setSupportActionBar(toolbar);
        if (isEntried()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        accountSettingPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_account_setting;
    }

    @Override
    public void entryInAccount() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.entry_flg), true);
        editor.apply();
    }

    @Override
    public boolean isEntried() {
        return sharedPreferences.getBoolean(getString(R.string.entry_flg), false);
    }

    @Override
    public void moveBookList() {
        startActivity(MainActivity.createIntent(this, MainActivity.BOOK_LIST_FRAGMENT));
        finish();
    }

    @Override
    public void moveSetting() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_save:
                accountSettingPresenter.save(emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        passwordConfirmEditText.getText().toString());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
