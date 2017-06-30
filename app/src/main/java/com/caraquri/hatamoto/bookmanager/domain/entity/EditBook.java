package com.caraquri.hatamoto.bookmanager.domain.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class EditBook {
    @Expose
    private int id;

    private String name;

    private int price;

    @SerializedName("purchase_date")
    private String purchaseDate;

    @SerializedName("image_data")
    private String imageData;

    public EditBook(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.purchaseDate = book.getPurchaseDate();
    }
}
