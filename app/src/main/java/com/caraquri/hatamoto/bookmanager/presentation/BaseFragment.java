package com.caraquri.hatamoto.bookmanager.presentation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.kaopiz.kprogresshud.KProgressHUD;

public class BaseFragment extends Fragment {

    protected KProgressHUD progress;

    @Override
    public void onResume() {
        super.onResume();
        progress = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }

    public void showDialog(String title, String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    public Context getContext() {
        return getActivity();
    }
}
