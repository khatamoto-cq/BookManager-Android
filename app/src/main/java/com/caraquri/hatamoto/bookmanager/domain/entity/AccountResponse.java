package com.caraquri.hatamoto.bookmanager.domain.entity;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponse {

    @SerializedName("request_token")
    private String requestToken;

    @SerializedName("user_id")
    private int userId;
}
