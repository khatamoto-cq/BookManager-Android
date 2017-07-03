package com.caraquri.hatamoto.bookmanager.data.api;

import com.caraquri.hatamoto.bookmanager.domain.entity.AccountRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.AddBookRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBookRequest;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    Single<AccountResponse> login(@Body AccountRequest account);

    @POST("signup")
    Single<AccountResponse> signup(@Body AccountRequest account);

    @GET("books")
    Observable<BookResult> getBooks(@Header("Authorization") String token, @Query("user_id") int userId, @Query("page") String page);

    @POST("books")
    Single<BookResponse> addBook(@Header("Authorization") String token, @Body AddBookRequest book);

    @PATCH("books/{id}")
    Single<BookResponse> editBook(@Header("Authorization") String token, @Body EditBookRequest book, @Path("id") int id);
}
