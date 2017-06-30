package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.AddBook;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBook;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface BookRepository {
    Observable<BookResult> getBooks(int userId, String page);
    Single<BookResponse> addBook(AddBook book);
    Single<BookResponse> editBook(EditBook book);
}
