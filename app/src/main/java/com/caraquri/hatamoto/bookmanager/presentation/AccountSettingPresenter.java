package com.caraquri.hatamoto.bookmanager.presentation;

import android.text.TextUtils;

import com.caraquri.hatamoto.bookmanager.R;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountRequest;
import com.caraquri.hatamoto.bookmanager.domain.entity.AccountResponse;
import com.caraquri.hatamoto.bookmanager.domain.repository.AccountRepository;
import com.caraquri.hatamoto.bookmanager.presentation.contract.AccountSettingContract;
import com.caraquri.hatamoto.bookmanager.presentation.validator.AccountValidator;
import com.caraquri.hatamoto.bookmanager.util.mvp.BasePresenter;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class AccountSettingPresenter extends BasePresenter<AccountSettingContract.View>
        implements AccountSettingContract.Action {

    Scheduler scheduler;
    AccountRepository accountRepository;

    public AccountSettingPresenter(Scheduler scheduler, AccountRepository accountRepository) {
        this.scheduler = scheduler;
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(String email, String password, String passwordConfirm) {
        List<String> errors = AccountValidator.validate(getView().getContext(), email, password, passwordConfirm);
        if (!errors.isEmpty()) {
            getView().showDialog(getView().getContext().getString(R.string.validation_title), TextUtils.join("\n", errors));
            return;
        }

        accountRepository.create(new AccountRequest(email, password))
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AccountResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(@NonNull AccountResponse accountResponse) {
                        getView().saveAccessTokenAndUserId(accountResponse.getRequestToken(),
                                accountResponse.getUserId());

                        if (!getView().isEntried()) {
                            getView().entryInAccount();
                            getView().moveToBookList();
                            return;
                        }

                        getView().moveToSetting();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e.getMessage());
                        getView().showDialog(getView().getContext().getString(R.string.error_title),
                                getView().getContext().getString(R.string.error_networking));
                    }
                });
    }
}
