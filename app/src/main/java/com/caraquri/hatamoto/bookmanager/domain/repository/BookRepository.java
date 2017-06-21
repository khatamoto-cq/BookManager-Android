package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;

import io.reactivex.Observable;

public interface BookRepository {
    Observable<Book> getBooks(int userId, String page);
}
