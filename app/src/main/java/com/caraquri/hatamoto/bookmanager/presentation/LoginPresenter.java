package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;
import com.caraquri.hatamoto.bookmanager.domain.repository.AccountRepository;
import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.LoginValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Action {

    Scheduler scheduler;
    AccountRepository accountRepository;

    public LoginPresenter(Scheduler scheduler, AccountRepository accountRepository) {
        // API実装時に必要なRxJavaのスケジューラをDIしとく
        this.scheduler = scheduler;
        this.accountRepository = accountRepository;
    }

    @Override
    public void login(String email, String password) {
        List<String> errors = LoginValidator.validate(getView().getContext(), email, password);

        if (!errors.isEmpty()) {
            getView().showDialog(getView().getContext().getString(R.string.validation_title), TextUtils.join("\n", errors));
            return;
        }

        accountRepository.login(new AccountRequest(email, password))
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AccountResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull AccountResponse accountResponse) {
                        getView().saveAccessTokenAndUserId(accountResponse.getRequestToken(), accountResponse.getUserId());
                        getView().moveToBookList();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Timber.e(e.getMessage());
                        getView().showDialog(getView().getContext().getString(R.string.error_title),
                                getView().getContext().getString(R.string.error_login));
                    }
                });
    }
}
