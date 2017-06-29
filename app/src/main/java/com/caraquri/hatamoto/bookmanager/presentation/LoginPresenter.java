package com.caraquri.hatamoto.bookmanager.presentation;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.Account;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;
import com.caraquri.hatamoto.bookmanager.domain.repository.AccountRepository;
import com.caraquri.hatamoto.bookmanager.presentation.contract.LoginContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.LoginValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
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

        // TODO: APIでログインしてトークンを保存



        Timber.d("[Log] APIにアクセスしてログインします");
        // TODO: APIでログインしてトークンを保存
        accountRepository.login(new Account(email, password))
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccountResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull AccountResponse accountResponse) {
                        Timber.d("[Log] Next: " + accountResponse.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("[Log] onError: " + e.getMessage());
                        getView().showDialog(getView().getContext().getString(R.string.error_title), "ログインできませんでした。");
                    }

                    @Override
                    public void onComplete() {
                        getView().moveToBookList();
                    }
                });

    }
}
