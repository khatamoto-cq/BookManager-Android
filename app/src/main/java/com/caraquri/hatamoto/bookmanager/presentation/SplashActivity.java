package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.caraquri.hatamoto.bookmanager.App;
import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.presentation.contract.SplashContract;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private static final int DELAY = 2000;

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        App.getAppComponent(this).inject(this);
        splashPresenter.attachView(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
        splashPresenter.moveNextScreen();
    }

    @Override
    public boolean isEntried() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.shared_prefference), Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(getString(R.string.entry_flg), false);
    }

    @Override
    public void moveAccountSetting() {
        new Handler().postDelayed(() -> startActivity(AccountSettingActivity.class), DELAY);
    }

    @Override
    public void moveLogin() {
        new Handler().postDelayed(() -> startActivity(LoginActivity.class), DELAY);
    }
}
