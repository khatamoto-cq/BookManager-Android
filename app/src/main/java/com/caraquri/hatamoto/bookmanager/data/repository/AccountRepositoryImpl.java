package com.caraquri.hatamoto.bookmanager.data.repository;

import com.caraquri.hatamoto.bookmanager.data.api.ApiService;
import com.caraquri.hatamoto.bookmanager.domain.entity.Account;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;
import com.caraquri.hatamoto.bookmanager.domain.repository.AccountRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class AccountRepositoryImpl implements AccountRepository {
    @Inject
    ApiService service;

    public AccountRepositoryImpl(ApiService service) {
        this.service = service;
    }

    @Override
    public Single<AccountResponse> login(Account account) {
        return service.login(account);
    }

    @Override
    public Single<AccountResponse> create(Account account) {
        return service.signup(account);
    }
}
