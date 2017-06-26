package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected static final int RESULT_PICK_IMAGEFILE = 101;

    protected KProgressHUD mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        mProgress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        init(savedInstanceState);
    }

    protected void init(@Nullable Bundle savedInstanceState) {
    }

    protected abstract int getLayoutRes();

    protected void startActivity(Class activity) {
        startActivity(activity, true);
    }

    protected void startActivity(Class activity, boolean isFinished) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (isFinished) {
            finish();
        }
    }

    public void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    public Context getContext() {
        return this;
    }
}
