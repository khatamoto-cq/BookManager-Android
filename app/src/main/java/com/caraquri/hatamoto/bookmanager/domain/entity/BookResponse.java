package com.caraquri.hatamoto.bookmanager.domain.entity;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookResponse {
    @SerializedName("book_id")
    private int bookId;
}