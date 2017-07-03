package com.caraquri.hatamoto.bookmanager.data.repository;

import com.caraquri.hatamoto.bookmanager.data.api.ApiService;
import com.caraquri.hatamoto.bookmanager.domain.entity.AddBookRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResult;
import com.caraquri.hatamoto.bookmanager.domain.entity.EditBookRequest;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

public class BookRepositoryImpl implements BookRepository {

    private ApiService service;

    public BookRepositoryImpl(ApiService service) {
        this.service = service;
    }

    @Override
    public Observable<BookResult> getBooks(String token, int userId, String page) {
        return service.getBooks(token, userId, page);
    }

    @Override
    public Single<BookResponse> addBook(String token, AddBookRequest book) {
        return service.addBook(token, book);
    }

    @Override
    public Single<BookResponse> editBook(String token, EditBookRequest book) {
        return service.editBook(token, book, book.getId());
    }
}
