package com.caraquri.hatamoto.bookmanager.data.api;

import com.caraquri.hatamoto.bookmanager.domain.entity.Account;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @Headers( "Content-Type: application/json")
    @POST("login")
    Observable<AccountResponse> login(@Body Account account);

    @Headers( "Content-Type: application/json")
    @POST("signup")
    Observable<AccountResponse> signup(@Body Account account);

    @Headers( "Content-Type: application/json")
    @GET("books")
    Observable<List<Book>> getBooks(@Query("user_id") int userId, @Query("page") String page);
}
