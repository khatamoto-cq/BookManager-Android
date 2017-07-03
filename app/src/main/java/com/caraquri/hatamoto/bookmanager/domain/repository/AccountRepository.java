package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.AccountRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;

import io.reactivex.Single;

public interface AccountRepository {
    Single<AccountResponse> login(AccountRequest account);

    Single<AccountResponse> create(AccountRequest account);
}
