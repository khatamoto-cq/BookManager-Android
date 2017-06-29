package com.caraquri.hatamoto.bookmanager.data.api;

import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiServiceRequiredAuthentication {
    @Headers( "Content-Type: application/json")
    @GET("books")
    Observable<BookResponse> getBooks(@Query("user_id") int userId, @Query("page") String page);
}
