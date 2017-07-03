package com.caraquri.hatamoto.bookmanager.domain.entity;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class EditBookRequest {
    @Expose
    private int id;

    private String name;

    private int price;

    private String purchaseDate;

    private String imageData;

    public EditBookRequest(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.purchaseDate = book.getPurchaseDate();
    }
}
