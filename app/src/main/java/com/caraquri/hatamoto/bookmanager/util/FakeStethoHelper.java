package com.caraquri.hatamoto.bookmanager.util;

import android.content.Context;

import okhttp3.OkHttpClient;

public class FakeStethoHelper implements StethoHelper {
    @Override
    public void init(Context context) {
        // Noop
    }

    @Override
    public void configureInterceptor(OkHttpClient.Builder httpClient) {
        // Noop
    }
}
