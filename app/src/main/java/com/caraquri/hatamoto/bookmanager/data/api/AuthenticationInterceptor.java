package com.caraquri.hatamoto.bookmanager.data.api;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String requestToken;

    public AuthenticationInterceptor(String requestToken) {
        this.requestToken = requestToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", requestToken)
                .method(original.method(), original.body());

        Request request = builder.build();

        return chain.proceed(request);
    }
}
