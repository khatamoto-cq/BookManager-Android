package com.caraquri.hatamoto.bookmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AddBook {

    private String name;

    private int price;

    private String purchaseDate;

    private String imageData;

    private int userId;

    public AddBook(Book book) {
        this.name = book.getName();
        this.price = book.getPrice();
        this.purchaseDate = book.getPurchaseDate();
    }
}
