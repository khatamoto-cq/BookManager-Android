package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.domain.entity.BookResponse;

import java.util.List;

import io.reactivex.Observable;

public interface BookRepository {
    Observable<BookResponse> getBooks(int userId, String page);
}
