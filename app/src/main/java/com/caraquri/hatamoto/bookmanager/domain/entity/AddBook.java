package com.caraquri.hatamoto.bookmanager.domain.entity;

import com.google.gson.annotations.SerializedName;

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

    @SerializedName("purchase_date")
    private String purchaseDate;

    @SerializedName("image_data")
    private String imageData;

    @SerializedName("user_id")
    private int userId;

    public AddBook(Book book) {
        this.name = book.getName();
        this.price = book.getPrice();
        this.purchaseDate = book.getPurchaseDate();
    }
}
