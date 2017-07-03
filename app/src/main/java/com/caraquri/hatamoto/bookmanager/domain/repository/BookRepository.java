package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.AddBookRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBookRequest;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface BookRepository {
    Observable<BookResult> getBooks(String token, int userId, String page);

    Single<BookResponse> addBook(String token, AddBookRequest book);

    Single<BookResponse> editBook(String token, EditBookRequest book);
}
