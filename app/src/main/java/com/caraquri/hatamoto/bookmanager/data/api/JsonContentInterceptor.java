package com.caraquri.hatamoto.bookmanager.data.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class JsonContentInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method(), original.body());

        Request request = builder.build();

        return chain.proceed(request);
    }
}