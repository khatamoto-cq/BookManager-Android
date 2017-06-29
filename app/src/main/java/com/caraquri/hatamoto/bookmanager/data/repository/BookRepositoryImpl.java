package com.caraquri.hatamoto.bookmanager.data.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Observable<List<Book>> getBooks(int userId, String page) {
        return createDummyData();
    }

    private Observable<List<Book>> createDummyData() {
        return Observable.create(e -> {
            List<Book> books = new ArrayList<>();
            books.add(new Book(1, "https://images-fe.ssl-images-amazon.com/images/I/51d%2B%2B75T0tL.jpg", "詳解Swift", 3200, "2017/03/20"));
            books.add(new Book(2, "https://images-fe.ssl-images-amazon.com/images/I/51xV%2Bnr7hhL.jpg", "Gradle徹底入門", 4800, "2017/04/05"));
            books.add(new Book(3, "https://images-na.ssl-images-amazon.com/images/I/51RWpUlhNxL._SX385_BO1,204,203,200_.jpg", "リファクタリング", 2980, "2017/06/02"));
            books.add(new Book(4, "https://images-na.ssl-images-amazon.com/images/I/41CE7ad4jUL._SX390_BO1,204,203,200_.jpg", "Effective Android", 3980, "2015/07/14"));
            books.add(new Book(5, "https://images-na.ssl-images-amazon.com/images/I/510pbZeAFPL._SX350_BO1,204,203,200_.jpg",
                    "良いAndroidアプリを作る139の鉄則", 3980, "2015/07/14"));
            e.onNext(books);
            e.onComplete();
        });
    }
}
