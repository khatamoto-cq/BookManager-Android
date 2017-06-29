package com.caraquri.hatamoto.bookmanager.data.api;

import com.caraquri.hatamoto.bookmanager.domain.entity.Account;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers( "Content-Type: application/json")
    @POST("login")
    Observable<AccountResponse> login(@Body Account account);

    @Headers( "Content-Type: application/json")
    @POST("signup")
    Observable<AccountResponse> signup(@Body Account account);

}
