package com.caraquri.hatamoto.bookmanager.data.api;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
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
    private static final String API_BASE_URL = BuildConfig.BASE_URL;

    public static <S> S create(Class<S> serviceClass) {

        if (retrofit == null) {
            OkHttpClient.Builder httpClient = getHttpClientBuilder();
            Retrofit.Builder builder = getRetrofitBuilder(httpClient);
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    public static <S> S create(Class<S> serviceClass, String requestToken) {
        if (!TextUtils.isEmpty(requestToken)) {
            OkHttpClient.Builder httpClient = getHttpClientBuilder();
            httpClient.addInterceptor(new AuthenticationInterceptor(requestToken));
            Retrofit.Builder builder = getRetrofitBuilder(httpClient);
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    private static OkHttpClient.Builder getHttpClientBuilder() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(new JsonContentInterceptor());
        httpClient.addNetworkInterceptor(new StethoInterceptor());

        return httpClient;
    }

    private static Retrofit.Builder getRetrofitBuilder(OkHttpClient.Builder httpClientBuilder) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(httpClientBuilder.build());
        return retrofitBuilder;
    }

    private static Gson getGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }
}
