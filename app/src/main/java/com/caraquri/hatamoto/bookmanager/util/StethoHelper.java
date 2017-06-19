package com.caraquri.hatamoto.bookmanager.util;

import android.content.Context;

import okhttp3.OkHttpClient;

public interface StethoHelper {
    void init(Context context);

    void configureInterceptor(OkHttpClient.Builder httpClient);
}
