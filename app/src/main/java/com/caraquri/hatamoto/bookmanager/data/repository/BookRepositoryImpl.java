package com.caraquri.hatamoto.bookmanager.data.repository;

import com.caraquri.hatamoto.bookmanager.data.api.ApiService;
import com.caraquri.hatamoto.bookmanager.data.api.ApiServiceRequiredAuthentication;
import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;

import java.util.List;

import io.reactivex.Observable;

public class BookRepositoryImpl implements BookRepository {

    private ApiServiceRequiredAuthentication service;

    public BookRepositoryImpl(ApiServiceRequiredAuthentication service) {
        this.service = service;
    }

    @Override
    public Observable<BookResponse> getBooks(int userId, String page) {
        return service.getBooks(userId, page);
    }
}
