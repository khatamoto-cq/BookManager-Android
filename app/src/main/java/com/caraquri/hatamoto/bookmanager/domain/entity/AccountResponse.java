package com.caraquri.hatamoto.bookmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponse {

    private String requestToken;

    private int userId;
}
