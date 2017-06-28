package com.caraquri.hatamoto.bookmanager.presentation;

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

    @OnClick(R.id.login_button)
    public void login() {
        loginPresenter.login(loginEditText.getText().toString(),
                passwordEditText.getText().toString());
    }
}
