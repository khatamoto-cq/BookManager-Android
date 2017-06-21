package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.passwordConfirmEditText)
    EditText passwordConfirmEditText;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        sharedPreferences = getSharedPreferences(
                getResources().getString(R.string.shared_prefference), Context.MODE_PRIVATE);

        toolbar.setTitle(R.string.title_account_setting);
        toolbar.inflateMenu(R.menu.menu_account_setting);
        setSupportActionBar(toolbar);
        if (isEntried()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_account_setting;
    }

    @Override
    public void setEntryFlg() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getResources().getString(R.string.entry_flg), true);
        editor.apply();
    }

    @Override
    public boolean isEntried() {
        return sharedPreferences.getBoolean(getResources().getString(R.string.entry_flg), false);
    }

    @Override
    public void showError(String message) {
        showDialog(getResources().getString(R.string.validation_title), message);
    }

    @Override
    public void moveBookList() {
        startActivity(MainActivity.class);
    }

    @Override
    public void backSetting() {
//        startActivity(SettingActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Toast.makeText(AccountSettingActivity.this, "戻るがクリックされた", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_save:
                Toast.makeText(AccountSettingActivity.this, "登録しました", Toast.LENGTH_LONG).show();
                startActivity(MainActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }
}
