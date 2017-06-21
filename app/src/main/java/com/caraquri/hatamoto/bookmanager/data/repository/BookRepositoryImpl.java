package com.caraquri.hatamoto.bookmanager.data.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Book;
import com.caraquri.hatamoto.bookmanager.domain.repository.BookRepository;

import io.reactivex.Observable;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Observable<Book> getBooks(int userId, String page) {
        return createDummyData();
    }

    private Observable<Book> createDummyData() {
        return Observable.create(e -> {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("詳解Swift");
            book1.setPrice(3200);
            book1.setPurchaseDate("2017/03/20");
            e.onNext(book1);

            Book book2 = new Book();
            book2.setId(2);
            book2.setName("Gradle徹底入門");
            book2.setPrice(4800);
            book2.setPurchaseDate("2017/04/05");
            e.onNext(book2);

            Book book3 = new Book();
            book3.setId(3);
            book3.setName("リファクタリング");
            book3.setPrice(2890);
            book3.setPurchaseDate("2017/06/02");
            e.onNext(book3);

            e.onComplete();
        });
    }
}
