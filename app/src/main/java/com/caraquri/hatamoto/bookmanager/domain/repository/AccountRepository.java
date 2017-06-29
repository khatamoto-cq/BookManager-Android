package com.caraquri.hatamoto.bookmanager.domain.repository;

import com.caraquri.hatamoto.bookmanager.domain.entity.Account;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;

import io.reactivex.Observable;

public interface AccountRepository {
    public Observable<AccountResponse> login(Account account);

    public Observable<AccountResponse> create(Account account);
}
