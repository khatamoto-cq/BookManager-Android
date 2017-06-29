package com.caraquri.hatamoto.bookmanager.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookResponse {
    @SerializedName("result")
    private List<Book> books;
}
