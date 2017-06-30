package com.caraquri.hatamoto.bookmanager.data.api;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit retrofit;

    public static final String apiBaseUrl = BuildConfig.BASE_URL;

    final static Gson gson = new GsonBuilder()
            .create();

    private static Retrofit.Builder builer = new Retrofit.Builder();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS);

    public static <S> S create(Class<S> serviceClass) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addNetworkInterceptor(new StethoInterceptor());

        builer.addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(apiBaseUrl)
                .client(httpClient.build());

        retrofit = builer.build();

        return retrofit.create(serviceClass);
    }

    public static <S> S create(Class<S> serviceClass, String requestToken) {
        String credentials = null;

        if (!TextUtils.isEmpty(requestToken)) {
            httpClient.addInterceptor(new AuthenticationInterceptor(requestToken));
        }

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addNetworkInterceptor(new StethoInterceptor());

        builer.addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(apiBaseUrl)
                .client(httpClient.build());

        return retrofit.create(serviceClass);
    }
}
