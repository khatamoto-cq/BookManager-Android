package com.caraquri.hatamoto.bookmanager.data.api;

import com.caraquri.hatamoto.bookmanager.domain.entity.AddBook;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBook;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceRequiredAuthentication {
    @Headers( "Content-Type: application/json")
    @GET("books")
    Observable<BookResult> getBooks(@Query("user_id") int userId, @Query("page") String page);

    @Headers( "Content-Type: application/json")
    @POST("books")
    Single<BookResponse> addBook(@Body AddBook book);

    @Headers( "Content-Type: application/json")
    @PATCH("books/{id}")
    Single<BookResponse> editBook(@Body EditBook book, @Path("id") int id);
}
