package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Account;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;

import io.reactivex.Single;

public interface AccountRepository {
    Single<AccountResponse> login(Account account);

    Single<AccountResponse> create(Account account);
}
