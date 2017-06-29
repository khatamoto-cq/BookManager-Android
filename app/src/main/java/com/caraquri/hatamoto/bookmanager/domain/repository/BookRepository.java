package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;

import java.util.List;

import io.reactivex.Observable;

public interface BookRepository {
    Observable<List<Book>> getBooks(int userId, String page);
}
