package com.caraquri.hatamoto.bookmanager.data.repository;

import com.caraquri.hatamoto.bookmanager.data.api.ApiServiceRequiredAuthentication;
import com.caraquri.hatamoto.bookmanager.domain.entity.AddBook;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBook;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

public class BookRepositoryImpl implements BookRepository {

    private ApiServiceRequiredAuthentication service;

    public BookRepositoryImpl(ApiServiceRequiredAuthentication service) {
        this.service = service;
    }

    @Override
    public Observable<BookResult> getBooks(int userId, String page) {
        return service.getBooks(userId, page);
    }

    @Override
    public Single<BookResponse> addBook(AddBook book) {
        return service.addBook(book);
    }

    @Override
    public Single<BookResponse> editBook(EditBook book) {
        return null;
    }
}
