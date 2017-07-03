package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.email_edit_text)
    EditText loginEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        loginPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void moveToBookList() {
        startActivity(MainActivity.class);
    }

    @Override
    public void saveAccessTokenAndUserId(String requestToken, int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.shared_prefference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.request_token), requestToken);
        editor.putInt(getString(R.string.user_id), userId);
        editor.apply();
    }

    @OnClick(R.id.login_button)
    public void login() {
        loginPresenter.login(loginEditText.getText().toString(),
                passwordEditText.getText().toString());
    }
}
